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
            <span>�����ð��Ѿ��� ����� �ð��Ѿ� ���� ��� �ε���</span><br>
            <span>�ŷ��ҿ� ����� ��� ȭ�� ����� ���� ��ü�� �帧�� �ľ��� �� �ִ� ��ǥ</span><br><br>
            <span></span>
            <span></span>
            <h5 id="indexinfo">UBAI (UPBIT Altcoin Index)</h5><br>
            <span>��Ʈ������ ������ UBMI �ε���</span><br>
            <span>��Ʈ������ �������� ������� ������ ��Ʈ������ �������� �� �� �ִ� ��ǥ</span><br>
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