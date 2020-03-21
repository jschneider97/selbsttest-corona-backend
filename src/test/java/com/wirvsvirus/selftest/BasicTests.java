package com.wirvsvirus.selftest;

import com.wirvsvirus.selftest.api.SelftestSubject;
import com.wirvsvirus.selftest.api.enums.AccessDevice;
import com.wirvsvirus.selftest.service.SelftestSubjectService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

/**
 * @author Justus Schneider
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasicTests {

    @Autowired
    SelftestSubjectService selftestSubjectService;

	/*@Test
	void contextLoads() {
	}*/

    static Long subId;

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
    void selftestGetTest() {
        SelftestSubject subject = this.selftestSubjectService.getSelftestSubject(subId);
        Assert.assertTrue(subject.getCity().equals("Münster"));
    }

    @Test
    @Order(3)
    void selftestPutTest() {
        SelftestSubject subject = this.selftestSubjectService.getSelftestSubject(subId);
        subject.setDeviceID("5678");
        subject = this.selftestSubjectService.updateSelftestSubject(subId, subject);
        Assert.assertTrue(subject.getDeviceID().equals("5678"));
    }
}
