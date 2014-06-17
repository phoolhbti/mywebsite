<%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Text-Image component

  Combines the text and the image component

--%><%@ page import="com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.WCMMode,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.foundation.Image" %><%
%><%@include file="/apps/mywebsite/global.jsp"%><%
  %>
  <cq:includeClientLib categories="barChartjquery" />
   <div>

<style>
#signup .indent label.error {
  margin-left: 0;
}
#signup label.error {
  font-size: 0.8em;
  color: #F00;
  font-weight: bold;
  display: block;
  margin-left: 215px;
}
#signup  input.error, #signup select.error  {
  background: #FFA9B8;
  border: 1px solid red;
}
</style>
<script>
 
   
$(document).ready(function() {
   
    $('#mbeancont').hide().fadeIn(5000);
          
$('#submit').click(function() {
    var failure = function(err) {
             alert("Unable to retrive data "+err);
   };
   
 
   var claimId = 0; 
   
    //Use JQuery AJAX request to post data to a Sling Servlet
    $.ajax({
         type: 'POST',    
         url:'/bin/getCount',
         data:'id='+ claimId,
         success: function(msg){
  
           var myMsg = msg; 
 
 
            $('#ClaimNum').val(myMsg); 
 
         }
     });
  });
      
}); // end ready
</script>
</head>
     
<title>Adobe AEM MBean Page</title>
     
<div id="mbeancont">
       
            
<h1>Adobe CQ MBean Form</h1>
           
 
           
<form method="#">
             
 <table border="1" align="left">
   
 <tr>
 <td>
<label for="ClaimNum" id="ClaimNumLabel" >A. Number of Stale Workflow items</label>
 </td>
 <td>
 <input id="ClaimNum" name="ClaimNum" type="text" value="">
 </td>
 </tr> 
 
 <tr>
 <td></td>
 <td>
<input type="button" value="Submit"  name="submit" id="submit" value="Submit">
  
 </td>
   
 </tr>     
 </table>
  
</form>
</div>
</div>