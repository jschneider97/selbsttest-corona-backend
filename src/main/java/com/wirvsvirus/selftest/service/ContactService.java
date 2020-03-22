package com.wirvsvirus.selftest.service;

import com.wirvsvirus.selftest.api.Contact;
import com.wirvsvirus.selftest.domain.ContactTbl;
import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;
import com.wirvsvirus.selftest.mapper.ContactMapper;
import com.wirvsvirus.selftest.repository.ContactRepository;
import com.wirvsvirus.selftest.repository.SelftestSubjectRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

/**
 * @author Justus Schneider
 */

@Service
public class ContactService {

    private final SelftestSubjectRepository selftestSubjectRepository;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactService(SelftestSubjectRepository selftestSubjectRepository, ContactRepository contactRepository, ContactMapper contactMapper) {
        this.selftestSubjectRepository = selftestSubjectRepository;
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Transactional
    public Contact addContactToSubject(Long subjectId, Contact contact) {
        SelftestSubjectTbl subject = this.selftestSubjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
        ContactTbl contactEntity = this.contactMapper.mapToBean(contact);

        subject.setContact(contactEntity);
        this.contactRepository.save(contactEntity);
        this.selftestSubjectRepository.save(subject);


        return this.contactMapper.mapFromBean(contactEntity);
    }

}
