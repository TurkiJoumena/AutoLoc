
package tn.esprit.tpautoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.domain.Equipement;
import tn.esprit.tpautoloc.service.IEquipementService;

import java.util.List;

@Tag(
        name = "Equipements",
        description = "Gestion des équipements des véhicules"
)
@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;

    @Operation(
            summary = "Lister les équipements",
            description = "Retourne la liste de tous les équipements"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des équipements retournée"
    )
    @GetMapping
    public List<Equipement> findAll() {
        return equipementService.findAll();
    }

    @Operation(
            summary = "Consulter un équipement par ID",
            description = "Recherche un équipement à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipement trouvé"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    @GetMapping("/{id}")
    public Equipement findById(
            @Parameter(description = "Identifiant de l'équipement", example = "1")
            @PathVariable Long id) {
        return equipementService.findById(id);
    }

    @Operation(
            summary = "Créer un équipement",
            description = "Enregistre un nouvel équipement"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Équipement créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipement create(@Valid @RequestBody Equipement equipement) {
        return equipementService.create(equipement);
    }

    @Operation(
            summary = "Modifier un équipement",
            description = "Met à jour les informations d'un équipement existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipement modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    @PutMapping("/{id}")
    public Equipement update(
            @Parameter(description = "Identifiant de l'équipement à modifier", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody Equipement equipement) {
        return equipementService.update(id, equipement);
    }

    @Operation(
            summary = "Supprimer un équipement",
            description = "Supprime un équipement existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Équipement supprimé"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Identifiant de l'équipement à supprimer", example = "1")
            @PathVariable Long id) {
        equipementService.deleteById(id);
    }
}
