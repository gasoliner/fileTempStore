// function downloadFile(inputAuthCodeId,fileId) {
//     if (inputAuthCodeId != 'no') {
//         alert("inputAuthCode != no  " + inputAuthCodeId);
//         $.post("file/download",
//             {
//                 "authoricode":$("#inputAuthCodeId").val(),
//                 "fileId":fileId
//             },
//             function (res) {
//                 alert(res);
//             })
//     } else {
//         alert("inputAuthCode == no ");
//         $.post("file/download",
//             {
//                 "fileId":fileId
//             },
//         function (res) {
//             alert(res);
//         })
//     }
// }

function downloadFile(inputAuthCodeId,fileId) {
    if (inputAuthCodeId != 'no') {
        // alert("inputAuthCode != no  " + $("#" + inputAuthCodeId +"").val());
        window.open("/file/download?authoricode="+$("#" + inputAuthCodeId +"").val()+"&fileId="+fileId);
    } else {
        // alert("inputAuthCode == no ");
        window.open("/file/download?fileId="+fileId);
    }
}

function fillAction(access,fileId) {
    var fileIdHtml = fileId.replace(/\//g, "");
    console.log("fileHtmlId1 = " + fileIdHtml);
    fileIdHtml = fileIdHtml.replace(/\./g,"");
    console.log("filehtmlId2 = " + fileIdHtml);
    if (access == 2) {
        return "<div class=\"form-inline\"><input id=\"" + fileIdHtml + "\" type=\"text\" class=\"form-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class=\"btn btn-success\" onclick=\"downloadFile('" + fileIdHtml + "','" + fileId + "')\">下载</button>&nbsp;&nbsp;" +
            "<button class=\"btn btn-info\" data-toggle=\"modal\" onclick=\"requestPreview('" + fileIdHtml + "','" + fileId + "')\">预览该文件</button></div>";
    } else {
        return "<button class=\"btn btn-success\" onclick=\"downloadFile('no','" + fileId + "')\">下载</button>&nbsp;&nbsp;" +
                   "<button class=\"btn btn-info\" data-toggle=\"modal\" onclick=\"requestPreview('no','" + fileId + "')\">预览该文件</button>";
    }
}

function refreshFileTab() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "file/list",
        success: function (msg) {
            if (msg.state == 0) {
                var str = "";
                var data = msg.data;
                for (i in data) {
                    str += "<tr>" +
                        "<td>" + data[i].name + "</td>" +
                        "<td>" + data[i].size + "</td>" +
                        "<td>" + data[i].start + "</td>" +
                        "<td>" + data[i].expirationTime + "</td>" +
                        "<td>" + data[i].day + " 天 " + data[i].hour + " 小时 " + data[i].minute + " 分钟 " + "</td>" +
                        "<td>" + data[i].access + "</td>" +
                        "<td>" + fillAction(data[i].access,data[i].fileid) + "</td>" +
                        "</tr>";
                }
                $("#fileTbody").html(str);
            }
        },
        error: function () {
            alert("查询失败");
        }
    });
}

function uploadFile() {
    var option = {
        url : '/file/upload',
        type : 'POST',
        dataType : 'json',
        // headers : {"ClientCallMode" : "ajax"}, //添加请求头部
        success :function (response,statusText) {
            alert(statusText);
            $("#uploadFileForm").resetForm();
            refreshFileTab();
            // response = $.parseJSON(response);
            // alert(response.message);
        }
        // error:showResponse()
    };
    $("#uploadFileForm").ajaxSubmit(option);

}

function fastText() {
    var option = {
        url : '/file/fastText',
        type : 'POST',
        dataType : 'json',
        // headers : {"ClientCallMode" : "ajax"}, //添加请求头部
        success :function (response,statusText) {
            alert(statusText);
        }
        // error:showResponse()
    };
    $("#fastTextForm").ajaxSubmit(option);
    $("#fastTextForm").resetForm();
    refreshFileTab();
}
function requestPreview(inputAuthCodeId,id) {
    var url;
    if (inputAuthCodeId != 'no') {
        url = "/file/previewed?fileid=" + id + "&authoricode=" + $("#" + inputAuthCodeId).val();
    } else {
        url = "/file/previewed?fileid=" + id;
    }
    $.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        beforeSend:function (){
            $("#waitModal").modal('show');
        },
        success: function (msg) {
            $("#waitModal").modal('hide');

            var data = msg.data;
            $("#previewArea").html(data);
            $("#previewModal").modal('show');
        },
        error: function () {
            $("#waitModal").modal('hide');
            alert("查询失败");
        }
    });
}








// //字典管理的按类型查询
// $(function () {
//     $('#searchInput').combobox({
//         onChange: function(){
//             $("#dg").datagrid({
//                 url:'/systemddl/list/'+$("#searchInput").combobox("getValue")
//             });
//             // $("#dg").datagrid("load");
//         }
//     });
// });
// //课题的按学年查询
// $(function () {
//     $('#topic_searchInput').combobox({
//         onChange: function(){
//             $("#dg").datagrid({
//                 url:'/topic/list/'+$("#topic_searchInput").combobox("getValue")
//             });
//             // $("#dg").datagrid("load");
//         }
//     });
// });
// //结合科研情况按年查询
// $(function () {
//     $('#research_searchInput').combobox({
//         onChange: function(){
//             $("#dg").datagrid({
//                 url:'/topic/research/'+$("#research_searchInput").combobox("getValue")
//             });
//         }
//     });
// });
// //教师通过课题按年查询
// $(function () {
//     $('#passedTopic_searchInput').combobox({
//         onChange: function(){
//             $("#dg").datagrid({
//                 url:'/user/passtopic/'+$("#passedTopic_searchInput").combobox("getValue")
//             });
//         }
//     });
// });
// //指导时间和地点按年查询
// $(function () {
//     $('#timeAndPlace_searchInput').combobox({
//         onChange: function(){
//             $("#dg").datagrid({
//                 url:'/student/timeAndPlace/'+$("#timeAndPlace_searchInput").combobox("getValue")
//             });
//         }
//     });
// });
//
// function perInf1() {
//     $.post("/user/personalInfo",{
//         phone:$("#phone").val(),
//         email:$("#email").val()
//     },function (data) {
//         alert(data);
//     });
// }
// function subNewUser() {
//     $.post("/user/addition",{
//         employeenum:$("#employeenum").val(),
//         username:$("#username").val(),
//         roleidsstr:$("#roleSelect").val(),
//         deptid:$("#depSelect").val(),
//         email:$("#email").val(),
//         phone:$("#phone").val(),
//     },function (backresult) {
//         alert(backresult);
//     })
// }