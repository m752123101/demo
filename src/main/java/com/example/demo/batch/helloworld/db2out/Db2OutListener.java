package com.example.demo.batch.helloworld.db2out;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.Date;

/**
 * 读取数据库-》输出控制台
 *
 * @author hanwen.dong
 * @date 2019/9/7 16:23
 * @Description auto
 */

public class Db2OutListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("======================================");
        JobParametersBuilder builder=new JobParametersBuilder();
        builder.addDate("date",new Date());
        System.out.println("before");
        System.out.println("======================================");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("======================================");
        System.out.println("after");
        System.out.println("======================================");

    }
}
