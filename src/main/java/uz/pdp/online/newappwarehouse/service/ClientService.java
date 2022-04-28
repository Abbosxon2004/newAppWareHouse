package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Client;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client) {
        boolean existsByName = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByName)
            return new Result("Bunday telefon raqami mavjud", false);
        clientRepository.save(client);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    public Result getClients() {
        final List<Client> clientList = clientRepository.findAll();
        return new Result("Client list found.", true, clientList);
    }

    public Result getClientById(Integer id) {
        final Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("Client id not found", false);
        return new Result("Client id found.", true, optionalClient.get());
    }

    public Result editClient(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (client.getName() != null) {
            if (!optionalClient.isPresent())
                return new Result("Client id not found", false);
            Client editClient = optionalClient.get();
            if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
                return new Result("New phone number already exists.", false);
            editClient.setName(client.getName());
            editClient.setPhoneNumber(client.getPhoneNumber());
            clientRepository.save(editClient);
            return new Result("Client edited.", true);
        }
        return new Result("Client didn't edited", false);
    }

    public Result deleteClient(Integer id) {
        clientRepository.deleteById(id);
        return new Result("Client deleted.", true);
    }
}
