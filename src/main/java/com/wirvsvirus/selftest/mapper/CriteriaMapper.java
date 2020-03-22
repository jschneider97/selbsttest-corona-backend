package com.wirvsvirus.selftest.mapper;

import com.wirvsvirus.selftest.api.Criteria.*;
import com.wirvsvirus.selftest.domain.criteria.ContactCriteriaTbl;
import com.wirvsvirus.selftest.domain.criteria.SymptomsMediumCriteriaTbl;
import com.wirvsvirus.selftest.domain.criteria.TravelingCriteriaTbl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Justus Schneider
 */

@Mapper(componentModel = "spring")
public interface CriteriaMapper {

    ContactCriteria mapFromBean(ContactCriteriaTbl entity);
    RiskGroupCriteria mapFromBean(RiskGroupCriteria entity);
    SymptomsCriteria mapFromBean(SymptomsCriteria entity);
    SymptomsHighCriteria mapFromBean(SymptomsHighCriteria entity);
    SymptomsMediumCriteria mapFromBean(SymptomsMediumCriteriaTbl entity);
    TravelingCriteria mapFromBean(TravelingCriteriaTbl entity);

}
