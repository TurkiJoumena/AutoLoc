
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
import tn.esprit.tpautoloc.domain.Vehicule;
import tn.esprit.tpautoloc.service.IVehiculeService;

import java.util.List;

@Tag(
        name = "Véhicules",
        description = "Gestion des véhicules de location"
)
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Operation(
            summary = "Lister les véhicules",
            description = "Retourne la liste de tous les véhicules"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des véhicules retournée"
    )
    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @Operation(
            summary = "Consulter un véhicule par ID",
            description = "Recherche un véhicule à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Véhicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @GetMapping("/{id}")
    public Vehicule findById(
            @Parameter(
                    description = "Identifiant du véhicule",
                    example = "1"
            )
            @PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @Operation(
            summary = "Créer un véhicule",
            description = "Enregistre un nouveau véhicule"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Véhicule créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@Valid @RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @Operation(
            summary = "Modifier un véhicule",
            description = "Met à jour les informations d'un véhicule existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Véhicule modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @PutMapping("/{id}")
    public Vehicule update(
            @Parameter(
                    description = "Identifiant du véhicule à modifier",
                    example = "1"
            )
            @PathVariable Long id,
            @Valid @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @Operation(
            summary = "Supprimer un véhicule",
            description = "Supprime un véhicule existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Véhicule supprimé"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(
                    description = "Identifiant du véhicule à supprimer",
                    example = "1"
            )
            @PathVariable Long id) {
        vehiculeService.deleteById(id);
    }
}
