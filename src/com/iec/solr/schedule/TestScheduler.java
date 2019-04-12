package com.iec.solr.schedule;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Controller
@Component("testScheduler")
public class TestScheduler extends AbsJobScheduler{

	@Override
	protected void schedule() {
		
//		System.out.println("this is a test scheduler。。。。。。");

	}
}
