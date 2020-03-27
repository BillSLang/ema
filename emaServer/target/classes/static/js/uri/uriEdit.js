var vm = new Vue({
	el:'#addForm',
	data:{
		uri:{
			id:'',
			name:'',
			title:'',
			url:'',
			site:'',
			description:''
		},
		sites:''
	},
	mounted(){
		this.initForm();
		this.submit();
	},
	methods:{
		async initForm(){
			await this.initData();
			await this.initSelect();
			await form.render(null,'adduri');			
		},
		async initData(){
			this.uri.id = $("#id").val();
			if(this.uri.id!=""){
				await $.post(baseURL+"/uri/info/"+this.uri.id,r=>{
					this.uri = r.data;
				})
			}	
			await $.post(baseURL + '/site/all',r=>{
				console.log(r.data)
				this.sites = r.data
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
				el:'#siteIds',
				name:'siteId',
				radio: true,
				clickClose: true,	
				paging:true,
				pageSize:5,
				data:vm.sites,
				layVerify: 'required',
				initValue:[vm.uri.site],
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
			$.post(baseURL+'/uri/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		edit(param){
			$.post(baseURL+'/uri/edit',param,r=>{
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