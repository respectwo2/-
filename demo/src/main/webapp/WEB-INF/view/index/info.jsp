<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>    
<table>
        <tr>
            <th>Ticker ID</th>
            <th>Ticker</th>
            <th>Ticker Name</th>
            <th>Ticker Issuer</th>
            <th>Ticker TFeature</th>
            <th>Ticker Info</th>
        </tr>
        <tr>
            <td><%= coinInfo.getTicker_id() %></td>
            <td><%= coinInfo.getTicker() %></td>
            <td><%= coinInfo.getTicker_name() %></td>
            <td><%= coinInfo.getTicker_issuer() %></td>
            <td><%= coinInfo.getTicker_tfeature() %></td>
            <td><%= coinInfo.getTicker_info() %></td>
        </tr>
    </table>

</body>
</html>