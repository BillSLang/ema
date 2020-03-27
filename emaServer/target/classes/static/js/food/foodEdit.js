var vm = new Vue({
	el:'#addForm',
	data:{
		food:{
			id:'',
			name:'',
			foodTypeId:'',
			productCodeId:'',
			brandId:'',
			producerId:'',
			storeMethodId:'',
			tasteId:'',
			expireId:'',
			description:''
		},
		foodTypes:'',
		productCodes:'',
		brands:'',
		producers:'',
		storeMethods:'',
		tastes:'',
		expires:'',
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

			form.render(null,'addFood');
		},
		async initData(){
			this.food.id = $("#id").val();
			if(this.food.id!=""){
				await $.post(baseURL+"/food/info/"+this.food.id,r=>{
					this.food = r.data;
					console.log(this.food)
				})
			}
			await $.post(baseURL + '/foodType/all',r=>{
				this.foodTypes = r.data
			})
			await $.post(baseURL + '/productCode/all',r=>{
				this.productCodes = r.data
			})
			await $.post(baseURL + '/brand/all',r=>{
				this.brands = r.data
			})
			await $.post(baseURL + '/producer/all',r=>{
				this.producers = r.data
			})
			await $.post(baseURL + '/storeMethod/all',r=>{
				this.storeMethods = r.data
			})
			await $.post(baseURL + '/taste/all',r=>{
				this.tastes = r.data
			})
			await $.post(baseURL + '/expire/all',r=>{
				this.expires = r.data
			})
		},create(param){
			$.post(baseURL+'/food/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},edit(param){
			$.post(baseURL+'/food/edit',param,r=>{
				if(r.code==0){
					parent.layer.msg("编辑成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		submit(){
			form.on('submit(confirm)',data=>{
					var param = data.field;
						if(data.field.id=='')
							this.create(param)
						else
							this.edit(param)
						return false;
				})
		},
		initSelectM(){
			xmSelect.render({
				el:'#foodTypeIds',
				name:'foodTypeId',
				radio: true,
				clickClose: true,	
				paging:true,
				pageSize:5,
				data:vm.foodTypes,
				initValue:[vm.food.foodTypeId],
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
			xmSelect.render({
				el:'#producerIds',
				name:'producerId',
				radio: true,
				clickClose: true,
				paging:true,
				pageSize:5,
				data:vm.producers,
				initValue:[vm.food.producerId],
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
			
			xmSelect.render({
				el:'#productCodeIds',
				name:'productCodeId',
				radio: true,
				clickClose: true,
				paging:true,
				pageSize:5,
				data:vm.productCodes,
				initValue:[vm.food.productCodeId],
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
			xmSelect.render({
				el:'#brandIds',
				name:'brandId',
				radio: true,
				clickClose: true,
				paging:true,
				pageSize:5,
				data:vm.brands,
				initValue:[vm.food.brandId],
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
			xmSelect.render({
				el:'#storeMethodIds',
				name:'storeMethodId',
				radio: true,
				clickClose: true,
				paging:true,
				pageSize:5,
				data:vm.storeMethods,
				initValue:[vm.food.storeMethodId],
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
			xmSelect.render({
				el:'#tasteIds',
				name:'tasteId',
				radio: true,
				clickClose: true,
				paging:true,
				pageSize:5,
				data:vm.tastes,
				initValue:[vm.food.tasteId],
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
			xmSelect.render({
				el:'#expireIds',
				name:'expireId',
				radio: true,
				clickClose: true,
				paging:true,
				pageSize:5,
				data:vm.expires,
				initValue:[vm.food.expireId],
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
		},cancel(){
			var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
			parent.layer.close(index); // 再执行关闭
		}
	}
})