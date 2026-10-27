
package tn.esprit.tpautoloc.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.tpautoloc.domain.Reservation;
import tn.esprit.tpautoloc.exception.ResourceNotFoundException;
import tn.esprit.tpautoloc.repository.IReservationRepository;
import tn.esprit.tpautoloc.service.IReservationService;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    // Vérifier que la date de fin ne précède pas la date de début
    private void verifierDates(Reservation reservation) {
        if (reservation.getDateDebut() != null
                && reservation.getDateFin() != null
                && reservation.getDateFin()
                .isBefore(reservation.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin doit être postérieure ou égale à la date de début"
            );
        }
    }

    @Override
    public Reservation create(Reservation reservation) {
        verifierDates(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        Reservation existante = findById(id);

        // Modifier les champs simples uniquement s'ils sont fournis
        if (reservation.getDateDebut() != null) {
            existante.setDateDebut(reservation.getDateDebut());
        }

        if (reservation.getDateFin() != null) {
            existante.setDateFin(reservation.getDateFin());
        }

        if (reservation.getStatut() != null) {
            existante.setStatut(reservation.getStatut());
        }

        // Modifier les relations uniquement si elles sont fournies
        if (reservation.getClient() != null) {
            existante.setClient(reservation.getClient());
        }

        if (reservation.getVehicule() != null) {
            existante.setVehicule(reservation.getVehicule());
        }

        // Le contrat est une relation inverse : on ne le modifie pas ici
        verifierDates(existante);

        return reservationRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }

        reservationRepository.deleteById(id);
    }
}
