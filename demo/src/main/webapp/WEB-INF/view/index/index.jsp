<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
    <style>
        /* 스타일링 코드 */
        #sidebar {
            float: left;
            width: 30%;
        }

        #searchResults {
            width: 70%;
            height: 200px;
            overflow-y: scroll;
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
            // 검색 폼의 submit 이벤트 리스너
            $("#searchForm").submit(function(e) {
                e.preventDefault(); // 기본 submit 이벤트 막기

                // 검색어 가져오기
                var searchTerm = $("#searchInput").val();

                // AJAX 요청 보내기
                $.ajax({
                    url: "exchange?ticker={ticker}", // 실제 검색 결과를 받을 URL로 대체해야 합니다.
                    type: "GET",
                    data: { search: searchTerm }, // 검색어를 파라미터로 전달
                    success: function(response) {
                        // 검색 결과를 받았을 때 처리하는 로직
                        // 받은 데이터를 활용하여 검색 결과 목록 업데이트
                        $("#searchResults").html(response); // 받은 데이터를 목록에 적용
                    },
                    error: function(error) {
                        // 에러 처리 로직
                        console.log(error);
                    }
                });
            });
        });
    </script>
</body>
</html>