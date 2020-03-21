package com.wirvsvirus.selftest.repository;

import com.wirvsvirus.selftest.domain.SelftestSubjectTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Justus Schneider
 */

@Repository
public interface SelftestSubjectRepository extends JpaRepository<SelftestSubjectTbl, Long> {

    Optional<SelftestSubjectTbl> findById(Long id);
    Set<SelftestSubjectTbl> findByDeviceID(String deviceId);
}
