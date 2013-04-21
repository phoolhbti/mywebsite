$(document).ready(function() {
   $.getJSON('http://search.twitter.com/search.json?rpp=10&callback=?&q=AdobeMktgCloud' ,function(data){
        for(var i=0;i<data.results.length;i++){
            var tweeter = data.results[i].from_user;
            var tweetText = data.results[i].text;
            var tweetText = tweetText.substring(0, 139);
            tweetText = tweetText.replace(/http:\/\/\S+/g, '<a href="$&" target="_blank">$&</a>');
        tweetText = tweetText.replace(/(@)(\w+)/g, ' $1<a href="http://twitter.com/$2" target="_blank">$2</a>');
        tweetText = tweetText.replace(/(#)(\w+)/g, ' $1<a href="http://search.twitter.com/search?q=%23$2" target="_blank">$2</a>');
            $('#tw').append('<li class="tweet"><div class="tweetImage"><a href="http://twitter.com/'+tweeter+'" target="_blank"><img src="'+data.results[i].profile_image_url+'" width="48" border="0" /></a></div><div class="tweetBody">'+tweetText+'</div></li>');    
        }
   });
    
   function autoScroll() {
       var itemHeight = $('#tw li').outerHeight();
            
       var moveFactor = parseInt($('#tw').css('top')) + itemHeight;
       /* animate the carousel */
       $('#tw').animate(
           {'top' : moveFactor}, 'slow', 'linear', function(){
               /* put the last item before the first item */
             
               $("#tw li:first").before($("#tw li:last"));
               /* reset top position */             
               $('#tw').css({'top' : '-6em'});
       });
   };
   /* make the carousel scroll automatically when the page loads */
   var moveScroll = setInterval(autoScroll, 6000);
});