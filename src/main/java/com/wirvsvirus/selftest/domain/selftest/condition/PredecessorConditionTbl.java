package com.wirvsvirus.selftest.domain.selftest.condition;

import com.wirvsvirus.selftest.domain.BaseModel;
import com.wirvsvirus.selftest.domain.selftest.ChoiceAnswerTbl;
import com.wirvsvirus.selftest.domain.selftest.QuestionTbl;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "predecessor_condition")
public class PredecessorConditionTbl extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    QuestionTbl question;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<ChoiceAnswerTbl> choiceAnswerConditions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_condition", referencedColumnName = "id")
    QuestionConditionTbl questionCondition;

    public QuestionTbl getQuestion() {
        return question;
    }

    public void setQuestion(QuestionTbl question) {
        this.question = question;
    }

    public Set<ChoiceAnswerTbl> getChoiceAnswerConditions() {
        return choiceAnswerConditions;
    }

    public void setChoiceAnswerConditions(Set<ChoiceAnswerTbl> choiceAnswerConditions) {
        this.choiceAnswerConditions = choiceAnswerConditions;
    }

    public QuestionConditionTbl getQuestionCondition() {
        return questionCondition;
    }

    public void setQuestionCondition(QuestionConditionTbl questionCondition) {
        this.questionCondition = questionCondition;
    }
}
