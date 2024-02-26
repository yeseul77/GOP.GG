<<<<<<< HEAD
const searchBar = document.querySelector('.search-bar');
const barRecord = searchBar.querySelector('.search-bar-record');

searchBar.addEventListener('click', function (e) {
  e.stopPropagation()
  if (barRecord.classList.contains('show')) {
    barRecord.classList.remove('show')
  } else {
    barRecord.classList.add('show')
  }
});

barRecord.addEventListener('click', function (e) {
  e.stopPropagation()
});

window.addEventListener('click', function () {
  barRecord.classList.remove('show')
});

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
=======
//const searchBar = document.querySelector('.search-bar');
//const barRecord = searchBar.querySelector('.search-bar-record');
//
//searchBar.addEventListener('click', function (e) {
//  e.stopPropagation()
//  if (barRecord.classList.contains('show')) {
//    barRecord.classList.remove('show')
//  } else {
//    barRecord.classList.add('show')
//  }
//});
//
//barRecord.addEventListener('click', function (e) {
//  e.stopPropagation()
//});
//
//window.addEventListener('click', function () {
//  barRecord.classList.remove('show')
//});
//
//new Swiper('.duo .swiper', {
//  autoplay: {
//    delay: 5000
//  },
//  loop: true,
//  spaceBetween: 10,
//  slidesPerView: 3,
//
//  navigation: {
//    nextEl: '.swiper-button-next',
//    prevEl: '.swiper-button-prev'
//  }
//});
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
