/**
 * Created by mwq on 2017/12/4.
 */

var questionController = function () {
    var that = this;
    this.init= function () {
       that.regEvent() ;
    }
    this.regEvent = function () {
        that.checkInput();
    }
 /*   this.regEventLogin = function(){
        var url = "http://www.log.php"
        $(document).delegate("#btn_submit","click",function(){
            if(that.input()){
                $.ajax({
                    url:url,
                    type:"post",
                    dataType:"json",
                    data:{
                        username: $("#user_name").val(),
                        password: $("#user_pwd").val(),

                    },
                    success:function(res,textStatus,request){
                        if(res.code == "000000"){
                            window.location.href = "index.html";
                        }else if(res.code == "2200"){

                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        Showbo.Msg.alert(XMLHttpRequest.responseText);
                    }

                })

            }

        })

    }*/
    this.checkInput = function () {
        $(document).delegate("#btn_submit","click",function(){
            var user_name = $("#user_name");
            var user_pwd = $("#user_pwd");
            //var code_identify=$("#idenfy_code");
            if ($.trim(user_name.val()) == "") {
                $("#inputMess").html("请输入用户名!").show();
                user_name.focus();
                return false;
            }
            if ($.trim(user_pwd.val()) == "") {
                $("#inputMess").html("请输入密码!").show();
                user_pwd.focus();
                return false;
            }
            return true;
        })

    }
}

var qst = new questionController();
qst.init();