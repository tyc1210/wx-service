package com.tyc.wx.config;

import com.tyc.wx.utils.ThreadMdcUtil;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-12-27 10:19:37
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor commonExecutor(){
        // 封装 ThreadPoolTaskExecutor 使之支持traceId透传
        ThreadPoolTaskExecutor executor = new ThreadMdcUtil.ThreadPoolTaskExecutorMdcWrapper();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setMaxPoolSize(30);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("common-");
        executor.setQueueCapacity(100);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * @Async线程池封装使之支持traceId透传
     */
    @Bean("SpExecutor")
    public Executor getAsyncExecutor(){
        new ThreadPoolTaskExecutor(){
            @Override
            public <T> Future<T> submit(Callable<T> task) {
                // 传入线程池之前先复制当前线程的MDC
                return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
            }
            @Override
            public void execute(Runnable task) {
                super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
            }
        };
        return null;
    }
}
