var vm = new Vue({
	el:'#userForm',
	data:{
		data:{
			username:'',
			email:'',
			gender:''
		},
		eData:'',
		tableIns:''
	},
	mounted(){
		this.initForm();
		this.initTable();	
		this.selectEvent();
		this.toolbarEvent();
	}
	,methods:{
		initForm(){
			form.render(null,'userForm');
			
		},
		selectEvent(){
			form.on('select(gender)',function(data){
				vm.data.gender = data.value;
			})
		},exportData(){
			console.log(this.tableIns.config.cols)
			table.exportFile(this.tableIns.config.id, this.tableIns.config.cols, 'xls'); //默认导出 csv，也可以为：xls		}
		},initTable(){						
			// 方法渲染：
		  this.tableIns = table.render({
				id:'userlist',
				title:'用户管理',
				elem:'#userTable',
				toolbar: '#toolbar',
				defaultToolbar: ['filter','print',  {
					    title: '提示' // 标题
					    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
					    ,icon: 'layui-icon-tips' // 图标类名
				}],
				even:true,
				size:'sg',
				loading:false,
				url:baseURL+'/user/list',
				height:525,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,type:'checkbox',align:'center'}	
					,{field: 'id',hide:true}
					,{field: 'LAY_INDEX',title:'序号',width:60,templet:row=>row.LAY_INDEX}
					,{field: 'username', title: '用户名'}
					,{field: 'name' , title:'昵称',width:60}
					,{field: 'phone', title: '电话'}
					,{field: 'email', title: '邮箱'}
					,{field: 'gender',title: '性别',width:60,align:'center'}
					,{field: 'enabled', title: '状态', width: 70,align:'center'
					,templet:function(row){
						var arr = row.roleNames;
						var start ='';
						var close ='';
						start+='<div class="layui-btn-sm layui-btn layui-btn-normal" style="height:30px;width:40px;text-align:center;">';
						start+='启动';
						start+='</div>';
						close+='<div class="layui-btn-sm layui-btn" style="height:30px;width:40px;text-align:center;">';
						close+='关闭';
						close+='</div>';
						
							return row.enabled==0?start:close;
					}}	
					,{field:'roleNames',title: '角色',align:'center',width:130,templet:function(row){
							var arr = row.roleNames;
							var ret ='';
							for(var key in arr){
								ret+='<div class="layui-btn-sm layui-btn layui-btn-danger" style="height:30px;;width:90px;margin:0px 5px 5px 5px;text-align:center;">';
								ret+=arr[key];
								ret+='</div>';
							}
							return ret;
						}
					}
					,{field: 'birthday',title: '生日',width:105}
					,{field: 'createTime', title: '创建时间',width:105}
					,{field: 'updateTime', title: '修改时间',width:105}
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
			table.on('toolbar(userTable)', function(obj){
				  switch(obj.event){
				  	case 'export':
				  		vm.exportData();
				  	break;
				    case 'add':
				    	vm.addUser();
				    break;
				    case 'delete':
				    	if(vm.selectOnceMore())
				    	vm.deleteUser();
				    break;
				    case 'update':
				    	if(vm.selectOnce())
				    		vm.editUser();
				    break;
				  };
			});		
		},selectOnce(){
			var data = table.checkStatus('userlist').data;
	    	if(data.length !== 1){
	    		layer.msg('请选择一个记录进行编辑')
	    		return false;
	    	}
	    	return true;
		},selectOnceMore(){
			var data = table.checkStatus('userlist').data;
			console.log(data.length)
	    	if(data.length == 0){
	    		layer.msg('请选择至少一个记录')
	    		return false;
	    	}
	    	return true;
		},editUser(){
			layer.open({
				type:2,
				title:'添加用户',
				content:baseURL+'/user/userEdit.html/'+table.checkStatus('userlist').data[0].id,
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
		},addUser(){
			layer.open({
				type:2,
				title:'添加用户',
				content:baseURL+'/user/userEdit.html',
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
			
		},deleteUser(){
			layer.open({
				title:'提示',
				content:'是否删除该记录',
				btn:['是','否'],
				async yes(index,layero){
			    	var data = table.checkStatus('userlist').data;
			    	var param = {};
			    	for( var i in data){
			    		console.log(i)
			    		param[i] = data[i].id;
			    	}
					await $.post(baseURL+'/user/delete',param)
					await vm.tableIns.reload();
					layer.close(index);
				}
			})
		}
	}
})