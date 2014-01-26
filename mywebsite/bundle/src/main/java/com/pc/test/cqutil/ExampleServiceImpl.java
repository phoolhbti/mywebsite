package com.pc.test.cqutil;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

@Component
/**
 * The @Component annotation is the only required annotation.
 * If this annotation is not declared for a Java class, the class is not declared as a component.
 * 
 */
@Service(value=EventHandler.class)
/**
 * The @Service annotation defines whether and which service interfaces are provided by the component. This is a class annotation.
 * This annotation is used to declare <service> and <provide> elements of the component declaration. 
 *
 */
@Properties({
	@Property(name= EventConstants.EVENT_TOPIC,value={SlingConstants.TOPIC_RESOURCE_ADDED,SlingConstants.TOPIC_RESOURCE_CHANGED, SlingConstants.TOPIC_RESOURCE_REMOVED}),
	@Property(name=EventConstants.EVENT_FILTER,value="(path=/content/my/root/*/myNode)")
})
public class ExampleServiceImpl implements EventHandler {
	
 final String[] eventProps = { "resourceAddedAttributes", "resourceChangedAttributes", "resourceRemovedAttributes" };

 public void handleEvent(org.osgi.service.event.Event event) {
  for (String eventProp : eventProps){
   String[] props = (String[])event.getProperty(eventProp);
  
 }      
}
}