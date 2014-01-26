<%@include file="/libs/foundation/global.jsp"%>
<cq:includeClientLib categories="mywebsite.components.hero" />
 
<%@ page import="com.day.cq.commons.Doctype,
                    com.day.cq.wcm.foundation.Image,
                    com.day.cq.wcm.api.components.DropTarget,
                    com.day.cq.wcm.api.components.EditConfig,
                    com.day.cq.wcm.commons.WCMUtils,
                    org.apache.jackrabbit.commons.JcrUtils"
                        %>
 
----------------------------------
 
<b>&nbsp;HERO HOOK COMPONENT:&nbsp;&nbsp;</b>
 
----------------------------------
 
 
<%String displayType = properties.get("displayType", String.class);%>
<b>&nbsp;Display Type:&nbsp;&nbsp;</b><cq:text property="jcr:text" value="<%= displayType %>" />
 
<%String imageurl = properties.get("imageurl", String.class);%>
<b>&nbsp;URL:&nbsp;&nbsp;</b><cq:text property="jcr:text" value="<%= imageurl %>" />
 
<%String imagealttext = properties.get("imagealttext", String.class);%>
<b>&nbsp;Alt Text:&nbsp;&nbsp;</b><cq:text property="jcr:text" value="<%= imagealttext %>" />
 
 
 
<b>&nbsp;Hero Image:</b>
   
<%
        Image img = new Image(resource);
        img.setSelector(".img");
        img.setDoctype(Doctype.fromRequest(request));
        img.setAlt("image alt text");
        img.draw(out); 
%>
 
-------------------------------
 
<b>TITLE:</b>
 
<c:forEach var="title" items="${properties.title}">
${title} 
</c:forEach>
 
-------------------------------
 
<b>SUB TITLE:</b>
 
<c:forEach var="subtitle" items="${properties.subtitle}">
${subtitle} 
</c:forEach>
 
-------------------------------
 
<b>DESCRIPTION:</b>
 
<c:forEach var="description" items="${properties.description}">
${description} 
</c:forEach>
 
-------------------------------
 
<b>FOOT NOTE:</b>
 
<c:forEach var="footnote" items="${properties.footnote}">
${footnote} 
</c:forEach>
 
 
-------------------------------
 
<b>&nbsp;LINKS TAB:</b>
 
<% Node node = null;
    if(resource.adaptTo(Node.class)!=null)
    {
 
    node=resource.adaptTo(Node.class);
 
    PropertyIterator props=null;
    if (node.getProperties()!=null)
        props = node.getProperties();
 
        while (props.hasNext()) {
            Property prop = props.nextProperty();
            String name = prop.getName();
            //System.out.println("property -- "+name);
            String value=null;
            String[] linkFields =null;
 
                if (prop.getDefinition().isMultiple() && (name.equalsIgnoreCase("links"))) {
                    StringBuffer buf = new StringBuffer();
                    //buf.append("Link Type : ");
                    Value[] values = prop.getValues();
                    for (int i = 0; i < values.length; i++) {
                        buf.append(i > 0 ? "," : "");
                        buf.append(values[i].getString());
                    }
 
                    value = buf.toString();
                    String[] tokens = value.split(",");
 
                    for (int i=0;i<tokens.length;i++) 
                        {
                            //System.out.println(" tokens "+tokens[i]);
                            linkFields = tokens[i].split("\\\\");
                            for (int k=0; k<linkFields.length; k++)
                                {
                                //System.out.println(" value of k "+ k + "    linkfield"  + linkFields[k] );
                                if (k==0){
                                    %> <b>Link Type:</b> <%= linkFields[k] %>,<% continue;}
                                if (k==1){
                                    %> <b> Link Text: </b><%= linkFields[k] %>,<%continue;}
                                if (k==2){
                                    %> <b>Link URL: </b><%= linkFields[k] %><%continue;}
                                }
                        }
     
                }
                else {
                    if (name.equalsIgnoreCase("links")){
                value = prop.getString();
                    linkFields = value.split("\\\\");
                            for (int k=0; k<linkFields.length; k++)
                                {
                                //System.out.println(" value of k "+ k + "    linkfield"  + linkFields[k] );
                                if (k==0){
                                    %> <b>Link Type:</b> <%= linkFields[k] %>,<% continue;}
                                if (k==1){
                                    %> <b> Link Text: </b><%= linkFields[k] %>,<%continue;}
                                if (k==2){
                                    %> <b>Link URL: </b><%= linkFields[k] %><%continue;}
                                }
 
                    }
                }
        }
        %>
 
-------------------------------
 
 
<b>OTHER IMAGES:</b>
 
<% 
       for (Node n1 : JcrUtils.getChildNodes(node)){
            String imagePath = n1.getPath().toString();
            Resource imageResource = resourceResolver.getResource(imagePath);
            Node imageNode = imageResource.adaptTo(Node.class);
            ValueMap imageNodeProps = imageResource.adaptTo(ValueMap.class);
            String imageName = null;
            imageName= imageNodeProps.get("fileReference", "None");
 
 
%>
 
<b>Image Path :</b> <%= imageName %>
 
<%
 
       }
    }
 
%>
-------------------------------
<b>**HERO ENDS**</b>
-------------------------------