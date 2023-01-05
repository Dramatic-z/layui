/**
 @Name：layuiAdmin 表单验证模块
    
 */
	//方法一将不允许出现的替换 清空
    //实时监听input内的值，只允许字母数字-.出现
	$('.numAlpha').on("input propertychange", function () {
	    var val = $(this).val();
		val = val.replace(/[^\w#\.\/\-]/ig, '');
	    $(this).val(val);
	});

    //方法二将判断如果为不满足 验证条件，清空
    //实时监听input内的值，只允许最多两位正整数出现
	$('.numAlpha1').on("input propertychange", function () {
	    var reg= /^([1-9]\d?|99)$/;
	    if(!reg.test($(this).val())){
		
	       $(this).val("");
	    }
	});
	
	/**
	 * 商铺添加修改页面表单验证。
	 * 
	 */
	//20字符以内
	$('#test-nameChina').on("input propertychange", function () {
		if($(this).val().length > 21){
			$('#check-nameChina').text("当前已超出20字符");
			$('#check-nameChina').css("color","red");
		}else{
			$('#check-nameChina').text("20个字符以内");
			$('#check-nameChina').css("color","#999");
		}
	});
	//60字符以内
	$('#test-nameEng').on("input propertychange", function () {
		if($(this).val().length > 61){
			$('#check-nameEng').text("当前已超出60字符");
			$('#check-nameEng').css("color","red");
		}else{
			$('#check-nameEng').text("60个字符以内");
			$('#check-nameEng').css("color","#999");
		}
	});
	//20字符以内
	$('#test-nameFan').on("input propertychange", function () {
		if($(this).val().length > 61){
			$('#check-nameFan').text("当前已超出60字符");
			$('#check-nameFan').css("color","red");
		}else{
			$('#check-nameFan').text("60个字符以内");
			$('#check-nameFan').css("color","#999");
		}
	});
	//20字符以内
	$('#test-keyword').on("input propertychange", function () {
		if($(this).val().length > 21){
			$('#check-keyword').text("当前已超出20字符");
			$('#check-keyword').css("color","red");
		}else{
			$('#check-keyword').text("20个字符以内");
			$('#check-keyword').css("color","#999");
		}
	});
	//500字符以内
	$('#test-ch').on("input propertychange", function () {
		if($(this).val().length > 501){
			$('#check-ch').text("当前已超出500字符");
			$('#check-ch').css("color","red");
		}else{
			$('#check-ch').text("500个字符以内");
			$('#check-ch').css("color","#999");
		}
	});
	//2000字符以内
	$('#test-eng').on("input propertychange", function () {
		if($(this).val().length > 2001){
			$('#check-eng').text("当前已超出2000字符");
			$('#check-eng').css("color","red");
		}else{
			$('#check-eng').text("2000个字符以内");
			$('#check-eng').css("color","#999");
		}
	});
	//500字符以内
	$('#test-fan').on("input propertychange", function () {
		if($(this).val().length > 501){
			$('#check-Fan').text("当前已超出500字符");
			$('#check-Fan').css("color","red");
		}else{
			$('#check-Fan').text("500个字符以内");
			$('#check-Fan').css("color","#999");
		}
	});
	
	
	
	
	
	
	//
	$('.test-passwords').blur(function(){
		var tar = $(this);
		var reg=/(.+){6,12}$/;
		if(!reg.test(tar)){
			$(this).val("");
			layer.alert("密码必须6到12位");
		}
	});
