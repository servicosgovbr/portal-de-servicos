jQuery(function($) {

  function contrasteOn() {
    Cookies.set('contraste', 'on', { path: '/' });
    $('body').addClass('contraste');
  }

  function contrasteOff() {
    Cookies.set('contraste', 'off', { path: '/' });
    $('body').removeClass('contraste');
  }

  $('#siteaction-contraste').find('a').click(function(e) {
    (Cookies.get('contraste') === 'on') ? contrasteOff() : contrasteOn();
    e.preventDefault();
    return false;
  });


  (Cookies.get('contraste') === 'on') ? contrasteOn() : contrasteOff();
});
