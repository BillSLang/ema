var vm = new Vue({
	el:'#storeMethodForm',
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
			form.render(null,'storeMethodForm');
		},exportData(){
			console.log(this.tableIns.config.cols)
			table.exportFile(this.tableIns.config.id, this.eData, 'xls'); //默认导出 csv，也可以为：xls		}
		},initTable(){						
			// 方法渲染：
		  this.tableIns = table.render({
				id:'storeMethodlist',
				title:'品牌管理',
				elem:'#storeMethodTable',
				toolbar: '#toolbar',
				defaultToolbar: ['filter','print',  {
					    title: '提示' // 标题
					    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
					    ,icon: 'layui-icon-tips' // 图标类名
				}],
				even:true,
				size:'sg',
				loading:false,
				url:baseURL+'/storeMethod/list',
				height:525,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,type:'checkbox',align:'center'}	
					,{field: 'id',hide:true}
					,{field: 'LAY_INDEX',title:'序号',templet:row=>row.LAY_INDEX,align:'center'}
					,{field: 'name' , title:'名称',align:'center'}
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
			table.on('toolbar(storeMethodTable)', function(obj){
				  switch(obj.event){
				  	case 'export':
				  		vm.exportData();
				  	break;
				    case 'add':
				    	vm.addstoreMethod();
				    break;
				    case 'delete':
				    	if(vm.selectOnceMore())
				    	vm.deletestoreMethod();
				    break;
				    case 'update':
				    	if(vm.selectOnce())
				    		vm.editstoreMethod();
				    break;
				  };
			});		
		},selectOnce(){
			var data = table.checkStatus('storeMethodlist').data;
	    	if(data.length !== 1){
	    		layer.msg('请选择一个记录进行编辑')
	    		return false;
	    	}
	    	return true;
		},
		selectOnceMore(){
			var data = table.checkStatus('storeMethodlist').data;
			console.log(data.length)
	    	if(data.length == 0){
	    		layer.msg('请选择至少一个记录')
	    		return false;
	    	}
	    	return true;
		},editstoreMethod(){
			layer.open({
				type:2,
				title:'添加品牌',
				content:baseURL+'/storeMethod/storeMethodEdit.html/'+table.checkStatus('storeMethodlist').data[0].id,
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
		},addstoreMethod(){
			layer.open({
				type:2,
				title:'添加品牌',
				content:baseURL+'/storeMethod/storeMethodEdit.html',
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
			
		},deletestoreMethod(){
			layer.open({
				title:'提示',
				content:'是否删除该记录',
				btn:['是','否'],
				async yes(index,layero){
			    	var data = table.checkStatus('storeMethodlist').data;
			    	var param = {};
			    	for( var i in data){
			    		console.log(i)
			    		param[i] = data[i].id;
			    	}
					await $.post(baseURL+'/storeMethod/delete',param)
					await vm.tableIns.reload();
					layer.close(index);
				}
			})
		}
	}
})