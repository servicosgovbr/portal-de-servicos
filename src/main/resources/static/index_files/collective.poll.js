!function ($) {

    "use strict"; // jshint ;_;

    var DrawPoll = function (element, options) {
        this.init(element);
    };

    DrawPoll.prototype = {

        constructor: DrawPoll,

        init : function (poll) {
            this.poll = $(poll);
            this.ajax_submit();
            this.content_handler = this.get_handler();
            if (this.content_handler[0] !== undefined ) {
                this.type = this.get_type();
                this.data = this.get_data();
                this.extra_conf = {'width':undefined};

                switch (this.type) {
                    //XXX at some poing we are going to have more cases :P
                    case 'portlet':
                        this.setup_portlet(this.data.type);
                        break;
                    default:
                        this.setup_default(this.data.type);
                }

                this.draw(this.data, this.content_handler, this.extra_conf);
            }
        },

        draw : function(data, content_handler, extra_conf) {
            content_handler.polls({
                width: extra_conf.width,
                type : data.type,
                data : data.result_data
            });
        },

        get_data : function(poll) {
            var poll = poll !== undefined ? poll : this.poll,
                raw_results = poll.find('.poll-results'),
                result = {},
                result_data = [];

            raw_results.find('li').each(function(i){
                var li = $(this),
                    data = {};

                data['label'] = li.find('.option_description').text();
                data['data'] = li.find('.option_result').text()*1;
                result_data[i] = data;
            });

            result['type'] = this.get_graph_type(poll);
            result['result_data'] = result_data;
            raw_results.hide();

            return result
        },

        get_handler : function(poll) {
            var poll = poll !== undefined ? poll : this.poll,
                handler = poll.find('[class*=pollresultholder]');

            return handler
        },

        get_type : function(handler) {
            var handler = handler !== undefined ? handler : this.content_handler,
                re = /\w+(?=pollresultholder)/,
                match = handler.attr('class').match(re),
                type  = match ? match[0] : match;

            if (type === undefined) {
                type = 'default';
            }

            return type
        },

        get_graph_type : function(poll) {
            var poll = poll !== undefined ? poll : this.poll,
                re = /\w+(?=-poll)/,
                match = poll.attr('class').match(re),
                type  = match ? match[0] : match;

            return type
        },

        setup_portlet : function(type) {
            if (type == 'pie') {
                this.content_handler.height(150);
            }
        },
        setup_default : function(type) {
            var width = 350;
            switch (type) {
                case 'pie':
                    this.content_handler.height(200);
                    this.content_handler.width(width);
                    break;
                case 'bar':
                    width = 300;
                    this.content_handler.width(width);
                    break;
            }
            this.extra_conf['width'] = width;
        },

        ajax_submit : function() {
            this.poll.submit(function(event) {
                var $this = $(this);
                var $parent = $this.parents('.votePortlet');
                var url = $this.attr('action');
                var data = $this.serialize()+'&ajax_load=True&poll.submit=True';
                //show the ajax load spinner
                $('.poll-spinner', $parent).show();
                $this.css({'visibility': 'hidden'});

                $.ajax({
                    url: url,
                    data: data,
                    type: 'POST',
                    success: function(html){
                        //gets the portlet assigment column
                        var manager = ''
                        if ($parent[0] !== undefined){
                            manager = $parent.attr('data-manager');
                        }
                        if (! manager){
                            // Portlet reload will not work, so we refresh the page.
                            window.location = window.location;
                        }
                        else {
                            // Get the base for the url, which could
                            // be the url of the front-page.
                            var base = $parent.attr('data-url');
                            if (! base) {
                                base = '.';
                            }
                            $.ajax({
                                url: base + '/@@poll_portlet_render',
                                data: {'column':manager},
                                success:function(data){
                                    $parent.replaceWith(data);
                                    $('.poll-data').drawpoll();
                                    $('.spinner', $parent).hide();
                                }
                            })
                        };

                    }
                });
                return false;
            });
        }
    };

    /*we define a jquery plugin, in that way we can go out of the js scope
     and yes i know, cluttering the global namespace*/

    $.fn.drawpoll = function (option) {
        return this.each(function () {
            var $this = $(this),
                data = $this.data('drawpoll'),
                options = typeof option == 'object' && option;

            if (!data) $this.data('drawpoll', (data = new DrawPoll(this, options)));
            if (typeof option == 'string') data[option]();
        })
    };

    $.fn.drawpoll.Constructor = DrawPoll;

    $(function () {
        $('.poll-data, .votePortlet form').drawpoll();
    });

}(window.jQuery);