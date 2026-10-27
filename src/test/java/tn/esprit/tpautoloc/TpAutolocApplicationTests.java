package tn.esprit.tpautoloc;

import com.mysql.cj.xdevapi.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpautoloc.domain.*;
import tn.esprit.tpautoloc.exception.ResourceNotFoundException;
import tn.esprit.tpautoloc.repository.IAgenceRepository;
import tn.esprit.tpautoloc.service.IAgenceService;
import tn.esprit.tpautoloc.service.IClientService;
import tn.esprit.tpautoloc.service.IVehiculeService;


import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TpAutolocApplicationTests {
    @Autowired
    private IAgenceRepository agencerepo;

    @Test
    void testagencerepository() {
        System.out.println("***********Agence*****************************");
        //creation d'une instance agence
        Agence agence = new Agence();
        agence.setNom("agence1");
        agence.setAdresse("tunis");
        Agence agencesaved = agencerepo.save(agence);
        System.out.println("agence cree : " + agencesaved);

        //findall
        List<Agence> agences = agencerepo.findAll();
        System.out.println("listes des agences : " + agences);
        System.out.println("nbe des agences : " + agences.size());
        assertNotNull(agences);
        assertFalse(agences.isEmpty(), "la basedoit contenir au moins une agence ");
    }

    @Autowired
    private IAgenceService agenceservice;

    @Test
    void testAgenceService() {

        System.out.println("*********** Agence ***********");

        // 1. Creer une agence
        Agence agence = new Agence();
        agence.setNom("agence1");
        agence.setAdresse("tunis");

        // 2. Enregistrer l'agence
        Agence agencesaved = agenceservice.create(agence);
        System.out.println("Agence creee : " + agencesaved);

        // 3. Afficher toutes les agences
        List<Agence> agences = agenceservice.findAll();
        System.out.println("Liste des agences : " + agences);

        // 4. Rechercher l'agence par ID
        Agence agenceTrouvee =
                agenceservice.findById(agencesaved.getIdAgence());
        System.out.println("Agence trouvee : " + agenceTrouvee);

        // 5. Afficher le nombre d'agences
        System.out.println("Nombre d'agences : " + agences.size());

        // 6. Supprimer l'agence
        agenceservice.deleteById(agencesaved.getIdAgence());

        // 7. Verifier la suppression
        assertThrows(
                ResourceNotFoundException.class,
                () -> agenceservice.findById(agencesaved.getIdAgence())
        );

        System.out.println("Agence supprimee avec succes !");
    }


    @Autowired
    private IClientService clientservice;

    @Test
    void testClientService() {

        System.out.println("*********** Client ***********");

        // 1. Creer deux clients
        client client1 = new client();
        client1.setNom("client1");

        client client2 = new client();
        client2.setNom("client2");

        // 2. Enregistrer les clients
        client clientSaved1 = clientservice.create(client1);
        client clientSaved2 = clientservice.create(client2);

        System.out.println("Client cree : " + clientSaved1);
        System.out.println("Client cree : " + clientSaved2);

        // 3. Afficher tous les clients
        List<client> clients = clientservice.findAll();
        System.out.println("Liste des clients : " + clients);

        // 4. Rechercher un client par ID
        client clientTrouve =
                clientservice.findById(clientSaved1.getIdClient());
        System.out.println("Client trouve : " + clientTrouve);

        // 5. Afficher le nombre de clients
        System.out.println("Nombre de clients : " + clients.size());

        // 6. Supprimer un client
        clientservice.deleteById(clientSaved1.getIdClient());

        // 7. Verifier que le client n'existe plus
        assertThrows(
                ResourceNotFoundException.class,
                () -> clientservice.findById(clientSaved1.getIdClient())
        );

        System.out.println("Client supprime avec succes !");
    }


    @Autowired
    private IVehiculeService vehiculeservice;

    @Test
    void testVehiculeService() {

        System.out.println("*********** Vehicule ***********");

        // 1. Creer un vehicule
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("123 TU 456");
        vehicule.setMarque("Toyota");
        vehicule.setModele("Yaris");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("80.00"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        // 2. Enregistrer le vehicule
        Vehicule vehiculeSaved = vehiculeservice.create(vehicule);
        System.out.println("Vehicule cree : " + vehiculeSaved);

        // 3. Afficher tous les vehicules
        List<Vehicule> vehicules = vehiculeservice.findAll();
        System.out.println("Liste des vehicules : " + vehicules);

        // 4. Rechercher un vehicule par ID
        Vehicule vehiculeTrouve =
                vehiculeservice.findById(vehiculeSaved.getIdVehicule());
        System.out.println("Vehicule trouve : " + vehiculeTrouve);

        // 5. Afficher le nombre de vehicules
        System.out.println("Nombre de vehicules : " + vehicules.size());

        // 6. Supprimer le vehicule
        vehiculeservice.deleteById(vehiculeSaved.getIdVehicule());

        // 7. Verifier que le vehicule n'existe plus
        assertThrows(
                ResourceNotFoundException.class,
                () -> vehiculeservice.findById(vehiculeSaved.getIdVehicule())
        );

        System.out.println("Vehicule supprime avec succes !");
    }
}