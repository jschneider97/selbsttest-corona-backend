package com.wirvsvirus.selftest.domain;

import com.wirvsvirus.selftest.api.Contact;
import com.wirvsvirus.selftest.api.enums.AccessDevice;
import com.wirvsvirus.selftest.api.selftest.Selftest;
import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Justus Schneider
 */


//Represents a user checking his symptoms
@Entity
@Table(name = "selftest_subject") //TODO: Index auf DeviceId
public class SelftestSubjectTbl extends BaseModel {

    @Column(name = "zip_code", length = 5)
    private String zipCode;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "accessDevice")
    @Enumerated(EnumType.STRING)
    private AccessDevice accessDevice;
    @Column(name = "deviceId")
    private String deviceID;
    //Order By Date asc
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}, mappedBy = "subject")
    private List<SelftestTbl> selftests;

    @OneToOne(orphanRemoval = true)
    private ContactTbl contact;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AccessDevice getAccessDevice() {
        return accessDevice;
    }

    public void setAccessDevice(AccessDevice accessDevice) {
        this.accessDevice = accessDevice;
    }

    public List<SelftestTbl> getSelftests() {
        if (this.selftests == null) {
            this.selftests = new ArrayList<>();
        }
        return selftests;
    }

    public void setSelftests(List<SelftestTbl> selftests) {
        this.selftests = selftests;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
