<%@include file="/libs/foundation/global.jsp"%>

<cq:includeClientLib categories="chartjquery" />
<html>
<head>
<title>Plots AEM Data !!!</title>
  
  
 <style type="text/css">
body { font-family: Verdana, Arial, sans-serif; font-size: 12px; }
h1 { width: 450px; margin: 0 auto; font-size: 12px; text-align: center; }
#placeholder {
    width: 450px;
    height: 200px;
}
.legend table, .legend > div { height: 82px !important; opacity: 1 !important; right: -55px; top: 10px; width: 116px !important; }
.legend table { border: 1px solid #555; padding: 5px; }
#flot-tooltip { font-size: 12px; font-family: Verdana, Arial, sans-serif; position: absolute; display: none; border: 2px solid; padding: 2px; background-color: #FFF; opacity: 0.8; -moz-border-radius: 5px; -webkit-border-radius: 5px; -khtml-border-radius: 5px; border-radius: 5px; }
</style>
  
<script>
alert("first");
$(document).ready(function() {
    $('body').hide().fadeIn(5000);
 alert("body");
    //Make an AJAX call to the OSGi bundle to plot the revenue data
    var url = location.pathname.replace(".html", "/_jcr_content.query.json") ;
       alert(url);  
$.ajax(url, {
        dataType: "text",
        success: function(rawData, status, xhr) {
            var data;
            try {
                data = $.parseJSON(rawData);
                   
                //Set the fields in the forum
                var myXML = data.xml;
              alert(myXML);
                 //Loop through this function for each Customer element
                 //in the returned XML
                 var index = 0 ; //track the index of the chart
                 var revdata = []; 
                 $(myXML).find('Customer').each(function(){
                          
                    var $field = $(this);
                    var revenue = $field.find('rev').text();
 
                  //Populate the array - whose values are plotted in the chart
                    revdata.push([index,revenue]); //push the data into the array
          
                    //increment index
                    index++; 
              });
             //alert(revenue);
             //plot the revenue data   
                $.plot("#placeholder", [revdata], {
            series: {
                bars: {
                    show: true,
                    barWidth: 0.6,
                    align: "center"
                }
            },
            xaxis: {
                mode: "categories",
                tickLength: 0
            }
        }); 
      
            } catch(err) {
                alert(err);
            }
        },
        error: function(xhr, status, err) {
            alert(err);
        } 
    });
     
       
});
</script>
 
 
</head>
<body>
<h2>This page dynamically displays AEM data in a chart</h2>
 
 
    <div id="placeholder"></div>
 
</body>
</html>