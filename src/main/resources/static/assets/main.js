jQuery(document).ready(function($) {

  $('.portletNavigationTree .portletHeader').click(function() {
    $(this).next()
		.slideToggle();

    $(this).find('i.fa')
		.toggleClass('fa-chevron-up')
		.toggleClass('fa-chevron-down');
  });

  $('form').each(function(_, f) {
    $(f).validate();
  });
});
