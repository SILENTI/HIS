<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>楼兰医院</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>
<body>
<%--文件上传--%>
<div class="layui-btn-container">
    <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
    <button type="button" class="layui-btn" id="test9">开始上传</button>
</div>

<%--表格部分--%>
<table class="layui-table" lay-data="{height: 'full-200', cellMinWidth: 80, url:'userDisply', page:true, id:'idTest'}" lay-filter="demo">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'uid', width:150, sort: true, fixed: true}">ID</th>
<%--        <th lay-data="{field:'uid', width:150}">UID</th>--%>
        <th lay-data="{field:'username', width:250}">用户名</th>
        <th lay-data="{field:'password', width:250}">密码</th>
        <th lay-data="{fixed: 'right', width:400, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-warm " lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger" lay-event="del">删除</a>
</script>

<script src="layui/layui.js" charset="utf-8"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.jquery;
        //监听表格复选框选择
        table.on('checkbox(demo)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            var layer = layui.layer;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                //删除操作
                //发送Ajax请求

                $.ajax({
                    type:'post',//ajax请求类型
                    url:'delUserInfo', //ajax请求命令,
                    data:{data:JSON.stringify(data)},//请求传递的参数，会自动拼装
                    synch:true, //设置异步的ajax请求，还是同步的ajax请求
                    success:function (result){
                        //响应成功后调用的回调函数
                        layer.alert('成功',function(){
                            layer.closeAll();//关闭所有的layer弹出层
                        });
                    }
                });
            } else if(obj.event === 'edit'){
                //也就是修该用户名和密码信息
                //打开一个弹窗，默认显示原有的信息，但id不变
                //引入模板
                layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }
        });

        var $ = layui.$, active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
<script>
    layui.use(['upload', 'element', 'layer'], function(){
        var $ = layui.jquery
            ,upload = layui.upload
            ,element = layui.element
            ,layer = layui.layer;
        //选完文件后不自动上传
        upload.render({
            elem: '#test8'
            ,url: 'userInfoImport' //改成您自己的上传接口
            ,auto: false
            ,accept: 'file'
            // /*,exts: 'xlsx|xls' //定义上传文件的类型*/
            //,multiple: true
            ,bindAction: '#test9'
            ,done: function(res){
                if (parseInt(res.code) === 0) {
                    layer.msg('导入成功');
                }
            }
            ,error: function(index, upload){
                //当上传失败时，你可以生成一个“重新上传”的按钮，点击该按钮时，执行 upload() 方法即可实现重新上传
            }
        });
    });
</script>
</body>
</html>
