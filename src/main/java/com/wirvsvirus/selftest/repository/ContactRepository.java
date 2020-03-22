package com.wirvsvirus.selftest.repository;

import com.wirvsvirus.selftest.domain.ContactTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Justus Schneider
 */

@Repository
public interface ContactRepository extends JpaRepository<ContactTbl, Long> {
}
