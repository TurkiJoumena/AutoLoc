package tn.esprit.tpautoloc.domain;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Vehicule {@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idVehicule;
    private String immatriculation;
    private String marque;
    private String modele;
    private CategorieVehicule categorie;
    @DecimalMin(
            value = "0.00",
            message = "Le tarif journalier ne peut pas être négatif"
    )
    private BigDecimal tarifJournalier;    private StatutVehicule statut;

    @ManyToOne
    @JsonBackReference("Vehicule-Agence")
    private Agence agence1;

    @ManyToMany(mappedBy = "vehicules" ,cascade = CascadeType.ALL)
    private Set<Equipement> equipements;

    @OneToMany(mappedBy = "vehicule" ,cascade = CascadeType.ALL)
    @JsonManagedReference("Reservation-Vehicule")
    private Set<Reservation> reservationss;

    @OneToMany(mappedBy = "vehicule" ,cascade = CascadeType.ALL)
    @JsonManagedReference("Maintenance-Vehicule")
    private Set<Maintenance> maintenances;

}
