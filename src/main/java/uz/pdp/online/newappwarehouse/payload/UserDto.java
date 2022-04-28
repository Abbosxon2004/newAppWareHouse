package uz.pdp.online.newappwarehouse.payload;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String code;

    private String password;

    private Set<Integer> warehouseIds;
}
