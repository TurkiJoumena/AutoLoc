
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
import tn.esprit.tpautoloc.domain.Reservation;
import tn.esprit.tpautoloc.service.IReservationService;

import java.util.List;

@Tag(
        name = "Réservations",
        description = "Gestion des réservations de véhicules"
)
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @Operation(
            summary = "Lister les réservations",
            description = "Retourne la liste de toutes les réservations"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des réservations retournée"
    )
    @GetMapping
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @Operation(
            summary = "Consulter une réservation par ID",
            description = "Recherche une réservation à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Réservation trouvée"),
            @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    })
    @GetMapping("/{id}")
    public Reservation findById(
            @Parameter(
                    description = "Identifiant de la réservation",
                    example = "1"
            )
            @PathVariable Long id) {
        return reservationService.findById(id);
    }

    @Operation(
            summary = "Créer une réservation",
            description = "Enregistre une réservation pour un client et un véhicule"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Réservation créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou dates incohérentes")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(
            @Valid @RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @Operation(
            summary = "Modifier une réservation",
            description = "Met à jour une réservation existante"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Réservation modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou dates incohérentes"),
            @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    })
    @PutMapping("/{id}")
    public Reservation update(
            @Parameter(
                    description = "Identifiant de la réservation à modifier",
                    example = "1"
            )
            @PathVariable Long id,
            @Valid @RequestBody Reservation reservation) {
        return reservationService.update(id, reservation);
    }

    @Operation(
            summary = "Supprimer une réservation",
            description = "Supprime une réservation existante"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Réservation supprimée"),
            @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(
                    description = "Identifiant de la réservation à supprimer",
                    example = "1"
            )
            @PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
