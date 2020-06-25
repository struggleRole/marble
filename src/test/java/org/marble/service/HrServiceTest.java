package org.marble.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@Slf4j
class HrServiceTest {
    @Autowired
    private HrService hrService;

    @Test
    void getAllHrs() {
        log.info("test");
    }
}