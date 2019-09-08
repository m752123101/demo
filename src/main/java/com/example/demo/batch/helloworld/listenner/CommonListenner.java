package com.example.demo.batch.helloworld.listenner;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import javax.batch.api.listener.JobListener;
import java.text.SimpleDateFormat;

/**
 * job 监听器
 *
 * @author hanwen.dong
 * @date 2019/8/29 18:53
 * @Description auto
 */

public class CommonListenner implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        dateFormat.format(        jobExecution.getStartTime());
        System.out.println("job listener  start ");
        System.out.println(dateFormat.toString());

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        dateFormat.format(        jobExecution.getStartTime());
        System.out.println(dateFormat.toString());
        System.out.println("job listener  end ");

    }
}
