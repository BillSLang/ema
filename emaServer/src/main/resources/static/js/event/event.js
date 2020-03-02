var vm = new Vue({
	el:'#eventForm',
	data:{
		data:{
			name:'',
			description:''
		},
		eData:'',
		tableIns:''
	},
	mounted(){
		this.initForm();
		this.initTable();	
		this.toolbarEvent();
	}
	,methods:{
		initForm(){
			form.render(null,'eventForm');
		},exportData(){
			console.log(this.tableIns.config.cols)
			table.exportFile(this.tableIns.config.id, this.eData, 'xls'); //默认导出 csv，也可以为：xls		}
		},initTable(){						
			// 方法渲染：
		  this.tableIns = table.render({
				id:'eventlist',
				title:'事件管理',
				elem:'#eventTable',
				toolbar: '#toolbar',
				defaultToolbar: ['filter','print',  {
					    title: '提示' // 标题
					    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
					    ,icon: 'layui-icon-tips' // 图标类名
				}],
				even:true,
				size:'sg',
				loading:false,
				url:baseURL+'/event/list',
				height:525,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,type:'checkbox',align:'center'}	
					,{field: 'id',hide:true}
					,{field: 'LAY_INDEX',title:'序号',templet:row=>row.LAY_INDEX,align:'center'}
					,{field: 'name' , title:'名称',align:'center'}
					,{field: 'foodbatch' , title:'一批食品',align:'center'}
					,{field: 'auditor' , title:'审核人',align:'center'}
					,{field: 'subject' , title:'涉事主体',align:'center'}
					,{field: 'reportor' , title:'发布者',align:'center'}
					,{field: 'uri' , title:'资源',align:'center'}
					,{field: 'description', title: '描述',align:'center'}
					,{field: 'auditTime', title: '审核时间',align:'center'}
					,{field: 'status', title: '状态',align:'center',templet:function(row){
						var arr = row.roleNames;
						var audit ='';
						var noAudit ='';
						var failAudit ='';
						audit+='<div class="layui-btn-sm layui-btn layui-btn-normal" style="height:30px;text-align:center;">';
						audit+='已审核';
						audit+='</div>';
						noAudit+='<div class="layui-btn-sm layui-btn layui-btn-warm" style="height:30px;text-align:center;">';
						noAudit+='未审核';
						noAudit+='</div>';
						failAudit+='<div class="layui-btn-sm layui-btn layui-btn-danger" style="height:30px;text-align:center;">';
						failAudit+='审核未通过';
						failAudit+='</div>';
						if(row.status==0)
							return noAudit;
						if(row.status==1)
							return failAudit;
						if(row.status==2)
							return audit;
					}}
				]],
				parseData: function(res){ // res 即为原始返回的数据
				    return {
				      'code': res.code, // 解析接口状态
				      'msg': res.msg, // 解析提示文本
				      'count': res.data.totalCount, // 解析数据长度
				      'data': res.data.list // 解析数据列表
				    };
				  },
				  done:function(res, curr, count){
		                vm.eData=res.data;
		          }
			});
		},getParam(){
			var param = {};
			for(var key in this.data){
				param[key] = this.data[key];
			}
			return param;
		},refresh(){
			this.tableIns.reload({where:null});
		},submit(){			
			this.tableIns.reload({
				where:this.getParam(),
				page:{
					curr:1
				}
			});			
		},toolbarEvent(){
			table.on('toolbar(eventTable)', function(obj){
				  switch(obj.event){
				  	case 'export':
				  		vm.exportData();
				  	break;
				    case 'add':
				    	vm.addevent();
				    break;
				    case 'delete':
				    	if(vm.selectOnceMore())
				    	vm.deleteevent();
				    break;
				    case 'update':
				    	if(vm.selectOnce())
				    		vm.editevent();
				    break;
				    case 'pass':
				    	if(vm.selectOnceMore())
				    		vm.passevent();
				    break;
				    case 'noPass':
				    	if(vm.selectOnceMore())
				    		vm.noPassevent();
				    break;
				  };
			});		
		},selectOnce(){
			var data = table.checkStatus('eventlist').data;
	    	if(data.length !== 1){
	    		layer.msg('请选择一个记录进行编辑')
	    		return false;
	    	}
	    	return true;
		},
		selectOnceMore(){
			var data = table.checkStatus('eventlist').data;
			console.log(data.length)
	    	if(data.length == 0){
	    		layer.msg('请选择至少一个记录')
	    		return false;
	    	}
	    	return true;
		},editevent(){
			layer.open({
				type:2,
				title:'添加品牌',
				content:baseURL+'/event/eventEdit.html/'+table.checkStatus('eventlist').data[0].id,
				area:['500px','500px'],
				end(){
					vm.tableIns.reload();
				}
			})
		},addevent(){
			layer.open({
				type:2,
				title:'添加品牌',
				content:baseURL+'/event/eventEdit.html',
				area:['500px','500px'],
				end(){
					vm.tableIns.reload();
				}
			})
			
		},deleteevent(){
			layer.open({
				title:'提示',
				content:'是否删除该记录',
				btn:['是','否'],
				async yes(index,layero){
			    	var data = table.checkStatus('eventlist').data;
			    	var param = {};
			    	for( var i in data){
			    		console.log(i)
			    		param[i] = data[i].id;
			    	}
					await $.post(baseURL+'/event/delete',param)
					await vm.tableIns.reload();
					layer.close(index);
				}
			})
		},passevent(){
			layer.open({
				title:'提示',
				content:'是否通过记录',
				btn:['是','否'],
				async yes(index,layero){
			    	var data = table.checkStatus('eventlist').data;
			    	var param = {};
			    	for( var i in data){
			    		console.log(i)
			    		param[i] = data[i].id;
			    	}
			    	console.log(param)
					await $.post(baseURL+'/event/pass',param)
					await vm.tableIns.reload();
					layer.close(index);
				}
			})
		},noPassevent(){
			layer.open({
				title:'提示',
				content:'是否不通过记录',
				btn:['是','否'],
				async yes(index,layero){
			    	var data = table.checkStatus('eventlist').data;
			    	var param = {};
			    	for( var i in data){
			    		console.log(i)
			    		param[i] = data[i].id;
			    	}
					await $.post(baseURL+'/event/noPass',param)
					await vm.tableIns.reload();
					layer.close(index);
				}
			})
		}
	}
})