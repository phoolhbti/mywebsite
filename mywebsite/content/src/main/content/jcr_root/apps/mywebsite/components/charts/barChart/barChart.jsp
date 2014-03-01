<%@include file="/apps/mywebsite/global.jsp"%>

<cq:includeClientLib categories="barChartjquery" />
 
  
<script>
//alert("first");
$(function() {
   //$('body').hide().fadeIn(5000);
 //alert("body");
    //Make an AJAX call to the OSGi bundle to plot the revenue data
  //  alert(location);
    var url = "/apps/services/customers.json";
     //alert("url--"+url);    
$.ajax(url, {
        dataType: "text",
        success: function(rawData, status, xhr) {
            var data;
            try {
                data = $.parseJSON(rawData);
                   
                //Set the fields in the forum
                var myXML = data.xml;
           //   alert(myXML);
                 //Loop through this function for each Customer element
                 //in the returned XML
                 var index = 0 ; //track the index of the chart
                 var revdata = []; 
                 $(myXML).find('Customer').each(function(){
                          
                    var $field = $(this);
                    var revenue = $field.find('rev').text();
// alert(revenue);
                  //Populate the array - whose values are plotted in the chart
                    revdata.push([index,revenue]); //push the data into the array
          
                    //increment index
                    index++; 
              });
            // alert(revdata);
          //  revdata= [ ["January", 10], ["February", 8], ["March", 4], ["April", 13], ["May", 17], ["June", 9] ];
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

<div id="content">
        <div class="demo-container">
            <div id="placeholder" class="demo-placeholder"></div>
        </div>
</div>