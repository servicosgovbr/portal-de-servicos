jQuery(document).ready(function($) {

  $('#ir-para-busca').click(function(e) {
    $(e.target.hash).focus();
  });

  $('.nav-tree dt').click(function() {
    $(this).next()
      .slideToggle();

    $(this).find('i.fa')
      .toggleClass('fa-chevron-up')
      .toggleClass('fa-chevron-down');
  });

  $('form').each(function(_, f) {
    $(f).validate();
  });

  $('.paginacao-letras select').on('change', function() {
    window.location = $(this).val();
  });
});
