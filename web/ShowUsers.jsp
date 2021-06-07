<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>楼兰医院</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css" />
    <script src="layui/layui.js"></script>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/dist/css/layui.css"  media="all">
</head>
<body>


    <%--用户信息上传--%>
                <%--将用户的信息上传到是数据库--%>
   <%-- <div class="layui-upload">
        <button type="button" class="layui-btn layui-btn-normal"  id="test8">导入用户信息</button>
        <button type="button" class="layui-btn" id="test9">开始导入</button>
    </div>--%>

    <button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>导入</button>

    <script src="layui/dist/layui.js" charset="utf-8"></script>

    <script>
        layui.use('upload', function(){
            var $ = layui.jquery
                ,upload = layui.upload;

           /* //选完文件后不自动上传
            upload.rend er({
                elem: '#test8'
                ,url: 'InputUSerInformation' //改成您自己的上传接口
                ,auto: false
              /!*  ,multiple: true  是否允许多文件上传。设置 true即可开启。不支持ie8/9 *!/
                ,bindAction: '#test9'
                ,done: function(res){
                    layer.msg('上传成功');
                    console.log(res)
                }
            });
*/
            //指定允许上传的文件类型
            upload.render({
                elem: '#test3'
                ,url: 'InputUSerInformation' //改成您自己的上传接口
                ,accept: 'file' //普通文件
                /*,exts: 'csv'*/
                ,done: function(res){
                    layer.msg('上传成功');
                    console.log(res);
                }
            });

        });
    </script>

    <%--知识用户信息的区域--%>
        <table align="center" width="90%" border="1">
            <thead>
            <tr>
                <th>编号</th>
                <th>用户名</th>
                <th>密码</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <!-- 循环组装 jstl+el-->
            <c:forEach items="${requestScope.users.data}" var="user">
                <tr align="center">
                    <td>${user.serial}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>
                        <a href="" class="layui-btn layui-btn-sm  layui-btn-normal">
                            <i class="layui-icon layui-icon-edit">编辑</i>
                        </a>
                            <%--跳转到另一个的界面--%>

                        <a href="DeteleUserInformation?username=${user.username}&password=${user.password}"  class="layui-btn layui-btn-sm layui-btn-danger">
                            <i class="layui-icon layui-icon-delete">删除</i>
                        </a>


                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <%--底层分页栏--%>

     <div id="demo7"></div>




             <script>


                 layui.use('laypage', function(){
                     //获得这个要使用的组件
                     var laypage = layui.laypage;

                 //完整功能
                 laypage.render({
                     elem: 'demo7'
                     ,count: ${requestScope.users.total}
                     ,limit: ${requestScope.users.rows}
                     ,limits:[2,5,8,10]
                     ,curr:${requestScope.users.page}
                     ,layout: [ 'prev', 'page', 'next']
                     ,jump: function(obj,first){
                         console.log(obj.curr); //切换页码  console.log() === System.out.println()
                         console.log(obj.limit); //选择的条数
                         //首次不执行
                         if(!first){
                             //js实现的超链接 展示下一页的资料-调用原来的方法再执行一次。
                             location.href = 'findUser?page='+obj.curr+'&rows='+obj.limit ;
                         }
                     }
                 });
             });
             </script>


</body>
</html>
