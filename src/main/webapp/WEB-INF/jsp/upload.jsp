<%--
  Created by IntelliJ IDEA.
  User: wanzhenghang
  Date: 2018/2/26
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
    <link rel="stylesheet" type="text/css" href="/ui/fts.css">
    <script type="text/javascript" src="/ui/fts.js"></script>
</head>
<body>
<form method="post" action="/file/upload" enctype="multipart/form-data">
    <table>
        <tr>
            <td>请输入文件名</td>
            <td><input type="text" placeholder="默认为源文件名" name="name"></td>
        </tr>
        <tr>
            <td>请选择文件</td>
            <td><input type="file" name="srcFile"></td>
        </tr>
        <tr>
            <td>输入文件保存时长（最长10天）</td>
            <td><input class="smallText" type="text" value="0" name="day">天
                <input class="smallText" type="text" value="0" name="hour">小时
                <input class="smallText" type="text" value="0" name="minute">分钟
            </td>
        </tr>
        <tr>
            <td>访问权限</td>
            <td>
                <input name="access" type="radio" value="1">所有人可读<br/>
                <input name="access" type="radio" value="2" checked="checked">授权码可读<input name="authoricode" placeholder="缺省值为您设置的默认授权码"><br/>
                <input name="access" type="radio" value="3">私人可读<br/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
