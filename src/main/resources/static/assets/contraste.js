jQuery(function($) {
  $('#siteaction-contraste a').click(function(e) {
    if ($.cookie('contraste') === null || $.cookie('contraste') !== 'on') {
      $.cookie('contraste', 'on');
      $('body').addClass('contraste');
    } else {
      $.cookie('contraste', 'off');
      $('body').removeClass('contraste');
    }
    e.preventDefault();
    return false;
  });

  if ($.cookie('contraste') === 'on') {
    $('body').addClass('contraste');
    return false;
  }
});
