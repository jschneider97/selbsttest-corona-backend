package com.wirvsvirus.selftest.repository;

import com.wirvsvirus.selftest.domain.selftest.ChoiceAnswerTbl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Justus Schneider
 */

public interface AnswerRepository extends JpaRepository<ChoiceAnswerTbl, Long> {

}
