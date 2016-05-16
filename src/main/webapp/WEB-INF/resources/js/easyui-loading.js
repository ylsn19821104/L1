/**
 * Created by luopotaotao on 2016/5/13.
 */
$.fn.extend({
    loading:function (msg) {
        $('<div class="datagrid-mask"></div>').css({display:"block",width:this.width(),height:this.height()}).appendTo(this);
        $('<div class="datagrid-mask-msg"></div>').html(msg).appendTo(this).css({display:"block",left:(this.width()-$("div.datagrid-mask-msg",this).outerWidth())/2,top:(this.height()-$("div.datagrid-mask-msg",this).outerHeight())/2});
    },
    loaded:function (callback,data) {
        this.find("div.datagrid-mask-msg").remove();
        this.find("div.datagrid-mask").remove();
        if($.isFunction(callback)){
            callback(data);
        }
    }
});