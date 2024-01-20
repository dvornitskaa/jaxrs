package oksana.dvornitska.repository.interfaces;

import oksana.dvornitska.model.WarehouseCollection;
import oksana.dvornitska.model.WarehouseDto;
import oksana.dvornitska.resources.beans.WarehouseFilter;

import java.util.Optional;

public interface WarehouseRepositoryI {

    Optional<WarehouseDto> findById(int id);

    boolean save(WarehouseDto warehouseDto);

    boolean delete(int id);

    WarehouseCollection getCollection(WarehouseFilter warehouseFilter);
}
