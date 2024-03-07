<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
    <style>
        #sidebar {
            float: left;
            width: 30%;
        }

        #searchResults {
            width: 70%;
            height: 200px;
        }

        #mainContent {
            float: right;
            width: 70%;
        }
        #searchForm {
            width: 1000px;
            height: 70px;
        }
        
    </style>
</head>
<body>
    <div id="sidebar">
        <form id="searchForm">
            <input type="search" id="searchInput">
            <button type="submit">Search</button>
        </form>
    </div>

    <div id="mainContent">
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
    $("#searchForm").submit(function(e) {
        e.preventDefault(); 

        var searchTerm = $("#searchInput").val();
        var url = "/main?tvwidgetsymbol=" + searchTerm;
        window.location.href = url;
    });
});
</script>
</body>
</html>