
package tn.esprit.tpautoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.domain.Contrat;
import tn.esprit.tpautoloc.service.IContratService;

import java.util.List;

@Tag(
        name = "Contrats",
        description = "Gestion des contrats de location"
)
@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @Operation(
            summary = "Lister les contrats",
            description = "Retourne la liste de tous les contrats"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des contrats retournée"
    )
    @GetMapping
    public List<Contrat> findAll() {
        return contratService.findAll();
    }

    @Operation(
            summary = "Consulter un contrat par ID",
            description = "Recherche un contrat à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    @GetMapping("/{id}")
    public Contrat findById(
            @Parameter(
                    description = "Identifiant du contrat",
                    example = "1"
            )
            @PathVariable Long id) {
        return contratService.findById(id);
    }

    @Operation(
            summary = "Créer un contrat",
            description = "Enregistre un nouveau contrat de location"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrat créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant fourni")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contrat create(@RequestBody Contrat contrat) {
        return contratService.create(contrat);
    }

    @Operation(
            summary = "Modifier un contrat",
            description = "Met à jour un contrat existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    @PutMapping("/{id}")
    public Contrat update(
            @Parameter(
                    description = "Identifiant du contrat à modifier",
                    example = "1"
            )
            @PathVariable Long id,
            @RequestBody Contrat contrat) {
        return contratService.update(id, contrat);
    }

    @Operation(
            summary = "Supprimer un contrat",
            description = "Supprime un contrat existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrat supprimé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(
                    description = "Identifiant du contrat à supprimer",
                    example = "1"
            )
            @PathVariable Long id) {
        contratService.deleteById(id);
    }
}
