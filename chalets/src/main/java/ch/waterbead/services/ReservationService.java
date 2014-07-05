package ch.waterbead.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.waterbead.business.Chalet;
import ch.waterbead.business.Reservation;

public class ReservationService {
	private static final String PERSISTENCE_UNIT_NAME = "default";
	private static EntityManagerFactory emf;
	
	public Chalet getChalet() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		Chalet chalet = em.find(Chalet.class,1L);
		return chalet;
	}
	
	public List<Reservation> getReservations() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		TypedQuery<Reservation> query = em.createQuery("SELECT r FROM Reservation r",Reservation.class);
		return query.getResultList();
	}
}
