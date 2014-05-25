package com.pc.event;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Property;

@Component(immediate=true)
@Service
@Property(name="scheduler.expression", value="0 0/10 * * * ?")
public class StaleWorkflowScheduler implements java.lang.Runnable {
	  public void run() {
		    // code to execute goes here
		  }
		}