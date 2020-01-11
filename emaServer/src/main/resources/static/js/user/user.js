var vm = new Vue({
	el:'#userForm',
	data:{
		
	},
	mounted(){
		this.initForm();
		this.initTable();		
	}
	,methods:{
		initForm(){
			form.render();
		}
		,initTable(){						
			// 方法渲染：
			table.render({
				title:"用户管理",
				elem:'#userTable',
				toolbar: '<div><span class="title">用户管理</span></div>',
				defaultToolbar:['filter','exports',{
					title:'新建',
					icon:'layui-icon-add-1'					
				},{
					title:'编辑',
					icon:'layui-icon-form'					
				},{
					title:'删除',
					icon:'layui-icon-delete'
				}],text:{
					none:'暂无相关数据'
				},
				skin:'row',
				even:true,
				size:'sm',
				loading:false,
				url:baseURL+'/user/list',
				height:500,
				page:true,
				cols:  [[ // 标题栏
					{field:'all',title:'全选',width:60,type:'checkbox'}
					,{field: 'id', title: 'ID',edit:'text'}
					,{field: 'username', title: '用户名'}
					,{field: 'phone', title: '电话'}
					,{field: 'email', title: '邮箱'}
					,{field: 'createTime', title: '创建时间'}
					,{field: 'updateTime', title: '修改时间'}
				]],
				parseData: function(res){ //res 即为原始返回的数据
					console.log(res)
				    return {
				      "code": res.code, //解析接口状态
				      "msg": res.msg, //解析提示文本
				      "count": res.data.totalCount, //解析数据长度
				      "data": res.data.list //解析数据列表
				    };
				  }
			});
		}
	}
})