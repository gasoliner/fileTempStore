<%--
  Created by IntelliJ IDEA.
  User: Ww
  Date: 2018/6/15
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/ui/jquery.min.js"></script>
<script type="text/javascript">
    $.ajax({
        type: "POST",
        dataType:"json",
        url:"setting/current",
        success: function(data){
            data = JSON.stringify(data,null,2);
            $("#settingArea").html(data);
        }
    });
</script>
<pre style="width:100%;height:100%" id="settingArea"></pre>
