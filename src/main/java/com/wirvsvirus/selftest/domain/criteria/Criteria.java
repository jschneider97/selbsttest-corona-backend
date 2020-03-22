package com.wirvsvirus.selftest.domain.criteria;

import com.wirvsvirus.selftest.domain.BaseModel;
import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * @author Justus Schneider
 */

@MappedSuperclass
public class Criteria extends BaseModel {

    public Criteria() {

    }

    public Criteria(SelftestTbl selftest) {
        this.selftest = selftest;
        this.score = 0;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "selftest_id", referencedColumnName = "id")
    private SelftestTbl selftest;

    @Column(name = "score")
    private int score;

    public SelftestTbl getSelftest() {
        return selftest;
    }

    public void setSelftest(SelftestTbl selftest) {
        this.selftest = selftest;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int num) {
        this.score += num;
    }
}
