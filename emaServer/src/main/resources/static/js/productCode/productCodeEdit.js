var vm = new Vue({
	el:'#addForm',
	data:{
		productCode:{
			id:'',
			name:'',
			license:'',
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
			await form.render(null,'addproductCode');			
		},
		async initData(){
			this.productCode.id = $("#id").val();
			if(this.productCode.id!=""){
				await $.post(baseURL+"/productCode/info/"+this.productCode.id,r=>{
					this.productCode = r.data;
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
				initValue:[vm.productCode.addressId],
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
			$.post(baseURL+'/productCode/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		edit(param){
			$.post(baseURL+'/productCode/edit',param,r=>{
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