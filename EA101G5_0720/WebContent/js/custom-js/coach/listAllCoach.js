
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
		}, {
			field : 'coaname',
			title : '姓名',
			align : 'center'
		}, {
			field : 'coamail',
			title : '信箱',
			align : 'center'
		}, {
			field : 'coatel',
			title : '電話',
			align : 'center'
		}, {
			field : 'coasta',
			title : '狀態',
			editable : {
				type : 'select',
				title : '狀態',
				source : [ {
					text : "授權",
					value : "授權"
				}, {
					text : "未授權",
					value : "未授權"
				}, {
					text : "停權",
					value : "停權"
				} ]
			},
			sortable : true
		}, {
			field : 'coapic',
			title : '照片',
			formatter : function(value, row, index) {
				return '<img src="data:image/png;base64,' + arrayBufferToBase64(row.coapic) + '" width="80px;" height="80px;"/>'
			}
		}, {
			field : 'coasex',
			title : '性別',
			align : 'center'
		}, {
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
			align : 'center'
		}, {
			title : '專長',
			formatter : function(value, row, index) {
				return `<form METHOD="post"
						action="${context}/coach/coach.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-outline-primary" value="詳情">
						<input type="hidden" name="action" value="getOneForBackend">
						<input type="hidden" name="coano" value="${row.coano}">
					</form>`
			}
		} ],
		onEditableSave : function(field, row, oldValue, $el) {
			$.ajax({
				type : "post",
				url : context + "/coach/coach.do",
				data : "action=BackendUpdate&coano=" + row.coano + "&coasta=" + row.coasta,
				success : function(data, status) {
					if (data.error_code == "0") {
						alert('提交資料成功');
					}
				},
				error : function() {
					alert('編輯失敗');
				},
				complete : function() {
				}
			});
		},
	})
});
