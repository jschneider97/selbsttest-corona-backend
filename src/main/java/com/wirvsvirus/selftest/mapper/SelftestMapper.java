package com.wirvsvirus.selftest.mapper;

import com.wirvsvirus.selftest.api.selftest.ChoiceAnswer;
import com.wirvsvirus.selftest.api.selftest.DateAnswer;
import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.api.selftest.Selftest;
import com.wirvsvirus.selftest.domain.selftest.ChoiceAnswerTbl;
import com.wirvsvirus.selftest.domain.selftest.QuestionTbl;
import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Justus Schneider
 */


@Mapper(componentModel = "spring")
public interface SelftestMapper {


    @Mapping(target = "contactCriteria", ignore = true)
    @Mapping(target = "symptomsCriteria", ignore = true)
    @Mapping(target = "travelingCriteria", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Selftest mapFromBean(SelftestTbl entity);

    @Mapping(target = "answer", ignore = true)
    Question mapFromBean(QuestionTbl questionTbl);

    ChoiceAnswer mapFromBean(ChoiceAnswerTbl entity);
    DateAnswer mapFromBean(DateAnswer entity);

}
