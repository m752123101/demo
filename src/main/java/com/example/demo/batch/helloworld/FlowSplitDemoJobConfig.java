package com.example.demo.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * 并发执行
 * @author hanwen.dong
 * @date 2019/8/17 17:38
 * @Description auto
 */
//@Configuration
//@EnableBatchProcessing
public class FlowSplitDemoJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowSplitDemoJob() {
        return jobBuilderFactory.get("flowSplitDemoJob")
                .start(demoSplitFlow1()).split(new SimpleAsyncTaskExecutor()).add(demoSplitFlow2()).end().build();
    }

    @Bean
    public Step flowSplitDemoStep1() {
        return stepBuilderFactory.get("flowSplitDemoStep1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=====================");
                System.out.println("flowSplitStep1");
                System.out.println("=====================");

                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step flowSplitDemoStep2() {
        return stepBuilderFactory.get("flowSplitDemoStep2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=====================");
                System.out.println("flowSplitStep2");
                System.out.println("=====================");

                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step flowSplitDemoStep3() {
        return stepBuilderFactory.get("flowSplitDemoStep3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("=====================");
                System.out.println("step3");
                System.out.println("=====================");

                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Flow demoSplitFlow1() {
        return new FlowBuilder<Flow>("demoSplitFlow1").start(flowSplitDemoStep1())
                .next(flowSplitDemoStep2()).end();
    }

    @Bean
    public Flow demoSplitFlow2() {
        return new FlowBuilder<Flow>("demoSplitFlow2").start(flowSplitDemoStep3())
                .end();
    }

}
