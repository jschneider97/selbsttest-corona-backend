package com.wirvsvirus.selftest.domain.criteria;

import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Justus Schneider
 */


@Entity
@Table(name = "symptoms_high_criteria")
public class SymptomsHighCriteriaTbl extends Criteria{

    public SymptomsHighCriteriaTbl() {
    }

    public SymptomsHighCriteriaTbl(SelftestTbl selftestTbl) {
        super(selftestTbl);
    }

}
