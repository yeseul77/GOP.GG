//비밀번호 유효성 검사

function validdateUserPw() {
  const userPwInput = document.getElementById('password');
  const userPw = userPwInput.value.trim();

  const errorUserPw = document.getElementById('errorUserPw');
  const successUserPw = document.getElementById('successUserPw');

  errorUserPw.textContent = '';
  successUserPw.textContent = '';

  if (userPw === '') {
    return;
  }

  if (/[\u3131-\uD79D]/.test(userPw) || userPw.length < 4) {
    errorUserPw.textContent = '올바르지 않는 형식입니다.';
  } else {
    successUserPw.textContent = '사용 가능한 비밀번호입니다.'
  }
}

//비밀번호 확인 유효성 검사

function validdatePwMatch() {
  const userPwReInput = document.getElementById('password2');
  const userPwRe = userPwReInput.value.trim();
  const userPwInput = document.getElementById('password');
  const userPw = userPwInput.value.trim();

  const errorMatch = document.getElementById('errorMatch');
  const successMatch = document.getElementById('successMatch');

  errorMatch.textContent = '';
  successMatch.textContent = '';

  if (userPwRe === '') {
    return;
  }

  if (userPwRe === userPw) {
    successMatch.textContent = '비밀번호가 일치합니다.';
  } else {
    errorMatch.textContent = '비밀번호가 다릅니다.';
  }
}

//닉네임 유효성 검사

function validdateUserNickname() {
  const userNicknameInput = document.getElementById('userName');
  const userNickname = userNicknameInput.value.trim();
  
  const errorNickname = document.getElementById('errorNickname');
  const successNickname = document.getElementById('successNickname');	
	
  errorNickname.textContent = '';
  successNickname.textContent = '';
  
  function signUpComplete() {
    const userIdSuccess = document.getElementById('successEmail');
    const userPwSuccess = document.getElementById('successUserPw');
    const userPwMatchSuccess = document.getElementById('successMatch');
    const userNameInput = document.getElementById('userName');
    
    const btnSignUp = document.querySelector('.btnSignUp');
    
    function updateButtonState() {
      const isAnyfieldEmpty = !userIdSuccess.textContent.trim() || !userPwSuccess.textContent.trim() || !userPwMatchSuccess.textContent.trim() || !successNickname.textContent.trim();
      btnSignUp.disabled = isAnyfieldEmpty;
    }
    
    userNameInput.addEventListener('input', updateButtonState);	
    
    const observer = new MutationObserver(updateButtonState); 
    
    document.querySelectorAll('.success-message').forEach(function (e) {
      observer.observe(e, {
        subtree: true,
        characterDate: true,
        childList: true
      })
    }); 
   }

  if (userNickname === '') {
    return;
  }
  
  if (/[!@#$%^&*(),.?":{}|<>]/.test(userNickname) || userNickname.length < 4) {
    errorNickname.textContent = '올바르지 않는 형식입니다.';
    signUpComplete();
  } else {
    successNickname.textContent = '사용 가능한 닉네임입니다.'
  }
  	
}

const back = document.querySelector('.back');
const randomNumber = Math.floor(Math.random()*5)+1;
back.style.backgroundImage = `url("/images/back${randomNumber}.jpg")`;

