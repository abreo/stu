<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>USER</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<!-- css -->
<link rel="/stu/hplus/shortcut icon" href="favicon.ico">
<link href="/stu/hplus/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="/stu/hplus/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link href="/stu/hplus/css/animate.min.css" rel="stylesheet">
<link href="/stu/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body>
<div id="toolbar" class="btn-group">
   <button id="btn_add" type="button" class="btn btn-default">
    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
   </button>
   <button id="btn_edit" type="button" class="btn btn-default">
    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
   </button>
   <button id="btn_delete" type="button" class="btn btn-default">
    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
   </button>
  </div>
<table id="table"></table>
    <!-- js -->
    <script src="/stu/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="/stu/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <!-- <script src="/stu/hplus/js/content.min.js?v=1.0.0"></script> -->
    <script src="/stu/hplus/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="/stu/hplus/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="/stu/hplus/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	
	<script type="text/javascript">
	$(function(){
		$('#table').bootstrapTable({
			method:'post',
			url:'/stu/user/list.htm',
			pagination:'true',
			sortName:'id',
			sortOrder:'asc',
			sidePagination:'server',
			idField:'id',
			toolbar:'#toolbar',
			toolbarAlign:'right',
			contentType:'application/x-www-form-urlencoded',
			queryParamsType:'',
		    columns: [{
		        field: 'id',
		        title: 'id'
		    }, {
		        field: 'loginname',
		        title: 'Name'
		    }]
		});
	});
	</script>
</body>
</html>