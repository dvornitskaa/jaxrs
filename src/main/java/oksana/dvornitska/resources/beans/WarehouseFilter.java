package oksana.dvornitska.resources.beans;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.ws.rs.QueryParam;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseFilter {

    @QueryParam("name")
    String name;
    @QueryParam("address_line_1")
    String address_line_1;
    @QueryParam("address_line_2")
    String address_line_2;
    @QueryParam("city")
    String city;
    @QueryParam("state")
    String state;
    @QueryParam("country")
    String country;
    @QueryParam("inventory_quantity")
    int inventory_quantity;
    @QueryParam("limit")
    int limit;
    @QueryParam("offset")
    int offset;
    @QueryParam("sort")
    String sort;

}
