package com.pc.user;
    
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;    
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Reference;    
import javax.jcr.Session;   
   
//Sling Imports
import org.apache.sling.api.resource.ResourceResolverFactory ; 
import org.apache.sling.api.resource.ResourceResolver;  
 
//Jackrabbit User APIs
import org.apache.jackrabbit.api.JackrabbitSession ;
import org.apache.jackrabbit.api.security.user.UserManager  ;
import  org.apache.jackrabbit.api.security.user.Authorizable ; 
 
//This is a component so it can provide or consume services
@Component
   
@Service
public class SearchUsersImpl implements SearchUsers {
     
         
/** Default log. */
protected final Logger log = LoggerFactory.getLogger(this.getClass());
               
private Session session;
private java.util.Iterator<Authorizable> users = null ;
     
//Inject a Sling ResourceResolverFactory
@Reference
private ResourceResolverFactory resolverFactory;
 
@Override
public String getCQUsers() {         
 try
 {
  //Invoke the adaptTo method to create a Session 
  ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
  session = resourceResolver.adaptTo(Session.class);
             
  //Create a UserManager instance from the session object
  UserManager userManager = ((JackrabbitSession) session).getUserManager();
 
   users= userManager.findAuthorizables("jcr:primaryType", "rep:User");
 
  while (users.hasNext()) {
   
      Authorizable auth = users.next();
      if (!auth.isGroup()) {
 
          //Get the ID of the user
        String id = auth.getID();
        log.info("Retrieved USER "+id) ;
            }
        }
     
    // Log out
    session.logout();             
    return "All AEM Users are written to the log file" ;  
}
catch(Exception e)
{
         log.info("CQ ERROR: "+e.getMessage())  ; 
}
         
return null;
 }
}