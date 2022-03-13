
<%@ include file="head.jsp" %>

  
        <%@ include file="menu.jsp" %>
       <form method="post" action="" class="container">
       <div class="form-group">
		    <label for="Nom">Nom</label>
		    <input type="text" class="form-control" name="nom" id="Nom"placeholder=" Nom">
  	</div>
  	
  	  <div class="form-group">
		    <label for="tel">Téléphone</label>
		    <input type="text" class="form-control" name="tel"  id="tel" placeholder=" votre numero de téléphone">
  	</div>
  	
  	<div class="form-group">
		    <label for="adresse">Adresse</label>
		    <input type="text" class="form-control" name="adresse"  id="adresse" placeholder=" votre adresse">
  	</div>
<!--   	<div class="form-group">
		    <label for="adresse">Latitude</label>
		    <input type="number" class="form-control"  name="latitude"  id="latitude" placeholder="Latitude">
  	</div>
  	<div class="form-group">
		    <label for="adresse">Longitude</label>
		    <input type="number"class="form-control"  name="longitude"  id="longitude" placeholder="longitude">
  	</div> -->
	<div class="d-flex">
  	<c:forEach var="typeIncident" items="${typeIncidents}"> 
		 <div class="form-check">
		  <input class="form-check-input"  name="incident${typeIncident.id}"   type="checkbox" value="${typeIncident.id}" id="${typeIncident.nom}">
		  <label class="form-check-label" for="${typeIncident.nom}">
		    <c:out value="${typeIncident.nom}"/>
		  </label>
		</div>
	</c:forEach>
	</div>

  	<button type="submit" name="createHero" class="btn btn-primary">Enregistrer</button>
  	
  	
		</form>
<%@ include file="fin.jsp" %>
        