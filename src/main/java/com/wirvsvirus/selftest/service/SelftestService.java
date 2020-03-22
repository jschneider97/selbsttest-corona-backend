package com.wirvsvirus.selftest.service;

import com.wirvsvirus.selftest.api.selftest.Selftest;
import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;
import com.wirvsvirus.selftest.domain.criteria.*;
import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;
import com.wirvsvirus.selftest.mapper.QuestionMapping;
import com.wirvsvirus.selftest.mapper.SelftestMapper;
import com.wirvsvirus.selftest.repository.SelftestRepository;
import com.wirvsvirus.selftest.repository.SelftestSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Justus Schneider
 */

@Service
public class SelftestService {

    private final SelftestRepository selftestRepository;
    private final SelftestSubjectRepository selftestSubjectRepository;
    private final SelftestMapper selftestMapper;
    private final QuestionMapping questionMapping;

    @Autowired
    public SelftestService(SelftestRepository selftestRepository, SelftestSubjectRepository selftestSubjectRepository, SelftestMapper selftestMapper, QuestionMapping questionMapping) {
        this.selftestRepository = selftestRepository;
        this.selftestSubjectRepository = selftestSubjectRepository;
        this.selftestMapper = selftestMapper;
        this.questionMapping = questionMapping;
    }

    @Transactional
    public Selftest getById(Long id) {
        SelftestTbl entity = this.selftestRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Selftest selftest = this.selftestMapper.mapFromBean(entity);
        selftest.setQuestions(entity.getQuestionAnswerList().stream().map(questionMapping::mapFromBean).collect(Collectors.toList()));

        return selftest;
    }

    @Transactional
    public List<Selftest> findBySubject(Long subjectId) {
        SelftestSubjectTbl subject = this.selftestSubjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
        List<SelftestTbl> entity = this.selftestRepository.findBySubjectOrderByCreateDateAsc(subject);

        return entity.stream().map(selftestMapper::mapFromBean).collect(Collectors.toList());
    }

    @Transactional
    public Selftest createForSubject(Long subjectId) {
        SelftestSubjectTbl subject = this.selftestSubjectRepository.findById(subjectId).orElseThrow(EntityNotFoundException::new);
        SelftestTbl entity = new SelftestTbl();
        entity.setFinished(false);
        entity.setSubject(subject);
        entity.setContactCriteria(new ContactCriteriaTbl(entity));
        entity.setRiskGroupCriteria(new RiskGroupCriteriaTbl(entity));
        entity.setSymptomsCriteria(new SymptomsCriteriaTbl(entity));
        entity.setSymptomsMediumCriteria(new SymptomsMediumCriteriaTbl(entity));
        entity.setSymptomsHighCriteria(new SymptomsHighCriteriaTbl(entity));
        entity.setTravelingCriteria(new TravelingCriteriaTbl(entity));

        this.selftestRepository.save(entity);

        return selftestMapper.mapFromBean(entity);
    }

}
