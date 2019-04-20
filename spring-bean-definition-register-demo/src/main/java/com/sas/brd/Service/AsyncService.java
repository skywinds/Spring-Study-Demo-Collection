package com.sas.brd.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("taskExecutor")
    public void startJob() {
        System.out.println("异步执行test1！！！");
        System.out.println("线程id：" + Thread.currentThread().getId());
        System.out.println("线程名称：" + Thread.currentThread().getName());
    }
}
