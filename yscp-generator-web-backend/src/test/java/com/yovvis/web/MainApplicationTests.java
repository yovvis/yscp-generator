package com.yovvis.web;

import com.yovvis.web.config.WxOpenConfig;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 */
@Slf4j
@SpringBootTest
class MainApplicationTests {

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    void contextLoads() {
        System.out.println(wxOpenConfig);
    }

    @Test
    void testJayspy() {
        String username = stringEncryptor.encrypt("yovvis");
        String password = stringEncryptor.encrypt("yovvis666");
        String mwUsername = stringEncryptor.decrypt("Xv0/Jy4e6216h0hvNxqKCVK5imCjV/9EIlCkMMb6f4LQ9lBfd2tkAe6roJqymY+N");
        String mvPassword = stringEncryptor.decrypt("dQrOEplZmy1NzAYUcK/V2+tnEJWsV/lIPJfF0tPWCj8zU7hIQL5hkyqr1d0QOkym");
        log.info("username: " + username);
        log.info("password: " + password);
        log.info("username: " + mwUsername);
        log.info("password: " + mvPassword);
    }
}
