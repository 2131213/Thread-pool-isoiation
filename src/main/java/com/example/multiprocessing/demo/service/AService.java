package com.example.multiprocessing.demo.service;

import java.util.concurrent.Future;

/**
 * 创建时间：2024/7/22 14:18
 * 作者：Jun bu jian
 */
public interface AService {
    public Future<String> asynctmb(int i);
}
