/**
 * description: blockUI
 * @author      dennisit@163.com | En.dennisit | Cn.苏若年
 * @version     1.0.0
 */
if(typeof(globalBlock) === "undefined"){var globalBlock = function(){}};

GlobalBlock = {};

$.extend(GlobalBlock, {

    //show block
    showBlock:function(messageText){
        var defaultMsgText = "数据处理中...";
        if($.trim(messageText) != ""){
            defaultMsgText = messageText;
        }
        $.blockUI({
            css: {
                width:'280px',
                left:  '45%',
                height: '75px',
                border: 'none',
                padding: '15px',
                '-webkit-border-radius': '5px',
                '-moz-border-radius': '5px',
                backgroundColor: '#fff',
                color:'#14599B',
                'z-index':'9999',
                'border-radius': '2px'
            },
            overlayCSS:  {
                backgroundColor: '#000',
                opacity: 0.6,
                cursor:'wait'
            },
            fadeIn: 0,
            fadeOut: 0,
            message: '<h5><img src="/assets/js/blockUI/img/min-loading-0.gif"/><span  style="padding-left:15px;">' + defaultMsgText + '</span></h5>'
        });
    },

    //hide block
    hideBlock:function(timeout){
        var defaultTimeout = 500;
        if($.trim(defaultTimeout) != ""){
            defaultTimeout = timeout;
        }
        setTimeout($.unblockUI, defaultTimeout);
    }

});
