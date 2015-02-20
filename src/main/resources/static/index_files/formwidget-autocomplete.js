function formwidget_autocomplete_ready(event, data, formatted) {
    (function($) { 
        var input_box = $(event.target);
        formwidget_autocomplete_new_value(input_box,data[0],data[1]);
    }(jQuery));
}

function formwidget_autocomplete_new_value(input_box,value,label) {
    (function($) { 
        var base_id = input_box[0].id.replace(/-widgets-query$/,"");
        var base_name = input_box[0].name.replace(/\.widgets\.query$/,"");
        var widget_base = $('#'+base_id+"-input-fields");

        var all_fields = widget_base.find('input:radio, input:checkbox');
        
        // Clear query box and uncheck any radio boxes
        input_box.val("");
        widget_base.find('input:radio').prop('checked', false);
        
        // If a radio/check box for this value already exists, check it.
        var selected_field = widget_base.find('input[value="' + value + '"]');
        if(selected_field.length) {
            selected_field.each(function() { this.checked = true; });
            return;
        }

        widget_base, base_name, base_id
        // Create the box for this value
        var idx = all_fields.length;
        var klass = widget_base.data('klass');
        var title = widget_base.data('title');
        var type = widget_base.data('input_type');
        var multiple = widget_base.data('multiple');
        var span = $('<span/>').attr("id",base_id+"-"+idx+"-wrapper").attr("class","option");
        // Note that Internet Explorer will usually *not* let you set the name via setAttribute.
        // Also, setting the type after adding a input to the DOM is also not allowed.
        // Last but not least, the checked attribute doesn't always behave in a way you'd expect
        // so we generate this one as text as well.
        span.append($("<label/>").attr("for",base_id+"-"+idx)
                                 .append($('<input type="' + type + '"' +
                                                ' name="' + base_name + (multiple?':list"':'"') +
                                                ' checked="checked" />')
                                            .attr("id",base_id+"-"+idx)
                                            .attr("title",title)
                                            .attr("value",value)
                                            .addClass(klass)
                                        )
                                 .append(" ")
                                 .append($("<span>").attr("class","label").text(label))
                                 );
        widget_base.append(span);
    }(jQuery));
}

// Generate a result-parsing function that picks out fieldNum as the value
function formwidget_autocomplete_parser(formatResult, fieldNum) {
	return function(data) {
		var parsed = [];
        // If the server responds with 204 No Content, then data will not
        // be a string.
        if(data.split){
            var rows = data.split("\n");
            for (var i=0; i < rows.length; i++) {
                var row = $.trim(rows[i]);
                if (row) {
                    row = row.split("|");
                    parsed[parsed.length] = {
                        data: row,
                        value: row[fieldNum],
                        result: formatResult(row, row[fieldNum])
                    };
                }
            }
        }
		return parsed;
	};
}
