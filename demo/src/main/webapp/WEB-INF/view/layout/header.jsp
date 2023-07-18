<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
#info {line-height:3; 
}
.card-body { margin-right:50px;
}
.form-control{ margin-right:50px;
}
.input-group{ margin-right:950px;

}
#indexinfo{ font-size: 30px;
}
.left-sidebar{
	background-color:#AFEEEE;
}

/* .navbar.navbar-expand-lg.navbar-light {
  border: 1px solid #5F9EA0;
  border-radius: 5px;
} */
</style>
<title>Coinnable</title>
<link rel="shortcut icon" type="image/png"
	href="../images/logos/coinnablefavicon.png" />
<link rel="stylesheet" href="../css/styles.min.css" />
<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
</head>
<body>
	<!--  Body Wrapper -->
	<div class="page-wrapper" id="main-wrapper" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed">
		<%@include file="leftSidebar.jsp"%>
		<!--  Main wrapper -->
		<div class="body-wrapper">
			<!--  Header Start -->
			
			<!--  search 부분 -->
			<header class="app-header">
				<nav
            class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
            id="layout-navbar"
          >
            <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
              <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                <i class="bx bx-menu bx-sm"></i>
              </a>
            </div>

            <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
              <!-- Search -->
              <div class="navbar-nav align-items-center">
                <div class="nav-item d-flex align-items-center">
                  <i class="bx bx-search fs-4 lh-0">
                  </i>
                  <a id="searchButton">
      <img src="../images/logos/search.PNG"/>
    </a>
    <input
      id="searchInput"
      type="text"
      class="form-control border-0 shadow-none"
      placeholder="Search..."
      aria-label="Search..."
    />
                </div>
              </div>
              <!-- /Search -->
              
              
            </div>
            <ul class="navbar-nav flex-row align-items-center ms-auto">
                <!-- Place this tag where you want the button to render. -->
                <li class="nav-item lh-1 me-3">
                  <h4>About Coinnable</h4>
                </li>
              </ul>
          </nav>
			<div class="topbar">
			<div class="tradingview-widget-container">
  <div class="tradingview-widget-container__widget"></div>
  <div class="tradingview-widget-copyright"><a href="https://kr.tradingview.com/" rel="noopener nofollow" target="_blank"></a></div>
  <script type="text/javascript" src="https://s3.tradingview.com/external-embedding/embed-widget-tickers.js" async>
  {
  "symbols": [
    {
      "description": "비트코인",
      "proName": "UPBIT:BTCKRW"
    },
    {
      "description": "이더리움",
      "proName": "UPBIT:ETHKRW"
    },
    {
      "description": "메탈",
      "proName": "UPBIT:MTLKRW"
    },
    {
      "description": "도지코인",
      "proName": "UPBIT:DOGEKRW"
    },
    {
      "description": "리플",
      "proName": "UPBIT:XRPKRW"
    },
    {
      "description": "스택스",
      "proName": "UPBIT:STXKRW"
    },
    {
      "description": "에이브",
      "proName": "UPBIT:AAVEKRW"
    },
    {
      "description": "퀀텀",
      "proName": "UPBIT:QTUMKRW"
    }
  ],
  "colorTheme": "light",
  "isTransparent": false,
  "showSymbolLogo": true,
  "largeChartUrl": "http://localhost:8080/main",
  "locale": "kr"
}
  </script>
</div>
			</div>
			
			</header>
			<!--  Header End -->
<br>
<br>
<br>
<br>
<br>
