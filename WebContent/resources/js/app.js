/**
 * 显示请求失败的异常错误消息提示
 * 
 * @param {}
 *            data
 */
function showErrorMsg(data) {
	// 如果是form提交后的异常处理
	if (data.responseText == null || data.responseText == 'undefined') {
		var msg = '系统异常, 请稍后再试！';
		var result = $.parseJSON(data);
 
		if (result.debug == 'true' || result.debug == true) {
			if (result.msg == null) {
				msg = '系统异常, 请稍后再试！';
			} else {
				msg = result.msg;
			}
		}
		showMsg(msg);
		return;
	}
	// 其他处理方式
	$.messager.progress('close');
	if (data.status == '404') {
		showMsg('无效的请求！');
	} else if (data.status == '500') {
		var msg = '系统异常, 请稍后再试！';
		var result = $.parseJSON(data.responseText);
		if (result.debug == 'true' || result.debug == true) {
			if (result.msg == null) {
				msg = '系统异常, 请稍后再试！';
			} else {
				msg = result.msg;
			}
		}
		showMsg(msg);
	} else {
		showMsg('系统异常，请稍后再试！');
	}
}

/**
 * 显示提示消息
 * 
 * @param {}
 *            msg
 * @param {}
 *            type success, fail
 */
function showMsg(msg, type) {
	if (type == 'success') {
		$.messager.show({
			title : '消息',
			msg : msg,
			showType : 'slide',
			timeout : 2000
		});
		return;
	}
	$.messager.alert('消息', msg, 'info');
}

/**
 * form提交之前的验证
 * @param obj
 * @returns {Boolean}
 */
function validSubmit(obj) {
	var isValid = $(obj).form('validate');
	if (isValid == false) {
		$.messager.progress('close');
		return false;
	}
	return true;
}

/**
 * 格式化日期
 * @param date
 * @returns
 */
function formatTime(date) {
	if(date == null){
		return '';
	}
	var now = new Date(date);
	var year = now.getFullYear();    
    var month = now.getMonth() + 1;     
    var day = now.getDate();     
    var hour = now.getHours();     
    var minute = now.getMinutes();     
    var second = now.getSeconds();     
    return  year + "-" + get0(month) + "-" + get0(day) + " " + get0(hour) + ":" + get0(minute) + ":" + get0(second);
}

/**
 * 格式化日期
 * @param date
 * @returns
 */
function formatDate(date) {
	if(date == null){
		return null;
	}
	var now = new Date(date);
	var year = now.getFullYear();    
    var month = now.getMonth() + 1;     
    var day = now.getDate();     
    return  year + "-" + get0(month) + "-" + get0(day);
}

function get0(val){
	if(val < 10){
		return '0' + val;
	}
	return val;
}


function formatMoney(num) {
	if(num == null){
		return null;
	}
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}

// 控制台输出调试信息
function C(str) {
	console.log(str);
}