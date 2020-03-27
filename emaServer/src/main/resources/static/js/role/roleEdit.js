var vm = new Vue({
	el:'#addForm',
	data:{
		role:{
			id:'',
			name:'',
			description:''
		},
	},
	mounted(){
		this.initForm();
		this.submit();
	},
	methods:{
		async initForm(){
			await this.initData();
			await form.render(null,'addRole');			
		},
		async initData(){
			this.role.id = $("#id").val();
			if(this.role.id!=""){
				await $.post(baseURL+"/role/info/"+this.role.id,r=>{
					this.role = r.data;
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
			$.post(baseURL+'/role/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		edit(param){
			$.post(baseURL+'/role/edit',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		cancel(){
			var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
			parent.layer.close(index); // 再执行关闭
		}
	}
})