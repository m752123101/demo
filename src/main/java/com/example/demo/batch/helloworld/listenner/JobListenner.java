package com.example.demo.batch.helloworld.listenner;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import javax.batch.api.listener.JobListener;

/**
 * job 监听器
 *
 * @author hanwen.dong
 * @date 2019/8/29 18:53
 * @Description auto
 */

public class JobListenner implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("job listener  start ");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("job listener  end ");

    }
}
