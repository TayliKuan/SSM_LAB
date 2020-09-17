
			
		
function init(){

	$(function() {
		
	    $( "#from" ).datepicker({
	    dateFormat:'yy-mm-dd',
	      defaultDate: "+1w",
	      changeMonth: true,
	      timepicker: false,
	      numberOfMonths: 3,
	      onClose: function( selectedDate ) {
	        $( "#to" ).datepicker( "option", "minDate", selectedDate );
	      }
	    });
	    $( "#to" ).datepicker({
	    dateFormat:'yy-mm-dd',
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 3,
	      onClose: function( selectedDate ) {
	        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
	      }
	    });
	  });
    
	
			var myFile = document.getElementById("myFile");
			var preview = document.getElementById("preview");
			var deletebtn = document.getElementById("deletebtn");

			myFile.addEventListener('change', function(e){
			//圖片檔案資料流
			var files = myFile.files;

			if(files !== null && files.length >0) {
				for(var i = 0; i < files.length; i++) {
					var file = files[i]; 

					if(file.type.indexOf('image') != -1){
						var reader = new FileReader();

						reader.addEventListener('load', function(e){
							var result = e.target.result;
							//把圖片結果輸出 建一個img標籤
							var img = document.createElement('img');
							img.src = result;
							img.setAttribute("name", "image");
							preview.append(img);
							//建一個checkbox標籤
							var checkbox = document.createElement('input');
							checkbox.setAttribute('type', 'checkbox');
							checkbox.setAttribute("name", "checkbox");
							preview.append(checkbox);
						});

						reader.readAsDataURL(file); 
					}
				}
			}
			
		});
		//刪除
		deletebtn.addEventListener('click',function(e){
			var img = document.getElementsByName('image');
			var checkbox = document.getElementsByName('checkbox');
			// console.log(checkbox[0]);
			for(var i =0 ; i<checkbox.length;i++){
				if(checkbox[i].checked){//P.145 input物件 選取欄位
					// console.log("ok");
					checkbox[i].remove();
					img[i].remove();
				 i--;//再回去判斷一次 不然只能一次刪一個
				}
			}
		});
		


	}

	window.onload = init;