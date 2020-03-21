package com.wirvsvirus.selftest.domain.selftest.condition;

import com.wirvsvirus.selftest.api.enums.Criteria;
import com.wirvsvirus.selftest.domain.BaseModel;

import javax.persistence.*;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "criteria_score_condition")
public class CriteriaScoreCondition extends BaseModel {

    @Column(name = "criteria")
    @Enumerated(EnumType.STRING)
    Criteria criteria;
    @Column(name = "min_score")
    int minScore;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_condition", referencedColumnName = "id")
    QuestionConditionTbl questionCondition;

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public QuestionConditionTbl getQuestionCondition() {
        return questionCondition;
    }

    public void setQuestionCondition(QuestionConditionTbl questionCondition) {
        this.questionCondition = questionCondition;
    }
}
