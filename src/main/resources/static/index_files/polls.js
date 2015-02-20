!function ($) {

    "use strict"; // jshint ;_;

    /* Poll PUBLIC CLASS DEFINITION
    * =============================== */

    var Poll = function ( element, options ) {
        this.init('polls', element, options);
    };

    Poll.prototype = {

        constructor: Poll,

        init: function (type, element, options) {

            this.type = type;
            this.$element = $(element);
            this.options = this.get_options(options);

            if (this.options.type == 'bar') {
                this.setup_bars_poll();
            }
            
            if (this.options.type == 'pie') {
                this.setup_pie_poll();            
            }
        },

        get_options: function (options) {
            //get the options from the data attribute and the defaults options
            options = $.extend({}, $.fn[this.type].defaults, options, this.$element.data());

            return options
        },

        _create_bar: function(bar_data) {
            var label = $('<span/>').addClass('bar-label');
            var bar = $('<div/>').addClass('bar');
            var vote_counter = $('<span/>').append(' ('+bar_data['value']+')');
            var bar_row = $('<div/>').addClass('bar-row');
            bar_row.data('bar_row_value', bar_data['value']);

            var bg = bar_data['color'];

            label.html(bar_data['label']);
            if (!this.options['show_labels']) {
                label.css('display', 'none');
            }
            if (this.options['show_votes']) {
                label.append(vote_counter);
            }

            bar.css({
                'background-color':bg,
                'height':this.options['bars_height'],
                'display':'block'
            });

            bar_row.css({
                'margin-bottom':this.options['bars_separation']
            });

            bar_row.append(label).append(bar);

            return bar_row
        },
        
        _calculate_values: function(total_votes){
            var global_this = this;
            var rows = this.$element.find('.bar-row');
            var max_width = this.options['width'] ? this.options['width'] : this.$element.width();
        
            rows.each(function(){
                var $this = $(this);
                var porcentual = 100 / total_votes * $this.data('bar_row_value');
                var porcentual_real_str = (porcentual+'').substring(0,4) + ' %';
                
                $this.find('.bar').css({
                    'width': max_width / 100 * porcentual + 'px'
                });
                
                if (global_this.options['show_porcentual']) {
                    $this.find('.bar').append($('<span/>')
                                      .html(porcentual_real_str));
                }
            })
        },

        setup_bars_poll: function () {
            var data = this.options.data;
            var bar = {};
            var label = 'label';
            var total_votes = 0;
            for(var i=0, len=data.length; i < len; i++){
                var $d = data[i];
                if (typeof $d == "number") {
                    bar['value'] = $d;
                } else {
                    bar['value'] = $d['data'];
                }
                bar['label'] = $d['label'] ? $d['label'] : label+i;
                bar['color'] = $d['color'];
                if (!$d['color']) {
                    bar['color'] = this.options['colors'][i];
                }
                total_votes += bar['value'];
                var bar_row = this._create_bar(bar);
                this.$element.append(bar_row);
            }
            
            this._calculate_values(total_votes);
        },
        
        setup_pie_poll: function () {
            var data = this.options.data;
            $.plot(this.$element, data,
	            {
		            series: {
			            pie: { 
			        	    show: true
			            }
		            },
		            legend: {
                        show: this.options.show_labels
                    }
	            }
            );
        }

    };


    /*
    *  POLLS PLUGIN DEFINITION
    */

    $.fn.polls = function (option) {
        return this.each(function () {
            var $this = $(this),
                data = $this.data('polls'),
                options = typeof option == 'object' && option;

            if (!data) $this.data('polls', (data = new Poll(this, options)));
            if (typeof option == 'string') data[option]();
        })
    };

    $.fn.polls.Constructor = Poll;

    $.fn.polls.defaults = {
        type: 'bar',
        width: undefined,
        bars_height: '14px',
        bars_separation: '10px',
        show_labels: true,
        show_votes: true,
        show_porcentual: true,
        colors: ['#599B10', '#008B88', '#009ADD', '#F1AD00', '#FA5600',
                 '#F8002A', '#599B10', '#008B88', '#009ADD', '#F1AD00', 
                 '#FA5600', '#F8002A'],
        data: []
    };

}(window.jQuery);
