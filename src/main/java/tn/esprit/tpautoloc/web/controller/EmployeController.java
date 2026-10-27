
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
import tn.esprit.tpautoloc.domain.Employe;
import tn.esprit.tpautoloc.service.IEmployeService;

import java.util.List;

@Tag(
        name = "Employés",
        description = "Gestion des employés des agences"
)
@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @Operation(
            summary = "Lister les employés",
            description = "Retourne la liste de tous les employés"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des employés retournée"
    )
    @GetMapping
    public List<Employe> findAll() {
        return employeService.findAll();
    }

    @Operation(
            summary = "Consulter un employé par ID",
            description = "Recherche un employé à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employé trouvé"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    @GetMapping("/{id}")
    public Employe findById(
            @Parameter(description = "Identifiant de l'employé", example = "1")
            @PathVariable Long id) {
        return employeService.findById(id);
    }

    @Operation(
            summary = "Créer un employé",
            description = "Enregistre un nouvel employé associé à une agence"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employé créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employe create(@Valid @RequestBody Employe employe) {
        return employeService.create(employe);
    }

    @Operation(
            summary = "Modifier un employé",
            description = "Met à jour les informations d'un employé existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employé modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    @PutMapping("/{id}")
    public Employe update(
            @Parameter(description = "Identifiant de l'employé à modifier", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody Employe employe) {
        return employeService.update(id, employe);
    }

    @Operation(
            summary = "Supprimer un employé",
            description = "Supprime un employé existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employé supprimé"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "Identifiant de l'employé à supprimer", example = "1")
            @PathVariable Long id) {
        employeService.deleteById(id);
    }
}
