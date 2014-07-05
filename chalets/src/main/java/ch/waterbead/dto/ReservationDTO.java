package ch.waterbead.dto;

import java.io.Serializable;
import java.util.Date;

public class ReservationDTO implements Serializable {
	private String nomChalet;
	private String dateDebut;
	private String dateFin;
	public String getNomChalet() {
		return nomChalet;
	}
	public void setNomChalet(String nomChalet) {
		this.nomChalet = nomChalet;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
}	
