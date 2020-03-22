package com.wirvsvirus.selftest.repository;

import com.wirvsvirus.selftest.domain.selftest.QuestionAnswerTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Justus Schneider
 */

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswerTbl, Long> {
}
