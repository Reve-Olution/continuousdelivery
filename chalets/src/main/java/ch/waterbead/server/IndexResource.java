package ch.waterbead.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.waterbead.business.Chalet;
import ch.waterbead.business.Reservation;
import ch.waterbead.business.User;
import ch.waterbead.dto.ChaletDTO;
import ch.waterbead.dto.ReservationDTO;
import ch.waterbead.services.ReservationService;

import com.yammer.metrics.annotation.Timed;

@Path("/")
public class IndexResource {
    @Path("/reservations")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<ReservationDTO> index2() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    	ReservationService reservationService = new ReservationService();
    	List<Reservation> reservations = reservationService.getReservations();
    	List<ReservationDTO> reservationDTOs = new ArrayList<>();
    	for(Reservation reservation : reservations) {
    		ReservationDTO reservationDTO = new ReservationDTO();
    		reservationDTO.setNomChalet(reservation.getChalet().getNom());
    		reservationDTO.setDateDebut(sdf.format(reservation.getDateDebut()));
    		reservationDTO.setDateFin(sdf.format(reservation.getDateFin()));
    		reservationDTOs.add(reservationDTO);
    	}
    	return reservationDTOs;
    }

    @Path("/chalet")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public ChaletDTO index3() {
    	ReservationService reservationService = new ReservationService();
    	Chalet chalet = reservationService.getChalet();
    	ChaletDTO chaletDTO = new ChaletDTO();
    	chaletDTO.setId(chalet.getId());
    	chaletDTO.setNom(chalet.getNom());
    	chaletDTO.setDescription(chalet.getDescription());
    	return chaletDTO;
    }
}