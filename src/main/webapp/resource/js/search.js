function hideTable() {
	var item = document.getElementById('tb');
	if (!item)
		return;
	item.parentNode.removeChild(item);
}
function showTable(data, inputid) {
	var tb = document.createElement('table');
	tb.cellPadding = 2, tb.cellSpacing = 0, tb.id = 'tb';
	var tbody = document.createElement('tbody');
	for (var i = 0, len = data.length; i < len; i++) {
		var tr = document.createElement('tr'), td = document
				.createElement('td');
		td.innerHTML = data[i];
		tr.appendChild(td);
		tbody.appendChild(tr);
	}
	tb.appendChild(tbody);
	document.body.insertBefore(tb, document.getElementsByTagName('script')[0]);
	
	tbody.setAttribute("onclick","clickitem(e,'"+inputid+"')");
	var obj = document.getElementsByTagName('td');
	for (var i = 0, len = obj.length; i < len; i++) {
		obj[i].onmouseover = function() {
			this.className = 'hover';
		};
		obj[i].onmouseout = function() {
			this.className = '';
		}
	}
}
function clickitem(e,inputid){
	var e = e ? e : window.e;
	var target = e.target || e.srcElement;
	var wd = target.innerHTML;
	document.getElementById(inputid).value = wd;
}