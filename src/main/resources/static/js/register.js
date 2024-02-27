$(document).ready(function() {
    $("#username").on("focusout", function() {
        var username = $(this).val();
        if(username == '') {
            $("#usernameError").css("color", "red").text("사용할 닉네임을 입력해주세요.");
            return false;
        } else {
            $("#usernameError").text("");
        }

        $.ajax({
            url: './confirmusername',
            data:
            { 
            	'username': username 
            	},
            type: 'POST',
            dataType: 'json',
            success: function(result) {
                if (result == true) {
                    $("#usernameError").css("color", "green").text("사용 가능한 닉네임 입니다.");
                } else {
                    $("#usernameError").css("color", "red").text("이미 사용중인 닉네임 입니다.");
                    $("#username").val('');
                }
            }
        });
    });

    $('#email_address').on('change', function() {
        var emailDomain = $(this).val();
        if(emailDomain === 'direct') {
            $('#email_direct').show();
        } else {
            $('#email_direct').hide();
        }
    });

    $('form').on('submit', function(e) {
        if (!registerCheck()) {
            e.preventDefault(); // 유효성 검사 실패 시, 폼 제출 방지
        }
    });
});
// 회원가입 유효성검사 =============================
function registerCheck() {
    var username = $('#username').val().trim();
    var email = $('#email').val().trim() + '@' + ($('#email_address').val() === 'direct' ? $('#email_direct').val().trim() : $('#email_address').val().trim());
   	//이메일 주소@도메일 결합해서 가져옴 
    var password = $('#password').val().trim();
    var password2 = $('#password2').val().trim();

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        $('#emailError').text('유효한 이메일 주소를 입력해주세요.');
        return false;
    } else {
        $('#emailError').text('');
    }

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
    $("#mail-Check-Btn").click(function() {
        var email = $("#email").val();
        $.ajax({
            type : "POST",
            url : "login/mailConfirm", // 해당 URL에 맞게 수정
            data : {
                "email" : email
            },
            success : function(data){
                alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.");
                console.log("data : " + data);
                chkEmailConfirm(data);
            }
        });
    });

    // 이메일 인증번호 체크 함수
    function chkEmailConfirm(emailConfirmCode){
        $("#code").on("keyup", function(){
            var inputCode = $(this).val();
            if (emailConfirmCode !== inputCode) {
                $("#emailError").html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>").css({
                    "color" : "#FA3E3E",
                    "font-weight" : "bold",
                    "font-size" : "10px"
                });
            } else {
                $("#emailError").html("<span id='emconfirmchk'>인증번호 확인 완료</span>").css({
                    "color" : "#0D6EFD",
                    "font-weight" : "bold",
                    "font-size" : "10px"
                });
            }
        });
    }
