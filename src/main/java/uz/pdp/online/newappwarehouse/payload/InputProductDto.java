package uz.pdp.online.newappwarehouse.payload;

import lombok.Data;

@Data
public class InputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private String expireDate;

    private Integer inputId;
}
