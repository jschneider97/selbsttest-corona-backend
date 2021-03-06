package com.wirvsvirus.selftest.domain.selftest.condition;

import com.wirvsvirus.selftest.domain.BaseModel;
import com.wirvsvirus.selftest.domain.selftest.QuestionTbl;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "question_condition")
public class QuestionConditionTbl extends BaseModel {

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "questionCondition")
    Set<PredecessorConditionTbl> predecessorConditions;
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "questionCondition")
    Set<CriteriaScoreCondition> criteriaScoreConditions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    QuestionTbl question;

    public Set<PredecessorConditionTbl> getPredecessorConditions() {
        return predecessorConditions;
    }

    public void setPredecessorConditions(Set<PredecessorConditionTbl> predecessorConditions) {
        this.predecessorConditions = predecessorConditions;
    }

    public Set<CriteriaScoreCondition> getCriteriaScoreConditions() {
        return criteriaScoreConditions;
    }

    public void setCriteriaScoreConditions(Set<CriteriaScoreCondition> criteriaScoreConditions) {
        this.criteriaScoreConditions = criteriaScoreConditions;
    }

    public QuestionTbl getQuestion() {
        return question;
    }

    public void setQuestion(QuestionTbl question) {
        this.question = question;
    }
}
