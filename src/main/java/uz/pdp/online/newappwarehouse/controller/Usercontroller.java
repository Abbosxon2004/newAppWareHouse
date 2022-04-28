package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.payload.UserDto;
import uz.pdp.online.newappwarehouse.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class Usercontroller {
    @Autowired
    UserService userService;

    @PostMapping
    public Result addUser(UserDto userDto) {
        final Result result = userService.addUser(userDto);
        return result;
    }

    @GetMapping
    public Result getUsers() {
        final Result resultUsers = userService.getUsers();
        return resultUsers;
    }

    @GetMapping("/userId/{id}")
    public Result getUserById(@PathVariable Integer id) {
        final Result resultUserById = userService.getUserById(id);
        return resultUserById;
    }

    @PutMapping("/userId/{id}")
    public Result editUser(@PathVariable Integer id, UserDto userDto) {
        final Result result = userService.editUser(id, userDto);
        return result;
    }

    @DeleteMapping("/userId/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        final Result result = userService.deleteUser(id);
        return result;
    }
}
