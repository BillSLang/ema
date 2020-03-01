var vm = new Vue({
	el:'#addForm',
	data:{
		food:{
			id:'',
			name:'',
		}
	},
	mounted(){
		this.initForm();
		this.validate();
		this.submit();
	},
	methods:{
		async initForm(){
			this.food.id = $("#id").val();
			if(this.food.id!=""){
				console.log(this.food.id)
				await $.post(baseURL+"/food/info/"+this.food.id,r=>{
					this.food = r.data;
				})
			}
			form.render(null,'addFood');
		},
		submit(){
			form.on('submit(confirm)',data=>{
					var param = data.field;
						if(data.field.id=='')
							$.post(baseURL+'/food/create',param,r=>{
									if(r.code==0){
										parent.layer.msg("保存成功")
										var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
										parent.layer.close(index); //再执行关闭
									}
								});
						else
							$.post(baseURL+'/food/edit',param,r=>{
									if(r.code==0){
										parent.layer.msg("保存成功")
										var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
										parent.layer.close(index); //再执行关闭
									}
								});
						return false;
				})
		},cancel(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭
		},validate(){
			form.verify({
				isFoodnameExist(value,item){
					$.ajaxSettings.async = false;
					var msg = "";
					if(!value==vm.food.name)
					$.get(baseURL + "/verify/isFoodnameExist/"+value,r=>{				
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