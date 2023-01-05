
/*-根据商场id获取商场一级业态和楼层数据 -*/
function obtainbformatfloorbymarketid(data,path){
	var marketid;
	if(data.value==null){
		marketid=data;
	}else{
		marketid = data.value;
	}

//	alert(path);
	$.ajax({
		url: path+'/market/selectFloorAndBigfByMarketId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#test-floor").empty();
					$("#test-bigFormat").empty();
					$("#test-smallFormat").empty();
					var floors=data.floors;
					var bformat=data.bigformat;
					if(floors!=null){
						$("#test-floor").append("<option value=''>-请选择楼层-</option>");
						for(var i=0;i<floors.length;i++){
							var fl=floors[i];
							if(fl>0){
								$("#test-floor").append("<option value="+fl+">"+fl+"层"+"</option>");
							}else{
								var flt=fl-1;
								$("#test-floor").append("<option value="+fl+">"+flt+"层"+"</option>");
							}
						};
					}else{
						$("#test-floor").append("<option value=''>-请选择其他商场-</option>");
					}
					if(bformat!=null){
						$("#test-bigFormat").append("<option value=''>-请选择一级分类-</option>");
						for (var i=0;i<bformat.length;i++){
							$("#test-bigFormat").append("<option value='"+bformat[i].id+"'>"+bformat[i].nameChina+"</option>");
						}
						$("#test-smallFormat").append("<option value=''>-请选择一级分类-</option>");
					}else{
						$("#test-bigFormat").append("<option value=''>-请选择其他商场-</option>");
						$("#test-smallFormat").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#test-floor").empty();
					$("#test-bigFormat").empty();
					$("#test-smallFormat").empty();
					$("#test-floor").append("<option value=''>-请先选择商场-</option>");
					$("#test-bigFormat").append("<option value=''>-请先选择商场-</option>");
					$("#test-smallFormat").append("<option value=''>-请先选择商场-</option>");
				}
			}
		}
	});
}

/*-根据商场id获取商场楼层数据 -*/
function obtainbfloorbymarketid(data,path){
	var marketid = data.value;
//	alert(path);
	$.ajax({
		url: path+'/market/selectFloorAndBigfByMarketId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#test-floor").empty();
					var floors=data.floors;
					if(floors!=null){
						$("#test-floor").append("<option value=''>-请选择楼层-</option>");
						for(var i=0;i<floors.length;i++){
							var fl=floors[i];
							if(fl>0){
								$("#test-floor").append("<option value="+fl+">"+fl+"层"+"</option>");
							}else{
								var flt=fl-1;
								$("#test-floor").append("<option value="+fl+">"+flt+"层"+"</option>");
							}
						};
					}else{
						$("#test-floor").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#test-floor").empty();
					$("#test-floor").append("<option value=''>-请选择其他商场-</option>");
				}
			}
		}
	});
}

/*-根据大业态id获取商场二级业态数据 -*/
function obtainsmallformatbybformatid(data,path){
	var bigformat = data.value;
//	alert(path);
	$.ajax({
		url: path+'/market/selectSfByBigfId?bigformatId='+bigformat,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#test-smallFormat").empty();
					var smaformat=data.smaformat;
					if(smaformat!=null){
						$("#test-smallFormat").append("<option value=''>-请选择二级分类-</option>");
						for (var i=0;i<smaformat.length;i++){
							$("#test-smallFormat").append("<option value='"+smaformat[i].id+"'>"+smaformat[i].nameChina+"</option>");
						}
					}else{
						$("#test-smallFormat").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#test-smallFormat").empty();
					$("#test-smallFormat").append("<option value=''>-请先选择一级分类-</option>");
				}
			}
		}
	});
}


/*-根据商场id获取商场一级业态 (二级业态修改页面) -*/
function obtainbformatofsfbymarketid(data,path){
	var marketid = data.value;
	$.ajax({
		url: path+'/market/selectFloorAndBigfByMarketId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#edit-bformat").empty();
					var bformat=data.bigformat;
					if(bformat!=null){
						$("#edit-bformat").append("<option value=''>-请选择一级分类-</option>");
						for (var i=0;i<bformat.length;i++){
							$("#edit-bformat").append("<option value='"+bformat[i].id+"'>"+bformat[i].nameChina+"</option>");
						}
					}else{
						$("#edit-bformat").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#edit-bformat").empty();
					$("#edit-bformat").append("<option value=''>-请先选择商场-</option>");
				}
			}
		}
	});
}
/**
 * 根据商场id获取商场一级业态 (二级业态table页面)
 * @param data
 * @param path
 */
function obtainbformatofsfbymarketidoftable(data,path){
	var marketid = data.value;
	$.ajax({
		url: path+'/market/selectFloorAndBigfByMarketId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#search-bigFormatId").empty();
					var bformat=data.bigformat;
					if(bformat!=null){
						$("#search-bigFormatId").append("<option value=''>-请选择一级分类-</option>");
						for (var i=0;i<bformat.length;i++){
							$("#search-bigFormatId").append("<option value='"+bformat[i].id+"'>"+bformat[i].nameChina+"</option>");
						}
					}else{
						$("#search-bigFormatId").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#search-bigFormatId").empty();
					$("#search-bigFormatId").append("<option value=''>-请先选择商场-</option>");
				}
			}
		}
	});
}


/*-根据商场id获取楼层数据 （设备表使用） -*/
function obtainfloorofdevicetablebymarketid(data,path){
	var marketid = data.value;
	$.ajax({
		url: path+'/market/selectFloorAndBigfByMarketId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#search-floor").empty();
					var floors=data.floors;
					if(floors!=null){
						$("#search-floor").append("<option value=''>-请选择楼层-</option>");
						for(var i=0;i<floors.length;i++){
							var fl=floors[i];
							if(fl>0){
								$("#search-floor").append("<option value="+fl+">"+fl+"层"+"</option>");
							}else{
								var flt=fl-1;
								$("#search-floor").append("<option value="+fl+">"+flt+"层"+"</option>");
							}
						};
					}else{
						$("#search-floor").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#search-floor").empty();
					$("#search-floor").append("<option value=''>-请先选择商场-</option>");
				}
			}
		}
	});
}
/*-根据商场id获取楼层数据 （设备表使用） -*/
function obtainfloorofdeviceeditbymarketid(data,path){
	var marketid = data.value;
	$.ajax({
		url: path+'/market/selectFloorAndBigfByMarketId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#edit-floor").empty();
					var floors=data.floors;
					if(floors!=null){
						$("#edit-floor").append("<option value=''>-请选择楼层-</option>");
						for(var i=0;i<floors.length;i++){
							var fl=floors[i];
							if(fl>0){
								$("#edit-floor").append("<option value="+fl+">"+fl+"层"+"</option>");
							}else{
								var flt=fl-1;
								$("#edit-floor").append("<option value="+fl+">"+flt+"层"+"</option>");
							}
						};
					}else{
						$("#edit-floor").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#edit-floor").empty();
					$("#edit-floor").append("<option value=''>-请先选择商场-</option>");
				}
			}
		}
	});
}

/*-根据商场id获取商铺数据-*/
function obtainstoresbymarketid(data,path){
	var marketid = data.value;
	$.ajax({
		url: path+'/market/selectStoresByBigfId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#edit-storeid").empty();
					var slist=data.storelist;
					if(slist!=null){
						$("#edit-storeid").append("<option value=''>-请选择商铺-</option>");
						for(var i=0;i<slist.length;i++){
							var st=slist[i];
							$("#edit-storeid").append("<option value="+st.id+">"+st.nameChina+"</option>");
						};
					}else{
						$("#edit-storeid").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#edit-storeid").empty();
					$("#edit-storeid").append("<option value=''>-请选择其他商场-</option>");
				}
			}
		}
	});
}

/*-根据商场id和楼层查询点位未使用数据 -*/
function obtainstorepointbybformatid(data,mid,path,point){
	var floor = data;
//	alert(path);
	$.ajax({
		url: path+'/market/selectStorePointByMIdFloor?marketId='+mid+'&floor='+floor,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					var pointl=data.pointlist;
					$("#editPoint").html('');
					if(pointl!=null&&pointl!=''){
						var arr ;
						if(point!=null&&point!=''){
							arr=point.split(',');
						}
						for (var i = 0; i < pointl.length; i++) {
							if(arr!=null){
								if(arr.indexOf(pointl[i].title)==-1){
									$("#editPoint").append("<input type='checkbox' lay-skin='primary' name='checkpoint' value="+pointl[i].title+" title="+pointl[i].title+">");
								}
							}else{
								$("#editPoint").append("<input type='checkbox' lay-skin='primary' name='checkpoint' value="+pointl[i].title+" title="+pointl[i].title+">");
							}
						}
					}else{
						$("#editPoint").html('');
					}
				}else{
					$("#editPoint").html('');
				}
			}
		}
	});
}

/*-根据商场id和楼层查询点位、数据 -   新版  查询所有的点位*/
function obtainallstorepointbybformatid(data,mid,path,point){
	var floor = data;
//	alert(path);
	$.ajax({
		url: path+'/market/selectAllStorePointByMIdFloor?marketId='+mid+'&floor='+floor,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var pointl = result.data;
				if(pointl!=null){
					$("#editPoint").html('');
					if(pointl!=null&&pointl!=''){
						var arr ;
						if(point!=null&&point!=''){
							arr=point.split(',');
						}
						for (var i = 0; i < pointl.length; i++) {
							if(arr!=null){
								if(arr.indexOf(pointl[i].point)==-1){
									if(pointl[i].used == 1){
										$("#editPoint").append("<div style='float: left;'><input type='checkbox' class='"+pointl[i].point+"' lay-skin='primary_u' name='checkpoint' value="+pointl[i].point+" title="+pointl[i].point
											+"></div>");
									}else{
										$("#editPoint").append("<div style='float: left;'><input type='checkbox' class='"+pointl[i].point+"'lay-skin='primary_n' name='checkpoint' value="+pointl[i].point+" title="+pointl[i].point
											+"></div>");
									}
								}
							}else{
								if(pointl[i].used == 1){
									$("#editPoint").append("<div style='float: left;'><input type='checkbox' class='"+pointl[i].point+"' lay-skin='primary_u' name='checkpoint' value="+pointl[i].point+""+" title="+pointl[i].point
										+"></div>");
								}else{
									$("#editPoint").append("<div style='float: left;'><input type='checkbox' class='"+pointl[i].point+"' lay-skin='primary_n' name='checkpoint' value="+pointl[i].point+" title="+pointl[i].point
										+"></div>");
								}
							}
						}
					}else{
						$("#editPoint").html('');
					}
				}else{
					$("#editPoint").html('');
				}
			}
		}
	});
}

/*-根据商场id获取商场年龄段数据 (人脸视频页面) -*/
function obtainfacescreenbymarketid(data,path){
	var marketid = data.value;
	$.ajax({
		url: path+'/market/selectFaceScreenAgeByMId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#edit-agegroup").empty();
					var arr=data.faceagearrs;
					if(arr!=null){
						$("#edit-agegroup").append("<option value=''>-请选择年龄段-</option>");
						for (var i=0;i<arr.length;i++){
							$("#edit-agegroup").append("<option value='"+arr[i]+"'>"+arr[i]+"岁"+"</option>");
						}
					}else{
						$("#edit-agegroup").append("<option value=''>-请选择其他商场-</option>");
					}
				}else{
					$("#edit-agegroup").empty();
					$("#edit-agegroup").append("<option value=''>-请先选择商场-</option>");
				}
			}
		}
	});
}

/*-根据商场id获取商场已有角色数据 -*/
function obtainbrolesbymarketid(data,path){
	var marketid = data.value;
//	alert(path);
	if(marketid!=null&&marketid!=''){
		$.ajax({
			url: path+'/user/module/queryRolesByMarketId?marketId='+marketid,
			type: "POST",
			dataType: 'json',
			async: false,
			success: function (result) {
				if ("0" == result.code) {
					var data = result.data
					if(data!=null){
						$("#functional").empty();
						for(var i=0;i<data.length;i++){
							var role=data[i];
							$("#functional").append('<input type="checkbox" name="checkRole" value="'+role.id+'" lay-skin="primary" title="'+role.name+'">');
						};
						$("#functionalDiv").show();
						$("#basticDiv").show();
					}else{
						$("#functional").empty();
						$("#functionalDiv").hide();
						$("#basticDiv").hide();
					}
				}else{
					$("#functional").empty();
					$("#functionalDiv").hide();
					$("#basticDiv").hide();
				}
			}
		});
	}else{
		$("#functional").empty();
		$("#functionalDiv").hide();
		$("#basticDiv").hide();
	}
}


/*-根据商场id获取商场公共设施分类 -*/
function obtainbPublicCategorybymarketid(data,path){
	var marketid = data.value;
	$.ajax({
		url: path+'/market/selectPublicCategoryByMId?marketId='+marketid,
		type: "POST",
		dataType: 'json',
		async: false,
		success: function (result) {
			if ("0" == result.code) {
				var data = result.data
				if(data!=null){
					$("#edit-categoryId").empty();
					$("#edit-categoryId").append("<option value=''>-请选择分类-</option>");
					for (var i=0;i<data.length;i++){
						$("#edit-categoryId").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
					}
				}else{
					$("#edit-categoryId").empty();
					$("#edit-categoryId").append("<option value=''>-请先选择商场-</option>");
				}
			}else{
				$("#edit-categoryId").empty();
				$("#edit-categoryId").append("<option value=''>-请先选择商场-</option>");
			}
		}
	});
}