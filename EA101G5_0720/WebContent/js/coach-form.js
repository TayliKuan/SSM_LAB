$(document).ready(function() {
	
	
     $("#save-btn").click(function(){

    	 $('#file').change(function() {
    		  var file = $('#file')[0].files[0];
    		  var reader = new FileReader;
    		  reader.onload = function(e) {
    		    $('#demo').attr('src', e.target.result);
    		  };
    		  reader.readAsDataURL(file);
    		});
    	 
//         console.log($("#add-coach-form").serialize());
         
         document.addCoachForm.submit();

    //     $.ajax({
    //         type: 'POST',
    //         url: 'some.php',
    //         data: {name: 'John', location: 'Boston'},
    //         success: function(msg) {
    //         alert('Data Saved: ' + msg);
    //         }
    //     });


         alert("save!");
     });

});