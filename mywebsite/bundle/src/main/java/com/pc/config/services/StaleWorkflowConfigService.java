package com.pc.config.services;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;

@Component(label = "Stale Workflow Service configuration", description = "Stale Workflow Service configuration Description", metatype = true, immediate = true)
public class StaleWorkflowConfigService {
	@Property(name="param.one", value="param one default value", label = "Sample Parameter", description = "Example of a component parameter")  
    private static final String SAMPLE_PARAM_NAME = "param.one"; 
	@Property(label="Quartz Cron Expression",description="Quartz Scheduler specific cron expression. Do not put unix cron expression",name="scheduler.expression", value="0 0/5 * * * ?")
	private static final String QUARTZ_EXP = "scheduler.expression"; 
	@Activate
    protected void activate(final Map<String, Object> props) {
        this.update(props);
    }

    @Modified
    protected void update(final Map<String, Object> props) {        
        System.out.println(props.get(SAMPLE_PARAM_NAME));
    }

}
