<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css" media="all">
    <script src="layui/layui.all.js"></script>
</head>
    <body>

    <%--菜单的新建按钮--%>
    <button type="button" onclick="toAdd(-1,'根菜单')" class="layui-btn layui-btn-normal"><i class="layui-icon">&#xe608;</i>菜单新建</button>
        <%--点击按钮绑定，新建事件 -1 表示是根菜单--%>

    <%--请求的位置--%>
        <div id="showFunction"></div>

<%--from表单--%>

    <%--新建功能的表格--%>
    <form id="funAddMenu" class="layui-form" action="funAdd" style="display:none;padding-top:10px;" >
        <div class="layui-form-item">
            <label class="layui-form-label">功能名称</label>
            <div class="layui-input-block">
                <input type="text" name="fname" lay-verify="required" autocomplete="off" placeholder="请输入功能名称" class="layui-input layui-input-inline">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">功能类别</label>
            <div  class="layui-input-inline">
                <input type="radio" name="ftype" value="菜单" title="菜单" checked>
                <input type="radio" name="ftype" value="按钮" title="按钮">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">功能链接(URL)</label>
            <div  class="layui-input-inline">
                <input type="text" name="fhref" placeholder="请输入链接" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">功能范围</label>
            <div  class="layui-input-inline">
                <input type="text" name="auth" required  lay-verify="required" placeholder="请输入功能范围" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">所属父级</label>
            <div  class="layui-input-inline">
                <input type="hidden" name="pid" id="pid" value="-1" />
                <input type="text" name="pname" id="pname" readonly  autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo" id="BOX">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </form>

    <%--treeTable列表--%>
        <script type="text/javascript">
            layui.config({
                base: 'treetable-lay/' //引用treeTable组件的位置
            }).use(['treeTable'], function () {
                var treeTable = layui.treeTable;

                // 渲染树形表格
                var insTb = treeTable.render({
                    elem: '#showFunction',
                    url: 'FunctionAll',
                    tree: {
                        iconIndex: 2,           // 折叠图标显示在第几列
                        isPidData: true,        // 是否是id、pid形式数据
                        idName: 'fid',          // id字段名称
                        pidName: 'pid'          // pid字段名称
                    },
                    cols: [[
                        {type:'checkbox'},
                        {title:'功能名称',field:'fname',width:145}, //title 表标题名
                        {title:'功能类别',field:'ftype',width:150},//field 对应展示数据的名
                        {title:'请求url',field:'fhref',width:248},
                        {title:'权限范围',field:'auth',width:248},
                        {title:'操作',templet:"#operateButtons"} //使用templet模块组件
                    ]]
                });
            });
        </script>

<%--templet模板--%>
    <%--在功能管理-treeTable的--%>
        <script type="text/html" id="operateButtons">
            <div  class="layui-btn-group">
                {{# if (d.ftype == "按钮"  ) { }}
                <%--按钮内不含含有新建--%>
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-disabled">
                    <i class="layui-icon">&#xe608;</i>新建</button>
                {{# }else { }}
                <%--菜单含有新建--%>
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm">
                    <i class="layui-icon">&#xe608;</i>新建</button>
                {{# } }}
                <%--修改--%>
                <button type="button" class="layui-btn layui-btn-warm layui-btn-sm" onclick="">
                    <i class="layui-icon">&#xe642;</i>编辑</button>
                <%--删除--%>
                <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" onclick="funDelect('{{d.fid}}','{{d.fname}}')">
                    <i class="layui-icon">&#xe640;</i>删除</button>
                <%--
                    要实行，删除功能，要获取功能的名称及功能的ID，通过AJAX向服务器发送请求
                    通过按钮调用模板？？
                --%>
            </div>
        </script>

    <script>

        //新建功能
        function toAdd (pid,pname){
            layui.use(['layer'],function(){ //"layer"是什么东西
                var layer = layui.layer;//在layui中引入layui
                var $ = layui.jquery; //引入jquery

                //引入默认的值
                var input = document.getElementById('pid') ; //在整个文档中，通过指定的id值(pid)获得标签对象
                input.value = pid; //设置input标签的value属性 <input value="pid" />
                $('#pname').val(pname);

                layer.open({
                    //打开弹出框
                    type:1,    //打开一个页面层，可以展示一些html效果
                    area:[400,400],  //页面的大小
                    title:'新建功能', //标题
                    content:$('#funAddMenu'),
                    end:function(){
                        // layer.closeAll();
                        $(".BOX").click();//弹出框  关闭后刷新，停留在当前页
                        // location.reload();//弹出层结束后，刷新主页面
                    }
                });
            });
        }

        //删除功能
        function funDelect (fid,fname){
            layui.use(['jquery', 'layer'], function(){
                var $ = layui.$ //重点处,
                layer = layui.layer;
                //后面就跟你平时使用jQuery一样

            });
        }
    </script>

    </body>

</html>
