var vm = new Vue({
	el:'#foodForm',
	data:{
		data:{
			name:'',
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
			form.render(null,'foodForm');
			
		},
		exportData(){
			console.log(this.tableIns.config.cols)
			table.exportFile(this.tableIns.config.id, this.eData, 'xls'); //默认导出 csv，也可以为：xls		}
		},initTable(){						
			// 方法渲染：
		  this.tableIns = table.render({
				id:'foodlist',
				title:'用户管理',
				elem:'#foodTable',
				toolbar: '#toolbar',
				defaultToolbar: ['filter','print',  {
					    title: '提示' // 标题
					    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
					    ,icon: 'layui-icon-tips' // 图标类名
				}],
				even:true,
				size:'sg',
				loading:false,
				url:baseURL+'/food/list',
				height:525,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,type:'checkbox',align:'center'}	
					,{field: 'id',hide:true}
					,{field: 'LAY_INDEX',title:'序号',width:60,templet:row=>row.LAY_INDEX,align:'center'}
					,{field: 'name' , title:'名字',width:130,align:'center'}
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
			table.on('toolbar(foodTable)', function(obj){
				  switch(obj.event){
				  	case 'export':
				  		vm.exportData();
				  	break;
				    case 'add':
				    	vm.addFood();
				    break;
				    case 'delete':
				    	vm.deleteFood();
				    break;
				    case 'update':
				    	if(vm.selectOnce())
				    		vm.editFood();
				    break;
				  };
			});		
		},selectOnce(){
			var data = table.checkStatus('foodlist').data;
	    	if(data.length !== 1){
	    		layer.msg('请选择一个记录进行编辑')
	    		return false;
	    	}
	    	return true;
		},editFood(){
			layer.open({
				type:2,
				title:'添加用户',
				content:baseURL+'/food/foodEdit.html/'+table.checkStatus('foodlist').data[0].id,
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
		},addFood(){
			layer.open({
				type:2,
				title:'添加用户',
				content:baseURL+'/food/foodEdit.html',
				area:['300px','400px'],
				end(){
					vm.tableIns.reload();
				}
			})
			
		},deleteFood(){
			layer.open({
				title:'提示',
				content:'是否删除该记录',
				btn:['是','否'],
				yes:function(index,layero){
			    	var data = table.checkStatus('foodlist').data;
			    	var param = {};
			    	for( var i in data){
			    		console.log(i)
			    		param[i] = data[i].id;
			    	}
			    	console.log(param)
					$.post(baseURL+'/food/delete',param)
					vm.tableIns.reload();
					layer.close(index);
				}
			})
		}
	}
})