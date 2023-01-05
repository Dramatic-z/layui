/*-双击查看大图 -*/
function doubleclickviewimage(data){
	layer.photos({
        photos: { "data": [{"src": data}] }
    });
}

/*-上传图片 -*/
function uploadimage_choose(obj,img_width_min,img_height_min,img_width_max,img_height_max,uploadInst1){
	 var flag = true;
	 obj.preview(function(index, file, result){
		 var img = new Image();
	      img.src = result;
	      img.onload = function () {  //初始化夹在完成后获取上传图片宽高，判断限制上传图片的大小。
	     	  if(img.width ==img_width_min && img.height ==img_height_min){
	              //满足条件调用上传方法
	     		   obj.upload(index, file);
	     		   layer.load(2);
	          }else if(img.width > 0 && img.height > 0 && img.width <= img_width_max && img.height <= img_height_max){
	       	   	   obj.upload(index, file);
	       	   	   //开启上传锁屏图标
	       	   	   layer.load(2);
	       	   /* layer.confirm('您选择的图片大小不符合最佳要求,确认上传吗?', {title:'提示'}, function(index){
	             	   obj.upload(index, file);
	             	   layer.close(index);
	             	}); */
	          }else{
	             	 flag = false;
	             	 uploadInst1.config.elem.next()[0].value = '';
	             	 layer.msg("您选择的图片大小超出"+img_width_max+"*"+img_height_max,+"无法上传");
	                 return false;
	          }
	      }
	      return flag;
	    });
}

function uploadimage_before(obj,picload,pic,picpath){
	//预读本地文件示例，不支持ie8
    $(picload).val(1);//上传开始标识符
    $(pic).val(0);
    obj.preview(function(index, file, result){
    	$(picpath).attr('src', result); //图片链接（base64）
    });
}

function uploadimage_done(res,pic,picload,picpath){
	  if(res.code == 0){
		layer.close(layer.index);
     	$(pic).val(res.code);//上传结束标识符
        $(picload).val(0);
        $(picpath).val(res.data.src);
        return layer.msg('上传成功');
      }else{
    	layer.close(layer.index);
        return layer.msg('上传失败');
      }
}


function uploadvideo_before(obj,picpath){
	//预读本地文件示例，不支持ie8
    obj.preview(function(index, file, result){
    	$(picpath).attr('src', result); //图片链接（base64）
    });
}

function uploadvideo_done(res,picpath){
	  if(res.code == 0 && res.data.src!=null){
     	$(picpath).val(res.data.src);
        return layer.msg('上传成功');
      }else{
        return layer.msg('上传失败');
      }
}

/*-上传视频 -*/
function uploadvideo_choose(obj,img_width_min,img_height_min,img_width_max,img_height_max,uploadInst){
	 var flag = true;
	 obj.preview(function(index, file, result){
		  
	      img.onload = function () {  //初始化夹在完成后获取上传图片宽高，判断限制上传图片的大小。
	     	  if(img.width ==img_width_min && img.height ==img_height_min){
	              //满足条件调用上传方法
	     		   obj.upload(index, file);
	          }else if(img.width > 0 && img.height > 0 && img.width <= img_width_max && img.height <= img_height_max){
	       	   obj.upload(index, file);
	       	   /* layer.confirm('您选择的图片大小不符合最佳要求,确认上传吗?', {title:'提示'}, function(index){
	             	   obj.upload(index, file);
	             	   layer.close(index);
	             	}); */
	          }else{
	             	 flag = false;
	             	 uploadInst.config.elem.next()[0].value = '';
	             	 layer.msg("您选择的图片大小超出"+img_width_max+"*"+img_height_max,+"无法上传");
	                 return false;
	          }
	      }
	      return flag;
	    });
}


