<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }
        
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
            text-align: center;
        }
        
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h1>검색 결과가 없습니다</h1>
            <p>다른 검색어로 다시 시도해주세요.</p>
        </div>
    </div>

    <script>
        var modal = document.getElementById("myModal");

        function closeModal() {
            modal.style.display = "none";
            history.back();
        }

        modal.style.display = "block";

        document.addEventListener("keydown", function(event) {
            if (event.keyCode === 27) {
                closeModal();
            }
        });
    </script>
</body>
</html>