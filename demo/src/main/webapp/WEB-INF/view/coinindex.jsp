<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ include file="layout/header.jsp"%>
<body>
  
      <div class="container-fluid">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">UBMI & UBAI Rate</h5>
            <%@ include file ="charts.jsp" %>
            <div><p class="mb-0"></p>
            <h5 id="indexinfo">UBMI (UPBIT Market Index)</h5><br>
            <span>유동시가총액을 사용한 시가총액 가중 방식 인덱스</span><br>
            <span>거래소에 상장된 모든 화폐를 계산해 시장 전체의 흐름을 파악할 수 있는 지표</span><br><br>
            <span></span>
            <span></span>
            <h5 id="indexinfo">UBAI (UPBIT Altcoin Index)</h5><br>
            <span>비트코인을 제외한 UBMI 인덱스</span><br>
            <span>비트코인의 지배적인 영향력을 제외한 알트코인의 움직임을 알 수 있는 지표</span><br>
            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Sample Page</h5>
            <p class="mb-0">This is a sample page </p>
          </div>
        </div>
      </div>
      <div class="container-fluid">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title fw-semibold mb-4">Sample Page</h5>
            <p class="mb-0">This is a sample page </p>
          </div>
        </div>
      </div>
      
    </div>
  </div>
  <script src="../libs/jquery/dist/jquery.min.js"></script>
  <script src="../libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
  <script src="../js/sidebarmenu.js"></script>
  <script src="../js/app.min.js"></script>
  <script src="../libs/simplebar/dist/simplebar.js"></script>
</body>
</body>
<script>
document.getElementById("searchButton").addEventListener("click", function(event) {
  event.preventDefault(); 

  var searchTerm = document.getElementById("searchInput").value;
  var url = "/main?tvwidgetsymbol=" + searchTerm;
  window.location.href = url;
});

</script>

</html>