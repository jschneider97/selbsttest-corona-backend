package com.wirvsvirus.selftest.service;

import com.wirvsvirus.selftest.api.enums.Criteria;
import com.wirvsvirus.selftest.api.enums.QuestionType;
import com.wirvsvirus.selftest.api.selftest.ChoiceAnswer;
import com.wirvsvirus.selftest.api.selftest.DateAnswer;
import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.domain.selftest.ChoiceAnswerTbl;
import com.wirvsvirus.selftest.domain.selftest.QuestionAnswerTbl;
import com.wirvsvirus.selftest.domain.selftest.QuestionTbl;
import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;
import com.wirvsvirus.selftest.domain.selftest.condition.CriteriaScoreCondition;
import com.wirvsvirus.selftest.domain.selftest.condition.PredecessorConditionTbl;
import com.wirvsvirus.selftest.domain.selftest.condition.QuestionConditionTbl;
import com.wirvsvirus.selftest.mapper.SelftestMapper;
import com.wirvsvirus.selftest.repository.AnswerRepository;
import com.wirvsvirus.selftest.repository.QuestionAnswerRepository;
import com.wirvsvirus.selftest.repository.QuestionRepository;
import com.wirvsvirus.selftest.repository.SelftestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Justus Schneider
 */

@Service
public class QuestionService {

    private final SelftestRepository selftestRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SelftestMapper selftestMapper;
    private final QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    public QuestionService(SelftestRepository selftestRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, SelftestMapper selftestMapper, QuestionAnswerRepository questionAnswerRepository) {
        this.selftestRepository = selftestRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.selftestMapper = selftestMapper;
        this.questionAnswerRepository = questionAnswerRepository;
    }


    @Transactional
    public Question getNextQuestionForSelftest(Long selftestId) {
        SelftestTbl selftest = this.selftestRepository.findById(selftestId).orElseThrow(EntityNotFoundException::new);
        List<QuestionTbl> allQuestion = this.questionRepository.findAll();

        //Filter already answered Questions
        allQuestion = allQuestion.stream().filter(questionTbl -> selftest.getQuestionAnswerList().stream().map(QuestionAnswerTbl::getQuestion).noneMatch(q -> q.equals(questionTbl))).collect(Collectors.toList());

        QuestionTbl foundQuestion = allQuestion.stream().max(Comparator.comparingInt(quest -> this.questionCriteriaMatches(selftest, quest.getConditions()))).orElse(null);

        Question retVal = selftestMapper.mapFromBean(foundQuestion);
        retVal.setAnswers(foundQuestion.getAnswers().stream().map(selftestMapper::mapFromBean).collect(Collectors.toList()));

        return retVal;
    }

    @Transactional
    public void answerQuestion(Long selftestId, Question question) {
        SelftestTbl selftestTbl = this.selftestRepository.findById(selftestId).orElseThrow(EntityNotFoundException::new);
        QuestionTbl questionTbl = this.questionRepository.getOne(question.getId());

        if(question.getQuestionType().equals(QuestionType.CHOICE_QUESTION)) {
            ChoiceAnswer choiceAnswer = (ChoiceAnswer) question.getAnswer();
            ChoiceAnswerTbl choiceAnswerTbl = this.answerRepository.getOne(choiceAnswer.getId());

            QuestionAnswerTbl questionAnswerTbl = new QuestionAnswerTbl();
            questionAnswerTbl.setChoiceAnswer(choiceAnswerTbl);
            questionAnswerTbl.setQuestion(questionTbl);
            questionAnswerTbl.setSelftest(selftestTbl);

            this.questionAnswerRepository.save(questionAnswerTbl);

            updateCriteria(selftestTbl, questionTbl, choiceAnswerTbl);
        } else {
            answerDateQuestion(selftestTbl, questionTbl, question.getDateAnswer());
        }
    }

    void answerDateQuestion(SelftestTbl selftest, QuestionTbl questionTbl, DateAnswer dateAnswer) {
        if(questionTbl.getCriteria().equals(Criteria.CONTACT)) {
            selftest.getContactCriteria().setContactDate(dateAnswer.getAnswerDate());
        }
        this.selftestRepository.save(selftest);
    }

    void updateCriteria(SelftestTbl selftest, QuestionTbl question, ChoiceAnswerTbl answer) {
        com.wirvsvirus.selftest.domain.criteria.Criteria criteria;
        if(question.getCriteria().equals(Criteria.SYMPTOMS_HIGH)) {
            criteria = selftest.getSymptomsHighCriteria();
        } else if(question.getCriteria().equals(Criteria.SYMPTOMS_MEDIUM)) {
            criteria = selftest.getSymptomsMediumCriteria();
        } else if(question.getCriteria().equals(Criteria.SYMPTOMS)) {
            criteria = selftest.getSymptomsCriteria();
        } else if(question.getCriteria().equals(Criteria.CONTACT)) {
            criteria = selftest.getContactCriteria();
        } else if(question.getCriteria().equals(Criteria.TRAVELING)) {
            criteria = selftest.getTravelingCriteria();
        } else if(question.getCriteria().equals(Criteria.RISK_GROUP)) {
            criteria = selftest.getRiskGroupCriteria();
        } else {
            return;
        }

        criteria.increaseScore(answer.getScoreImpact());
    }

    @Transactional(Transactional.TxType.MANDATORY)
    int questionCriteriaMatches(SelftestTbl selftest, Set<QuestionConditionTbl> conditions) {
        int predecessorMatch = 0;
        int criteriaMatch = 0;

        //Leere Conditions werden immer genommen aber mit niedriger Priorität
        if (conditions == null || conditions.isEmpty()) {
            return 0;
        }
        for (QuestionConditionTbl currentMainCondition : conditions) {
            int predecessorCurrentMatch = this.matchPredecessorConditions(selftest, currentMainCondition.getPredecessorConditions());
            int criteriaCurrentMatch = this.matchCriteriaConditions(selftest, currentMainCondition.getCriteriaScoreConditions());
            if (predecessorCurrentMatch >= 0 && criteriaCurrentMatch >= 0) {
                predecessorMatch += predecessorCurrentMatch;
                criteriaMatch += criteriaCurrentMatch;
            }
        }

        //Wenn nichts gematch hat
        if (predecessorMatch == 0 && criteriaMatch == 0) {
            return -1;
        }

        //Predecessor Matches überwiegen, daher Faktor 10
        return 10 * predecessorMatch + criteriaMatch;
    }

    @Transactional(Transactional.TxType.MANDATORY)
    int matchPredecessorConditions(SelftestTbl selftest, Set<PredecessorConditionTbl> conditionTbls) {
        int match = 0;

        //Null wenn erste Frage geholt wird
        QuestionAnswerTbl lastQA = selftest.getQuestionAnswerList().stream().reduce((first, second) -> second).orElse(null);
        if (lastQA == null) {
            return -1;
        }
        for (PredecessorConditionTbl currentCondition : conditionTbls) {
            int currentMatch = this.predecessorConditionMatchesQuestion(lastQA, currentCondition);
            if (currentMatch == -1) {
                return -1;
            } else {
                match += currentMatch;
            }
        }
        return match;
    }

    private int predecessorConditionMatchesQuestion(QuestionAnswerTbl questionAnswer, PredecessorConditionTbl conditionTbl) {
        if (conditionTbl.getQuestion().equals(questionAnswer.getQuestion())) {
            if (conditionTbl.getChoiceAnswerConditions().size() < 1) {
                return 1;
            } else {
                for (ChoiceAnswerTbl curAnswer : conditionTbl.getChoiceAnswerConditions()) {
                    if (curAnswer.equals(questionAnswer.getChoiceAnswer())) {
                        return 2;
                    }
                }
            }
        }
        return 0;
    }

    //TODO: Nullchecks
    private int matchCriteriaConditions(SelftestTbl selftestTbl, Set<CriteriaScoreCondition> conditions) {
        long conditionsMatch = conditions.stream().filter(condition -> this.criteriaConditionMatchesQuestion(selftestTbl, condition)).count();
        if (Long.valueOf(conditions.size()).equals(conditionsMatch)) {
            return conditions.size();
        } else {
            return -1;
        }
    }

    private boolean criteriaConditionMatchesQuestion(SelftestTbl selftestTbl, CriteriaScoreCondition condition) {
        com.wirvsvirus.selftest.domain.criteria.Criteria criteria;

        if (condition.getCriteria().equals(Criteria.CONTACT)) {
            criteria = selftestTbl.getContactCriteria();
        } else if (condition.getCriteria().equals(Criteria.RISK_GROUP)) {
            criteria = selftestTbl.getRiskGroupCriteria();
        } else if (condition.getCriteria().equals(Criteria.TRAVELING)) {
            criteria = selftestTbl.getTravelingCriteria();
        } else if (condition.getCriteria().equals(Criteria.SYMPTOMS)) {
            criteria = selftestTbl.getSymptomsCriteria();
        } else if (condition.getCriteria().equals(Criteria.SYMPTOMS_MEDIUM)) {
            criteria = selftestTbl.getSymptomsMediumCriteria();
        } else if (condition.getCriteria().equals(Criteria.SYMPTOMS_HIGH)) {
            criteria = selftestTbl.getSymptomsHighCriteria();
        } else {
            return false;
        }


        return criteria.getScore() >= condition.getMinScore();
    }

}
