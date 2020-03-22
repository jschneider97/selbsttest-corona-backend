package com.wirvsvirus.selftest.domain.criteria;

import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "contact_criteria")
public class ContactCriteriaTbl extends Criteria{

    public ContactCriteriaTbl() { }

    public ContactCriteriaTbl(SelftestTbl selftestTbl) {
        super(selftestTbl);
    }

    @Column(name = "contact_germany")
    Date contactDate;

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactGermany) {
        this.contactDate = contactDate;
    }
}
