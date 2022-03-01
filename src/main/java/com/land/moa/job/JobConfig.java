package com.land.moa.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j // log 사용 lombok 어노테이션
@RequiredArgsConstructor // 생성자 DI 역할 lombok 어노테이션
@Configuration // Job 설정을 스프링 컨테이너에 등록하는 역할 어노테이션
public class JobConfig {
  private final JobBuilderFactory jobBuilderFactory; // Job Repository 설정해주는 역할
  private final StepBuilderFactory stepBuilderFactory; // PlatformTransactionManager 설정해주는 역할

  @Bean
  public Job landPrice() {
    return jobBuilderFactory.get("landPriceJob")
        .start(landPriceStep())
        .build();
  }

  private Step landPriceStep() {
    return stepBuilderFactory.get("landPriceStep")
        .tasklet((contribution, chunkContext) -> {
          log.info(">>> land price step start");
          return RepeatStatus.FINISHED;
        }).build();
  }
}
