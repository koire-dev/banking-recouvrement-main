package cm.recouvrement.banking.controller;

import cm.recouvrement.banking.entity.Client;
import cm.recouvrement.banking.entity.Folder;
import cm.recouvrement.banking.repository.ClientRepository;
import cm.recouvrement.banking.repository.FolderRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FolderRepository folderRepository;

    @PostMapping("/addClient")
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        Client saveClient = clientRepository.save(client);
        return new ResponseEntity<>(saveClient, HttpStatus.OK);
    }
    //La liste de tous les clients
    @GetMapping("/findAllClient")
    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }

    @GetMapping("/findClientById/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){

        Client client = clientRepository.findById(id).get();

        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    //Rechercher un client par son nom
    @GetMapping("/findClientByName")
    public ResponseEntity<Client> getClientByName(@PathParam("name") String name){

        Client client = clientRepository.findClientByName(name);
        if (client == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    //Liste des dossiers actifs d'un client
    @GetMapping("/findFolderFromClient/{id}")
    public List<Folder> getAllFolderFromClient(@PathVariable("id") Long id){
        Client client = clientRepository.findById(id).get();

        List<Folder> folderList = new ArrayList<>();
        for (Folder folder : folderRepository.findAll()){
            if ((client.getId() == folder.getClient().getId()) && (folder.isActif() == true)){
                folderList.add(folder);
            }
        }
        return folderList;
    }
}
