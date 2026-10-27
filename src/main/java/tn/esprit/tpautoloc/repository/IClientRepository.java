package tn.esprit.tpautoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpautoloc.domain.client;

public interface IClientRepository extends JpaRepository<client, Long> {
}
