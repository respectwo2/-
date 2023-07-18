</div>
</div>
<script src="../libs/jquery/dist/jquery.min.js"></script>
<script src="../libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/sidebarmenu.js"></script>
<script src="../js/app.min.js"></script>
<script src="../libs/apexcharts/dist/apexcharts.min.js"></script>
<script src="../libs/simplebar/dist/simplebar.js"></script>
<script src="../js/dashboard.js"></script>

<script>
document.getElementById("searchButton").addEventListener("click", function(event) {
  event.preventDefault(); 
  performSearch();
});

document.getElementById("searchInput").addEventListener("keypress", function(event) {
  if (event.key === "Enter") {
    event.preventDefault();
    performSearch();
  }
});

function performSearch() {
  var searchTerm = document.getElementById("searchInput").value;
  var url = "/main?tvwidgetsymbol=" + searchTerm;
  window.location.href = url;
}
</script>

</body>
</html>