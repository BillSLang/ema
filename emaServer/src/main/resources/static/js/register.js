var vm = new Vue({
	el:'#registerForm',
	data:{
		username:"",
		password:"",
		affPassword:"",
		birthday:"",
		phone:"",
		name:"",
		email:"",
		gender:"",
		tips1:"",
		tips2:"",
		tips3:"",
		flag1:false,
		flag2:false,
		flag3:false,
	},created(){
		this.submit();
		this.validate();
	},
	mounted(){
		this.initForm();
	},
	methods:{
		initForm(){
			form.render(null,'register');
			layui.laydate.render({
				elem:"#birthday",
				position:'fixed',
				trigger: 'click',
				format:'yyyy-MM-dd',
				zIndex: 99999999
			})
		},
		submit(){
			form.on('submit(register)',data=>{
				$.ajax({
					url:baseURL + "/register",
					type:"POST",
					data:data.field,
					success(r){
						layer.msg(r.msg);
						window.location.href = "login.html"
					},error(r){
						layer.msg(r.msg);
					}
				})
			})
		},
		validate(){
			form.verify({
				isUsernameExist(value,item){
					$.ajaxSettings.async = false;
					var msg = "";
					$.get(baseURL + "/verify/isUsernameExist/"+value,r=>{				
						if(r.code!=0){
							msg = r.msg;
						}
					})
					return msg;
				},isPhoneExist(value,item){	
						$.ajaxSettings.async = false;
						var msg = ""
						$.get(baseURL + "/verify/isPhoneExist/"+value,r=>{				
							if(r.code!=0){
								msg = r.msg;
							}
						})
						return msg;
				},isEmailExist(value,item){
						$.ajaxSettings.async = false;
						var msg = ""
						$.get(baseURL + "/verify/isEmailExist/"+value,r=>{
							if(r.code!=0){
								msg = r.msg;
							}
						})
						return msg;
				},
				username(value,item){
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