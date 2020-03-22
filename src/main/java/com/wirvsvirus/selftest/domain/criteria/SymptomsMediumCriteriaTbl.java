package com.wirvsvirus.selftest.domain.criteria;

import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Justus Schneider
 */

@Entity
@Table(name = "symptoms_medium_criteria")
public class SymptomsMediumCriteriaTbl extends Criteria{

    public SymptomsMediumCriteriaTbl() {
    }

    public SymptomsMediumCriteriaTbl(SelftestTbl selftestTbl) {
        super(selftestTbl);
    }

}
