package uz.pdp.online.newappwarehouse.payload;

import lombok.Data;

@Data
public class OutputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private Integer outputId;
}
