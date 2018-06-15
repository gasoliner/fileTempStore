<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>index</title>
    <jsp:include page="common/head.jsp"/>
    <link href="/ui/fts.css" rel="stylesheet" />
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
                            <%--<form id="fastTextForm" class="form-horizontal style-form" enctype="multipart/form-data">--%>
                            <form id="fastTextForm" class="form-horizontal style-form" >
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">文件名</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <input type="text" class="form-control" name="name" placeholder="建议填写，因为后台使用UUID生成默认文件名，仅填写文件名，系统统一文件后缀.txt">
                                            <span class="input-group-addon">.txt</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">文本内容</label>
                                    <div class="col-sm-10">
                                        <textarea style="resize: none;" name="content" class="form-control" rows="10"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">输入文件保存时长（最长10天）</label>
                                    <div class="col-sm-10">
                                        <div class="input-group">
                                            <input type="text" class="form-control" name="day" value="0">
                                            <span class="input-group-addon">天</span>
                                        </div>
                                        <br>
                                        <div class="input-group">
                                            <input type="text" class="form-control" name="hour" value="0">
                                            <span class="input-group-addon">小时</span>
                                        </div>
                                        <br>
                                        <div class="input-group">
                                            <input type="text" class="form-control" name="minute" value="0">
                                            <span class="input-group-addon">分钟</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">访问权限</label>
                                    <div class="col-sm-10">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="access" value="1">
                                                所有人可读
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="access" value="2" checked>
                                                授权码可读
                                            </label>
                                        </div>
                                        <input type="text" name="authoricode"  class="form-control" placeholder="输入授权码，默认为缺省值">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="access" value="3">
                                                私人可读
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">
                                        <button type="button" onclick="fastText()" class="btn" ><i class="fa fa-heart"></i> 提交</button>
                                    </label>
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
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="day" value="0">
                                        <span class="input-group-addon">天</span>
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="hour" value="0">
                                        <span class="input-group-addon">小时</span>
                                    </div>
                                    <br>
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="minute" value="0">
                                        <span class="input-group-addon">分钟</span>
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
            <th class="hidden-phone"><i class="fa fa-question-circle"></i> 剩余时间</th>
            <th><i class="fa fa-bookmark"></i> 访问权限</th>
            <th><i class=" fa fa-edit"></i> 操作</th>
            <th></th>
        </tr>
        </thead>
        <tbody id="fileTbody">
        </tbody>
    </table>
</div>

<!-- 请求预览结果 -->
<div class="modal fade" id="previewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">预览</h4>
            </div>
            <div class="modal-body"><div id="previewArea"></div></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" data-backdrop="static" id="waitModal">
    <div class="modal-dialog modal-sm">
        <img src="/ui/img/wait.gif">
    </div>
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
