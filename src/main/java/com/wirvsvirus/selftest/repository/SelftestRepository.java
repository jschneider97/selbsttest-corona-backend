package com.wirvsvirus.selftest.repository;

import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;
import com.wirvsvirus.selftest.domain.selftest.SelftestTbl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Justus Schneider
 */

public interface SelftestRepository extends JpaRepository<SelftestTbl, Long> {

    Optional<SelftestTbl> findById(Long id);
    List<SelftestTbl> findBySubjectOrderByCreateDateAsc(SelftestSubjectTbl subject);
}
