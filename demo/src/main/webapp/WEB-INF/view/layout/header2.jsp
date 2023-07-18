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
<title>Insert title here</title>
<link rel="shortcut icon" type="image/png"
	href="../images/logos/favicon.png" />
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
			<header class="app-header">
				<nav class="navbar navbar-expand-lg navbar-light">
					<ul class="navbar-nav">
						<li class="nav-item d-block d-xl-none"><a
							class="nav-link sidebartoggler nav-icon-hover"
							id="headerCollapse" href="javascript:void(0)"> <i
								class="ti ti-menu-2"></i>
						</a></li>
						<li class="nav-item"><a class="nav-link nav-icon-hover"
							href="javascript:void(0)"> <i class="ti ti-bell-ringing"></i>
								<div class="notification bg-primary rounded-circle"></div>
						</a></li>
					</ul>
					<div class="navbar-collapse justify-content-end px-0" id="navbarNav">
            <ul class="navbar-nav flex-row ms-auto align-items-center justify-content-end">
    <input type="search" id="searchInput" class="form-control" />
            <!-- <form id="searchForm">
            <input type="search" id="searchInput">
             <button type="submit" class="btn btn-primary">Search</button>
 		      </form>  -->
 		      <div class="input-group">
  <div id="search-autocomplete" class="form-outline">
    <button id="searchButton" class="btn btn-primary">Search</button>
  </div>
</div>
              <!-- <li class="nav-item dropdown">
                <a class="nav-link nav-icon-hover" href="javascript:void(0)" id="drop2" data-bs-toggle="dropdown"
                  aria-expanded="false">
                  <img src="../images/profile/user-1.jpg" alt="" width="35" height="35" class="rounded-circle">
                </a> -->
                <div class="dropdown-menu dropdown-menu-end dropdown-menu-animate-up" aria-labelledby="drop2">
                  <div class="message-body">
                    <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-user fs-6"></i>
                      <p class="mb-0 fs-3">My Profile</p>
                    </a>
                    <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-mail fs-6"></i>
                      <p class="mb-0 fs-3">My Account</p>
                    </a>
                    <a href="javascript:void(0)" class="d-flex align-items-center gap-2 dropdown-item">
                      <i class="ti ti-list-check fs-6"></i>
                      <p class="mb-0 fs-3">My Task</p>
                    </a>
                    <a href="./authentication-login.html" class="btn btn-outline-primary mx-3 mt-2 d-block">Logout</a>
                  </div>
                </div>
              </li>
            </ul>
          </div>
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
