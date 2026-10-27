
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
import tn.esprit.tpautoloc.domain.client;
import tn.esprit.tpautoloc.service.IClientService;

import java.util.List;

@Tag(
        name = "Clients",
        description = "Gestion des clients"
)
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @Operation(
            summary = "Lister les clients",
            description = "Retourne la liste de tous les clients"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des clients retournée"
    )
    @GetMapping
    public List<client> findAll() {
        return clientService.findAll();
    }

    @Operation(
            summary = "Consulter un client par ID",
            description = "Recherche un client à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @GetMapping("/{id}")
    public client findById(
            @Parameter(description = "Identifiant du client", example = "1")
            @PathVariable Long id) {
        return clientService.findById(id);
    }

    @Operation(
            summary = "Créer un client",
            description = "Enregistre un nouveau client"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public client create(@Valid @RequestBody client client) {
        return clientService.create(client);
    }

    @Operation(
            summary = "Modifier un client",
            description = "Met à jour les informations d'un client existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @PutMapping("/{id}")
    public client update(
            @Parameter(description = "Identifiant du client à modifier", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody client client) {
        return clientService.update(id, client);
    }

    @Operation(
            summary = "Supprimer un client",
            description = "Supprime un client existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Identifiant du client à supprimer", example = "1")
            @PathVariable Long id) {
        clientService.deleteById(id);
    }
}
