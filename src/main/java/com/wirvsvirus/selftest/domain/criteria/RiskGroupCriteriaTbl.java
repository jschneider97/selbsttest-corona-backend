package com.wirvsvirus.selftest.domain.criteria;

import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Justus Schneider
 */

@Table(name = "risk_group_criteria")
@Entity
public class RiskGroupCriteriaTbl extends Criteria{

    public RiskGroupCriteriaTbl() {
    }

    public RiskGroupCriteriaTbl(SelftestTbl selftestTbl) {
        super(selftestTbl);
    }

}
