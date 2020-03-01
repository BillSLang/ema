var vm = new Vue({
	el:'#addForm',
	data:{
		authorization:{
			id:'',
			roleName:'',
			permissionIds:'',
			userIds:''
		},
		permissions:'',
		users:'',
	},
	mounted(){
		this.initForm();
		this.validate();
		this.submit();
	},
	methods:{
		async initForm(){
			await this.initData();
			await this.initSelectM();
			form.render(null,'addAuthorization');			
		},
		async initData(){
			var str = $('#id').val().split(',');
			$('#id').val(str[0]);
			this.authorization.id = str[0];
			this.authorization.username = str[1];
			await $.post(baseURL+"/authorization/info/"+this.authorization.id,r=>{
				this.authorization = r.data;
			})
			await $.post(baseURL + '/permission/all',r=>{
				this.permissions = r.data
			})
			await $.post(baseURL + '/user/all',r=>{
				this.users = r.data
			})
		},
		initSelectM(){
				xmSelect.render({
					el:'#userIds',
					name:'users',
					paging:true,
					pageSize:5,
					//pageRemote: true,
					pageEmptyShow: false,
					filterable:true,
					data:vm.users,
					initValue:vm.authorization.userIds,
					prop:{
						name:'username',
						value:'id'
					},
					theme:{
						color:'#1E9FFF'
					}
				});	
				xmSelect.render({
					el:'#permissionIds',
					name:'permissions',
					paging:true,
					pageSize:5,
					filterable:true,
					data:vm.permissions,
					initValue:vm.authorization.permissionIds,
					prop:{
						value:'id'
					},
					theme:{
						color:'#FF5722'
					}
				});
		},
		submit(){
			form.on('submit(confirm)',data=>{
					var param = data.field;
					console.log(param)
					this.authorize(param);
					return false;
				})
		},
		authorize(param){
			$.post(baseURL+'/authorization/authorize',param,r=>{
				if(r.code==0){
					parent.layer.msg('保存成功')
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		cancel(){
			var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
			parent.layer.close(index); // 再执行关闭
		},
		validate(){
			form.verify({
				isRolenameExist(value,item){
					$.ajaxSettings.async = false;
					var msg = '';
					if(!value==vm.role.name)
					$.get(baseURL + '/verify/isRolenameExist/'+value,r=>{				
						if(r.code!=0){
							msg = r.msg;
						}
					})
					return msg;
				}
			})
		}
	}
})