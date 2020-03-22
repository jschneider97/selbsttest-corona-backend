package com.wirvsvirus.selftest.domain.criteria;

import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Justus Schneider
 */
@Entity
@Table(name = "symptoms_criteria")
public class SymptomsCriteriaTbl extends Criteria {

    public SymptomsCriteriaTbl() {
    }

    public SymptomsCriteriaTbl(SelftestTbl selftestTbl) {
        super(selftestTbl);
    }

}
