package com.wirvsvirus.selftest.mapper;

import com.wirvsvirus.selftest.api.Contact;
import com.wirvsvirus.selftest.domain.ContactTbl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Justus Schneider
 */

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    ContactTbl mapToBean(Contact contact);

    Contact mapFromBean(ContactTbl contactTbl);
}
