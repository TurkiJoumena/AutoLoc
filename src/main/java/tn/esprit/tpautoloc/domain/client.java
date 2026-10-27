package tn.esprit.tpautoloc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    @NotBlank(message = "Le numéro de permis est obligatoire")
    private String numPermis;
    private String dateInscription;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "client")
    @JsonManagedReference("client-Reservation")
    private Set<Reservation> reservations;

}