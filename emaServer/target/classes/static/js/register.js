var vm = new Vue({
	el:'#registerForm',
	data:{
		username:"",
		password:"",
		affPassword:"",
		email:"",
		tips1:"",
		tips2:"",
		flag1:false,
		flag2:false
	},created(){
		this.submit();
		this.validate();
	},
	methods:{
		submit(){
			form.on('submit(register)',data=>{
				console.log(JSON.stringify(data.field))
				$.ajax({
					url:baseURL + "/register",
					type:"POST",
					contentType: 'application/json; charset=UTF-8',
					dataType:'json',
					data:JSON.stringify(data.field),
					success(r){
						layer.msg(r.msg);

					},error(r){
						layer.msg(r.msg);
					}
				})
			})
		},
		validate(){
			form.verify({				
				username(value,item){
					console.log("xx")
					var username = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
					if(!username.test(value))
						return "账号错误";				
				},password(value,item){
					var password = /^[a-zA-Z]\w{5,17}$/;
					if(!password.test(value))
						return "密码错误";
				},affPassword(value,item){
					var affPassword = /^[a-zA-Z]\w{5,17}$/;					
					if(!affPassword.test(value))
						return "两次密码不一样";
					console.log($("#password").val())
					if(value!=$("#password").val())
						return "两次密码不一样";
				},email(value,item){
					var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					if(!email.test(value))
						return "邮箱错误"
				}
			})
		},
		isUsernameExist(){
			$.get(baseURL + "/verify/isUsernameExist/"+this.username,r=>{				
				if(r.code!=0){
					this.tips1 = r.msg;
					this.flag1 = true;
				}
			})
		},
		isEmailExist(){
			$.get(baseURL + "/verify/isEmailExist/"+this.email,r=>{
				if(r.code!=0){
					this.tips2 = r.msg;
					this.flag2 = true;
				}
			})
		}
	}
})