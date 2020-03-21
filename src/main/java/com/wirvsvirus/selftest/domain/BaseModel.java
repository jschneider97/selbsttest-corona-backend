package com.wirvsvirus.selftest.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Justus Schneider
 */

@MappedSuperclass
public abstract class BaseModel {

    //TODO: Have this in BaseDto or in the entities?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Date updateDate;
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
