
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
import tn.esprit.tpautoloc.domain.Agence;
import tn.esprit.tpautoloc.service.IAgenceService;

import java.util.List;

@Tag(
        name = "Agences",
        description = "Gestion des agences de location"
)
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(
            summary = "Lister les agences",
            description = "Retourne la liste de toutes les agences"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des agences retournée"
    )
    @GetMapping
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @Operation(
            summary = "Consulter une agence par ID",
            description = "Recherche une agence à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @GetMapping("/{id}")
    public Agence findById(
            @Parameter(description = "Identifiant de l'agence", example = "1")
            @PathVariable Long id) {
        return agenceService.findById(id);
    }

    @Operation(
            summary = "Créer une agence",
            description = "Enregistre une nouvelle agence"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agence créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence create(@Valid @RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @Operation(
            summary = "Modifier une agence",
            description = "Met à jour les informations d'une agence existante"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @PutMapping("/{id}")
    public Agence update(
            @Parameter(description = "Identifiant de l'agence à modifier", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @Operation(
            summary = "Supprimer une agence",
            description = "Supprime une agence existante"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Identifiant de l'agence à supprimer", example = "1")
            @PathVariable Long id) {
        agenceService.deleteById(id);
    }
}
