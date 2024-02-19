// 현재 username에<!-- <input> 영역의 입력 상황에 따라 <button>을 활성화 또는 비활성화  -->
document.addEventListener('DOMContentLoaded', function () {
    var inputUsername = document.getElementById('username');
    var submitButton = document.querySelector('input[type="submit"]');
    var originalUsername = inputUsername.value;

    submitButton.disabled = true;

    inputUsername.addEventListener('input', function() {
        var currentUsername = inputUsername.value;
        submitButton.disabled = !(currentUsername.length > 0 && currentUsername !== originalUsername);
    });
});

//==================================
    document.addEventListener('DOMContentLoaded', function () {
        // 삭제버튼
        var btnDelete = document.getElementById('btnDelete');
        if (btnDelete) {
            btnDelete.addEventListener('click', function() {    
                alert('프로필 이미지가 삭제되었습니다.'); 
            });
        }
    });