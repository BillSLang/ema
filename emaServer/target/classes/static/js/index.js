var iframe = document.getElementById('iframe');
var vm = new Vue({
	el:'#index',
	created(){
		console.log("test")
		this.route();
		router.render();	
		element.render();

	},
	methods:{
		route(){
			router.map('/user',function(){
				iframe.setAttribute('src','user/user.html')
			}),
			router.map('/role',function(){
				iframe.setAttribute('src','role/role.html')
			}),
			router.map('/permission',function(){
				iframe.setAttribute('src','permission/permission.html')
			}),
			router.map('/authorization',function(){
				iframe.setAttribute('src','authorization/authorization.html')
			}),
			router.map('/food',function(){
				iframe.setAttribute('src','food/food.html')
			}),
			router.map('/hello',function(){
				iframe.setAttribute('src','hello.html')
			})
		},
		logout(){
			$.post(baseURL+"/logout")
		},
		menuShrink(){
			console.log("test")
			var isShow = true;
			$('.layui-icon-shrink-right').click(function(){
				
			})
		}
	}
})