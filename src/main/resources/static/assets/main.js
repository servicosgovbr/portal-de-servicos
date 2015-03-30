jQuery(document).ready(function($) {

  $('.portletNavigationTree .portletHeader').click(function() {
    $(this).next().slideToggle();
    $(this).find('i.fa').toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
  });

  var menuTrigger = $('.menuTrigger');
  var navigationEl = $('#navegacao');
  menuTrigger.click(function(e) {
    e.preventDefault();
    navigationEl.toggleClass('ativo');
  });

  var sectionTrigger = $('.mobile .portletNavigationTree dt a');
  sectionTrigger.append('<span></span>');
  sectionTrigger.click(function(e) {
    e.preventDefault();
    $(this).parent().parent().toggleClass('ativo');
  });

  $('ul li:last-child').addClass('last-item');
  
  var responsiveResize, root;
  root = typeof exports !== 'undefined' && exports !== null ? exports : this;
  root.ResponsiveResize = function() {
    var _Singleton, _base;
    _Singleton = function() {
      function _Singleton() {}
      _Singleton.prototype.perspectiva_anterior = '';
      _Singleton.prototype.scrollbar = false;
      _Singleton.prototype.resize = function() {
        var perspectiva_atual;
        if ($(window).width() <= 480) {
          perspectiva_atual = 'mobile';
        } else {
          perspectiva_atual = 'desktop';
        }
        if (this.perspectiva_anterior !== perspectiva_atual) {
          this.perspectiva_anterior = perspectiva_atual;
          if (perspectiva_atual === 'mobile') {
            $('body').addClass('mobile');
          } else {
            $('body').removeClass('mobile');
          }
        }
      };
      return _Singleton;
    }();
    if ((_base = root.ResponsiveResize).instance == null) {
      _base.instance = new _Singleton();
    }
    return root.ResponsiveResize.instance;
  };

  var resize = function() {
    responsiveResize = new root.ResponsiveResize();
    responsiveResize.resize();
  };

  $(window).resize(function() {
    resize();
  });

  resize();

  $('#portal-column-one div:first-child').addClass('first-item');
});
