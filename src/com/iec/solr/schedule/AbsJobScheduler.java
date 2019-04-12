package com.iec.solr.schedule;

import org.apache.log4j.Logger;

/**
 * 调度抽象类，（公共逻辑放这里处理）
 * @author LUBANG713
 * @date 2014-3-21
 */
public abstract class AbsJobScheduler implements JobScheduler {	

	public static Logger logger = Logger.getLogger(AbsJobScheduler.class);
	public final void execute(){
		
		long scheduleId = System.currentTimeMillis();
		long startT = System.currentTimeMillis();
		
//		logger.info(" schedule start scheduleId="+scheduleId+",>>>>>>>>>>");
		schedule();
			
//		logger.info(" schedule end scheduleId="+scheduleId+",cost="+((System.currentTimeMillis()-startT)/1000)+"s");
		
	}

	protected abstract void schedule();
}
