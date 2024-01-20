package oksana.dvornitska.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseDto {

    Integer id;

    String name;

    String address_line_1;

    String address_line_2;

    String city;

    String state;

    String country;

    Integer inventory_quantity;

}
