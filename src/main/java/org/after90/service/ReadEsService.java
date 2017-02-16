package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepositoryBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhaogj on 16/02/2017.
 */
@Service
@Slf4j
public class ReadEsService {
    @Autowired
    private ESRepositoryBack es;

    public void readES() {
        log.info("start readES");
        log.info("end readES");
    }
}
