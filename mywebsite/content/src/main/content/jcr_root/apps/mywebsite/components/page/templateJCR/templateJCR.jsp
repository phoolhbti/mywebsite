<%@include file="/libs/foundation/global.jsp"%>
<cq:includeClientLib categories="jquerysamples" />
<cq:includeClientLib categories="jcrtemplate" />
<html>
<head>
<meta charset="UTF-8">
<title>Adobe CQ Persist Page</title>
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
jQuery(document).ready(function() {
       
   jQuery('body').hide().fadeIn(5000);
       
jQuery('#submit').click(function() {
    var failure = function(err) {
             alert("Unable to retrive data "+err);
   };
       
    //Get the user-defined values to persist in the database
    var myFirst= jQuery('#first').val() ; 
    var myLast= jQuery('#last').val() ; 
    var myDescription= jQuery('#description').val() ; 
    var myAddress= jQuery('#address').val() ; 
       
    var url = location.pathname.replace(".html", "/_jcr_content.persist.json") + "?first="+ myFirst +"&last="+myLast +"&desc="+myDescription +"&phone="+myAddress;
       alert("url-->"+url);    
    jQuery.ajax(url, {
        dataType: "text",
        success: function(rawData, status, xhr) {
            var data;
            try {
                data = jQuery.parseJSON(rawData);
                                 
                //Set the fields in the forum
                jQuery('#custId').val(data.pk); 
             } catch(err) {
                failure(err);
            }
        },
        error: function(xhr, status, err) {
            failure(err);
        } 
    });
  });
   
}); // end ready
</script>
</head>
<body>
<div class="wrapper">
    <div class="header">
        <p class="logo">Adobe CQ Data Customer Persist Application</p>
    </div>
    <div class="content">
    <div class="main">
    <h1>CQ Data Persist Example</h1>
        <form name="signup" id="signup">
            <div>
                <label for="first" class="label">First Name:</label>
                <input name="first" type="text" id="first"/>
            </div>
            <div>
                <label for="last" class="label">Last Name:</label>
                <input name="last" type="text" id="last"/>
            </div>
            <div>
                <label for="description" class="label">Description:</label>
                <input name="description" type="text" id="description"/>
            </div>
            <div>
                <label for="address" class="label">Address:</label>
                <input name="address" type="text" id="address"/>
            </div>
            <div>
                <label for="custId" class="label">Customer Id:</label>
                <input name="custId" type="text" id="custId" readonly="readonly"/>
          </div>
               
             
            <div>
                <input type="button" value="Add Customer!"  name="submit" id="submit" value="Submit"/>
            </div>
        </form>
        </div>
    </div>
       
</div>
</body>
</html>