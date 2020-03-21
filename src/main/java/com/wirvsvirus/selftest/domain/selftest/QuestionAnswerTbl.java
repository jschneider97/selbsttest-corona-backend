package com.wirvsvirus.selftest.domain.selftest;

import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.domain.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "question_answer")
public class QuestionAnswerTbl extends BaseModel {

    //LAZY oder EAGER
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionTbl question;
    //s.o
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_answer_id", referencedColumnName = "id")
    private ChoiceAnswerTbl choiceAnswer;

    @Column(name = "date_answer")
    @Temporal(TemporalType.DATE)
    private Date dateAnswer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "selftest_id", referencedColumnName = "id")
    private SelftestTbl selftest;

    public QuestionTbl getQuestion() {
        return question;
    }

    public void setQuestion(QuestionTbl question) {
        this.question = question;
    }

    public ChoiceAnswerTbl getChoiceAnswer() {
        return choiceAnswer;
    }

    public void setChoiceAnswer(ChoiceAnswerTbl choiceAnswer) {
        this.choiceAnswer = choiceAnswer;
    }

    public Date getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(Date dateAnswer) {
        this.dateAnswer = dateAnswer;
    }

    public SelftestTbl getSelftest() {
        return selftest;
    }

    public void setSelftest(SelftestTbl selftest) {
        this.selftest = selftest;
    }
}
