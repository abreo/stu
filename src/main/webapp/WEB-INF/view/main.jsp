<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="com.nihao.util.JspUtil" %>
<%
Object obj = session.getAttribute("SESSIONINFO");
if(obj==null)
	response.sendRedirect(request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>STU - 主页</title>
<meta name="keywords" content="STU">
<meta name="description" content="STU">
<!-- css -->
<link href="/stu/static/plugins/hplus/css/bootstrap.min14ed.css"
	rel="stylesheet">
<link href="/stu/static/plugins/hplus/css/font-awesome.min93e3.css"
	rel="stylesheet">
<link href="/stu/static/plugins/hplus/css/animate.min.css" rel="stylesheet">
<link href="/stu/static/plugins/hplus/css/style.min862f.css" rel="stylesheet">
<link href="/stu/static/plugins/lightbox-dialog/dist/css/Lobibox.min.css" rel="stylesheet">
<script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
		    <!-- 背景 -->
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
			    <ul class="nav" id="side-menu">
			        <li class="nav-header">
			            <div class="dropdown profile-element">
                            <span><img alt="未上传头像" style="border-radius:50px;" src="${SESSIONINFO.info.photo}" width="64" height="64"/></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${SESSIONINFO.info.username}</strong></span>
                                <span class="text-muted text-xs block">${SESSIONINFO.organization.organizationname}<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="profile.html">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="contacts.html">联系我们</a>
                                </li>
                                <li><a class="J_menuItem" href="mailbox.html">信箱</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="/stu/user/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">STU
                        </div>
			        </li>
			        <%=JspUtil.getResources(request)%>
			        
			    </ul>
			</div>
			
		</nav>
		
		
		<div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="#">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="welcome.html">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="/stu/user/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="/stu/page/welcome" frameborder="0" data-id="welcome.html" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2015-2016 <a href="javascript:void(0);" target="_blank">stu</a>
                </div>
            </div>
        </div>
        
        
        
        
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-2">

                    <li class="active">
                        <a data-toggle="tab" href="#tab-1">
                            <i class="fa fa-gear"></i> 主题
                        </a>
                    </li>
                    <li class=""><a data-toggle="tab" href="#tab-2">
                        通知
                    </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i></small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                            </div>
                        </div>
                    </div>
                    <div id="tab-2" class="tab-pane">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 最新通知</h3>
                            <small><i class="fa fa-tim"></i> 您当前有10条未读信息</small>
                        </div>
                        <div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/stu/static/image/a1.jpg">
                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        通知实例。
                                        <br>
                                        <small class="text-muted">今天 9:21</small>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--右侧边栏结束-->
		
		
	</div>


	<!-- js -->
	<script src="/stu/static/plugins/hplus/js/jquery.min.js"
		type="text/javascript"></script>
	<script src="/stu/static/plugins/hplus/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="/stu/static/plugins/hplus/js/plugins/metisMenu/jquery.metisMenu.js"
		type="text/javascript"></script>
	<script src="/stu/static/plugins/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="/stu/static/plugins/hplus/js/plugins/layer/layer.min.js"
		type="text/javascript"></script>
	<script src="/stu/static/plugins/hplus/js/hplus.min.js" type="text/javascript"></script>
	<script src="/stu/static/plugins/hplus/js/contabs.min.js" type="text/javascript"></script>
	<script src="/stu/static/plugins/hplus/js/plugins/pace/pace.min.js"
		type="text/javascript"></script>
    <script src="/stu/static/plugins/lightbox-dialog/dist/js/lobibox.min.js"></script> 
	<script src="/stu/static/js/main.js" type="text/javascript"></script>
	<script type="text/javascript">

    var TopLobibox=function(model,type,options){
    	if(model=='notify'){
    		Lobibox.notify(type, {
    			title:typeof(options.title)=='undefined'?null:options.title,
    			msg:typeof(options.msg)=='undefined'?null:options.msg,
    			delay:typeof(options.delay)=='undefined'?1500:options.delay,
    			soundPath:'/stu/static/sounds/'
    		});
    	}
    	else if(model=='alert'){
    		$("<div class='hidden need-remove-sound'></div>").sound(typeof(options.sound)=='undefined'?'sound1.ogg':options.sound);
    		Lobibox.alert(type, {
    			title:typeof(options.title)=='undefined'?null:options.title,
    	    	msg:typeof(options.msg)=='undefined'?null:options.msg
			});
    	}
    };
	</script>
</body>
</html>