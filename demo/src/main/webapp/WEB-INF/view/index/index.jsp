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
            <!-- 검색 결과를 동적으로 업데이트할 목록 -->
        </div>
    </div>

    <div id="mainContent">
        <!-- 검색 결과에 따른 메인 페이지 -->
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
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
            url: "exchange/tickerinfo",
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
});
</script></body>
</html>