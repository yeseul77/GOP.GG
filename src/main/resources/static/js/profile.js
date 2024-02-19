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