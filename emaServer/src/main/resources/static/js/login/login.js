var form = layui.form;
var vm = new Vue({
	el:'#loginForm',
	data:{
		username:"",
		password:"",
		captcha:"",
		src:"captcha.jpg"
	},created(){
		this.submit();
	},
	methods:{
		submit(){
			form.on('submit(login)', function(data) {
				console.log(data.field);
				return false;// 阻止表单跳转
			})
		},
		refreshCode(){
			this.src="captcha.jpg?t="+$.now();
		}
	}
})