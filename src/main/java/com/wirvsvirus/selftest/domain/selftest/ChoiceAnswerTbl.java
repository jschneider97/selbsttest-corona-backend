package com.wirvsvirus.selftest.domain.selftest;

import com.wirvsvirus.selftest.api.enums.QuestionType;
import com.wirvsvirus.selftest.api.selftest.Answer;
import com.wirvsvirus.selftest.domain.BaseModel;

import javax.persistence.*;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "choice_answer")
public class ChoiceAnswerTbl extends BaseModel {
    @Column(name = "text")
    private String answertText;

    @Column(name = "scoreImpact")
    private int scoreImpact;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionTbl question;

    public int getScoreImpact() {
        return scoreImpact;
    }

    public void setScoreImpact(int scoreImpact) {
        this.scoreImpact = scoreImpact;
    }

    public QuestionType getAnswerType() {
        return QuestionType.CHOICE_QUESTION;
    }

    public String getAnswertText() {
        return answertText;
    }

    public void setAnswertText(String answertText) {
        this.answertText = answertText;
    }
}
