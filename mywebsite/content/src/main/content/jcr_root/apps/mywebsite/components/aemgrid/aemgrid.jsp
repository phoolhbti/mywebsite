<%@include file="/apps/mywebsite/global.jsp"%><%
 
    Node node = resource.adaptTo(Node.class);
    // load properties
    int width = properties.get("width", 600);
    int height = properties.get("height", 300);
    boolean docked = properties.get("docked", false);
 
%>
<h3>Exercise 5: Grid Overview</h3><%
%><p>Learn about:
    <ul>
        <li>The grid config</li>
        <li>The store config</li>
        <li>The expected data format
<code><pre>
{
    results: 3,
    root: [
        { 'id': 1, 'firstname': 'Bill', occupation: 'Gardener' },
        { 'id': 2, 'firstname': 'Frank', occupation: 'Programmer' },
        { 'id': 3, 'firstname': 'Ben' , occupation: 'Horticulturalist' }
    ]
}
</pre></code>
        The above data would require the following store config to consume:
<code><pre>
var store = new CQ.Ext.data.JsonStore({
    reader: {
        totalProperty:"results",
        root:"root",
        id:"id"
    },
    url:"/content/mywebsite/en/test.json"
});
</pre></code>
        </li>
    </ul>
</p>
 <script type="text/javascript" src="/apps/mywebsite/components/aemgrid/defaultgrid.js"></script> 
 <script type="text/javascript"> 
 
    var grid = CQ.Ext.getCmp("<%= node.getName() %>-grid");
    if (!grid) {
 
        var gridPanel = getGridPanel();
 
        grid = new CQ.Ext.Window({
            id:"<%= node.getName() %>-grid",
            title:"Grid Example 24",
            layout:"fit",
            hidden:true,
            collapsible:true,
            renderTo:"CQ",
            width:<%= width %>,
            height:<%= height %>,
            x:<%= docked ? 0 : 220 %>,
            y:<%= docked ? 0 : 200 %>,
            closeAction:'hide',
            items: gridPanel,
            listeners: {
                beforeshow: function() {
                    gridPanel.getStore().load();
                }
            },
            buttons:[{
                text:"Close",
                handler: function() {
                    grid.hide();
                }
            },{
                text:"Dock",
                handler: function() {
                    grid.setPosition(0,0);
                }
            }]
        });
        grid.show();
    } else {
        grid.setWidth(<%= width %>);
        grid.setHeight(<%= height %>);
        grid.setPosition(<%= docked ? 0 : 500 %>,<%= docked ? 0 : 100 %>);
        grid.show();
    }
 
</script>