<%@include file="/apps/mywebsite/global.jsp"%>
<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
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
      
       var aDataSet = [
                       ['','','','',''],
                       ['','','','','']
                   ];
                
 
 
 
      jQuery('#dynamic').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
      jQuery('#example').dataTable( {
          "aaData": aDataSet,
          "aoColumns": [
              { "sTitle": "First Name" },
              { "sTitle": "Last Name" },
              { "sTitle": "Address", "sClass": "center" },
              { "sTitle": "Description", "sClass": "center" }
          ]
      } );
 
 
    jQuery('body').hide().fadeIn(5000);
      
jQuery('#submit').click(function() {
    var failure = function(err) {
      //  $(".main").unmask();
        alert("Unable to retrive data "+err);
          
    };
      
      
    //Get the user-defined values to persist in the database
    var myFirst= jQuery('#first').val() ; 
    var myLast= jQuery('#last').val() ; 
    var myDescription= jQuery('#description').val() ; 
    var myAddress= jQuery('#address').val() ; 
      
    var url = location.pathname.replace(".html", "/_jcr_content.persist.json") + "?first="+ myFirst +"&last="+myLast +"&desc="+myDescription +"&phone="+myAddress;
      
    //$(".main").mask("Saving Data...");
  
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
 
 
//Get customer data -- called when the submitget button is clicked
//this method populates the data grid with data retrieved from the //Adobe CQ JCR
jQuery('#submitget').click(function() {
    var failure = function(err) {
          alert("Unable to retrive data "+err);
      };
       
    //Get the query filter value from drop down control
    var filter=   jQuery('#custQuery').val() ; 
    alert("filter---"+filter);
        
    var url = location.pathname.replace(".html", "/_jcr_content.query.json") + "?filter="+ filter;
        
    jQuery.ajax(url, {
        dataType: "text",
        success: function(rawData, status, xhr) {
            var data;
            try {
                data = jQuery.parseJSON(rawData);
               alert("data--"+data);   
                             
                //Set the fields in the forum
                var myXML = data.xml;
 alert("xml is"+myXML);
                var loopIndex = 0; 
 
                //Reference the data grid, clear it, and add new records
                //queried from the Adobe CQ JCR
                 var oTable = jQuery('#example').dataTable();
                 oTable.fnClearTable(true);
 
 
                 //Loop through this function for each Customer element
                 //in the returned XML
                 jQuery(myXML).find('Customer').each(function(){
                         
                    var $field = jQuery(this);
                    var firstName = $field.find('First').text();
                     
                    var lastName = $field.find('Last').text();
                    var Description = $field.find('Description').text();
                    var Address = $field.find('Address').text();     
 
                    //Set the new data 
                    oTable.fnAddData( [
                        firstName,
                        lastName,
                        Address,
                        Description,]
                    );
            
                    });
            
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
        <p class="logo">Adobe CQ JCR Customer Persist/Query Application</p>
    </div>
    <div class="content">
    <div class="main">
    <h1>CQ Data Persist Example</h1>
      
    <form name="signup" id="signup">
     <table>
    <tr>
    <td>
    <label for="first">First Name:</label>
    </td>
     <td>
    <input type="first" id="first" name="first" value="" />
    </td>
    </tr>
    <tr>
    <td>
    <label for="last">Last Name:</label>
    </td>
     <td>
    <input type="last" id="last" name="last" value="" />
    </td>
    </tr>
     <tr>
    <td>
    <label for="address">Address:</label>
    </td>
     <td>
    <input type="address" id="address" name="address" value="" />
    </td>
    </tr>
     <tr>
    <td>
   <label for="description">Description:</label>
    </td>
    <td>
    <select id="description"  >
            <option>Active Customer</option>
            <option>Past Customer</option>  
        </select>
    </td>
    </tr>
     <tr>
    <td>
    <label for="custId">Customer Id:</label>
    </td>
     <td>
    <input type="custId" id="custId" name="custId" value="" readonly="readonly"/>
    </td>
    </tr>
     
</table>
            <div>
                <input type="button" value="Add Customer!"  name="submit" id="submit" value="Submit">
            </div>
        </form>
        </div>
    </div>
     
    <div id="container">
     <form name="custdata" id="custdata">
    
    <h1>Query Customer Data from the AEM JCR</h1>
   <div>
     <select id="custQuery"  >
            <option>All Customers</option>
            <option>Active Customer</option>
            <option>Past Customer</option>    
        </select>
    </div>
    <div id="dynamic"></div>
    <div class="spacer"></div>
 
   <div>
      <input type="button" value="Get Customers!"  name="submitget" id="submitget" value="Submit">
    </div>
   </form>
      
</div>
<h2>This page displays twitter feeds from @AdobeMktgCloud</h2>
<cq:include path="par" resourceType="foundation/components/parsys" />
</body>
</html>