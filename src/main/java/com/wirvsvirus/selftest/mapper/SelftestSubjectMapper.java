package com.wirvsvirus.selftest.mapper;

import com.wirvsvirus.selftest.api.Contact;
import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.domain.ContactTbl;
import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Justus Schneider
 */

@Mapper(componentModel = "spring")
public interface SelftestSubjectMapper {

    @Mapping(target = "selftests", ignore = true)
    @Mapping(target = "contact", ignore = true)
    public SelftestSubject mapFromBean(SelftestSubjectTbl entity);
    @Mapping(target = "selftests", ignore = true)
    @Mapping(target = "contact", ignore = true)
    @Mapping(target = "id", ignore = true)
    public SelftestSubjectTbl mapToBean(SelftestSubject dto);

    @Mapping(target = "selftests", ignore = true)
    @Mapping(target = "contact", ignore = true)
    @Mapping(target = "id", ignore = true)
    public void mapToBean(SelftestSubject source, @MappingTarget SelftestSubjectTbl target);

    public Contact mapFromBean(ContactTbl entity);
    public ContactTbl mapToBean(Contact contact);
}
