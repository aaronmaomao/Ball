/**
 * common
 */
function dateFormat(date) {
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
}

function check(data) {
	if (data != null && data.isSuccess != null && data.isSuccess == "true") {
		return true;
	} else {
		$.messager.alert('失败', data.data);
		return false;
	}
}

/*******************************************************************************
 * cellStyler and cellFormat
 ******************************************************************************/
function idCellFormat(value, row, index) {
	var l = 5;
	if (row.mark == "getBallTimes") {
		l = 2
	}
	return PrefixInteger(value, l);
}

function redCellStyler(value, row, index) {
	return "color:#ff0000";
}

function redCellFormat(value, row, index) {
	var l = 2;
	if (row.mark == "getBallTimes") {
		l = 3
	}
	return PrefixInteger(value, 2);
}

function blueCellStyler(value, row, index) {
	return "color:#0000ff";
}

function blueCellFormat(value, row, index) {
	return PrefixInteger(value, 2);
}

function sumCellStyler(value, row, index) {
	return "color:#ff5fd4";
}

function sumCellFormat(value, row, index) {
	if (value == null) {
		value = row.r1 + row.r2 + row.r3 + row.r4 + row.r5 + row.r6;
	}
	return PrefixInteger(value, 3);
}

function avgCellStyler(value, row, index) {
	return "color:#1da80e";
}

function avgCellFormat(value, row, index) {
	if (value == null) {
		value = (row.r1 + row.r2 + row.r3 + row.r4 + row.r5 + row.r6) / 6.0;
	}
	return PrefixInteger(value, 2, 4);
}

// 补0输出
function PrefixInteger(num, length1, length2) {
	if (num == null) {
		return num;
	}
	var str = "0000000000" + num;
	var i = str.indexOf(".");
	if (i < 0) {
		str += ".0000000000";
	} else {
		str += "00000000";
	}
	var ary = str.split(".");
	var zheng = ary[0].substring(ary[0].length - length1, ary[0].length);
	var xiao = "";
	if (length2 != null) {
		xiao = "." + ary[1].substring(0, length2);
	}
	return zheng + xiao;
}
