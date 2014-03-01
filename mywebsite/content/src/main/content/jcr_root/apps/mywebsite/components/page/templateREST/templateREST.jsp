<%@include file="/apps/mywebsite/global.jsp"%>
<cq:includeClientLib categories="chartjquery" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adobe CQ Restful Service example</title>
<script>
$(document).ready(function() {
 
    $('body').hide().fadeIn(5000);
       
$('#submit').click(function() {
    var failure = function(err) {
       
        alert("Unable to retrive data "+err);
           
    };
    
   //Set the Request to query.json  
    var url = location.pathname.replace(".html", "/_jcr_content.query.json");
   
    $.ajax(url, {
        dataType: "text",
        success: function(rawData, status, xhr) {
            var data;
            try {
                var json = JSON.parse(rawData);
 
                var json2 = jQuery.parseJSON(json.json);
 
 
                //Get the values from JSON encoded Data
                var orginalCity=json2.origin_addresses[0];;
                 
                var destinCity=json2.destination_addresses[0];
                var distance = json2.rows[0].elements[0].distance.text ;
                   
                 
                //Set the fields in the forum
                $('#destination').val(destinCity);
                $('#original').val(orginalCity);
                $('#distance').val(distance);
                
              
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
    
    <div class="main">
    <h1>Adobe Experience Manager Restful Service Example</h1>
    <h3>Calculate the distance between San Francisco CA and Vancouver BC</h3>
       
    <form name="signup" id="signup">
     <table>
     <tr>
    <td>
    <label for="last">Destination City:</label>
    </td>
     <td>
    <input type="destination" id="destination" name="destination" value="" />
    </td>
    </tr>
    <tr>
    <td>
    <label for="last">Original City:</label>
    </td>
     <td>
    <input type="original" id="original" name="original" value="" />
    </td>
    </tr>
     
    <tr>
    <td>
    <label for="first">Distance:</label>
    </td>
     <td>
    <input type="distance" id="distance" name="distance" value="" />
    </td>
    </tr>
    
      
</table>
            <div>
                <input type="button" value="Get Distance!"  name="submit" id="submit" value="Submit">
            </div>
      </form>
      </div>
    </div>
    
</body>
</html>