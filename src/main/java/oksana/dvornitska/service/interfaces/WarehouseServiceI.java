package oksana.dvornitska.service.interfaces;

import oksana.dvornitska.model.WarehouseCollection;
import oksana.dvornitska.model.WarehouseDto;
import oksana.dvornitska.resources.beans.WarehouseFilter;

public interface WarehouseServiceI {

    WarehouseCollection getCollection(WarehouseFilter warehouseFilter);

    WarehouseDto getById(int id);

    boolean create(WarehouseDto warehouseDto);

    boolean update(WarehouseDto warehouseDto);

    boolean delete(int id);

}
