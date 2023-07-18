<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="layout/header.jsp"%>


<div class="container-fluid">
	<div class="row">
		<div class="col-lg-6 d-flex align-items-strech">
			<div class="card w-100">
				<div class="card-body p-4">
					<div class="d-flex align-items-center justify-content-between">
						<div>
							<h5 class="card-title fw-semibold">주간 변동량 Best Top 5</h5>
							<p class="card-subtitle mb-0">${currentTime}</p>
						</div>
					</div>
					<c:forEach items="${coins}" var="coin" varStatus="status">
						<c:if test="${status.index < 5}">
							<div class="card shadow-none mb-0 mt-3">
								<div class="d-flex align-items-center gap-3 py-3 border-bottom">
									<div
										class="round bg-light d-flex align-items-center justify-content-center rounded-circle">
										<i class="cc ETH fs-6"></i>
									</div>
									<div>
										<h6 class="mb-0 fw-semibold">${coin.coinName}</h6>
									</div>
									<c:choose>
										<c:when test="${coin.priceChange > 0}">
											<div class="ms-auto text-end">
												<span class="text-success">${coin.priceChange}%</span>
											</div>
										</c:when>
										<c:when test="${coin.priceChange == 0}">
											<div class="ms-auto text-end">
												<span class="text-dark">${coin.priceChange}%</span>
											</div>
										</c:when>
										<c:when test="${coin.priceChange < 0}">
											<div class="ms-auto text-end">
												<span class="text-danger">${coin.priceChange}%</span>
											</div>
										</c:when>
									</c:choose>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="col-lg-6 d-flex align-items-strech">
			<div class="card w-100">
				<div class="card-body p-4">
					<div class="d-flex align-items-center justify-content-between">
						<div>
							<h5 class="card-title fw-semibold">주간 변동량 Worst Top 5</h5>
							<p class="card-subtitle mb-0">${currentTime}</p>
						</div>
					</div>
					<c:forEach  varStatus="index" begin="${fn:length(coins)  - 1}" end="${fn:length(coins)  - 6}">
							<div class="card shadow-none mb-0 mt-3">
								<div class="d-flex align-items-center gap-3 py-3 border-bottom">
									<div
										class="round bg-light d-flex align-items-center justify-content-center rounded-circle">
										<i class="cc ETH fs-6"></i>
									</div>
									<div>
										<h6 class="mb-0 fw-semibold">${coin[index].coinName}</h6>
									</div>
									<c:choose>
										<c:when test="${coin.priceChange > 0}">
											<div class="ms-auto text-end">
												<span class="text-success">${coin[index].priceChange}%</span>
											</div>
										</c:when>
										<c:when test="${coin.priceChange == 0}">
											<div class="ms-auto text-end">
												<span class="text-dark">${coin[index].priceChange}%</span>
											</div>
										</c:when>
										<c:when test="${coin.priceChange < 0}">
											<div class="ms-auto text-end">
												<span class="text-danger">${coin[index].priceChange}%</span>
											</div>
										</c:when>
									</c:choose>
								</div>
							</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="card">
			<div class="card-body">
				<div class="bg-white px-2 pt-1 mt-2 text-center">
					<div class="table-responsive">
						<table class="table text-dark">
							<thead>
								<tr>
									<th>이름</th>
									<th>현재 가격 </th>
									<th>전일 종가 </th>
									<th>변화량</th>
									<th>52주 신고가</th>
									<th>52주 신저가</th>
								</tr>
							</thead>
							<tbody id="marketData" class="text-center">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="layout/footer.jsp"%>
<script src="../js/allUpbitDataCall.js"></script>

