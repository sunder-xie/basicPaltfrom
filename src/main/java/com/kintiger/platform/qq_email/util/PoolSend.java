package com.kintiger.platform.qq_email.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Title: 邮件送之线程池
 * Description: TestDemo
 * @author: xg.chen
 * @date:2017年2月23日 下午2:23:04
 */
public class PoolSend {
	BlockingQueue<Runnable> workQueue;//任务队列
	ExecutorService executorService;//线程池接口
	
	//构造线程方法
	public PoolSend(){
		//构造无界的任务队列，资源足够，理论可以支持无限个任务
		workQueue = new LinkedBlockingQueue<Runnable>();
		executorService = new ThreadPoolExecutor(2, 10, 30,
				TimeUnit.SECONDS, workQueue,
				new ThreadPoolExecutor.CallerRunsPolicy());
	}
	//将任务放到线程池中
	public void send(Runnable task){
		System.out.println("Pool Send sending mail...");
		executorService.execute(task);
	}
	//关闭线程池
	public void close(){
		executorService.shutdown();
	}
}
