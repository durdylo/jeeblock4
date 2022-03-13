<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
	<div class="container">
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">Nom</th>
	      <th scope="col">Tel</th>

	    </tr>
	  </thead>
	  <tbody>
	   <c:forEach var="hero" items="${heros}"> 
	    <tr>
	      <td>${hero.nom}</td>
	      <td>${hero.telephone}</td>
	    </tr>
	   </c:forEach>
	  </tbody>
	</table>
</div>
<%@ include file="fin.jsp" %>
