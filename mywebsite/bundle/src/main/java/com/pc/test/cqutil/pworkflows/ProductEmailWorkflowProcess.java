package com.pc.test.cqutil.pworkflows;


import java.util.List;
import java.util.ArrayList;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.HtmlEmail;

import javax.jcr.Node;

import javax.jcr.PathNotFoundException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

//import org.apache.jackrabbit.value.ValueFactoryImpl;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.Constants;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.Route;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

/**
 * This process will Send Email file as expected extention
 *
 */



@Component
@Service
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "ProductEmailWorkflowProcess workflow process implementation."),
        @Property(name = Constants.SERVICE_VENDOR, value = "PC"),
        @Property(name = "process.label", value = "ProductEmailWorkflowProcess Workflow Process")})
public class ProductEmailWorkflowProcess implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(ProductEmailWorkflowProcess.class);
    ArrayList<InternetAddress> emailRecipients = new ArrayList<InternetAddress>();
   
   @Reference(policy = ReferencePolicy.DYNAMIC)
    private MessageGateway<HtmlEmail> messageGateway;

   
    @Reference(policy = ReferencePolicy.STATIC)
    private MessageGatewayService messageGatewayService;
    public void execute(WorkItem workItem, WorkflowSession workflowSession,MetaDataMap metaData)
     throws WorkflowException {
      
        log.debug("Product Email Sender workflow");
        HtmlEmail email = new HtmlEmail();
        WorkflowData workflowData = workItem.getWorkflowData();
        Session session = workflowSession.getSession();
        
        List<Route> routes = null;
        String path = workflowData.getPayload().toString();
        try {
            //metaData.remove("PROCESS_ARGS");
             String fileExtention = metaData.get("PROCESS_ARGS", String.class);
             if (fileExtention != null )
                  log.info("meta data "+fileExtention);
             else
                 fileExtention="flv";
                  log.info("meta data data ");
              //////////////////email code///////////////////
              emailRecipients.add(new InternetAddress("phoolhbti@gmail.com") );
              email.setTo(emailRecipients);
              email.setSubject("This is subject");
              email.setHtmlMsg( "Email testing is on..");
             

              //Check the logs to see that messageGatewayService is not null
              log.info("messageGatewayService : " + messageGatewayService);

              messageGateway = messageGatewayService.getGateway(HtmlEmail.class);

              //Check the logs to see that messageGateway is not null
              log.info("messageGateway : " + messageGateway);

              messageGateway.send(email);
             ///////////////////////////////////////// Email End/////////////////
           routes = workflowSession.getRoutes(workItem);
           Node node = (Node) session.getItem(path);
           log.info("Page Name on ie Node Name:"+node.getName());
           log.info("Page Name on ie Node Path :"+node.getPath());
           
            session.save();
            workflowSession.complete(workItem, (Route) routes.get(0));
           
        } catch (PathNotFoundException e) {
          log.info("PathNotFoundException :"+e.getMessage());
            throw new WorkflowException(e);
           
        } catch (RepositoryException e) {
           log.info("RepositoryException :"+e.getMessage());
            throw new WorkflowException(e);
        }
        catch(Exception e){
        log.info("Exception :"+e.getMessage());
            e.printStackTrace();
            
        }
    }
 }