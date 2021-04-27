<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>

</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
				
					<div class="page-header">
							<h1>
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									编辑菜单
								</small>
							</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">

						<form  action="huoxing/uploadFile.do" name="adminForm" id="adminForm" method="post" class="form-horizontal" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 文件 :</label>
								<div class="col-sm-9">
									<input type="file" name="file" id="fileName"  class="col-xs-10 col-sm-5" />
								</div>
							</div>
							
							<div class="clearfix form-actions">
								<div class="col-md-offset-3 col-md-9">
									<a class="btn btn-mini btn-primary" onclick="save();">上传文件</a>
									<%--<a class="btn btn-mini btn-danger" onclick="goback('${MENU_ID}');">取消</a>--%>
								</div>
							</div>
							<div class="hr hr-18 dotted hr-double"></div>
						</form>

						</div>
					</div>


					<div class="row">
						<div class="col-xs-12">

							<form  action="huoxing/update_expiration.do"  name="expirationForm" id="expirationForm" method="post" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 过期时间:</label>
									<div class="col-sm-9">
										<input type="text" name="dateStr" id="expiration"  class="col-xs-10 col-sm-5" placeholder="请输入时间yyyy-MM-dd格式" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 激活码:</label>
									<div class="col-sm-9">
										<input type="text" name="appKey" id="appKey"  placeholder="请输入激活码" class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">应用名称:</label>
									<div class="col-sm-9">
										<input type="text" name="appName" id="appName"  placeholder="请输入应用名称" class="col-xs-10 col-sm-5" />
									</div>
								</div>



								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<%--<a class="btn btn-mini btn-primary" onclick="confirm();">保存</a>--%>
										<%--<a class="btn btn-mini btn-danger" onclick="goback('${MENU_ID}');">取消</a>--%>
										<input type="button" value="确认修改过期时间" id="confirm"/>
									</div>
								</div>
								<div class="hr hr-18 dotted hr-double"></div>
							</form>
						</div>
					</div>


					<div class="row">
						<div class="col-xs-12">

							<form  action="huoxing/find_version.do" id="appVersionForm"  method="post" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 查找app版本信息:</label>
									<div class="col-sm-9">
										<input type="text" name="appName" id="appVersionName"  class="col-xs-10 col-sm-5" placeholder="请输入app名字" />
									</div>
								</div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<input type="button" value="查找app版本信息" id="findVersion"/>
									</div>
								</div>
								<div class="hr hr-18 dotted hr-double"></div>
							</form>
						</div>
					</div>


					<div class="row">
						<div class="col-xs-12">

							<form  action="huoxing/update_version.do" id="versionForm" method="post" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> app名称:</label>
									<div class="col-sm-9">
										<input type="text" name="appName"  value="${mv.appname}" id="tName" class="col-xs-10 col-sm-5" placeholder="请输入app名字" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> app版本:</label>
									<div class="col-sm-9">
										<input type="text" name="version"  value="${mv.version}" id="tVersion" class="col-xs-10 col-sm-5" placeholder="请输入版本信息" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 文件名称:</label>
									<div class="col-sm-9">
										<input type="text" name="fileName"  value="${mv.filename}"  id="tFileName" class="col-xs-10 col-sm-5" placeholder="请输入文件名称" />
									</div>
								</div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<input type="button" value="确认修改版本信息" id="updateAppVersion"/>
									</div>
								</div>
								<div class="hr hr-18 dotted hr-double"></div>
							</form>
						</div>
					</div>



				</div>
			</div>
		</div>
		<!-- /.main-content -->


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>

	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">

		$(top.hangge());

		//保存
		function save(){
			$("#adminForm").submit();
		};

		$('#confirm').click(function(){
			var date=$('#expiration').val();
			var appKey=$('#appKey').val();
			var appName=$('#appName').val();
            // var url='http://localhost:8080/huoxing/update_expiration.do';
            var url='http://118.24.11.132:8080/huoxing/update_expiration.do';
            alert("url="+url);


            $.ajax({
                url:url,
                type:"post",
                data:"dateStr="+date+"&appKey="+appKey+"&appName="+appName,
                success:function(data){
					alert(data.result);
                }
            });

		});

		$('#findVersion').click(function(){
		    var appName=$('#appVersionName').val();
		    if(appName==""){
                $('#appVersionName').tips({
                    side:3,
                    msg:'请输入app名称',
                    bg:'#AE81FF',
                    time:2
                });
                $('#appVersionName').focus();
                return false;
			}
            $('#appVersionForm').submit();


		});

		$('#updateAppVersion').click(function(){
		    var appName=$('#tName').val();
		    if(appName==""){
                $('#tName').tips({
                    side:3,
                    msg:'请输入app名称',
                    bg:'#AE81FF',
                    time:2
                });
                $('#tName').focus();
                return false;
			}
			var version=$('#tVersion').val();
		    if(version==""){
                $('#tVersion').tips({
                    side:3,
                    msg:'请输入版本信息',
                    bg:'#AE81FF',
                    time:2
                });
                $('#tVersion').focus();
                return false;
			}
			var fileName=$('#tFileName').val();
		    if(fileName==""){
                $('#tFileName').tips({
                    side:3,
                    msg:'请输入文件名',
                    bg:'#AE81FF',
                    time:2
                });
                $('#tFileName').focus();
                return false;
			}
			// var url="http://localhost:8080/huoxing/update_version.do";
            var url="http://118.24.11.132:8080/huoxing/update_version.do";


			$.ajax({
				url:url,
				type:'post',
				data:"appName="+appName+"&version="+version+"&fileName="+fileName,
				success:function(data){
				    alert(data.result);
				}
			});

		});


	</script>


</body>
</html>