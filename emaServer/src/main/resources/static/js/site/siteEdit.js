var vm = new Vue({
	el:'#addForm',
	data:{
		site:{
			id:'',
			name:'',
			license:'',
			domain:'',
			addressId:'',
			description:''
		},
		addresss:''
	},
	mounted(){
		this.initForm();
		this.submit();
	},
	methods:{
		async initForm(){
			await this.initData();
			//await this.initSelect();
			await form.render(null,'addsite');			
		},
		async initData(){
			this.site.id = $("#id").val();
			if(this.site.id!=""){
				await $.post(baseURL+"/site/info/"+this.site.id,r=>{
					this.site = r.data;
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
		initSelect(){
			xmSelect.render({
				el:'#addressIds',
				name:'addressId',
				radio: true,
				clickClose: true,	
				paging:true,
				pageSize:5,
				data:vm.addresss,
				layVerify: 'required',
				initValue:[vm.site.addressId],
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
			$.post(baseURL+'/site/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		edit(param){
			$.post(baseURL+'/site/edit',param,r=>{
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