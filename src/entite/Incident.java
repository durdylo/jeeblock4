package entite;

public class Incident {
	private String ville;
	private double latitude;
	private double longitude;
	private TypeIncident typeIncident; 
	public Incident(String ville, double latitude, double longitude) {
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Incident() {
		
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public TypeIncident getTypeIncident() {
		return typeIncident;
	}
	public void setTypeIncident(TypeIncident typeIncident) {
		this.typeIncident = typeIncident;
	}

}
