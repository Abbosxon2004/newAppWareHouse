package uz.pdp.online.newappwarehouse.payload;

import lombok.Data;

@Data
public class OutputDto {

    private String date;

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;

    private String factureNumber;

    private String code;
}
