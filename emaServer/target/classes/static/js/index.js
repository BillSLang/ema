var iframe = document.getElementById('iframe');
var vm = new Vue({
	el:'#index',
	created(){
		this.route();
		router.render();	
		element.render();
	},
	methods:{
		route(){
			router.map('/hello',function(){
				iframe.setAttribute('src','user.html')
			}),
			router.map('/user',function(){
				iframe.setAttribute('src','hello.html')
			})
		},
		menuShrink(){
			var isShow = true;
			$('.layui-icon-shrink-right').click(function(){
				
			})
		}
	}
})