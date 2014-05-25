CQ.form.CustomMultiField = CQ.Ext.extend(CQ.form.CompositeField, {

hiddenField: null,

linkText: null,

linkURL: null,

openInNewWindow: null,

constructor: function (config) {

config = config || {};

var defaults = {
    "border": true,
    "labelWidth": 75,
    "layout": "form"
    };   
config = CQ.Util.applyDefaults(config, defaults);
CQ.form.CustomMultiField.superclass.constructor.call(this, config);
},

initComponent: function () {
  
    CQ.form.CustomMultiField.superclass.initComponent.call(this);
  
    // Hidden field
    this.hiddenField = new CQ.Ext.form.Hidden({
         name: this.name
    });
    this.add(this.hiddenField);
  
    this.linkText = new CQ.Ext.form.TextField({
        cls: "customwidget-1",
        maxLength: 100,
        emptyText: "Enter Title",
        maxLengthText: "A maximum of 100 characters is allowed for the Link Text.",
        width: 335,
        allowBlank: true,
        name : "item",
        listeners: {
        change: {
        scope: this,
        fn: this.updateHidden
    }
    }
    });   
    this.add(this.linkText); 
  
    this.linkURL = new CQ.form.PathField({
        cls: "customwidget-2",
        allowBlank: true,
        emptyText: "Enter Title URL",
        width: 335,
        listeners: {
         change: {
            scope: this,
            fn: this.updateHidden
        },       
        dialogclose: {
            scope: this,
            fn: this.updateHidden
        }
       }
    });
    this.add(this.linkURL);
  
    // Link openInNewWindow
    this.openInNewWindow = new CQ.Ext.form.Checkbox({
        cls: "customwidget-3",
        boxLabel: "New window",
        listeners: {
        change: {
        scope: this,
        fn: this.updateHidden
        },
        check: {
        scope: this,
        fn: this.updateHidden
    }
    }
    });
    this.add(this.openInNewWindow);
  
},

processInit: function (path, record) {   
    this.linkText.processInit(path, record);
    this.linkURL.processInit(path, record);
    this.openInNewWindow.processInit(path, record); 
},

setValue: function (value) { 
    var link = JSON.parse(value);
    this.linkText.setValue(link.text);
    this.linkURL.setValue(link.url);
    this.openInNewWindow.setValue(link.openInNewWindow); 
},
getValue: function () {
return this.getRawValue();
},

getRawValue: function () {   
    var link = {
    "url": this.linkURL.getValue(),
    "text": this.linkText.getValue(),
    "openInNewWindow": this.openInNewWindow.getValue() 
    };   
    return JSON.stringify(link);
},
updateHidden: function () {
this.hiddenField.setValue(this.getValue());
}
});

CQ.Ext.reg("CustomMultiField", CQ.form.CustomMultiField);