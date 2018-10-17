/**
 * common
 */
function dateFormat(date) {
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate();
}

function check(data) {
	if (data != null && data.isSuccess != null && data.isSuccess == "true") {
		return true;
	} else {
		$.messager.alert('失败', data.data);
		return false;
	}
}
