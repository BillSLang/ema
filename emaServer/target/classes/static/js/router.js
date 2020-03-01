(function(){
	function Router(){
		this.routes={};
		this.curUrl='/';
	}	
	/*
	 * 启动路由功能
	 */
	Router.prototype.render = function(){
		window.addEventListener('hashchange',this.reloadPage.bind(this))
	}	
	/*
	 * 绑定window.onhashchange事件的回调函数
	 */
	Router.prototype.reloadPage = function(){
		this.curUrl = location.hash.substring(1)||'/';
		console.log(this.routes);
		this.routes[this.curUrl]();
	}	
	/*
	 * 路由注册方法
	 */
	Router.prototype.map = function(key,callback){
		this.routes[key] = callback;
	}				
	window.Router = new Router();
	window.router = window.Router;
})();

	