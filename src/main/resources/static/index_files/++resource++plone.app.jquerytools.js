/*
 
 jQuery Tools v1.2.7 Overlay - Overlay base. Extend it.

 NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.

 http://flowplayer.org/tools/overlay/

 Since: March 2008
 Date: 2012-04-30 14:24 
*/
(function(a){function t(d,b){var c=this,j=d.add(c),o=a(window),k,f,m,g=a.tools.expose&&(b.mask||b.expose),n=Math.random().toString().slice(10);if(g){if(typeof g=="string")g={color:g};g.closeOnClick=g.closeOnEsc=false}var p=b.target||d.attr("rel");f=p?a(p):d;if(!f.length)throw"Could not find Overlay: "+p;d&&d.index(f)==-1&&d.click(function(e){c.load(e);return e.preventDefault()});a.extend(c,{load:function(e){if(c.isOpened())return c;var h=q[b.effect];if(!h)throw'Overlay: cannot find effect : "'+b.effect+
'"';b.oneInstance&&a.each(s,function(){this.close(e)});e=e||a.Event();e.type="onBeforeLoad";j.trigger(e);if(e.isDefaultPrevented())return c;m=true;g&&a(f).expose(g);var i=b.top,r=b.left,u=f.outerWidth(true),v=f.outerHeight(true);if(typeof i=="string")i=i=="center"?Math.max((o.height()-v)/2,0):parseInt(i,10)/100*o.height();if(r=="center")r=Math.max((o.width()-u)/2,0);h[0].call(c,{top:i,left:r},function(){if(m){e.type="onLoad";j.trigger(e)}});g&&b.closeOnClick&&a.mask.getMask().one("click",
c.close);b.closeOnClick&&a(document).on("click."+n,function(l){a(l.target).parents(f).length||c.close(l)});b.closeOnEsc&&a(document).on("keydown."+n,function(l){l.keyCode==27&&c.close(l)});return c},close:function(e){if(!c.isOpened())return c;e=e||a.Event();e.type="onBeforeClose";j.trigger(e);if(!e.isDefaultPrevented()){m=false;q[b.effect][1].call(c,function(){e.type="onClose";j.trigger(e)});a(document).off("click."+n+" keydown."+n);g&&a.mask.close();return c}},getOverlay:function(){return f},getTrigger:function(){return d},
getClosers:function(){return k},isOpened:function(){return m},getConf:function(){return b}});a.each("onBeforeLoad,onStart,onLoad,onBeforeClose,onClose".split(","),function(e,h){a.isFunction(b[h])&&a(c).on(h,b[h]);c[h]=function(i){i&&a(c).on(h,i);return c}});k=f.find(b.close||".close");if(!k.length&&!b.close){k=a('<a class="close"></a>');f.prepend(k)}k.click(function(e){c.close(e)});b.load&&c.load()}a.tools=a.tools||{version:"v1.2.7"};a.tools.overlay={addEffect:function(d,b,c){q[d]=[b,c]},conf:{close:null,
closeOnClick:true,closeOnEsc:true,closeSpeed:"fast",effect:"default",fixed:!a.browser.msie||a.browser.version>6,left:"center",load:false,mask:null,oneInstance:true,speed:"normal",target:null,top:"10%"}};var s=[],q={};a.tools.overlay.addEffect("default",function(d,b){var c=this.getConf(),j=a(window);if(!c.fixed){d.top+=j.scrollTop();d.left+=j.scrollLeft()}d.position=c.fixed?"fixed":"absolute";this.getOverlay().css(d).fadeIn(c.speed,b)},function(d){this.getOverlay().fadeOut(this.getConf().closeSpeed,
d)});a.fn.overlay=function(d){var b=this.data("overlay");if(b)return b;if(a.isFunction(d))d={onBeforeLoad:d};d=a.extend(true,{},a.tools.overlay.conf,d);this.each(function(){b=new t(a(this),d);s.push(b);a(this).data("overlay",b)});return d.api?b:this}})(jQuery);

/*
 
 jQuery Tools v1.2.7 Scrollable - New wave UI design

 NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.

 http://flowplayer.org/tools/scrollable.html

 Since: March 2008
 Date: 2012-04-30 14:24 
*/
(function(e){function r(f,c){var b=e(c);return b.length<2?b:f.parent().find(c)}function v(f,c){var b=this,p=f.add(b),g=f.children(),l=0,j=c.vertical;k||(k=b);if(g.length>1)g=e(c.items,f);if(c.size>1)c.circular=false;e.extend(b,{getConf:function(){return c},getIndex:function(){return l},getSize:function(){return b.getItems().size()},getNaviButtons:function(){return m.add(n)},getRoot:function(){return f},getItemWrap:function(){return g},getItems:function(){return g.find(c.item).not("."+c.clonedClass)},
move:function(a,d){return b.seekTo(l+a,d)},next:function(a){return b.move(c.size,a)},prev:function(a){return b.move(-c.size,a)},begin:function(a){return b.seekTo(0,a)},end:function(a){return b.seekTo(b.getSize()-1,a)},focus:function(){return k=b},addItem:function(a){a=e(a);if(c.circular){g.children().last().before(a);g.children().first().replaceWith(a.clone().addClass(c.clonedClass))}else{g.append(a);n.removeClass("disabled")}p.trigger("onAddItem",[a]);return b},seekTo:function(a,d,h){a.jquery||(a*=
1);if(c.circular&&a===0&&l==-1&&d!==0)return b;if(!c.circular&&a<0||a>b.getSize()||a<-1)return b;var i=a;if(a.jquery)a=b.getItems().index(a);else i=b.getItems().eq(a);var s=e.Event("onBeforeSeek");if(!h){p.trigger(s,[a,d]);if(s.isDefaultPrevented()||!i.length)return b}i=j?{top:-i.position().top}:{left:-i.position().left};l=a;k=b;if(d===undefined)d=c.speed;g.animate(i,d,c.easing,h||function(){p.trigger("onSeek",[a])});return b}});e.each(["onBeforeSeek","onSeek","onAddItem"],function(a,d){e.isFunction(c[d])&&
e(b).on(d,c[d]);b[d]=function(h){h&&e(b).on(d,h);return b}});if(c.circular){var t=b.getItems().slice(-1).clone().prependTo(g),u=b.getItems().eq(1).clone().appendTo(g);t.add(u).addClass(c.clonedClass);b.onBeforeSeek(function(a,d,h){if(!a.isDefaultPrevented())if(d==-1){b.seekTo(t,h,function(){b.end(0)});return a.preventDefault()}else d==b.getSize()&&b.seekTo(u,h,function(){b.begin(0)})});var q=f.parents().add(f).filter(function(){if(e(this).css("display")==="none")return true});if(q.length){q.show();
b.seekTo(0,0,function(){});q.hide()}else b.seekTo(0,0,function(){})}var m=r(f,c.prev).click(function(a){a.stopPropagation();b.prev()}),n=r(f,c.next).click(function(a){a.stopPropagation();b.next()});if(!c.circular){b.onBeforeSeek(function(a,d){setTimeout(function(){if(!a.isDefaultPrevented()){m.toggleClass(c.disabledClass,d<=0);n.toggleClass(c.disabledClass,d>=b.getSize()-1)}},1)});c.initialIndex||m.addClass(c.disabledClass)}b.getSize()<2&&m.add(n).addClass(c.disabledClass);c.mousewheel&&e.fn.mousewheel&&
f.mousewheel(function(a,d){if(c.mousewheel){b.move(d<0?1:-1,c.wheelSpeed||50);return false}});if(c.touch){var o={};g[0].ontouchstart=function(a){a=a.touches[0];o.x=a.clientX;o.y=a.clientY};g[0].ontouchmove=function(a){if(a.touches.length==1&&!g.is(":animated")){var d=a.touches[0],h=o.x-d.clientX;d=o.y-d.clientY;b[j&&d>0||!j&&h>0?"next":"prev"]();a.preventDefault()}}}c.keyboard&&e(document).on("keydown.scrollable",function(a){if(!(!c.keyboard||a.altKey||a.ctrlKey||a.metaKey||e(a.target).is(":input")))if(!(c.keyboard!=
"static"&&k!=b)){var d=a.keyCode;if(j&&(d==38||d==40)){b.move(d==38?-1:1);return a.preventDefault()}if(!j&&(d==37||d==39)){b.move(d==37?-1:1);return a.preventDefault()}}});c.initialIndex&&b.seekTo(c.initialIndex,0,function(){})}e.tools=e.tools||{version:"v1.2.7"};e.tools.scrollable={conf:{activeClass:"active",circular:false,clonedClass:"cloned",disabledClass:"disabled",easing:"swing",initialIndex:0,item:"> *",items:".items",keyboard:true,mousewheel:false,next:".next",prev:".prev",size:1,speed:400,
vertical:false,touch:true,wheelSpeed:0}};var k;e.fn.scrollable=function(f){var c=this.data("scrollable");if(c)return c;f=e.extend({},e.tools.scrollable.conf,f);this.each(function(){c=new v(e(this),f);e(this).data("scrollable",c)});return f.api?c:this}})(jQuery);

/*
 
 jQuery Tools v1.2.7 Tabs- The basics of UI design.

 NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.

 http://flowplayer.org/tools/tabs/

 Since: November 2008
 Date: 2012-04-30 14:24 
*/
(function(d){function r(c,b,a){var e=this,l=c.add(this),h=c.find(a.tabs),i=b.jquery?b:c.children(b),j;h.length||(h=c.children());i.length||(i=c.parent().find(b));i.length||(i=d(b));d.extend(this,{click:function(f,g){var k=h.eq(f),n=!c.data("tabs");if(typeof f=="string"&&f.replace("#","")){k=h.filter('[href*="'+f.replace("#","")+'"]');f=Math.max(h.index(k),0)}if(a.rotate){var p=h.length-1;if(f<0)return e.click(p,g);if(f>p)return e.click(0,g)}if(!k.length){if(j>=0)return e;f=a.initialIndex;k=h.eq(f)}if(f===
j)return e;g=g||d.Event();g.type="onBeforeClick";l.trigger(g,[f]);if(!g.isDefaultPrevented()){n=n?a.initialEffect&&a.effect||"default":a.effect;q[n].call(e,f,function(){j=f;g.type="onClick";l.trigger(g,[f])});h.removeClass(a.current);k.addClass(a.current);return e}},getConf:function(){return a},getTabs:function(){return h},getPanes:function(){return i},getCurrentPane:function(){return i.eq(j)},getCurrentTab:function(){return h.eq(j)},getIndex:function(){return j},next:function(){return e.click(j+
1)},prev:function(){return e.click(j-1)},destroy:function(){h.off(a.event).removeClass(a.current);i.find('a[href^="#"]').off("click.T");return e}});d.each("onBeforeClick,onClick".split(","),function(f,g){d.isFunction(a[g])&&d(e).on(g,a[g]);e[g]=function(k){k&&d(e).on(g,k);return e}});if(a.history&&d.fn.history){d.tools.history.init(h);a.event="history"}h.each(function(f){d(this).on(a.event,function(g){e.click(f,g);return g.preventDefault()})});i.find('a[href^="#"]').on("click.T",function(f){e.click(d(this).attr("href"),
f)});if(location.hash&&a.tabs=="a"&&c.find('[href="'+location.hash+'"]').length)e.click(location.hash);else if(a.initialIndex===0||a.initialIndex>0)e.click(a.initialIndex)}d.tools=d.tools||{version:"v1.2.7"};d.tools.tabs={conf:{tabs:"a",current:"current",onBeforeClick:null,onClick:null,effect:"default",initialEffect:false,initialIndex:0,event:"click",rotate:false,slideUpSpeed:400,slideDownSpeed:400,history:false},addEffect:function(c,b){q[c]=b}};var q={"default":function(c,b){this.getPanes().hide().eq(c).show();
b.call()},fade:function(c,b){var a=this.getConf(),e=a.fadeOutSpeed,l=this.getPanes();e?l.fadeOut(e):l.hide();l.eq(c).fadeIn(a.fadeInSpeed,b)},slide:function(c,b){var a=this.getConf();this.getPanes().slideUp(a.slideUpSpeed);this.getPanes().eq(c).slideDown(a.slideDownSpeed,b)},ajax:function(c,b){this.getPanes().eq(0).load(this.getTabs().eq(c).attr("href"),b)}},m,o;d.tools.tabs.addEffect("horizontal",function(c,b){if(!m){var a=this.getPanes().eq(c);c=this.getCurrentPane();o||(o=this.getPanes().eq(0).width());
m=true;a.show();c.animate({width:0},{step:function(e){a.css("width",o-e)},complete:function(){d(this).hide();b.call();m=false}});if(!c.length){b.call();m=false}}});d.fn.tabs=function(c,b){var a=this.data("tabs");if(a){a.destroy();this.removeData("tabs")}if(d.isFunction(b))b={onBeforeClick:b};b=d.extend({},d.tools.tabs.conf,b);this.each(function(){a=new r(d(this),c,b);d(this).data("tabs",a)});return b.api?a:this}})(jQuery);

/*
 
 jQuery Tools v1.2.7 / Expose - Dim the lights

 NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.

 http://flowplayer.org/tools/toolbox/expose.html

 Since: Mar 2010
 Date: 2012-04-30 14:24 
*/
(function(b){function k(){if(b.browser.msie){var a=b(document).height(),d=b(window).height();return[window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth,a-d<20?d:a]}return[b(document).width(),b(document).height()]}function h(a){if(a)return a.call(b.mask)}b.tools=b.tools||{version:"v1.2.7"};var l;l=b.tools.expose={conf:{maskId:"exposeMask",loadSpeed:"slow",closeSpeed:"fast",closeOnClick:true,closeOnEsc:true,zIndex:9998,opacity:0.8,startOpacity:0,color:"#fff",onLoad:null,
onClose:null}};var c,i,e,g,j;b.mask={load:function(a,d){if(e)return this;if(typeof a=="string")a={color:a};a=a||g;g=a=b.extend(b.extend({},l.conf),a);c=b("#"+a.maskId);if(!c.length){c=b("<div/>").attr("id",a.maskId);b("body").append(c)}var m=k();c.css({position:"absolute",top:0,left:0,width:m[0],height:m[1],display:"none",opacity:a.startOpacity,zIndex:a.zIndex});a.color&&c.css("backgroundColor",a.color);if(h(a.onBeforeLoad)===false)return this;a.closeOnEsc&&b(document).on("keydown.mask",function(f){f.keyCode==
27&&b.mask.close(f)});a.closeOnClick&&c.on("click.mask",function(f){b.mask.close(f)});b(window).on("resize.mask",function(){b.mask.fit()});if(d&&d.length){j=d.eq(0).css("zIndex");b.each(d,function(){var f=b(this);/relative|absolute|fixed/i.test(f.css("position"))||f.css("position","relative")});i=d.css({zIndex:Math.max(a.zIndex+1,j=="auto"?0:j)})}c.css({display:"block"}).fadeTo(a.loadSpeed,a.opacity,function(){b.mask.fit();h(a.onLoad);e="full"});e=true;return this},close:function(){if(e){if(h(g.onBeforeClose)===
false)return this;c.fadeOut(g.closeSpeed,function(){h(g.onClose);i&&i.css({zIndex:j});e=false});b(document).off("keydown.mask");c.off("click.mask");b(window).off("resize.mask")}return this},fit:function(){if(e){var a=k();c.css({width:a[0],height:a[1]})}},getMask:function(){return c},isLoaded:function(a){return a?e=="full":e},getConf:function(){return g},getExposed:function(){return i}};b.fn.mask=function(a){b.mask.load(a);return this};b.fn.expose=function(a){b.mask.load(a,this);return this}})(jQuery);

/*
 
 jQuery Tools v1.2.7 History "Back button for AJAX apps"

 NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.

 http://flowplayer.org/tools/toolbox/history.html

 Since: Mar 2010
 Date: 2012-04-30 14:24 
*/
(function(b){function h(c){if(c){var a=d.contentWindow.document;a.open().close();a.location.hash=c}}var g,d,f,i;b.tools=b.tools||{version:"v1.2.7"};b.tools.history={init:function(c){if(!i){if(b.browser.msie&&b.browser.version<"8"){if(!d){d=b("<iframe/>").attr("src","javascript:false;").hide().get(0);b("body").append(d);setInterval(function(){var a=d.contentWindow.document;a=a.location.hash;g!==a&&b(window).trigger("hash",a)},100);h(location.hash||"#")}}else setInterval(function(){var a=location.hash;
a!==g&&b(window).trigger("hash",a)},100);f=!f?c:f.add(c);c.click(function(a){var e=b(this).attr("href");d&&h(e);if(e.slice(0,1)!="#"){location.href="#"+e;return a.preventDefault()}});i=true}}};b(window).on("hash",function(c,a){a?f.filter(function(){var e=b(this).attr("href");return e==a||e==a.replace("#","")}).trigger("history",[a]):f.eq(0).trigger("history",[a]);g=a});b.fn.history=function(c){b.tools.history.init(this);return this.on("history",c)}})(jQuery);

/*
 
 jQuery Tools v1.2.7 Tooltip - UI essentials

 NO COPYRIGHTS OR LICENSES. DO WHAT YOU LIKE.

 http://flowplayer.org/tools/tooltip/

 Since: November 2008
 Date: 2012-04-30 14:24 
*/
(function(f){function p(a,b,c){var h=c.relative?a.position().top:a.offset().top,d=c.relative?a.position().left:a.offset().left,i=c.position[0];h-=b.outerHeight()-c.offset[0];d+=a.outerWidth()+c.offset[1];if(/iPad/i.test(navigator.userAgent))h-=f(window).scrollTop();var j=b.outerHeight()+a.outerHeight();if(i=="center")h+=j/2;if(i=="bottom")h+=j;i=c.position[1];a=b.outerWidth()+a.outerWidth();if(i=="center")d-=a/2;if(i=="left")d-=a;return{top:h,left:d}}function u(a,b){var c=this,h=a.add(c),d,i=0,j=
0,m=a.attr("title"),q=a.attr("data-tooltip"),r=o[b.effect],l,s=a.is(":input"),v=s&&a.is(":checkbox, :radio, select, :button, :submit"),t=a.attr("type"),k=b.events[t]||b.events[s?v?"widget":"input":"def"];if(!r)throw'Nonexistent effect "'+b.effect+'"';k=k.split(/,\s*/);if(k.length!=2)throw"Tooltip: bad events configuration for "+t;a.on(k[0],function(e){clearTimeout(i);if(b.predelay)j=setTimeout(function(){c.show(e)},b.predelay);else c.show(e)}).on(k[1],function(e){clearTimeout(j);if(b.delay)i=setTimeout(function(){c.hide(e)},
b.delay);else c.hide(e)});if(m&&b.cancelDefault){a.removeAttr("title");a.data("title",m)}f.extend(c,{show:function(e){if(!d){if(q)d=f(q);else if(b.tip)d=f(b.tip).eq(0);else if(m)d=f(b.layout).addClass(b.tipClass).appendTo(document.body).hide().append(m);else{d=a.next();d.length||(d=a.parent().next())}if(!d.length)throw"Cannot find tooltip for "+a;}if(c.isShown())return c;d.stop(true,true);var g=p(a,d,b);b.tip&&d.html(a.data("title"));e=f.Event();e.type="onBeforeShow";h.trigger(e,[g]);if(e.isDefaultPrevented())return c;
g=p(a,d,b);d.css({position:"absolute",top:g.top,left:g.left});l=true;r[0].call(c,function(){e.type="onShow";l="full";h.trigger(e)});g=b.events.tooltip.split(/,\s*/);if(!d.data("__set")){d.off(g[0]).on(g[0],function(){clearTimeout(i);clearTimeout(j)});g[1]&&!a.is("input:not(:checkbox, :radio), textarea")&&d.off(g[1]).on(g[1],function(n){n.relatedTarget!=a[0]&&a.trigger(k[1].split(" ")[0])});b.tip||d.data("__set",true)}return c},hide:function(e){if(!d||!c.isShown())return c;e=f.Event();e.type="onBeforeHide";
h.trigger(e);if(!e.isDefaultPrevented()){l=false;o[b.effect][1].call(c,function(){e.type="onHide";h.trigger(e)});return c}},isShown:function(e){return e?l=="full":l},getConf:function(){return b},getTip:function(){return d},getTrigger:function(){return a}});f.each("onHide,onBeforeShow,onShow,onBeforeHide".split(","),function(e,g){f.isFunction(b[g])&&f(c).on(g,b[g]);c[g]=function(n){n&&f(c).on(g,n);return c}})}f.tools=f.tools||{version:"v1.2.7"};f.tools.tooltip={conf:{effect:"toggle",fadeOutSpeed:"fast",
predelay:0,delay:30,opacity:1,tip:0,fadeIE:false,position:["top","center"],offset:[0,0],relative:false,cancelDefault:true,events:{def:"mouseenter,mouseleave",input:"focus,blur",widget:"focus mouseenter,blur mouseleave",tooltip:"mouseenter,mouseleave"},layout:"<div/>",tipClass:"tooltip"},addEffect:function(a,b,c){o[a]=[b,c]}};var o={toggle:[function(a){var b=this.getConf(),c=this.getTip();b=b.opacity;b<1&&c.css({opacity:b});c.show();a.call()},function(a){this.getTip().hide();a.call()}],fade:[function(a){var b=
this.getConf();if(!f.browser.msie||b.fadeIE)this.getTip().fadeTo(b.fadeInSpeed,b.opacity,a);else{this.getTip().show();a()}},function(a){var b=this.getConf();if(!f.browser.msie||b.fadeIE)this.getTip().fadeOut(b.fadeOutSpeed,a);else{this.getTip().hide();a()}}]};f.fn.tooltip=function(a){var b=this.data("tooltip");if(b)return b;a=f.extend(true,{},f.tools.tooltip.conf,a);if(typeof a.position=="string")a.position=a.position.split(/,?\s/);this.each(function(){b=new u(f(this),a);f(this).data("tooltip",b)});
return a.api?b:this}})(jQuery);

