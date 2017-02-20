package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by zhaogj on 20/02/2017.
 */
@Service
@Slf4j
public class WyfHaha {
    public void doSomeThine(String strInput) {
        log.info("start do some thing");
        log.info("strInput:{}", strInput);
        log.info("end do some thing");
    }
}
