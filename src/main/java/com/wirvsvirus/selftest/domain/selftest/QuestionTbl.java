package com.wirvsvirus.selftest.domain.selftest;

import com.wirvsvirus.selftest.api.enums.Criteria;
import com.wirvsvirus.selftest.api.enums.QuestionType;
import com.wirvsvirus.selftest.domain.BaseModel;
import com.wirvsvirus.selftest.domain.selftest.condition.QuestionConditionTbl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "question")
public class QuestionTbl extends BaseModel {

    @Column(name = "text") //Lenght?
    private String questionText;
    @Column(name = "criteria")
    @Enumerated(EnumType.STRING)
    private Criteria criteria;

    //SingleChoice oder Date
    @Column(name = "question_type")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    //ManyToMany?
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<ChoiceAnswerTbl> answers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<QuestionConditionTbl> conditions;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<ChoiceAnswerTbl> getAnswers() {
        if(this.answers == null) {
            this.answers = new ArrayList<>();
        }
        return answers;
    }

    public void setAnswers(List<ChoiceAnswerTbl> answers) {
        this.answers = answers;
    }

    public Set<QuestionConditionTbl> getConditions() {
        return conditions;
    }

    public void setConditions(Set<QuestionConditionTbl> conditions) {
        this.conditions = conditions;
    }
}
