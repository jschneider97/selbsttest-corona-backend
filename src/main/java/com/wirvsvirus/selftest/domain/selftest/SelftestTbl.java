package com.wirvsvirus.selftest.domain.selftest;

import com.wirvsvirus.selftest.api.BaseDto;
import com.wirvsvirus.selftest.api.Criteria.ContactCriteria;
import com.wirvsvirus.selftest.api.Criteria.SymptomsCriteria;
import com.wirvsvirus.selftest.api.Criteria.TravelingCriteria;
import com.wirvsvirus.selftest.api.selftest.Answer;
import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.domain.BaseModel;
import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "selftest")
public class SelftestTbl extends BaseModel {

    @Column(name = "finished")
    private Boolean finished;

    /*ContactCriteria contactCriteria;
    SymptomsCriteria symptomsCriteria;
    TravelingCriteria travelingCriteria;*/
    //...

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SelftestSubjectTbl subject;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "selftest")
    private List<QuestionAnswerTbl> questionAnswerList;

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /*public ContactCriteria getContactCriteria() {
        return contactCriteria;
    }

    public void setContactCriteria(ContactCriteria contactCriteria) {
        this.contactCriteria = contactCriteria;
    }

    public SymptomsCriteria getSymptomsCriteria() {
        return symptomsCriteria;
    }

    public void setSymptomsCriteria(SymptomsCriteria symptomsCriteria) {
        this.symptomsCriteria = symptomsCriteria;
    }

    public TravelingCriteria getTravelingCriteria() {
        return travelingCriteria;
    }

    public void setTravelingCriteria(TravelingCriteria travelingCriteria) {
        this.travelingCriteria = travelingCriteria;
    }*/

    public SelftestSubjectTbl getSubject() {
        return subject;
    }

    public void setSubject(SelftestSubjectTbl subject) {
        this.subject = subject;
    }

    public List<QuestionAnswerTbl> getQuestionAnswerList() {
        if(this.questionAnswerList == null) {
            this.questionAnswerList = new ArrayList<>();
        }
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<QuestionAnswerTbl> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }
}
