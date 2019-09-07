package com.example.demo.batch.helloworld;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * TODO
 *
 * @author hanwen.dong
 * @date 2019/8/17 18:23
 * @Description auto
 */

public class Mydecider implements JobExecutionDecider {
    private Integer count=0;
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;
        if(count%2==0){
            return new FlowExecutionStatus("even");
        }else {
            return new FlowExecutionStatus("odd");
        }
    }
}
