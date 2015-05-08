/**
 * description: 服务降级module-reduce.js
 * @author      dennisit@163.com | En.dennisit | Cn.苏若年
 * @version     1.0.0
 */
var ModuleReduce_URL = {
    InsertPage: '/moduleReduce/form',
    CloseSwitch: '/moduleReduce/close',
    OpenSwitch: '/moduleReduce/open',
    ModulePage: '/moduleReduce/page'
}

var ModuleReduce = function() {
    var func = {
        init:function() {
            func.bindInsertBtn();
            func.bindOpenSwitchBtn();
            func.binCloseSwitchBtn();
            func.bindSearchBtn();
            func.bindFormValidate();
            func.bindFormSubmitBtn();
            func.bindFormResetBtn();
        },
        bindOpenSwitchBtn:function(){
            $(".open-btn").click(function(){
                var moduleName = $(this).attr("data-name");
                var moduleToken = $(this).attr("data-token");
                var pageNum = $("#pageNumber").val();
                var pageSize = $("#pagePerSize").val();
                var  moduleNameSearch = $("#moduleName").val();
                var moduleSwitchSearch = $("#moduleSwitch").val();
                $.ajax( {
                    url: ModuleReduce_URL.OpenSwitch,
                    data: {"moduleName": $.trim(moduleName),"moduleToken": $.trim(moduleToken),"time": new Date().getTime()},
                    type: 'post',
                    cache:false,
                    async: false ,
                    dataType:'text',
                    success:function(data) {
                        if(data != "" && data == "true"){
                            $.ajax( {
                                url: ModuleReduce_URL.ModulePage,
                                data: {
                                    "pageNumber" : pageNum,
                                    "pagePerSize": pageSize,
                                    "moduleName":  $.trim($("#moduleName").val()),
                                    "moduleSwitch":$.trim($("#moduleSwitch").val()),
                                    "dt":new Date().getTime()
                                },
                                type:'post',
                                cache:false,
                                async: false ,
                                dataType:'text',
                                success:function(data) {
                                    jQuery("#page-result").html(data);
                                }
                            });
                        }
                    }
                });
            });
        },
        binCloseSwitchBtn:function(){
            $(".close-btn").click(function(){
                var moduleName = $(this).attr("data-name");
                var moduleToken = $(this).attr("data-token");
                var pageNum = $("#pageNumber").val();
                var pageSize = $("#pagePerSize").val();
                $.ajax( {
                    url: ModuleReduce_URL.CloseSwitch,
                    data: {"moduleName": $.trim(moduleName),"moduleToken": $.trim(moduleToken),"time": new Date().getTime()},
                    type:'post',
                    cache:false,
                    async: false ,
                    dataType:'text',
                    success:function(data) {
                        if(data != "" && data == "true"){
                            $.ajax( {
                                url: ModuleReduce_URL.ModulePage,
                                data: {
                                    "pageNumber" : pageNum,
                                    "pagePerSize": pageSize,
                                    "moduleName":  $.trim($("#moduleName").val()),
                                    "moduleSwitch":$.trim($("#moduleSwitch").val()),
                                    "dt":new Date().getTime()
                                },
                                type:'post',
                                cache:false,
                                async: false ,
                                dataType:'text',
                                success:function(data) {
                                    jQuery("#page-result").html(data);
                                }
                            });
                        }
                    }
                });
            });
        },
        bindSearchBtn:function(){
            $("#search-btn").click(function(){
                $("#moduleSearchForm").submit();
            });
        },
        bindInsertBtn:function(){
            $("#insert-btn").click(function(){
                window.location.href = ModuleReduce_URL.InsertPage ;
            });
        },
        bindFormValidate:function() {
            $("#moduleInsertForm").validate({
                rules:{
                    moduleName: {
                        required: true,
                        maxlength:70
                    },
                    moduleDepict: {
                        required: true,
                        maxlength:70
                    },
                    moduleToken: {
                        required: true,
                        minlength:5,
                        maxlength:70
                    }
                }
            });
        },
        bindFormSubmitBtn:function(){
            $("#submit-btn").click(function(){
                $("#moduleInsertForm").submit();
            });

        },
        bindFormResetBtn:function(){
            $("#reset-btn").click(function(){
                $("#moduleInsertForm")[0].reset();
            });
        }
        /*
        bindSubmitButton:function(){
            $("#submitForm").click(function(){
                var expireDateId = $("#expireDate").val();
                var expireDate = Date.parse(expireDateId.replace("-", "/").replace("-", "/"));
                var now = new Date();
                if((now.getTime()-expireDate) > 0){
                    $("#expireDate_error").text("修改时间不能小于当前系统时间").show();
                    return;
                }
                $('#putFormId').submit();
            });
        },
        bindResetButton:function(){
            $("#resetForm").click(function(){
                $('#putFormId')[0].reset();
            });
        },
        */
    }
    func.init();
};

$(document).ready(function() {
    new ModuleReduce();
});