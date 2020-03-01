var vm = new Vue({
	el:'#addForm',
	data:{
		user:{
			id:'',
			username:'',
			name:'',
			password:'',
			email:'',
			gender:'男',
			phone:'',
			birthday:''
		}
	},
	mounted(){
		this.initForm();
		this.radioEvent();
		this.validate();
		this.submit();
		this.showPassword();
	},
	methods:{
		async initForm(){
			this.user.id = $("#id").val();
			if(this.user.id!=""){
				console.log(this.user.id)
				await $.post(baseURL+"/user/info/"+this.user.id,r=>{
					console.log("20200226测试")
					this.user = r.data;
				})
			}
			
			if(this.user.gender=="男")
				$("#male").attr("checked","checked");
			else
				$("#female").attr("checked","checked");
			
			form.render(null,'addUser');
		 	layui.laydate.render({
				elem:"#birthday",
				position:'fixed',
				trigger: 'click',
				format:'yyyy-MM-dd',
				zIndex: 99999999
			})
			
		},
		submit(){
			form.on('submit(confirm)',data=>{
					var param = data.field;
						if(data.field.id=='')
							$.post(baseURL+'/user/create',param,r=>{
									if(r.code==0){
										parent.layer.msg("保存成功")
										var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
										parent.layer.close(index); //再执行关闭
									}
								});
						else
							$.post(baseURL+'/user/edit',param,r=>{
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
		},radioEvent(){
			form.on('radio(gender)',data=>{
				this.user.gender = data.value
				console.log(data.value)
			})
		},showPassword(){
			this.user.id = $("#id").val();
			if(this.user.id!=""){
				return false
			}
			return true
		},validate(){
			form.verify({
				isUsernameExist(value,item){
					$.ajaxSettings.async = false;
					var msg = "";
					if(!value==vm.user.username)
					$.get(baseURL + "/verify/isUsernameExist/"+value,r=>{				
						if(r.code!=0){
							msg = r.msg;
						}
					})
					return msg;
				},isPhoneExist(value,item){	
						$.ajaxSettings.async = false;
						var msg = "";
						if(!value==vm.user.phone)
						$.get(baseURL + "/verify/isPhoneExist/"+value,r=>{				
							if(r.code!=0){
								msg = r.msg;
							}
						})
						return msg;
				},isEmailExist(value,item){
						$.ajaxSettings.async = false;
						var msg = "";
						if(!value==vm.user.email)
						$.get(baseURL + "/verify/isEmailExist/"+value,r=>{
							if(r.code!=0){
								msg = r.msg;
							}
						})
						return msg;
					},
				username(value,item){
					var username = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
					if(!username.test(value)){
						return "账号错误";		
					}
				},password(value,item){
					var password = /^[a-zA-Z]\w{5,17}$/;
					if(!password.test(value)){
						return "密码错误";
					}
				},email(value,item){
					var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					if(!email.test(value)){
						return "邮箱错误"
					}
				},phone(value,item){
					var phone = /^[1][0-9]{10}$/;
					if(!phone.test(value)){
						return "手机号错误"
					}
				}
			})
		}
	}
})