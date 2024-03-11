$(document).ready(function() {
    // 이메일 유효성 검사
    $("#email").on("focusout", function() {
        var email = $(this).val();
        if (email === '') {
            $("#emailError").text("이메일을 입력해주세요.");
            return false;
        } else {
            $("#emailError").text("");
        }
    });

    // 임시 비밀번호 발송 함수
    function sendTempPassword() {
        var email = $('#email').val().trim();
        $.ajax({
            url: "/send-pwd",
            type: "GET",
            data: {
                mail: email
            },
            success: function(response) {
                if (response.message === '임시 비밀번호 발송 성공') {
                    showFeedbackModal('임시 비밀번호 전송', '이메일을 확인해주세요!');
                    setTimeout(function() {
                        window.location.href = "/";
                    }, 3000); 
                } else {
                    showFeedbackModal('오류', '임시 비밀번호 전송에 실패했습니다. 이메일 주소를 확인해주세요.');
                }
            },
            error: function(xhr, status, error) {
                console.error("Error:", xhr.status, error);
                var errorMessage = "오류가 발생했습니다.";
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                }
                showFeedbackModal("오류", errorMessage);
            }
        });
    }

    $("#findpwForm").on("submit", function(e) {
        e.preventDefault();
        sendTempPassword();
    });

    function showFeedbackModal(title, message) {
        alert(title + "\n\n" + message);
    }
});
