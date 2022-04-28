package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.User;
import uz.pdp.online.newappwarehouse.entity.Warehouse;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.payload.UserDto;
import uz.pdp.online.newappwarehouse.repository.UserRepository;
import uz.pdp.online.newappwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public String generateCode() {
        final List<User> userList = userRepository.findAll();
        int size = userList.size();
        String code = String.valueOf(size + 1);
        return code;
    }

    public Result addUser(UserDto userDto) {
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("This phone number already exists", false);

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setCode(generateCode());
        final List<Warehouse> allById = warehouseRepository.findAllById(userDto.getWarehouseIds());
        user.setWarehouses((Set<Warehouse>) allById);
        userRepository.save(user);
        return new Result("User added", true);
    }

    public Result getUsers() {
        final List<User> userList = userRepository.findAll();
        return new Result("Userlist found", true, userList);
    }

    public Result getUserById(Integer id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("User id not found", false);
        return new Result("User found", true, optionalUser.get());
    }

    public Result editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("User id not found", false);
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result("This phone number already exists", false);

        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        final List<Warehouse> allById = warehouseRepository.findAllById(userDto.getWarehouseIds());
        user.setWarehouses((Set<Warehouse>) allById);
        userRepository.save(user);
        return new Result("User edited", true);
    }

    public Result deleteUser(Integer id){
        userRepository.deleteById(id);
        return new Result("User deleted.",true);
    }
}
