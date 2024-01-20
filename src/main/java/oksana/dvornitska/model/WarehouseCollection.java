package oksana.dvornitska.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseCollection {

    List<WarehouseDto> list = new ArrayList<>();

    int total;

}
