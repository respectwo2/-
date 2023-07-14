<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        .positive {
            color: green;
        }
        .negative {
            color: red;
        }
    </style>
</head>
<body>
    <h1>주간 변동량</h1>
    <table>
        <thead>
            <tr>
                <th>Coin</th>
                <th>Change Amount</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the coins array and display coin name and change amount -->
            <c:forEach var="coin" items="${coins}">
                <tr>
                    <td>${coin.coinName}</td>
                    <td class="${coin.priceChange > 0 ? 'positive' : 'negative'}">${coin.priceChange}%</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>