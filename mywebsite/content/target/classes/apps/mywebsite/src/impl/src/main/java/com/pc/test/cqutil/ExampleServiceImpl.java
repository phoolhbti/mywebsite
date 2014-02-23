package com.pc.test.cqutil;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

@Component
@Service(value=EventHandler.class)
@Properties({
 @Property(name= EventConstants.EVENT_TOPIC,value= {SlingConstants.TOPIC_RESOURCE_ADDED,SlingConstants.TOPIC_RESOURCE_CHANGED, SlingConstants.TOPIC_RESOURCE_REMOVED}),
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