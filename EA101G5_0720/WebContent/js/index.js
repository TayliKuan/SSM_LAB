document.querySelector('.img__btn').addEventListener('click', function() {
	document.querySelector('.cont').classList.toggle('s--signup');
});

$(document).ready(function() {

	$("#logout-btn").click(function() {
		$.ajax({
			type : "post",
			url : context + "/logout.do",
			success : function(data, status) {
				alert('登出成功');
				window.location.href = context + "/front-end/index.jsp";
			},
			error : function() {
				alert('登出失敗');
			},
			complete : function() {
			}
		});
	})

	$("#student-login-btn").click(function() {
		$.ajax({
			type : "post",
			data : $(this).parent().serialize(),
			url : context + "/loginForStudent.do",
			dataType : 'json',
			success : function(data, status) {
				if (data.error_code === "0") {
					alert('登入成功！');
					location.reload();
				} else {
					alert(data.error_msg);
				}
			},
			error : function() {
				alert('帳號或密碼錯誤！');
			},
			complete : function() {
			}
		});

	})

	$("#coach-login-btn").click(function() {
		$.ajax({
			type : "post",
			data : $(this).parent().serialize(),
			url : context + "/loginForCoach.do",
			dataType : 'json',
			success : function(data, status) {
				if (data.error_code === "0") {
					alert('登入成功！');
					location.reload();
				} else {
					alert(data.error_msg);
				}
			},
			error : function() {
				alert('帳號或密碼錯誤！');
			},
			complete : function() {
			}
		});
	})

	$("#sign-up-coach").click(function() {
		window.location.href = context + "/front-end/coach/addCoach.jsp";
	});

	$("#sign-up-student").click(function() {
		window.location.href = context + "/front-end/student/addStudent.jsp";
	});

	$("#logo").click(function() {
		window.location.href = context + "/front-end/index.jsp";
	});

	$(".forgot-pass.coach").click(function() {
		window.open(context + "/front-end/forgetPsw.jsp?title=coach", '忘記密碼', config = 'height=403,width=640');
	});

	$(".forgot-pass.student").click(function() {
		window.open(context + "/front-end/forgetPsw.jsp?title=student", '忘記密碼', config = 'height=403,width=640');
	});
});
