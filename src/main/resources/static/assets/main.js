jQuery(document).ready(function($) {

  $('.portletNavigationTree .portletHeader').click(function() {
    $(this).next().slideToggle();
    $(this).find('i.fa').toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
  });

  $('ul li:last-child').addClass('last-item');
  $('#portal-column-one div:first-child').addClass('first-item');

  $('form').each(function(_, form) {
    $(form).validate();
  });
});
