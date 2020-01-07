var vm = new Vue({
	el:'#registerForm',
	data:{
		username:"",
		password:"",
		affPassword:"",
		email:""
	},created(){
		this.submit();
		this.validate();
	},
	methods:{
		submit(){
			form.on('submit(register)',data=>{
				$.ajax({
					url:baseURL + "/register",
					type:"POST",
					data:data.field,
					success(r){
						
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
		}
	}
})