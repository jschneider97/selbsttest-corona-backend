package com.wirvsvirus.selftest.mapper;

import com.wirvsvirus.selftest.api.enums.QuestionType;
import com.wirvsvirus.selftest.api.selftest.Answer;
import com.wirvsvirus.selftest.api.selftest.ChoiceAnswer;
import com.wirvsvirus.selftest.api.selftest.DateAnswer;
import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.domain.selftest.QuestionAnswerTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author Justus Schneider
 */

@Component
public class QuestionMapping {

    @Autowired
    private SelftestMapper selftestMapper;

    //TODO: Criteria
    @Transactional(Transactional.TxType.MANDATORY)
    public Question mapFromBean(QuestionAnswerTbl entity) {
        Question question = selftestMapper.mapFromBean(entity.getQuestion());
        if(question.getQuestionType().equals(QuestionType.CHOICE_QUESTION)) {
            question.setAnswer(selftestMapper.mapFromBean(entity.getChoiceAnswer()));
        } else {
            DateAnswer dateAnswer = new DateAnswer();
            dateAnswer.setAnswerDate(entity.getDateAnswer());
            question.setDateAnswer(dateAnswer);
        }


        return question;
    }

}
