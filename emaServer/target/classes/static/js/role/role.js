var vm = new Vue({
	el:'#roleForm',
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
			form.render(null,'roleForm');
		},exportData(){
			console.log(this.tableIns.config.cols)
			table.exportFile(this.tableIns.config.id, this.eData, 'xls'); //默认导出 csv，也可以为：xls		}
		},initTable(){						
			// 方法渲染：
		  this.tableIns = table.render({
				id:'rolelist',
				title:'角色管理',
				elem:'#roleTable',
				toolbar: '#toolbar',
				defaultToolbar: ['filter','print',  {
					    title: '提示' // 标题
					    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
					    ,icon: 'layui-icon-tips' // 图标类名
				}],
				even:true,
				size:'sg',
				loading:false,
				url:baseURL+'/role/list',
				height:525,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,type:'checkbox',align:'center'}	
					,{field: 'id',hide:true}
					,{field: 'LAY_INDEX',title:'序号',templet:row=>row.LAY_INDEX,align:'center'}
					,{field: 'name' , title:'角色',align:'center'}
					,{field: 'description', title: '描述',align:'center'}
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
			table.on('toolbar(roleTable)', function(obj){
				  switch(obj.event){
				  	case 'export':
				  		vm.exportData();
				  	break;
				    case 'add':
				    	vm.addRole();
				    break;
				    case 'delete':
				    	if(vm.selectOnceMore())
				    	vm.deleteRole();
				    break;
				    case 'update':
				    	if(vm.selectOnce())
				    		vm.editRole();
				    break;
				  };
			});		
		},selectOnceMore(){
			var data = table.checkStatus('rolelist').data;
			console.log(data.length)
	    	if(data.length == 0){
	    		layer.msg('请选择至少一个记录')
	    		return false;
	    	}
	    	return true;
		},selectOnce(){
			var data = table.checkStatus('rolelist').data;
	    	if(data.length !== 1){
	    		layer.msg('请选择一个记录进行编辑')
	    		return false;
	    	}
	    	return true;
		},editRole(){
			layer.open({
				type:2,
				title:'添加角色',
				content:baseURL+'/role/roleEdit.html/'+table.checkStatus('rolelist').data[0].id,
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
		},addRole(){
			layer.open({
				type:2,
				title:'添加角色',
				content:baseURL+'/role/roleEdit.html',
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
			
		},deleteRole(){
			layer.open({
				title:'提示',
				content:'是否删除该记录',
				btn:['是','否'],
				async yes(index,layero){
			    	var data = table.checkStatus('rolelist').data;
			    	var param = {};
			    	for( var i in data){
			    		console.log(i)
			    		param[i] = data[i].id;
			    	}
			    	console.log(param)
					await $.post(baseURL+'/role/delete',param)
					await vm.tableIns.reload();
					layer.close(index);
				}
			})
		}
	}
})