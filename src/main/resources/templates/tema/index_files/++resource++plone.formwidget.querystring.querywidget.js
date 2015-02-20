(function ($) {

    // Define querywidget namespace if it doesn't exist
    if (typeof($.querywidget) === "undefined") {
        $.querywidget = {
            config: {},
            initialized: false
        };
    }

    // Create a select menu
    $.querywidget.createSelect = function (values, selectedvalue, className, name) {

        // Create select
        var select = $(document.createElement('select'))
                            .addClass(className)
                            .attr('name', name);
        $.each(values, function (i, val) {
            if ((typeof(val.enabled) === "undefined") || (val.enabled)) {
                var option = $(document.createElement('option'))
                                .attr('value', i)
                                .html(val.title);
                if (i === selectedvalue) {
                    option.attr('selected', 'selected');
                }
                if (typeof(val.group) !== "undefined") {
                    var optgroup = select.find("optgroup[label=" + val.group + "]");
                    if (optgroup.length === 0) {
                        optgroup = $(document.createElement('optgroup'))
                                    .attr('label', val.group);
                        optgroup.append(option);
                        select.append(optgroup);
                    } else {
                        optgroup.append(option);
                    }
                } else {
                    select.append(option);
                }
            }
        });
        return select;
    };

    // Create a queryindex select menu
    $.querywidget.createQueryIndex = function (value, fname) {
        return $.querywidget.createSelect($.querywidget.config.indexes,
                            value,
                            'queryindex',
                            fname + '.i:records');
    };

    // Create a queryoperator select menu
    $.querywidget.createQueryOperator = function (index, value, fname) {
        return $.querywidget.createSelect($.querywidget.config.indexes[index].operators,
                            value,
                            'queryoperator',
                            fname + '.o:records');
    };

    $.querywidget.createWidget = function (type, index, fname) {
        switch (type) {
            case 'StringWidget':
                return $(document.createElement('input'))
                    .attr({
                        'autocomplete': 'off',
                        'type': 'text',
                        'name': fname + '.v:records'
                    })
                    .addClass('querywidget queryvalue stringWidget');
            case 'DateWidget':
                return $(document.createElement('input'))
                    .attr({
                        'autocomplete': 'off',
                        'type': 'text',
                        'name': fname + '.v:records'
                    })
                    .addClass('querywidget queryvalue dateWidget');
            case 'DateRangeWidget':
                return $(document.createElement('div'))
                    .addClass('querywidget dateRangeWidget')
                    .append($(document.createElement('input'))
                        .attr({
                            'autocomplete': 'off',
                            'type': 'text',
                            'name': fname + '.v:records:list'
                        })
                        .addClass('queryvalue')
                    )
                    .append($(document.createElement('span'))
                        .html(' and ')
                    )
                    .append($(document.createElement('input'))
                        .attr({
                            'autocomplete': 'off',
                            'type': 'text',
                            'name': fname + '.v:records:list'
                        })
                        .addClass('queryvalue')
                    );
            case 'ReferenceWidget':
                return $(document.createElement('dl'))
                    .addClass('querywidget referenceWidget')
                    .append($(document.createElement('dt'))
                        .html('Select...')
                        .addClass('hiddenStructure')
                    )
                    .append($(document.createElement('dd'))
                        .append($(document.createElement('input'))
                            .attr({
                                'autocomplete': 'off',
                                'type': 'text',
                                'name': fname + '.v:records'
                            })
                            .addClass('queryvalue')
                        )
                    );
            case 'RelativePathWidget':
                return $(document.createElement('input'))
                    .attr({
                        'autocomplete': 'off',
                        'type': 'text',
                        'name': fname + '.v:records'
                    })
                    .addClass('querywidget queryvalue relativePathWidget');
            case 'MultipleSelectionWidget':
                var dl = $(document.createElement('dl'))
                    .addClass('querywidget multipleSelectionWidget')
                    .append($(document.createElement('dt'))
                        .append($(document.createElement('span'))
                            .addClass('arrowDownAlternative')
                            .html('&#09660;')
                        )
                        .append($(document.createElement('span'))
                            .html('Select...')
                            .addClass('multipleSelectionWidgetTitle')
                        )
                    );
                var dd = $(document.createElement('dd')).addClass('hiddenStructure widgetPulldownMenu');
                $.each($.querywidget.config.indexes[index].values, function (i, val) {
                    dd.append($(document.createElement('label'))
                        .append($(document.createElement('input'))
                            .attr({
                                'type': 'checkbox',
                                'name': fname + '.v:records:list',
                                'value': i
                            })
                        )
                        .append($(document.createElement('span'))
                            .html(val.title)
                        )
                    );
                });
                dl.append(dd);
                return dl;
            default:
                return $(document.createElement('div'))
                    .html('&nbsp;')
                    .addClass('querywidget queryvalue emptyWidget');
        }
    };

    $.querywidget.getCurrentWidget  = function (node) {
        var classes = node.attr('class').split(' ');
        var i;
        for(i = 0; i<classes.length; i++) {
            if (classes[i].indexOf('Widget') !== -1) {
                var classname = classes[i];
                return classname.slice(0,1).toUpperCase() + classname.slice(1);
            }
        }
    };

    $.querywidget.updateSearch = function () {
        var context_url = (function() {
            var baseUrl, pieces;
            baseUrl = $('base').attr('href');
            if (!baseUrl) {
                pieces = window.location.href.split('/');
                pieces.pop();
                baseUrl = pieces.join('/');
            }
            return baseUrl;
        })();
        var query = context_url + "/@@querybuilder_html_results?";
        var querylist  = [];
        var items = $('.QueryWidget .queryindex');
        if (!items.length) {
            return;
        }
        items.each(function () {
            var results = $(this).parents('.criteria').children('.queryresults');
            var index = $(this).val();
            var operator = $(this).parents('.criteria').children('.queryoperator').val();
            var widget = $.querywidget.config.indexes[index].operators[operator].widget;
            querylist.push('query.i:records=' + index);
            querylist.push('query.o:records=' + operator);
            switch (widget) {
                case 'DateRangeWidget':
                    var daterangewidget = $(this).parents('.criteria').find('.querywidget');
                    querylist.push('query.v:records:list=' + $(daterangewidget.children('input')[0]).val());
                    querylist.push('query.v:records:list=' + $(daterangewidget.children('input')[1]).val());
                    break;
                case 'MultipleSelectionWidget':
                    var multipleselectionwidget = $(this).parents('.criteria').find('.querywidget');
                    multipleselectionwidget.find('input:checked').each(function () {
                        querylist.push('query.v:records:list=' + $(this).val());
                    });
                    break;
                default:
                    querylist.push('query.v:records=' + $(this).parents('.criteria').find('.queryvalue').val());
                    break;
            }

            $.get(context_url + '/@@querybuildernumberofresults?' + querylist.join('&'),
                  {},
                  function (data) { results.html(data); });
        });
        query += querylist.join('&');
        query += '&sort_on=' + $('#sort_on').val();
        if ($('#sort_order:checked').length > 0) {
            query += '&sort_order=reverse';
        }
        $.get(query, {}, function (data) { $('.QueryWidget .previewresults').html(data); });
    };

    /* Clicking outside a multipleSelectionWidget will close all open
       multipleSelectionWidgets */
    $.querywidget.hideMultiSelectionWidgetEvent = function(event) {
        if ($(event.target).parents('.multipleSelectionWidget').length) {
            return;
        }
        $('.multipleSelectionWidget dd').addClass('hiddenStructure');
    }


    // Enhance for javascript browsers
    $(document).ready(function () {

        // Check if QueryWidget exists on page
        if ($(".QueryWidget").length === 0) {
            return false;
        }

        // Init
        $.querywidget.init();

        // We need two keep two fields for each sorting field ('#sort_on',
        // '#sort_reversed'). The query results preview that calls
        // '@@querybuilder_html_results' in plone.app.querystring expects a
        // sort_on and sort_order param. To store the actual setting on the
        // collection we need the two z3c.form-based fields
        // ('#form-widgets-sort_on', '#form-widgets-sort_reversed')

        // Synchronize the '#sort_on' field with the hidden
        // #form-widgets-sort_on z3c.form field on load.
        $('#sort_on').val($('#form-widgets-sort_on').val());

        // Synchronize the '#sort_order' field with the hidden
        // #form-widgets-sort_reversed z3c.form field on load.
        if ($('#form-widgets-sort_reversed-0').attr('checked')) {
            $('#sort_order').attr('checked', true);
        } else {
            $('#sort_order').attr('checked', false);
        }

        // Synchronize the z3c.form '#form-widgets-sort_on' field
        // with the '#sort_on' field on user interaction
        $("#sort_on").live('click', function () {
            $('#form-widgets-sort_on').val($(this).val());
        });

        // Synchronize the z3c.form '#form-widgets-sort_reversed' field
        // with the '#sort_order' field on user interaction.
        $("#sort_order").live('click', function () {
            if ($(this).is(":checked")) {
                $('#form-widgets-sort_reversed-0').attr('checked', true);
            } else {
                $('#form-widgets-sort_reversed-0').attr('checked', false);
            }
        });

        // Hide the z3c.form widgets for sorting because they are only needed
        // internally.
        $('#formfield-form-widgets-sort_on').hide();
        $('#formfield-form-widgets-sort_reversed').hide();

    });

    // Init widget
    $.querywidget.init = function () {

        // Check if already initialized
        if ($.querywidget.initialized === true) {

            // Return nothing done
            return false;
        }

        // Set initialized
        $.querywidget.initialized = true;

        // Get configuration
        $.getJSON(portal_url + '/@@querybuilderjsonconfig', function (data) {
            $.querywidget.config = data;

            // Find querywidgets
            $(".QueryWidget").each(function () {

                // Get object
                var obj = $(this);
                var fname = obj.attr('data-fieldname');

                // Hide controls used for non-javascript only
                obj.find(".addIndexButton").hide();
                obj.find(".multipleSelectionWidget dt").removeClass('hiddenStructure');
                obj.find(".multipleSelectionWidget dd").addClass('hiddenStructure widgetPulldownMenu');

                $('div.queryindex').each(function () {
                    $(this).before(
                        $(document.createElement('div'))
                            .addClass('queryresults discreet')
                            .html('')
                    );
                    $(this).replaceWith($.querywidget.createQueryIndex($(this).children('input').val(), fname));
                });
                $('div.queryoperator').each(function () {
                    $(this).replaceWith($.querywidget.createQueryOperator($(this).parents('.criteria').children('.queryindex').val(),
                                                            $(this).children('input').val(), fname));
                });
                $.querywidget.updateSearch();
            });
        });

        $('.multipleSelectionWidget dt').live('click', function () {
            var multiselectionwidget = $(this).parent().children('dd');
            if(!$(multiselectionwidget).hasClass('hiddenStructure')) {
                $(multiselectionwidget).addClass('hiddenStructure');
                $(window).unbind('click', $.querywidget.hideMultiSelectionWidgetEvent);
            } else {
                $(multiselectionwidget).removeClass('hiddenStructure');
                $(window).bind('click', $.querywidget.hideMultiSelectionWidgetEvent);
            }
        });

        $('.queryindex').live('change', function () {
            var fname = $(this).closest('.QueryWidget').attr('data-fieldname');
            var index = $(this).find(':selected')[0].value;
            $(this).parents(".criteria").children('.queryoperator')
                .replaceWith($.querywidget.createQueryOperator(index, '', fname));
            var operatorvalue = $(this).parents('.criteria').children('.queryoperator').val();
            var widget = $.querywidget.config.indexes[index].operators[operatorvalue].widget;
            var querywidget = $(this).parent(".criteria").children('.querywidget');
            if ((widget !== $.querywidget.getCurrentWidget(querywidget)) || (widget === 'MultipleSelectionWidget')) {
                querywidget.replaceWith($.querywidget.createWidget(widget, index, fname));
            }
            $.querywidget.updateSearch();
        });

        $('.queryoperator').live('change', function () {
            var fname = $(this).closest('.QueryWidget').attr('data-fieldname');
            var index = $(this).parents('.criteria').children('.queryindex').val();
            var operatorvalue = $(this).children(':selected')[0].value;
            var widget = $.querywidget.config.indexes[index].operators[operatorvalue].widget;
            var querywidget = $(this).parent().children('.querywidget');
            if (widget !== $.querywidget.getCurrentWidget(querywidget)) {
                querywidget.replaceWith($.querywidget.createWidget(widget, index, fname));
            }
            $.querywidget.updateSearch();
        });

        $('#sort_on,#sort_order').live('change', function () {
            $.querywidget.updateSearch();
        });

        $('.multipleSelectionWidget input').live('change', function () {
            var widget = $(this).parents('.multipleSelectionWidget');
            var selected_values = [];
            widget.find('input:checked').each(function () {
                selected_values.push($(this).parent().children('span').html());
            });
            widget.find('.multipleSelectionWidgetTitle')
                .attr('title', selected_values.join(', '))
                .html(selected_values.join(', '));
            $.querywidget.updateSearch();
        });

        $('.queryvalue').live('keyup', function () {
            $.querywidget.updateSearch();
        });

        $('.queryvalue').live('keydown', function (e) {
            if (e.keyCode === 13) {
                return false;
            }
        });

        $('.addIndex').live('change', function () {
            var fname = $(this).closest('.QueryWidget').attr('data-fieldname');
            var index = $(this).find(':selected')[0].value;
            var criteria = $(this).parents('.criteria');
            var newcriteria = $(document.createElement('div'))
                                .addClass('criteria');

            newcriteria.append(
                    $(document.createElement('div'))
                        .addClass('queryresults discreet')
                        .html('')
                );
            newcriteria.append($.querywidget.createQueryIndex(index, fname));
            var operator = $.querywidget.createQueryOperator(index, '', fname);
            newcriteria.append(operator);
            var operatorvalue = $(operator.children()[0]).attr('value');
            newcriteria.append($.querywidget.createWidget($.querywidget.config.indexes[index].operators[operatorvalue].widget, index, fname));
            newcriteria.append(

                // How will we translate these values?

                $(document.createElement('input'))
                    .attr({
                        'value': 'Remove line',
                        'type': 'submit',
                        'name': 'removecriteria'
                    })
                    .addClass('removecriteria discreet')
            );
            criteria.before(newcriteria);
            $(this).val('');
            $.querywidget.updateSearch();
        });

        $('.removecriteria').live('click', function () {
            $(this).parents('.criteria').remove();
            $.querywidget.updateSearch();
            return false;
        });
    };

}(jQuery));
