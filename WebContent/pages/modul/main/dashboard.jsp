<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.kimia.farma.model.DashBoard"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
  <div class="row">
            <div class="col-xs-12 col-md-6 col-lg-3">
                <div class="panel panel-blue panel-widget ">
                  <a href="<?php echo base_url() ?>kkj/view">
                    <div class="row no-padding">
                        <div class="col-sm-3 col-lg-5 widget-left">
                            <svg class="glyph stroked bag"><use xlink:href="#stroked-bag"></use></svg>
                        </div>
                        <div class="col-sm-9 col-lg-7 widget-right">
                            <div class="large"><%if(session.getAttribute("dashBoard")!=null){DashBoard d =(DashBoard)session.getAttribute("dashBoard"); out.print(d.getTransaksi_obt_b());} %></div>
                            <div class="text-muted"></div>
                        </div>
                    </div>
                  </a>
                </div>
            </div>
            <div class="col-xs-12 col-md-6 col-lg-3">
                <div class="panel panel-orange panel-widget">
                  <a href="<?php echo base_url(); ?>group/view">
                    <div class="row no-padding">
                        <div class="col-sm-3 col-lg-5 widget-left">
                            <svg class="glyph stroked empty-message"><use xlink:href="#stroked-empty-message"></use></svg>
                        </div>
                        <div class="col-sm-9 col-lg-7 widget-right">
                            <div class="large"><%if(session.getAttribute("dashBoard")!=null){DashBoard d =(DashBoard)session.getAttribute("dashBoard"); out.print(d.getTransaksi_obt_h());}%></div>
                            <div class="text-muted"></div>
                        </div>
                    </div>
                  </a>
                </div>
            </div>
            <div class="col-xs-12 col-md-6 col-lg-3">
                <div class="panel panel-teal panel-widget">
                  <a href="<?php echo base_url(); ?>anggota/view">
                    <div class="row no-padding">
                        <div class="col-sm-3 col-lg-5 widget-left">
                            <svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg>
                        </div>
                        <div class="col-sm-9 col-lg-7 widget-right">
                            <div class="large"><%if(session.getAttribute("dashBoard")!=null){DashBoard d =(DashBoard)session.getAttribute("dashBoard"); out.print(d.getTransaksi_b());}%></div>
                            <div class="text-muted"></div>
                        </div>
                    </div>
                  </a>
                </div>
            </div>
            <div class="col-xs-12 col-md-6 col-lg-3">
                <div class="panel panel-red panel-widget">
                    <div class="row no-padding">
                        <div class="col-sm-3 col-lg-5 widget-left">
                            <svg class="glyph stroked app-window-with-content"><use xlink:href="#stroked-app-window-with-content"></use></svg>
                        </div>
                        <div class="col-sm-9 col-lg-7 widget-right">
                            <div class="large"><%if(session.getAttribute("dashBoard")!=null){DashBoard d =(DashBoard)session.getAttribute("dashBoard"); out.print(d.getPendapatan_b());}%></div>
                            <div class="text-muted"></div>
                        </div>
                    </div>
                </div>
            </div>
             <div class="col-xs-12 col-md-6 col-lg-3">
                <div class="panel panel-red panel-widget">
                    <div class="row no-padding">
                        <div class="col-sm-3 col-lg-5 widget-left">
                            <svg class="glyph stroked app-window-with-content"><use xlink:href="#stroked-app-window-with-content"></use></svg>
                        </div>
                        <div class="col-sm-9 col-lg-7 widget-right">
                            <div class="large"><%if(session.getAttribute("dashBoard")!=null){DashBoard d =(DashBoard)session.getAttribute("dashBoard"); out.print(d.getPendapatan_h());}%></div>
                            <div class="text-muted"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--/.row-->
</html>