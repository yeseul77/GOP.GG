

new Swiper('.duo .swiper', {
  autoplay: {
    delay: 5000
  },
  loop: true,
  spaceBetween: 10,
  slidesPerView: 3,

  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev'
  }
});

const back = document.querySelector('.back');
const randomNumber = Math.floor(Math.random()*5)+1;
back.style.backgroundImage = `url("/images/back${randomNumber}.jpg")`;
	