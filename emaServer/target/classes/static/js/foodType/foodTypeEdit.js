var vm = new Vue({
	el:'#addForm',
	data:{
		foodType:{
			id:'',
			name:'',
			parentId:'',
			description:''
		},
		parents:''
	},
	mounted(){
		this.initForm();
		this.validate();
		this.submit();
	},
	methods:{
		async initForm(){
			await this.initData();
			await this.initSelect();
			await form.render(null,'addfoodType');			
		},
		async initData(){
			this.foodType.id = $("#id").val();
			if(this.foodType.id!=""){
				await $.post(baseURL+"/foodType/info/"+this.foodType.id,r=>{
					this.foodType = r.data;
				})
			}	
			await $.post(baseURL + '/foodType/all',r=>{
				
				this.parents = r.data
			})
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
		initSelect(){
			xmSelect.render({
				el:'#parentIds',
				name:'parentId',
				radio: true,
				clickClose: true,	
				paging:true,
				pageSize:5,
				data:vm.parents,
				layVerify: 'required',
				initValue:[vm.foodType.parentId],
				// pageRemote: true,
				pageEmptyShow: false,
				filterable:true,
				prop:{
					value:'id'
				},
				theme:{
					color:'#1E9FFF'
				}
			});	
		},
		create(param){
			$.post(baseURL+'/foodType/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		edit(param){
			$.post(baseURL+'/foodType/edit',param,r=>{
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
		},
		validate(){
			form.verify({
				isfoodTypenameExist(value,item){
					$.ajaxSettings.async = false;
					var msg = "";
					if(!value==vm.foodType.name)
					$.get(baseURL + "/verify/isfoodTypenameExist/"+value,r=>{				
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