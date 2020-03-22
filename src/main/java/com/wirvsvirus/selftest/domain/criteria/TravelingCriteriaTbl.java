package com.wirvsvirus.selftest.domain.criteria;

import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "traveling_criteria")
public class TravelingCriteriaTbl extends Criteria {

    public TravelingCriteriaTbl() {
    }

    public TravelingCriteriaTbl(SelftestTbl selftestTbl) {
        super(selftestTbl);
    }

}
