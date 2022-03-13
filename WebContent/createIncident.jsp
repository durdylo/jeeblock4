<%@ include file="head.jsp" %>
        <%@ include file="menu.jsp" %>
       <form method="post" action="" class="container">

	<div class="form-group">
		    <label for="ville">Ville</label>
		    <input type="text" class="form-control" name="ville"  id="ville" placeholder=" votre adresse">
  	</div>
  	<div class="form-group">
		    <label for="latitude">Latitude</label>
		    <input type="number" class="form-control" step="0.01" name="latitude"  id="latitude" placeholder="longitude">
  	</div>
  	<div class="form-group">
		    <label for="longitude">Longitude</label>
		    <input type="number"class="form-control" step="0.01" name="longitude"  id="longitude" placeholder="longitude">
  	</div>
  	
 	<div class="d-flex">
  	<c:forEach var="typeIncident" items="${typeIncidents}"> 
		 <div class="form-check">
		  <input class="form-check-input"  name="incidentType"   type="radio" value="${typeIncident.id}" id="${typeIncident.nom}">
		  <label class="form-check-label" for="${typeIncident.nom}">
		    <c:out value="${typeIncident.nom}"/>
		  </label>
		</div>
	</c:forEach>
	</div>
	<button type="submit" name="createIncident" class="btn btn-primary">Enregistrer</button>
	</form>
        <%@ include file="fin.jsp" %>
        