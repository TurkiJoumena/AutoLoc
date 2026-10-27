
package tn.esprit.tpautoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.tpautoloc.domain.Paiement;
import tn.esprit.tpautoloc.service.IPaiementService;

import java.util.List;

@Tag(
        name = "Paiements",
        description = "Consultation des paiements associés aux contrats"
)
@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    @Operation(
            summary = "Lister les paiements",
            description = "Retourne la liste de tous les paiements"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des paiements retournée"
    )
    @GetMapping
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @Operation(
            summary = "Consulter un paiement par ID",
            description = "Recherche un paiement à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement trouvé"),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    })
    @GetMapping("/{id}")
    public Paiement findById(
            @Parameter(
                    description = "Identifiant du paiement",
                    example = "1"
            )
            @PathVariable Long id) {
        return paiementService.findById(id);
    }
}
