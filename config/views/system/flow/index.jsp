<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">

    Ext.onReady(function(){ 

    	var store = Ext.create('Ext.data.Store', {
    		model: 'system.Flow',
    		proxy: {
    			type: 'ajax',
    			url: '${WEB_PATH }/system/flow/getProcessList.do',
    			reader: {
    				type: 'json'
    			}
    		},
    		autoLoad: true
    	});


    	var btn_re = Ext.create('Ext.Button', {
    		text: '刷新',
    		iconCls: 'ico_001',
    		handler: function() {
    			store.load();
    		}
    	});
    	
    	
    	var btn_publish = Ext.create('Ext.Button', {
    		text: '发布',
    		iconCls: 'ico_014',
    		handler: function() {
    			Ext.MessageBox.confirm('消息', '您确认重新发布所有流程?', function(btn) {
					if (btn == 'yes') {
						var maskBox = showMask();//显示遮盖提示层
						
						Ext.Ajax.request({
							url: '${WEB_PATH }/system/flow/publish.do',
							params: {
							},
							success: function(response) {
								var result = Ext.JSON.decode(response.responseText);
									Ext.tip.msg('消息', result.msg);
								store.load();
								maskBox.close();
							}
						});
					}
        		});    			
        	}
    	});
    	
    	var btn_show = Ext.create('Ext.Button', {
    		disabled: true,
    		text: '流程图',
    		iconCls: 'ico_015',
    		handler: function() {
	            var flowId = getGridId(grid);
	                
    			var win = Ext.create('Ext.window.Window', {
    				autoDestroy: true,
    				title: '流程图',
    				height : 350,
    				width: 340,
    				modal: true,
    				border: false,
    				resizable: false,
    				loader: {
    					url: '${WEB_PATH }/system/flow/view/showflow.do',
    					loadMask: true,
    					scripts: true,
    					autoLoad: true
    				},
    				buttonAlign : 'center',
    				buttons : [{
    					text : '取 消',
    					iconCls: 'ico_02',
    					handler : function() {
    						win.close();
    					}
    				} ]
    			}).show();
    		}
    	});
    	
    	

    	var grid = Ext.create('Ext.grid.Panel', {
    		id: 'grid_flow',
    		store: store, 
    		border: false,
    		width: '100%', 
   	        height: '100%', 
    		layout: 'fit',
    		region: 'center',
    		disableSelection: false,
            columnLines: true,
    		loadMask: true,
    		renderTo: 'div_flow_index',
    		multiSelect: false,
    		viewConfig : {
    	        forceFit : true,
                enableTextSelection: true
    		},
    		tbar: [btn_re, '-', btn_publish, '-', btn_show],
    		columns: [{
    			xtype: 'rownumberer',
                width: 40,
                align: 'center',
                sortable: false,
                resizable: true
            },{
    			id: 'id',
    			header: 'Id',
    			dataIndex: 'id',
    			hidden: true
    		},
    		{
    			header: '流程名称',
    			flex: 1,
    			dataIndex: 'name'
    		}
    		,
    		{
    			header: '定义ID',
    			flex: 1,
    			dataIndex: 'deploymentId'
    		}
    		,
    		{
    			header: 'key',
    			flex: 1,
    			dataIndex: 'key'
    		}
    		,
    		{
    			header: '流程版本',
    			flex: 1,
    			dataIndex: 'version'
    		}
    		,
    		{
    			header: 'resourceName',
    			flex: 1,
    			dataIndex: 'resourceName'
    		},
    		{
    			header: 'diagramResourceName',
    			flex: 1,
    			dataIndex: 'diagramResourceName'
    		}]
    	});
    	
    	grid.getSelectionModel().on('selectionchange', function(selModel, selections) {
    		btn_show.setDisabled(selections.length === 0);
    	});

    	});
    </script>
<div id="div_flow_index"></div>
