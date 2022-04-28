package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.entity.Client;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping
    public Result addClient(Client client) {
        final Result result = clientService.addClient(client);
        return result;
    }

    @GetMapping
    public Result getClients() {
        final Result resultClients = clientService.getClients();
        return resultClients;
    }

    @GetMapping("/clientId/{id}")
    public Result getClientById(@PathVariable Integer id) {
        final Result resultClientById = clientService.getClientById(id);
        return resultClientById;
    }

    @PutMapping("/clientId/{id}")
    public Result editClient(@PathVariable Integer id, Client client) {
        final Result result = clientService.editClient(id, client);
        return result;
    }

    @DeleteMapping("/clientId/{id}")
    public Result deleteClient(@PathVariable Integer id) {
        final Result result = clientService.deleteClient(id);
        return result;
    }
}
