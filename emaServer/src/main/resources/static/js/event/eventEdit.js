var vm = new Vue({
	el:'#addForm',
	data:{
		event:{
			id:'',
			name:'',
			subjectId:'',
			foodbatchId:'',
			uriId:'',
			description:''
		},
		subjects:'',
		foodbatchs:'',
		uris:''
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
			await form.render(null,'addevent');			
		},
		async initData(){
			this.event.id = $("#id").val();
			if(this.event.id!=""){
				await $.post(baseURL+"/event/info/"+this.event.id,r=>{
					this.event = r.data;
				})
			}	
			await $.post(baseURL + '/subject/all',r=>{
				this.subjects = r.data
			})
			await $.post(baseURL + '/foodbatch/all',r=>{
				this.foodbatchs = r.data
			})
			await $.post(baseURL + '/uri/all',r=>{
				this.uris = r.data
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
				el:'#foodbatchIds',
				name:'foodbatchId',
				radio: true,
				clickClose: true,	
				paging:true,
				pageSize:5,
				data:vm.foodbatchs,
				layVerify: 'required',
				initValue:[vm.event.foodbatchId],
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
				el:'#subjectIds',
				name:'subjectId',
				radio: true,
				clickClose: true,	
				paging:true,
				pageSize:5,
				data:vm.subjects,
				layVerify: 'required',
				initValue:[vm.event.subjectId],
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
				el:'#uriIds',
				name:'uriId',
				radio: true,
				clickClose: true,	
				paging:true,
				pageSize:5,
				data:vm.uris,
				layVerify: 'required',
				initValue:[vm.event.uriId],
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
			$.post(baseURL+'/event/create',param,r=>{
				if(r.code==0){
					parent.layer.msg("保存成功")
					var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
					parent.layer.close(index); // 再执行关闭
				}
			});
		},
		edit(param){
			$.post(baseURL+'/event/edit',param,r=>{
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
				iseventnameExist(value,item){
					$.ajaxSettings.async = false;
					var msg = "";
					if(!value==vm.event.name)
					$.get(baseURL + "/verify/iseventnameExist/"+value,r=>{				
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