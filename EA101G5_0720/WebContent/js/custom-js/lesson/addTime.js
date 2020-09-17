
		

	$(function() {
		 var somedate1 = new Date('2020-06-20');
	    $( "#from" ).datepicker({
	    dateFormat:'yy-mm-dd',
	      defaultDate: "+1w",
	      changeMonth: true,
	      timepicker: false,
	      numberOfMonths: 3,
          beforeShowDay: function(date) {
        	  if (  date.getYear() <  somedate1.getYear() || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
              ) {
                   return [false, ""]
              }
              return [true, ""];
          	}});
	    
	  });
