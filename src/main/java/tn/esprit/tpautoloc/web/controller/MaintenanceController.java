
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
import tn.esprit.tpautoloc.domain.Maintenance;
import tn.esprit.tpautoloc.service.IMaintenanceService;

import java.util.List;

@Tag(
        name = "Maintenances",
        description = "Gestion des maintenances des véhicules"
)
@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @Operation(
            summary = "Lister les maintenances",
            description = "Retourne la liste de toutes les maintenances"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des maintenances retournée"
    )
    @GetMapping
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @Operation(
            summary = "Consulter une maintenance par ID",
            description = "Recherche une maintenance à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    @GetMapping("/{id}")
    public Maintenance findById(
            @Parameter(description = "Identifiant de la maintenance", example = "1")
            @PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @Operation(
            summary = "Créer une maintenance",
            description = "Enregistre une maintenance pour un véhicule"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Maintenance create(
            @Valid @RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @Operation(
            summary = "Modifier une maintenance",
            description = "Met à jour une maintenance existante"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    @PutMapping("/{id}")
    public Maintenance update(
            @Parameter(description = "Identifiant de la maintenance à modifier", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @Operation(
            summary = "Supprimer une maintenance",
            description = "Supprime une maintenance existante"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maintenance supprimée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Identifiant de la maintenance à supprimer", example = "1")
            @PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}
