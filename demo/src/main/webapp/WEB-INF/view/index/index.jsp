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
           /*  overflow-y: scroll; */
        }

        #mainContent {
            float: right;
            width: 70%;
        }
    </style>
</head>
<body>
    <div id="sidebar">
        <h1>Search</h1>
        <form id="searchForm">
            <input type="search" id="searchInput">
            <button type="submit">Search</button>
        </form>
        <div id="searchResults">
        </div>
    </div>

    <div id="mainContent">
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

$(document).ready(function() {
    $("#searchForm").submit(function(e) {
        e.preventDefault(); 

        var searchTerm = $("#searchInput").val();
        var url = "/exchange/tickerinfo?search=" + searchTerm;
        window.location.href = url;
    });
});




//AJAX로 정보 띄우는 코드

 /* $(document).ready(function() {
    $("#searchForm").submit(function(e) {
        e.preventDefault(); 

        var searchTerm = $("#searchInput").val();

        var data = {};

        if (searchTerm) {
            if (isNaN(searchTerm)) {
                data.ticker = searchTerm;
            } else {
                data.ticker_id = searchTerm;
            }
        }

        if (searchTerm && isNaN(searchTerm)) {
            data.ticker_name = searchTerm;
        }

        $.ajax({
            url: "/exchange/tickerinfo",
            type: "GET",
            data: data,
            success: function(response) {
                $("#searchResults").html(response); 
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
}); */
</script></body>
</html>