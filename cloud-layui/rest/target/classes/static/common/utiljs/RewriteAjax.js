//重写ajax方法 使得页面当用户过期时 跳转到登录页面
jQuery(function ($) {
	    // 备份jquery的ajax方法    
	    var _ajax = $.ajax;
	    // 重写ajax方法，先判断登录在执行error函数   
	    $.ajax = function (opt) {
	        var _error = opt && opt.error || function (a, b, c) { };
	        var _opt = $.extend(opt, {
	            error: function (xhr, status, error) {
	            	if (xhr.status == 401) {//status这里是error，因为是在相应中的401状态位，所以需要用xhr.status判断
                    //  warningInfo(xhr.responseText);//封装的弹窗提醒
                        top.location.href = "${rc.contextPath}/user/login";//跳转到登录页面
                        return;
                    }
                    _error(xhr, status, error);
	            }
	        });

	        _ajax(_opt);
	    };
});