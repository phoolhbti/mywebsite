<%--

  My Logo Component component.

   This is My Logo Component

--%><%@include file="/apps/mywebsite/global.jsp"%><%
%><%@ page import="com.day.cq.commons.Doctype,
                    com.day.cq.wcm.foundation.Image,
                    com.day.cq.wcm.api.components.DropTarget,
                    com.day.cq.wcm.api.components.EditConfig,
                    com.day.cq.wcm.commons.WCMUtils,
                     com.day.cq.commons.PathInfo,
                    org.apache.sling.api.resource.*,
                    java.util.*,         
org.apache.sling.commons.json.*,         
com.day.cq.wcm.api.PageFilter,         
com.day.cq.wcm.api.NameConstants,
java.io.FileInputStream,
java.io.IOException,
java.util.Properties,         
com.day.cq.wcm.api.Page" %>

<cq:includeClientLib categories="cq.jquery"/>
<%

 List<Map<String,String>> stateItems = new ArrayList<Map<String,String>>();
//Location of the state.json file that contains the list of US states in a JSON Object 
String jsonURL="/apps/mywebsite/resources/json/states.json/jcr:content";
String productProp="/apps/mywebsite/resources/json/product.properties/jcr:content";
Resource res=resourceResolver.getResource(jsonURL); 
Resource resProduct=resourceResolver.getResource(productProp); 
ValueMap jcrProp=res.adaptTo(ValueMap.class); 
ValueMap jcrProduct=resProduct.adaptTo(ValueMap.class); 
String jsonData=jcrProp.get("jcr:data",String.class); 
String propData=jcrProduct.get("jcr:data",String.class); 
out.println("ssssssssssssss"+jcrProduct.get("isbn",(String)null));


if (jsonData != null) {     
    try {         
       JSONArray statesArray = new JSONArray(jsonData);                
       for(int i = 0; i < statesArray.length(); i++) {          
          Map<String,String> stateItem = new HashMap<String,String>();          
          JSONObject jsonObject = statesArray.getJSONObject(i);          
          String stateValue=jsonObject.getString("value");          
          String stateText=jsonObject.getString("text"); 
          String stateLabel=jsonObject.getString("label");               
          stateItem.put("text",stateText);             
          stateItem.put("value",stateValue);       
          stateItem.put("label",stateLabel);          
          stateItems.add(stateItem);         
       }             
  } catch (Exception e) {
    log.error("Invalid JSON:" + jsonData);     } 
} 

pageContext.setAttribute("stateItems",stateItems); 
String pathname=request.getParameter("stateSelector");
if(pathname!=null){
%>
<c:set var="newvar">
<%=pathname%>
</c:set>
<c:forEach items="${stateItems}" varStatus="status" var="item">
<c:if test="${item.value == newvar}">
Product ID:${item.value}<br>
Product Name:${item.text}<br>
Product Lable:${item.label}<br>
</c:if>
</c:forEach>
<%
}


 %>
<!-- JSTL code-->
<div>
<form id="vproduct">
<select id="stateSelector" name="stateSelector" onchange="fun()">
<option id="" value="">Select a Product Name</option>
<c:forEach items="${stateItems}" varStatus="status" var="item">
<option class="${item.newWindow}" id="app" url="${item.url}" value="${item.value}">${item.text}</option>
</c:forEach>
</select>
<sling:include addSelectors="${item.value}"/>
</form>

</div>
<script type="text/javascript">
function fun(){
//alert("inside fun"); 
var selectval = $('#stateSelector').val();
var updatedurl = '<%= currentPage.getParent().getPath()+"/"+currentPage.getName()%>.'+ selectval +'.html';
//alert(updatedurl);
        //   alert("inside fun"+selectval); 
                $.ajax({ 
                url:updatedurl,          
                type:"POST",
                success: function() {
               // alert(selectval);
              //  window.open(updatedurl,'Continue_to_Application','width=200,height=400');
               // event.preventDefault();
                       $('form#vproduct').submit();
                     //  alert("success");   
              
                       },
                error: function() {
                      alert("error");   
                       }
                  }); 
           
         }

</script>