   
			function initMenu() {

				var block = $(".day");
					block.addClass("clickable");
					block.hover(function(){window.status = $(this)}, function(){window.status = ""});
				
				$('.open').hide();
				block.click(
					function() {
						$(this).parents('div:eq(0)').find('.open').slideToggle('fast');	
					}

				);}


			$(document).ready(function() {initMenu();});


	