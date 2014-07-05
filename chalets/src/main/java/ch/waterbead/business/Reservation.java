package ch.waterbead.business;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ch.waterbead.common.DomainEntity;

@Entity
@Table(name="Reservations")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	@JoinColumn(name="idUtilisateur")
	@ManyToOne()
	private User user;
	@JoinColumn(name="idChalet")
	@ManyToOne
	private Chalet chalet;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	public Reservation() {
		
	}
	
	public Reservation(User user, Chalet chalet, Date dateDebut, Date dateFin) {
		this.user = user;
		this.chalet = chalet;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Chalet getChalet() {
		return chalet;
	}
	public void setChalet(Chalet chalet) {
		this.chalet = chalet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
}	
