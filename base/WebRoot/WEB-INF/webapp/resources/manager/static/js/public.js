
/**
 * 通用提示框
 * @param msg
 */
function gAlert(_msg){
	layer.alert(_msg)
}

/**
 * 成功提示框
 * @param msg
 */
function gAlertOk(_msg){
	layer.alert(_msg, {
		icon: 1,
		skin: 'layer-ext-moon'
	})
}

/**
 * 错误提示框
 * @param msg
 */
function gAlertErr(_msg){
	layer.alert(_msg, {
		icon: 2,
		skin: 'layer-ext-moon'
	})
}

/**
 * 确认框
 * @param msg
 */
function gConfirm(_msg, yesCallback){
	layer.confirm(_msg, {
		btn: ['确认', '取消']
	}, yesCallback, function(index){
		closeAlert(index);
	});
}

/**
 * 警告框
 * @param msg
 */
function gAlertWarn(_msg){
	layer.alert(_msg, {
		icon: 0,
		skin: 'layer-ext-moon'
	})
}

/**
 * 关闭弹框
 * @param index
 * @returns
 */
function closeAlert(index){
	layer.close(index);
}

/**
 * 弹框
 * @param title
 * @param url
 * @param width
 * @param height
 * @returns
 */
function showGialog(_title, _url, _width, _height){
	
}

/**
 * 关闭弹窗
 * @param id
 */
function colseGialog(id){
	
}