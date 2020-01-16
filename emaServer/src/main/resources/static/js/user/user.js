var vm = new Vue({
	el:'#userForm',
	data:{
		data:{
			username:"",
			email:"",
			gender:""
		},
		tableIns:""
	},
	mounted(){
		this.initForm();
		this.initTable();	
		this.toolbarEvent();
		this.checkboxEvent();		
	}
	,methods:{
		initForm(){
			form.render(null,'test0');
		},initTable(){						
			// 方法渲染：
		  this.tableIns = table.render({
				id:'userlist',
				title:"用户管理",
				elem:'#userTable',
				toolbar: '#toolbar',
				defaultToolbar: ['filter', 'print', 'exports', {
					    title: '提示' // 标题
					    ,layEvent: 'LAYTABLE_TIPS' // 事件名，用于 toolbar 事件中使用
					    ,icon: 'layui-icon-tips' // 图标类名
				}],
				even:true,
				size:'lg',
				loading:false,
				url:baseURL+'/user/list',
				height:500,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,align:'center'
					,templet: row=>'<input type="checkbox" lay-filter="selected" lay-skin="primary">'}					
					,{field: 'id', title: 'ID',edit:'text'}
					,{field: 'username', title: '用户名'}
					,{field: 'phone', title: '电话'}
					,{field: 'email', title: '邮箱'}
					,{field: 'createTime', title: '创建时间'}
					,{field: 'updateTime', title: '修改时间'}
					,{field:'enabled', title: '启动状态', width: 90,align:'center'
					,templet: row=>'<input type="checkbox" name="zzz" lay-filter="enabled" lay-skin="switch" lay-text="开启|关闭">'}					
				]],
				parseData: function(res){ // res 即为原始返回的数据
					console.log(res)
				    return {
				      "code": res.code, // 解析接口状态
				      "msg": res.msg, // 解析提示文本
				      "count": res.data.totalCount, // 解析数据长度
				      "data": res.data.list // 解析数据列表
				    };
				  }
			});
		},
		getParam(){
			console.log(this.data)
			var param = {};
			for(var key in this.data){
				param[key] = this.data[key];
			}
			console.log(param)
			return param;
		},
		refresh(){
			this.tableIns.reload({where:null});
		},
		submit(){			
			this.tableIns.reload({
				where:this.getParam(),
				page:{
					curr:1
				}
			});			
		},
		toolbarEvent(){
			table.on('toolbar(test)', function(obj){
				  var checkStatus = table.checkStatus(obj.config.id);
				  switch(obj.event){
				    case 'add':
				      layer.msg('添加');
				    break;
				    case 'delete':
				      layer.msg('删除');
				    break;
				    case 'update':
				      layer.msg('编辑');
				    break;
				  };
			});
		},
		checkboxEvent(){
			form.on('switch(enabled)', function(data){
				console.log(data.elem);
			});
			form.on('checkbox(selected)',function(data){
				console.log(data.elem);
			});
		}
	}
})