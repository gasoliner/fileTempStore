<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
    <jsp:include page="common/head.jsp"/>
    <link href="/ui/fileupload/css/fileinput.css" rel="stylesheet" />
    <link href="/ui/fileupload/themes/explorer-fa/theme.css"/>
    <script type="text/javascript" src="/ui/fileupload/js/fileinput.js"></script>
    <script type="text/javascript" src="/ui/fileupload/themes/explorer-fa/theme.js"></script>
    <script type="text/javascript" src="/ui/fileupload/themes/fa/theme.js"></script>
    <script type="text/javascript" src="/ui/jquery.form.js"></script>
</head>

<body>
<div style="width:80%;margin:0 auto">

    <%--文件上传collapse开始--%>
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 align="right" class="panel-title">
                        <button type="button" class="btn" data-toggle="collapse" data-parent="#accordion"
                                data-target="#fastTextDiv"><i class="fa fa-heart"></i> 快速文本</button>
                    </h4>
                </div>
                <div id="fastTextDiv" class="panel-collapse collapse">
                    <div class="panel-body">
                        <form class="form-horizontal style-form" method="get">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Default</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Help text</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                    <span class="help-block">A block of help text that breaks onto a new line and may extend beyond one line.</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Rounder</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control round-form">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Input focus</label>
                                <div class="col-sm-10">
                                    <input class="form-control" id="focusedInput" type="text" value="This is focused...">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Disabled</label>
                                <div class="col-sm-10">
                                    <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Placeholder</label>
                                <div class="col-sm-10">
                                    <input type="text"  class="form-control" placeholder="placeholder">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Password</label>
                                <div class="col-sm-10">
                                    <input type="password"  class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 col-sm-2 control-label">Static control</label>
                                <div class="col-lg-10">
                                    <p class="form-control-static">email@example.com</p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 align="right" class="panel-title">
                        <button type="button" class="btn" data-toggle="collapse" data-parent="#accordion"
                                data-target="#uploadFileDiv"><i class="fa fa-heart"></i> 上传文件</button>
                    </h4>
                </div>
                <div id="uploadFileDiv" class="panel-collapse collapse">
                    <div class="panel-body">
                        <%--<form id="uploadFileForm" class="form-horizontal style-form" enctype="multipart/form-data">--%>
                        <form id="uploadFileForm" class="form-horizontal style-form" enctype="multipart/form-data" >
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">文件名</label>
                                <div class="col-sm-10">
                                    <input type="text" name="name"  class="form-control" placeholder="默认为源文件名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">选择文件</label>
                                <div class="col-sm-10">
                                    <input id="choose_file" class="file" type="file" name="srcFile"
                                           data-min-file-count="1"
                                           data-show-preview="false"
                                           data-show-upload="false"
                                           data-show-remove="false">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">输入文件保存时长（最长10天）</label>
                                <div class="col-sm-10">
                                    <div>
                                        <input type="text"  class="form-control" name="day" value="0">天
                                    </div>
                                    <div>
                                        <input type="text"  class="form-control" name="hour" value="0">小时
                                    </div>
                                    <div>
                                        <input type="text"  class="form-control" name="minute" value="0">分钟
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">访问权限</label>
                                <div class="col-sm-10">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="access" id="allRead" value="1">
                                            所有人可读
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="access" id="codeRead" value="2" checked>
                                            授权码可读
                                        </label>
                                    </div>
                                    <input type="text" name="authoricode"  class="form-control" placeholder="输入授权码，默认为缺省值">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="access" id="privateRead" value="3">
                                            私人可读
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">
                                    <button type="button" onclick="uploadFile()" class="btn" ><i class="fa fa-heart"></i> 上传</button>
                                </label>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <%--文件上传collapse结束--%>

    <table class="table table-striped table-advance table-hover">
        <h4><i class="fa fa-angle-right"></i> File Table
            <button type="button" class="btn" onclick="refreshFileTab()"><i class="fa fa-heart"></i> Refresh</button>
        </h4>
        <hr/>
        <thead>
        <tr>
            <th><i class="fa fa-bullhorn"></i> 文件名</th>
            <th class="hidden-phone"><i class="fa fa-question-circle"></i> 文件大小(kb)</th>
            <th class="hidden-phone"><i class="fa fa-question-circle"></i> 上传时间</th>
            <th class="hidden-phone"><i class="fa fa-question-circle"></i> 过期时间</th>
            <th><i class="fa fa-bookmark"></i> 访问权限</th>
            <th><i class=" fa fa-edit"></i> 操作</th>
            <th></th>
        </tr>
        </thead>
        <tbody id="fileTbody">
        </tbody>
    </table>
</div>

</body>
<script type="text/javascript">
    $(function () {
//        some actions for init index
        refreshFileTab();
//        $('#choose_file').fileinput({
//            theme: 'fa',
//            language: 'zh',
//            uploadUrl: '/file/upload'
//        });

    });
</script>
</html>
