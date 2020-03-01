var vm = new Vue({
	el:'#authorizationForm',
	data:{
		data:{
			username:'',
			roleName:'',
			permissionName:''
		},
		eData:'',
		tableIns:""
	},
	mounted(){
		this.initForm();
		this.initTable();	
		this.toolbarEvent();
	}
	,methods:{
		initForm(){
			form.render(null,'authorizationForm');
		},exportData(){
			console.log(this.tableIns.config.cols)
			table.exportFile(this.tableIns.config.id, this.eData, 'xls'); // 默认导出
																			// csv，也可以为：xls
																			// }
		},initTable(){						
			// 方法渲染：
		  this.tableIns = table.render({
				id:'authorizationlist',
				title:"授权信息",
				elem:'#authorizationTable',
				toolbar: '#toolbar',
				defaultToolbar: ['filter','print',  {
					    title: '提示' // 标题
					    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
					    ,icon: 'layui-icon-tips' // 图标类名
				}],
				even:true,
				size:'sg',
				loading:false,
				url:baseURL+'/authorization/list',
				height:525,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,type:'checkbox',align:'center'}	
					,{field: 'id',hide:true}
					,{field: 'LAY_INDEX',title:'序号',templet:row=>row.LAY_INDEX,align:'center',width:60}
					,{field: 'roleName' , title:'角色',align:'center',width:130}
					,{field: 'userNames', title: '用户名',align:'center',templet:function(row){
							var arr = row.userNames;
							var ret ='';
							for(var key in arr){
								ret+='<div class="layui-btn-sm layui-btn layui-btn-normal" style="height:30px;;width:105px;margin:0px 5px 5px 5px;text-align:center;">';
								ret+=arr[key];
								ret+='</div>';
							}
							return ret;
						}
					}
					,{field: 'permissionNames', title: '权限',align:'center',templet:function(row){
							var arr = row.permissionNames;
							var ret ='';
							for(var key in arr){
								ret+='<div class="layui-btn-sm layui-btn layui-btn-danger" style="height:30px;;width:90px;margin:0px 5px 5px 5px;text-align:center;">';
								ret+=arr[key];
								ret+='</div>';
							}
							return ret;
						}
					}

				]],
				parseData:function(res){
					return {
				      "code": res.code, // 解析接口状态
				      "msg": res.msg, // 解析提示文本
				      "count": res.data.totalCount, // 解析数据长度
				      "data": res.data.list // 解析数据列表
					}
				  },
				done:function(res, curr, count){
		                vm.eData=res.data;
		          }
			});
		},authorize(){
			layer.open({
				type:2,
				title:'授权',
				content:baseURL+'/authorization/authorizationEdit.html/'+
						table.checkStatus('authorizationlist').data[0].id+
						","+
						table.checkStatus('authorizationlist').data[0].username,
				area:['515px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
		},getParam(){
			var param = {};
			console.log(param)
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
			table.on('toolbar(authorizationTable)', function(obj){
				  switch(obj.event){
				  	case 'export':
				  		vm.exportData();
				  	break;
				  	case 'authorization':
				  		if(vm.selectOnce())
				  			vm.authorize();
				  	break;
				  };
			});		
		},selectOnce(){
			var data = table.checkStatus('authorizationlist').data;
			console.log(data[0])
	    	if(data.length !== 1){
	    		layer.msg("请选择一个记录进行编辑")
	    		return false;
	    	}
	    	return true;
		}
	}
})