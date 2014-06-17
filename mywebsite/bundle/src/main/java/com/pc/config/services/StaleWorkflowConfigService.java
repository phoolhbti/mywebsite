package com.pc.config.services;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

@Component(label = "Stale Workflow Service configuration", description = "Stale Workflow Service configuration Description",enabled = true, metatype = true, immediate = true)
@Service(StaleWorkflowConfigService.class)
public class StaleWorkflowConfigService {
	@Property(value="param one default value", label = "Sample Parameter", description = "Example of a component parameter")  
    private static final String SAMPLE_PARAM_NAME = "param.one"; 
	@Property(label="Quartz Cron Expression",description="Quartz Scheduler specific cron expression. Do not put unix cron expression", value="0 0/5 * * * ?")
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
