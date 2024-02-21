const login = document.querySelector('.side-menu .login');
const loginBox = login.querySelector('.loginBox');
const shadow = document.querySelector('.header .shadow');


login.addEventListener('click', function(e) {
  e.stopPropagation();
  if(loginBox.classList.contains('show') && shadow.classList.contains('show')) {
    loginBox.classList.remove('show');
    shadow.classList.remove('show');
  }else {
    loginBox.classList.add('show');
    shadow.classList.add('show');
  }
});
loginBox.addEventListener('click', function(e) {
  e.stopPropagation();
});

window.addEventListener('click', function() {
  loginBox.classList.remove('show');
  shadow.classList.remove('show');
});