package uz.pdp.online.newappwarehouse.payload;

import lombok.Data;

@Data
public class InputDto {

    private String date;

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private String factureNumber;

    private String code;
}
