$(document).ready(function() {
	// 닉네임 유효성 검사
	$("#username").on("focusout", function() {
		var username = $(this).val();
		if (username == '') {
			$("#usernameError").css("color", "red").text("사용할 닉네임을 입력해주세요.");
			return false;
		} else {
			$("#usernameError").text("");
		}

		$.ajax({
			url: './confirmusername',
			data: { 'username': username },
			type: 'POST',
			success: function(result) {
				if (result) {
					$("#usernameError").css("color", "green").text("사용 가능한 닉네임 입니다.");
				} else {
					$("#usernameError").css("color", "red").text("이미 사용중인 닉네임 입니다.");
					$("#username").val('');
				}
			}
		});
	});

	// 이메일 도메인 선택 또는 직접 입력 처리
	$('#email_address').on('change', function() {
		if ($(this).val() === 'direct') {
			$('#email_direct').show();
			$('#email_address').hide();
		} else {
			$('#email_direct').hide();
		}
	});

	// 회원가입 유효성 검사
	$('form').on('submit', function(e) {
		if (!registerCheck()) {
			e.preventDefault();
		}
	});
});




// 회원가입 유효성검사 ==================================================== 완벽..
function registerCheck() {
	var username = $('#username').val().trim();
	var email = $('#email').val().trim() + '@' + ($('#email_address').val() === 'direct' ? $('#email_direct').val().trim() : $('#email_address').val().trim());
	var password = $('#password').val().trim();
	var password2 = $('#password2').val().trim();


	if (password.length < 4) {
		$('#passwordError').text('비밀번호는 최소 4자 이상이어야 합니다.');
		return false;
	} else {
		$('#passwordError').text('');
	}

	if (password !== password2) {
		$('#password2Error').text('비밀번호가 일치하지 않습니다.');
		return false;
	} else {
		$('#password2Error').text('');
	}
	if (username === '') {
		$('#usernameError').text('닉네임을 입력하세요.');
		return false;
	} else {
		$('#usernameError').text('');
	}
	return true;
}


//이메일인증 인증번호
function sendNumber() {
	//	$("#mail_number").css("display", "block");
	//	  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	//    if (!emailRegex.test(email)) {
	//		console.log("error")
	//    } else {
	$('#emailError').text('');
	//이메일 주소@도메일 결합해서 가져옴 
	let emailp1 = $("#email").val()
	let emailp2 = "@"
	//let emailp3 = $("#email_address").val()
	let emailp3 = $("#email_address").val() === 'direct' ? $("#email_direct").val() : $("#email_address").val();
	let email = emailp1 + emailp2 + emailp3;
	console.log(email);
	var setemail = document.getElementById("sendemail");
	setemail.setAttribute('value', email);
	$.ajax({
		url: "/email",
		type: "GET",
		dataType: "json",
		data: {
			mail: email//$("#email").val()
		},
		success: function(response) {
			showFeedbackModal('인증 번호 전송', '이메일을 확인해주세요.');
		},
		error: function(xhr, status, error) {
			console.error("Error: ", status, error);
			showFeedbackModal('인증번호 전송 실패', '유효한 이메일인지 확인해주세요.');
		}
	});

	//      email2.innerHTML=`<input type='hidden' name='email' value=${emailc}/>`
	//		email2.before("#emailError")
	//	}

}// 이메일 인증번호 체크 함수
function confirmCode() {
	var code = document.getElementById('code').value;
	$.ajax({
		url: '/verify-code',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify({ code: code }),
		success: function(response) {
			if (response.valid) {

				showFeedbackModal('인증 성공', '이메일 인증이 성공적으로 완료되었습니다.');
				document.getElementById('Confirm').value = 'true';
			} else {

				showFeedbackModal('인증 실패', '인증 실패. 인증번호를 확인해주세요.');
				document.getElementById('emailError').innerHTML = '인증 실패. 인증번호를 확인해주세요.';
			}
		},
		error: function() {

			showFeedbackModal('서버 오류', '인증을 완료할 수 없습니다. 나중에 다시 시도해주세요.');
		}
	});
}