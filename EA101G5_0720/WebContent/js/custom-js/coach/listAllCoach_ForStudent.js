
 function arrayBufferToBase64(buffer) {
	var binary = '';
	var bytes = new Uint8Array(buffer);
	var len = bytes.byteLength;
	for (var i = 0; i < len; i++) {
		binary += String.fromCharCode(bytes[i]);
	}
	return window.btoa(binary);
}

$(document).ready(function() {
	$("#table").bootstrapTable({
		method : "GET",
		url : context + "/coach/coach.do",
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ],
		search : true,
		columns : [ {
			field : 'coano',
			title : '編號',
			align : 'center',
			sortable : true
		}, {
			field : 'coaname',
			title : '姓名',
			align : 'center',
			sortable : true
		}, {
			field : 'coapic',
			title : '照片',
			formatter : function(value, row, index) {
				return '<img src="data:image/png;base64,' + arrayBufferToBase64(row.coapic) + '" width="100px;" height="100px;"/>'
			}
		}, {
			field : 'coasex',
			title : '性別',
			align : 'center',
			sortable : true
		}, {
			field : 'expdescs',
			title : '專長',
			align : 'center',
			sortable : true,
			formatter : function(value, row, index) {
				// value.join(",") converts a list into string with specific
				// separator
				return value.join("<br>");
			}
		},{
			title : '總評價(1-5分)',
			align : 'center',
			formatter: function(value, row,index){
				var starHtml =  '<div class="star activity">';
				for (var i=0;i<row.coasctotal;i++) {
					starHtml += '<i class="fa fa-lg fa-star fa-goyellow" aria-hidden="true"></i>';
				}
				for (var i=0;i<5-row.coasctotal;i++){
					starHtml += '<i class="fa fa-lg fa-star-o" aria-hidden="true"></i>';
				}
				starHtml += '</div>'
				return starHtml;
			},
		}, {
			field : 'coascqty',
			title : '總評價人數',
			align : 'center',
			sortable : true
		}, {
			title : '教練詳請',
			formatter : function(value, row, index) {
				return `<form METHOD="post"
						action="${context}/coach/coach.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-outline-primary" value="詳情">
						<input type="hidden" name="action" value="getOne">
						<input type="hidden" name="coano" value="${row.coano}">
					</form>`
			}
		} ],
	})
	$("#table").bootstrapTable('filterBy',{'coasta':["授權"]})
});
