package com.luoxiang.weibo.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * packageName:	    com.luoxiang.weibo.utils
 * className:	    ThreadManager
 * author:	        Luoxiang
 * time:	        2017/2/4	14:06
 * desc:	        线程池动态代理类
 *
 * svnVersion:
 * upDateAuthor:    Vincent
 * upDate:          2017/2/4
 * upDateDesc:      TODO
 */

public class ThreadManager {

    private static ThreadPoolProxy mNormalPool = new ThreadPoolProxy(1,3,5*1000);

    public static ThreadPoolProxy getNormalPool() {
        return mNormalPool;
    }

    /**
     * 线程池的代理类
     */
    public static class ThreadPoolProxy {
        //核心工作线程大小(几条核心工作线程)
        private final int                mCorePoolSize;
        //最大工作线程大小
        private final int                mMaximumPoolSize;
        //线程的保持时间
        private final long               mKeepAliveTime;
        //线程池
        private       ThreadPoolExecutor mPool;


        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.mCorePoolSize = corePoolSize;
            this.mMaximumPoolSize = maximumPoolSize;
            this.mKeepAliveTime = keepAliveTime;
        }
        private void init() {
            if (mPool == null || mPool.isShutdown()) {
                //核心工作线程大小(几条核心工作线程)
                int corePoolSize = 1;
                //最大工作线程大小
                int maximumPoolSize = 3;
                //线程的保持时间
                long keepAliveTime = 5 * 1000;
                //线程的保持时间的单位
                TimeUnit unit = TimeUnit.MILLISECONDS;
                //线程队列
                BlockingQueue<Runnable> workQueue;
                workQueue = new ArrayBlockingQueue(3); //参数3 默认可以存放三个消息 first in first out 先进先出的模式
                //                workQueue = new SynchronousQueue();//只有一个可以执行 执行完成之后才加入后一个
                //                workQueue = new LinkedBlockingQueue();//默认没有参数 没有参数的话 无限大队列 有参数的话 传入的int参数就是最大的队列 现进先出模式

                //线程工厂(一般使用默认的)
                ThreadFactory threadFactory = Executors.defaultThreadFactory();
                //捕获异常的消息机制
                RejectedExecutionHandler handler;
                //                handler = new ThreadPoolExecutor.DiscardOldestPolicy();//如果这个线程池没有挂掉 就把线程队列的头取出来 然后再把当前的放入队列中
                //                handler = new ThreadPoolExecutor.AbortPolicy();//抛出异常 谁调用 谁捕获异常
                //                handler = new ThreadPoolExecutor.CallerRunsPolicy();//直接执行这个任务 runnable 在调用的线程执行 不经过线程池管理
                handler = new ThreadPoolExecutor.DiscardPolicy();//不作为
                mPool = new ThreadPoolExecutor(mCorePoolSize,
                                               mMaximumPoolSize,
                                               mKeepAliveTime,
                                               unit,
                                               workQueue,
                                               threadFactory,
                                               handler);
            }
        }

        /**
         * 执行任务
         * @param task 需要执行的任务
         *             这个是没有返回值的执行任务的方法
         */
        public void execute(Runnable task){
            init();
            //执行任务
            mPool.execute(task);
        }

        /**
         * 执行任务
         * @param task 需要执行的任务
         * @return 返回结果 可以对结果做操作 比如停止任务执行
         */
        public Future<?> submit(Runnable task){
            init();
            //执行任务 可以直接返回结果
            return mPool.submit(task);
        }

        public void remove(Runnable task){
            mPool.remove(task);
        }
    }
}
