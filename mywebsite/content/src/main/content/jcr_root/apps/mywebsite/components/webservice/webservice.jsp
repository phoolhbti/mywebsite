<%@include file="/apps/mywebsite/global.jsp"%>
<h1><%= properties.get("title", currentPage.getTitle()) %></h1>
 
<h2>Here is an pc service created from Apache CXF Java proxy classes:</h2>
<%
  
com.pc.ws.Weather weather = new com.pc.ws.Weather(); 
 
com.pc.ws.WeatherSoap wsoap = weather.getWeatherSoap(); 
  
com.pc.ws.WeatherReturn wr = wsoap.getCityWeatherByZIP("95101");
  
 
%>
  
<h2>The following describes the weather for 95101 ZIP Code</h2>
<h3><%= "The city is " +wr.getCity()%></h3>
<h3><%= "The state is " +wr.getState()%></h3>
<h3><%= "The description is " +wr.getDescription()%></h3>
<h3><%= "The wind is " +wr.getWind()%></h3>
<h3><%= "The current temp is " +wr.getTemperature()%></h3>
<h3><%= "The current Humidity is " +wr.getRelativeHumidity()%></h3>