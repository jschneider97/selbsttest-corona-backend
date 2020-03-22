package com.wirvsvirus.selftest.domain.selftest;

import com.wirvsvirus.selftest.api.Criteria.*;
import com.wirvsvirus.selftest.domain.BaseModel;
import com.wirvsvirus.selftest.domain.criteria.*;
import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "selftest")
public class SelftestTbl extends BaseModel {

    @Column(name = "finished")
    private Boolean finished;

    //TODO: Cascade prüfen
    @OneToOne(mappedBy = "selftest", cascade = CascadeType.ALL)
    ContactCriteriaTbl contactCriteria;
    @OneToOne(mappedBy = "selftest", cascade = CascadeType.ALL)
    SymptomsCriteriaTbl symptomsCriteria;
    @OneToOne(mappedBy = "selftest", cascade = CascadeType.ALL)
    TravelingCriteriaTbl travelingCriteria;
    @OneToOne(mappedBy = "selftest", cascade = CascadeType.ALL)
    RiskGroupCriteriaTbl riskGroupCriteria;
    @OneToOne(mappedBy = "selftest", cascade = CascadeType.ALL)
    SymptomsHighCriteriaTbl symptomsHighCriteria;
    @OneToOne(mappedBy = "selftest", cascade = CascadeType.ALL)
    SymptomsMediumCriteriaTbl symptomsMediumCriteria;

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

    public ContactCriteriaTbl getContactCriteria() {
        return contactCriteria;
    }

    public void setContactCriteria(ContactCriteriaTbl contactCriteria) {
        this.contactCriteria = contactCriteria;
    }

    public SymptomsCriteriaTbl getSymptomsCriteria() {
        return symptomsCriteria;
    }

    public void setSymptomsCriteria(SymptomsCriteriaTbl symptomsCriteria) {
        this.symptomsCriteria = symptomsCriteria;
    }

    public TravelingCriteriaTbl getTravelingCriteria() {
        return travelingCriteria;
    }

    public void setTravelingCriteria(TravelingCriteriaTbl travelingCriteria) {
        this.travelingCriteria = travelingCriteria;
    }

    public RiskGroupCriteriaTbl getRiskGroupCriteria() {
        return riskGroupCriteria;
    }

    public void setRiskGroupCriteria(RiskGroupCriteriaTbl riskGroupCriteria) {
        this.riskGroupCriteria = riskGroupCriteria;
    }

    public SymptomsHighCriteriaTbl getSymptomsHighCriteria() {
        return symptomsHighCriteria;
    }

    public void setSymptomsHighCriteria(SymptomsHighCriteriaTbl symptomsHighCriteria) {
        this.symptomsHighCriteria = symptomsHighCriteria;
    }

    public SymptomsMediumCriteriaTbl getSymptomsMediumCriteria() {
        return symptomsMediumCriteria;
    }

    public void setSymptomsMediumCriteria(SymptomsMediumCriteriaTbl symptomsMediumCriteria) {
        this.symptomsMediumCriteria = symptomsMediumCriteria;
    }

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
