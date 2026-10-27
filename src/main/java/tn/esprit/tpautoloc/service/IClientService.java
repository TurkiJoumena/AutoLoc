package tn.esprit.tpautoloc.service;



import java.util.List;
import tn.esprit.tpautoloc.domain.client;

public interface IClientService {
    client create(client client);
    client findById(Long id);
    List<client> findAll();
    client update(Long id, client client);
    void deleteById(Long id);
}

