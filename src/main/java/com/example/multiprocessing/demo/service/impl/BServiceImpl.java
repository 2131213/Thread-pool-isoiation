package com.example.multiprocessing.demo.service.impl;

import com.example.multiprocessing.demo.service.BService;
import com.example.multiprocessing.demo.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * 创建时间：2024/7/22 14:19
 * 作者：Jun bu jian
 */
@Slf4j
@Service
public class BServiceImpl implements BService {

    @Async(value="BThreadPool")
    @Override
    public Future<String> asynctmb(int i) {
        log.info("asynctmb begin");
        String start= TimeUtil.getMilliTimeNow();
        try {
            Thread.sleep(1000);    //延时1秒
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        //log.info("async function sleep   end");
        String end= TimeUtil.getMilliTimeNow();
        return new AsyncResult<>(String.format("asynctmb方法,第 %s 个线程:开始时间:%s,结束时间:%s",i,start,end));
    }
}
