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
				console.log(this.authorization)
			})
			await $.post(baseURL + '/permission/all',r=>{
				this.permissions = r.data
			})
			await $.post(baseURL + '/user/all',r=>{
				console.log(r.data)
				this.users = r.data
			})
		},
		initSelectM(){
			layui.config({
				base:moudle+'/lib/layui_extends/'
			}).use('xmSelect',args=>{
				var xmSelect = layui.xmSelect;
				xmSelect({
					elem:'#permissionIds',
					data:vm.permissions,
					width:400,
					delimiter:',',
					selected:vm.authorization.permissionIds,
					name:'permissions',
					tips:'请选择权限'
				});
				xmSelect({
					elem:'#userIds',
					data:vm.users,
					width:400,
					delimiter:',',
					selected:vm.authorization.userIds,
					name:'users',
					tips:'请选择用户',
					field:{idName:'id',titleName:'username'}
				});
			})
			
			
			/*layui.config({
				base:moudle + '/lib/layui_extends/'
			}).use('selectM',args=>{
				var selectM = layui.selectM;
				selectM({
					elem:'#permissionIds',
					data:vm.permissions,
					width:400,
					delimiter:',',
					selected:vm.authorization.permissionIds,
					name:'permissions',
					tips:'请选择权限'
				});
				selectM({
					elem:'#userIds',
					data:vm.users,
					width:400,
					delimiter:',',
					selected:vm.authorization.userIds,
					name:'users',
					tips:'请选择用户',
					field:{idName:'id',titleName:'username'}
				});
			})*/
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