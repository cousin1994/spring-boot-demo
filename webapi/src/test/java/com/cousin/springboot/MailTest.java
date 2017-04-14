package com.cousin.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by a7738 on 2016/12/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(MailTest.class);

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void test_sendMail() {



    }

}
