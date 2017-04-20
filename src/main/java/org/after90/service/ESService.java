package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhaogj on 20/04/2017.
 */
@Slf4j
@Service
public class ESService {
    @Autowired
    private ESRepository es;

    /**
     * 创建index，设置index
     */
    public void createIndex() {

    }

}
