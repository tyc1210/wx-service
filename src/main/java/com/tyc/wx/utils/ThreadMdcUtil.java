package com.tyc.wx.utils;

import cn.hutool.core.util.StrUtil;
import com.tyc.wx.common.Constant;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 封装普通线程池使之支持MDC
 *    利用 MDC.getCopyOfContextMap() 获取调用线程上下文映射的副本
 *    通过封装将副本传递给线程池中的线程
 * @author tyc
 * @version 1.0
 * @date 2022-12-27 11:01:15
 */
public class ThreadMdcUtil {
    public static void setTraceIdIfAbsent(){
        if(StrUtil.isEmpty(MDC.get(Constant.TRACE_ID))){
            MDC.put(Constant.TRACE_ID, TraceLogUtils.getTraceId());
        }
    }

    public static void setTraceId(){
        MDC.put(Constant.TRACE_ID, TraceLogUtils.getTraceId());
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context){
        return ()->{
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            }catch (Exception e){
                throw e;
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            }catch (Exception e){
                throw e;
            }finally {
                MDC.clear();
            }
        };
    }

    public static class ThreadPoolTaskExecutorMdcWrapper extends ThreadPoolTaskExecutor {
        @Override
        public void execute(Runnable task) {
            super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public void execute(Runnable task, long startTimeout) {
            super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()), startTimeout);
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public ListenableFuture<?> submitListenable(Runnable task) {
            return super.submitListenable(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
            return super.submitListenable(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }
    }

    public static class ThreadPoolExecutorMdcWrapper extends ThreadPoolExecutor {
        public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                                            RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        public void execute(Runnable task) {
            super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()), result);
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }
    }

    public static class ForkJoinPoolMdcWrapper extends ForkJoinPool {
        public ForkJoinPoolMdcWrapper() {
            super();
        }

        public ForkJoinPoolMdcWrapper(int parallelism) {
            super(parallelism);
        }

        public ForkJoinPoolMdcWrapper(int parallelism, ForkJoinWorkerThreadFactory factory,
                                      Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
            super(parallelism, factory, handler, asyncMode);
        }

        @Override
        public void execute(Runnable task) {
            super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }

        @Override
        public <T> ForkJoinTask<T> submit(Runnable task, T result) {
            return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()), result);
        }

        @Override
        public <T> ForkJoinTask<T> submit(Callable<T> task) {
            return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
        }
    }

}
