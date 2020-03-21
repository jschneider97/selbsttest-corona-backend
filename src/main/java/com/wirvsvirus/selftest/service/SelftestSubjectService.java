package com.wirvsvirus.selftest.service;

import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;
import com.wirvsvirus.selftest.mapper.SelftestSubjectMapper;
import com.wirvsvirus.selftest.repository.SelftestSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Justus Schneider
 */

//TODO: Find city by zipCode when creating an entity
@Service
public class SelftestSubjectService {

    private final SelftestSubjectRepository selftestSubjectRepository;
    private final SelftestSubjectMapper selftestSubjectMapper;

    @Autowired
    public SelftestSubjectService(SelftestSubjectRepository selftestSubjectRepository, SelftestSubjectMapper selftestSubjectMapper) {
        this.selftestSubjectRepository = selftestSubjectRepository;
        this.selftestSubjectMapper = selftestSubjectMapper;
    }

    @Transactional
    public SelftestSubject getSelftestSubject(Long id) {
        SelftestSubjectTbl entity = this.selftestSubjectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapReturnValue(entity);
    }

    public Set<SelftestSubject> findByDeviceId(String deviceId) {
        Set<SelftestSubjectTbl> entities = this.selftestSubjectRepository.findByDeviceID(deviceId);
        return entities.stream().map(this::mapReturnValue).collect(Collectors.toSet());
    }

    @Transactional
    public SelftestSubject createSelftestSubject(SelftestSubject selftestSubject) {
        SelftestSubjectTbl entity = this.selftestSubjectMapper.mapToBean(selftestSubject);
        return this.selftestSubjectMapper.mapFromBean(this.selftestSubjectRepository.save(entity));
    }

    @Transactional
    public SelftestSubject updateSelftestSubject(Long id, SelftestSubject selftestSubject) {
        SelftestSubjectTbl entity = this.selftestSubjectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.selftestSubjectMapper.mapToBean(selftestSubject, entity);

        return this.selftestSubjectMapper.mapFromBean(this.selftestSubjectRepository.save(entity));
    }

    @Transactional(Transactional.TxType.MANDATORY)
    protected SelftestSubject mapReturnValue(SelftestSubjectTbl entity) {
        SelftestSubject subject = this.selftestSubjectMapper.mapFromBean(entity);
        if(entity.getContact() != null) {
            subject.setContact(this.selftestSubjectMapper.mapFromBean(entity.getContact()));
        }

        return subject;
    }
}
