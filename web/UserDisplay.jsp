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
            <a data-method="offset" data-type="auto" class="layui-btn layui-btn-warm " lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger" lay-event="del">删除</a>
        </script>
<%--修改用户信息的表单--%>
        <form id="EditUserInfo" class="layui-form" action="EditUserInfo">
            <div class="layui-form-item">
                <label class="layui-form-label">I D</label>
                <div  class="layui-input-inline">
                    <input type="text" name="uid" id="uid" readonly  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input type="text" name="username" id="username" lay-verify="required" autocomplete="off" placeholder="请输入账号" class="layui-input layui-input-inline">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password" id="password" lay-verify="required" autocomplete="off" placeholder="请输入密码" class="layui-input layui-input-inline">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo" id="BOX">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    <script src="layui/layui.js" charset="utf-8"></script>

    <%--layui表格--%>
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
                    layer.confirm('确认删除！', function(index){
                        //发送Ajax请求
                        $.ajax({
                            type:'post',//ajax请求类型
                            url:'delUserInfo', //ajax请求命令,
                            data:{data:JSON.stringify(data)},//请求传递的参数，会自动拼装
                            synch:true, //设置异步的ajax请求，还是同步的ajax请求
                            success:function (){
                                //响应成功后调用的回调函数
                                obj.del();
                                layer.close(index);
                            }
                        });
                    });


                } else if(obj.event === 'edit'){
                    //也就是修该用户名和密码信息
                    //打开一个弹窗，默认显示原有的信息，但id不变
                    // layer.alert('编辑行：<br>'+ JSON.stringify(data))
                    //数据装配
                    var map = eval("("+JSON.stringify(data)+")");
                    $('#uid').val(map['uid']);
                    $('#username').val(map['username']);
                    $('#password').val(map['password']);
                    layer.open({
                        //打开弹出框
                        type:1,    //打开一个页面层，可以展示一些html效果
                        area:[400],  //页面的大小
                        title:'信息修改', //标题
                        content:$('#EditUserInfo'),//引入模板
                        end:function(){
                            // layer.closeAll();
                            // $(".BOX").click();//弹出框  关闭后刷新，停留在当前页
                            // location.reload();//弹出层结束后，刷新主页面
                            // parent.location.reload();
                            // window.parent.location.reload();//刷新父页面
                            //直接跳转


                        }
                    });

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

    <%--用户信息导入--%>
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
