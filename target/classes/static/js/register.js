$(document).ready(function() {
    // 닉네임 AJAX 검증
    $("#username").on("focusout", function() {
        var username = $(this).val();
        if(username === '') {
            $("#usernameError").css("color", "red").text("사용할 닉네임을 입력해주세요.");
            return false;
        } else {
            $("#usernameError").text("");
        }

        $.ajax({
            url: './confirmusername',
            data: { 
                'username': username 
            },
            type: 'POST',
            dataType: 'json',
            success: function(result) {
                if (result === true) {
                    $("#usernameError").css("color", "green").text("사용 가능한 닉네임 입니다.");
                } else {
                    $("#usernameError").css("color", "red").text("이미 사용중인 닉네임 입니다.");
                    $("#username").val('');
                }
            }
        });
    });

    // 이메일 인증번호 발송
    $("#mail-Check-Btn").click(function() {
        var email = $('#email').val().trim();
        if(email === '') {
            alert("이메일 주소를 입력해주세요.");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/login/mailConfirm",
            data: {
                "email": email
            },
            success: function(data) {
                alert("해당 이메일로 인증번호 발송이 완료되었습니다. 확인 부탁드립니다.");
            },
            error: function(xhr, status, error) {
                alert("인증번호 발송에 실패하였습니다. 다시 시도해 주세요.");
            }
        });
    });

    // 폼 제출 전 유효성 검사
    $('form').on('submit', function(e) {
        if (!registerCheck()) { 
            e.preventDefault(); // 폼 제출 중단
        }
    });
});

function registerCheck() {
    var username = $('#username').val();
    var email = $('#email').val().trim();
    var password = $('#password').val();
    var password2 = $('#password2').val();

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