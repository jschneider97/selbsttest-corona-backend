package com.wirvsvirus.selftest.repository;

import com.wirvsvirus.selftest.domain.selftest.QuestionTbl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @author Justus Schneider
 */

public interface QuestionRepository extends JpaRepository<QuestionTbl, Long> {

}
