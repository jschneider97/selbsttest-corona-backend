package com.wirvsvirus.selftest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "contact" ) //TODO: Index auf tel und mail
public class ContactTbl extends BaseModel {

    @Column(name = "telephone_no")
    private String telephoneNo;

    @Column(name = "EMail")
    private String EMail;

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }
}
