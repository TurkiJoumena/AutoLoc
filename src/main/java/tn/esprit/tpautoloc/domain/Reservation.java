package tn.esprit.tpautoloc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutVehicule statut;

    @ManyToOne
    @JsonBackReference("client-Reservation")
    private client client;

    @OneToOne(mappedBy ="reservation")
    private Contrat contrat;


    @ManyToOne
    @JsonBackReference("Reservation-Vehicule")
    private Vehicule vehicule;


}