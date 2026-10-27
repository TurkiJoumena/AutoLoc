package tn.esprit.tpautoloc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name = "Equipement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    @NotBlank(message = "Le libellé est obligatoire")
    private String libelle;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Vehicule> vehicules;
}