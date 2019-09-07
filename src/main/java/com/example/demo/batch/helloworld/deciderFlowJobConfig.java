package com.example.demo.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 决策器的使用
 * @author hanwen.dong
 * @date 2019/8/17 18:17
 * @Description auto
 */
@Configuration
public class deciderFlowJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step deciderStep1(){
        return stepBuilderFactory.get("deciderStep1").tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("======================");
                        System.out.println("deciderStep1>>>>START");
                        System.out.println("======================");
                        return RepeatStatus.FINISHED;
                    }
                }
        ).build();
    }
    @Bean
    public Step deciderStep2(){
        return stepBuilderFactory.get("deciderStep2").tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("======================");
                        System.out.println("deciderStep2>>>>EVEN");
                        System.out.println("======================");
                        return RepeatStatus.FINISHED;
                    }
                }
        ).build();
    }
    @Bean
    public Step deciderStep3(){
        return stepBuilderFactory.get("deciderStep3").tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("======================");
                        System.out.println("deciderStep3 ->>>>>ODD");
                        System.out.println("======================");
                        return null;
                    }
                }
        ).build();
    }
    @Bean
    public Step finalStep(){
        return stepBuilderFactory.get("finalStep").tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("======================");
                        System.out.println("finalStep");
                        System.out.println("======================");
                        return null;
                    }
                }
        ).build();
    }
    @Bean
    public JobExecutionDecider myDecider(){
        return new Mydecider();
    }

    // * 是通配符
    @Bean
    public Job testDeciderJob(){
        return jobBuilderFactory.get("testDeciderJob").start(deciderStep1())
                .next(myDecider())
                .from(myDecider()).on("even").to(deciderStep2())
                .from(myDecider()).on("odd").to(deciderStep3())
                .from(deciderStep3()).on("*").to(finalStep()).end().build();
    }
}
