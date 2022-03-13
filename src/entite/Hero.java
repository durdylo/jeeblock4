package entite;

public class Hero {
	private Long id;
	private String nom;
	private String telephone;
	private String adresse;
	private double latitude;
	private double longitude;
	
	public Hero(String nom, String telephone, String adresse, double latitude, double longitude) {
		this.nom = nom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Hero() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
