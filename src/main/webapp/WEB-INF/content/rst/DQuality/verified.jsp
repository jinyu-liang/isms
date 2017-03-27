<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>审批</title>
</head>
<body>
	<div class="page unitBox" >
		<s:property value="message" escapeHtml="false" />
		<div class="pageContent">
			<s:form method="post" action="d-report!verified" cssClass="pageForm"
				onsubmit="return validateCallback(this,closeAndRefresh);" id="%{_}">
				<s:hidden name="queryEntity.reportId"></s:hidden>
				<div>
					<div class="pageFormContent" layoutH="56"
						style="float: left; width: 500px;">
						<s:include value="_edit_ver.jsp" />
					</div>
					<div style="float: right; width: 475px;">
						<ul id="${_}myGallery">
							<s:if test="entity.fileName!=null&&entity.fileName.size()>0">
								<s:iterator value="entity.fileName" id="image">
									<li><img src="<%=basePath%>/<s:property value='image'/>" />
									</li>
								</s:iterator>
							</s:if>
							<s:else>
								<li><img src="<%=basePath%>/image/pub.jpg" />
								</li>
							</s:else>
						</ul>
					</div>
				</div>
				<div class="formBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<s:submit type="button" value="确定" />
								</div>
							</div></li>
						<li>
							<div class="button">
								<div class="buttonContent">
									<button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
								</div>
							</div></li>
					</ul>
				</div>
			</s:form>
		</div>
	</div>
	<script type="text/javascript">
		function closeAndRefresh(json) {
			if (json.statusCode == DWZ.statusCode.error) {
				if (json.message && alertMsg)
					alertMsg.error(json.message);
			} else if (json.statusCode == DWZ.statusCode.timeout) {
				if (alertMsg)
					alertMsg.error(json.message || DWZ.msg("sessionTimout"), {
						okCall : DWZ.loadLogin
					});
				else
					DWZ.loadLogin();
			} else {
				if (json.warnMessage && alertMsg) {
					alertMsg.error(json.warnMessage);
				} else if (json.infoMessage && alertMsg) {
					alertMsg.info(json.infoMessage);
				} else if (json.message && alertMsg) {
					alertMsg.correct(json.message);
					navTabSearch(this);
					$.pdialog.closeCurrent();
				}
			}
		}
		$("#${_} #${_}myGallery").ready(function() {
			$('#${_} #${_}myGallery').galleryView({
				transition_speed : 2000, //INT - duration of panel/frame transition (in milliseconds)
				transition_interval : 4000, //INT - delay between panel/frame transitions (in milliseconds)
				easing : 'swing', //STRING - easing method to use for animations (jQuery provides 'swing' or 'linear', more available with jQuery UI or Easing plugin)
				show_panels : true, //BOOLEAN - flag to show or hide panel portion of gallery
				show_panel_nav : true, //BOOLEAN - flag to show or hide panel navigation buttons
				enable_overlays : true, //BOOLEAN - flag to show or hide panel overlays

				panel_width : 460, //INT - width of gallery panel (in pixels)
				panel_height : 385, //INT - height of gallery panel (in pixels)
				panel_animation : 'slide', //STRING - animation method for panel transitions (crossfade,fade,slide,none)
				panel_scale : 'crop', //STRING - cropping option for panel images (crop = scale image and fit to aspect ratio determined by panel_width and panel_height, fit = scale image and preserve original aspect ratio)
				overlay_position : 'bottom', //STRING - position of panel overlay (bottom, top)
				pan_images : true, //BOOLEAN - flag to allow user to grab/drag oversized images within gallery
				pan_style : 'drag', //STRING - panning method (drag = user clicks and drags image to pan, track = image automatically pans based on mouse position
				pan_smoothness : 15, //INT - determines smoothness of tracking pan animation (higher number = smoother)
				start_frame : 1, //INT - index of panel/frame to show first when gallery loads
				show_filmstrip : true, //BOOLEAN - flag to show or hide filmstrip portion of gallery
				show_filmstrip_nav : true, //BOOLEAN - flag indicating whether to display navigation buttons
				enable_slideshow : true, //BOOLEAN - flag indicating whether to display slideshow play/pause button
				autoplay : true, //BOOLEAN - flag to start slideshow on gallery load
				show_captions : true, //BOOLEAN - flag to show or hide frame captions	
				filmstrip_size : 3, //INT - number of frames to show in filmstrip-only gallery
				filmstrip_style : 'scroll', //STRING - type of filmstrip to use (scroll = display one line of frames, scroll filmstrip if necessary, showall = display multiple rows of frames if necessary)
				filmstrip_position : 'bottom', //STRING - position of filmstrip within gallery (bottom, top, left, right)
				frame_width : 80, //INT - width of filmstrip frames (in pixels)
				frame_height : 50, //INT - width of filmstrip frames (in pixels)
				frame_opacity : 0.5, //FLOAT - transparency of non-active frames (1.0 = opaque, 0.0 = transparent)
				frame_scale : 'crop', //STRING - cropping option for filmstrip images (same as above)
				frame_gap : 5, //INT - spacing between frames within filmstrip (in pixels)
				show_infobar : true, //BOOLEAN - flag to show or hide infobar
				infobar_opacity : 1
			//FLOAT - transparency for info bar
			});
		});
	</script>
</body>
</html>
