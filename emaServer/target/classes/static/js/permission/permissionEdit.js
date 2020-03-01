var vm = new Vue({
	el:'#addForm',
	data:{
		permission:{
			id:'',
			name:'',
			description:''
		},
	},
	mounted(){
		this.initForm();
		this.validate();
		this.submit();
	},
	methods:{
		async initForm(){
			await this.initData();
			await form.render(null,'addPermission');			
		},
		async initData(){
			this.permission.id = $("#id").val();
			if(this.permission.id!=""){
				await $.post(baseURL+"/permission/info/"+this.permission.id,r=>{
					this.permission = r.data;
					console.log(this.permission)
				})
			}	
		},
		submit(){
			form.on('submit(confirm)',data=>{
					var param = data.field;
					if(data.field.id=='')
							this.create(param);
						else
							this.edit(param);
						return false;
				})
		},
		create(param){
			$.post(baseURL+'/permission/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		edit(param){
			$.post(baseURL+'/permission/edit',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		cancel(){
			console.log()
			var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
			parent.layer.close(index); // 再执行关闭
		},
		validate(){
			form.verify({
				isPermissionnameExist(value,item){
					$.ajaxSettings.async = false;
					var msg = "";
					console.log(!value==vm.permission.name)
					if(!value==vm.permission.name)
					$.get(baseURL + "/verify/isPermissionnameExist/"+value,r=>{				
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