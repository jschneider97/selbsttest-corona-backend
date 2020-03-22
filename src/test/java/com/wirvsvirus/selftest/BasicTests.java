package com.wirvsvirus.selftest;

import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.api.enums.AccessDevice;
import com.wirvsvirus.selftest.api.enums.Criteria;
import com.wirvsvirus.selftest.api.enums.QuestionType;
import com.wirvsvirus.selftest.api.selftest.Question;
import com.wirvsvirus.selftest.domain.selftest.ChoiceAnswerTbl;
import com.wirvsvirus.selftest.domain.selftest.QuestionTbl;
import com.wirvsvirus.selftest.repository.QuestionRepository;
import com.wirvsvirus.selftest.service.QuestionService;
import com.wirvsvirus.selftest.service.SelftestService;
import com.wirvsvirus.selftest.service.SelftestSubjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import javax.transaction.Transactional;

/**
 * @author Justus Schneider
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasicTests {

    @Autowired
    SelftestSubjectService selftestSubjectService;
    @Autowired
    SelftestService selftestService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionRepository questionRepository;

	/*@Test
	void contextLoads() {
	}*/

    static Long subId;
    static Long selfTestId;

    @Test
    @Order(5)
    @Transactional
    void init() {

    }

    @Test
    @Order(1)
    void selftestSubCreateTest() {
        SelftestSubject subject = new SelftestSubject();
        subject.setAccessDevice(AccessDevice.ANDROID);
        subject.setCity("Münster");
        subject.setZipCode("48149");
        subject.setDeviceID("12345");

        subId = this.selftestSubjectService.createSelftestSubject(subject).getId();
    }

    @Test
    @Order(2)
    void selftestSubGetTest() {
        SelftestSubject subject = this.selftestSubjectService.getSelftestSubject(subId);
        Assert.assertTrue(subject.getCity().equals("Münster"));
    }

    @Test
    @Order(3)
    void selftestSubPutTest() {
        SelftestSubject subject = this.selftestSubjectService.getSelftestSubject(subId);
        subject.setDeviceID("5678");
        subject = this.selftestSubjectService.updateSelftestSubject(subId, subject);
        Assert.assertTrue(subject.getDeviceID().equals("5678"));
    }

    @Test
    @Order(4)
    void selfTestCreateTest() {
        selfTestId = this.selftestService.createForSubject(subId).getId();
    }
    @Test
    @Order(6)
    void questionTest() {
        Question q = questionService.getNextQuestionForSelftest(selfTestId);
    }
}
