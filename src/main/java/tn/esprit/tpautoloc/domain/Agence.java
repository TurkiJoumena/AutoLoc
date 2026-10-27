package tn.esprit.tpautoloc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "Agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;
    @NotBlank(message = "Le nom de l'agence est obligatoire")
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "agence")
    @JsonManagedReference("Agence-Employe")
    private Set<Employe> employees;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agence1")
    @JsonManagedReference("Vehicule-Agence")
    private Set<Vehicule> vehicules;

}