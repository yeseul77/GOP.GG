$(document).ready(function() {
    var inputUsername = document.getElementById('username');
    var submitButton = document.querySelector('.submit-btn');
    var originalUsername = inputUsername ? inputUsername.value : '';

    if (submitButton && inputUsername) {
        submitButton.disabled = true;
        inputUsername.addEventListener('input', function() {
            var currentUsername = inputUsername.value.trim();
            submitButton.disabled = !(currentUsername.length > 0 && currentUsername !== originalUsername);
        });
    }

    $("#username").on("focusout", function() {
        var username = $(this).val().trim();
        if (username === '') {
            $('#modalMessage').text("사용할 닉네임을 입력해주세요.");
            $('#usernameModal').modal('show');
            return false;
        } 

        $.ajax({
            url: '/confirmusername',
            data: { 'username': username },
            type: 'POST',
            success: function(result) {
                if (result) {
                    $('#modalMessage').text("사용 가능한 닉네임입니다.");
                } else {
                    $('#modalMessage').text("이미 사용 중인 닉네임입니다.");
                    $("#username").val('');
                }
                $('#usernameModal').modal('show');
            },
            error: function() {
                $('#modalMessage').text("닉네임 검사 중 오류가 발생했습니다.");
                $('#usernameModal').modal('show');
            }
        });
    });
});
