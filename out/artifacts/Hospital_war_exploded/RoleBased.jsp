<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%--引入layui框架--%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
</head>
    <body>
           <%-- 利用layui的表结构
            点击，角色管理，通过ajax从数据库获取角色表的数据，并展示
/div>
        定位-展示的位置--%>


        <table class="layui-table" lay-data="{ url:'SelectRole', page:true, id:'idTest'}" lay-filter="demo">
            <thead>
            <tr>
                <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                <th lay-data="{field:'rid', width:148, sort: true, fixed: true}">ID</th>
                <th lay-data="{field:'rname', width:270}">用户名</th>
                <th lay-data="{field:'rdescription', width:488}">详细描述</th>
                <th lay-data="{fixed: 'right', width:200, align:'center', toolbar: '#barDemo'}">操作</th>
            </tr>
            </thead>
        </table>

        <script type="text/html" id="barDemo">
<%--            <a class="layui-btn layui-btn-norma layui-btn-sm layui-btn-normal" lay-event="detail">查看</a>--%>
            <a class="layui-btn layui-btn-sm layui-btn-warm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
        </script>


        <script src="layui/layui.js" charset="utf-8"></script>
        <!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
        <script>
            layui.use('table', function(){
                var table = layui.table;
                //监听表格复选框选择
                table.on('checkbox(demo)', function(obj){
                    console.log(obj)
                });
                //监听工具条
                table.on('tool(demo)', function(obj){
                    var data = obj.data;
                    if(obj.event === 'detail'){
                        layer.msg('ID：'+ data.id + ' 的查看操作');
                    } else if(obj.event === 'del'){
                        layer.confirm('真的删除行么', function(index){
                            obj.del();
                            layer.close(index);
                        });
                    } else if(obj.event === 'edit'){
                        layer.alert('编辑行：<br>'+ JSON.stringify(data))
                    }
                });


                $('.demoTable .layui-btn').on('click', function(){
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });
            });
        </script>


    <%--<table id="demo" lay-filter="test"></table>

        <script src="/layui/layui.js"></script>
        <script>

            layui.use('table', function(){
                var table = layui.table;

                //第一个实例
                table.render({
                    elem: '#demo'
                    ,height: 312
                    ,url: 'SelectRole' //数据接口
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {type:'checkbox'}
                        ,{field: 'rid', title: 'ID', width:50, sort: true, fixed: 'left'}
                        ,{field: 'rname', title: '用户名', width:80}
                        ,{field: 'rdescription', title: '描述', width:200, sort: true}
                    ]]
                });

            });
        </script>--%>



    </body>
</html>
