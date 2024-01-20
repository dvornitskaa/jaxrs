package oksana.dvornitska.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.model.WarehouseCollection;
import oksana.dvornitska.model.WarehouseDto;
import oksana.dvornitska.repository.WarehouseRepository;
import oksana.dvornitska.repository.interfaces.WarehouseRepositoryI;
import oksana.dvornitska.resources.beans.WarehouseFilter;
import oksana.dvornitska.service.interfaces.WarehouseServiceI;

import java.util.NoSuchElementException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WarehouseService implements WarehouseServiceI {

    WarehouseRepositoryI warehouseRepository = new WarehouseRepository();

    @Override
    public WarehouseCollection getCollection(WarehouseFilter warehouseFilter) {
        return warehouseRepository.getCollection(warehouseFilter);
    }

    @Override
    public WarehouseDto getById(int id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Warehouse with id: %s not found", id)));
    }

    @Override
    public boolean create(WarehouseDto warehouseDto) {
        return warehouseRepository.save(warehouseDto);
    }

    @Override
    public boolean update(WarehouseDto warehouseDto) {
        return warehouseRepository.save(warehouseDto);
    }

    @Override
    public boolean delete(int id) {
        return warehouseRepository.delete(id);
    }
}
