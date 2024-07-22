package com.example.multiprocessing.demo.controller;

import com.example.multiprocessing.demo.service.AService;
import com.example.multiprocessing.demo.service.BService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 创建时间：2024/7/22 14:17
 * 作者：Jun bu jian
 */
@RequestMapping("/home")
@Controller
public class HomeController {
    @Resource
    private BService bService;

    @Resource
    private AService aService;

    @Resource
    private ThreadPoolTaskExecutor AThreadPool;

    @Resource
    private ThreadPoolTaskExecutor BThreadPool;

    //监控线程池的状态,
    //我们得到的数字，只是大体接近，并不是严格的准确数字
    @GetMapping("/poolstatus")
    @ResponseBody
    public String poolstatus() {
        String statusStr = "";
        int queueSize = AThreadPool.getThreadPoolExecutor().getQueue().size();
        statusStr +="当前排队线程数：" + queueSize;
        int activeCount = AThreadPool.getThreadPoolExecutor().getActiveCount();
        statusStr +="当前活动线程数：" + activeCount;
        long completedTaskCount = AThreadPool.getThreadPoolExecutor().getCompletedTaskCount();
        statusStr +="执行完成线程数：" + completedTaskCount;
        long taskCount = AThreadPool.getThreadPoolExecutor().getTaskCount();
        statusStr +="总线程数：" + taskCount;
        statusStr += "\n";

        queueSize = BThreadPool.getThreadPoolExecutor().getQueue().size();
        statusStr +="当前排队线程数：" + queueSize;
        activeCount = BThreadPool.getThreadPoolExecutor().getActiveCount();
        statusStr +="当前活动线程数：" + activeCount;
        completedTaskCount = BThreadPool.getThreadPoolExecutor().getCompletedTaskCount();
        statusStr +="执行完成线程数：" + completedTaskCount;
        taskCount = BThreadPool.getThreadPoolExecutor().getTaskCount();
        statusStr +="总线程数：" + taskCount;

        return statusStr;
    }

    //异步执行sleep1秒10次
    @GetMapping("/asyncA")
    @ResponseBody
    public Map<String, Object> asyncsleepA() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Future<String> future = aService.asynctmb(i);
            futures.add(future);
        }
        List<String> response = new ArrayList<>();
        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        map.put("data", response);
        map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
        return map;
    }
    //异步执行sleep1秒10次
    @GetMapping("/asyncB")
    @ResponseBody
    public Map<String, Object> asyncsleepB() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Future<String> future = bService.asynctmb(i);
            futures.add(future);
        }
        List<String> response = new ArrayList<>();
        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        map.put("data", response);
        map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
        return map;
    }
}
