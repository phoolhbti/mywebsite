//------------------------------------------------------------------------------
// default grid showing basic config
 
function getGridPanel() {
    var myData = [
      ['3m Co',71.72,0.02,0.03,'9/1 12:00am'],
      ['Alcoa Inc',29.01,0.42,1.47,'9/1 12:00am'],
      ['Wal-Mart Stores, Inc.',45.45,0.73,1.63,'9/1 12:00am'],
      ['Walt Disney Company (The) (Holding Company)',29.89,0.24,0.81,'9/1 12:00am']
    ];
 
     
    //The Data model that is used to populate the grid
    var store = new CQ.Ext.data.Store({
        proxy: new CQ.Ext.data.MemoryProxy(myData),
        reader: new CQ.Ext.data.ArrayReader({}, [
            {name: 'company'},
            {name: 'price', type: 'float'},
            {name: 'change', type: 'float'},
            {name: 'pctChange', type: 'float'},
            {name: 'lastChange', type: 'date', dateFormat: 'n/j h:ia'}
        ])
    });
     
    //load the data
    store.load();
 
    var gridPanel = new CQ.Ext.grid.GridPanel({
        border:true,
        store: store,
        columns: [
            {
                id:'company',
                header: "Company",
                dataIndex: 'company'
            },{
                header: "Price",
                renderer: CQ.Ext.util.Format.usMoney,
                dataIndex: 'price'
            },{
                header: "Change",
                dataIndex: 'change'
            },{
                header: "% Change",
                dataIndex: 'pctChange'
            },{
                header: "Last Updated",
                renderer: CQ.Ext.util.Format.dateRenderer('m/d/Y'),
                dataIndex: 'lastChange'
            }
        ],
        viewConfig: {
            forceFit: true
        },
        sm: new CQ.Ext.grid.RowSelectionModel({singleSelect:true}),
        iconCls:'icon-grid'
    });
    gridPanel.getColumnModel().defaultSortable = true;
    return gridPanel;
}