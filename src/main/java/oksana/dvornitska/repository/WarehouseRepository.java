package oksana.dvornitska.repository;

import oksana.dvornitska.database.DbUtil;
import oksana.dvornitska.model.WarehouseCollection;
import oksana.dvornitska.model.WarehouseDto;
import oksana.dvornitska.repository.interfaces.WarehouseRepositoryI;
import oksana.dvornitska.resources.beans.WarehouseFilter;

import java.sql.*;
import java.util.Optional;

public class WarehouseRepository implements WarehouseRepositoryI {
    public WarehouseRepository() {
        try {
            Class.forName(DbUtil.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS warehouse(" +
                    "id INT NOT NULL," +
                    "name VARCHAR(50) NOT NULL, " +
                    "address_line_1 VARCHAR(100) NOT NULL," +
                    "address_line_2 VARCHAR(100)," +
                    "city VARCHAR(50) NOT NULL," +
                    "state VARCHAR(50) NOT NULL," +
                    "country VARCHAR(50) NOT NULL, " +
                    "inventory_quantity INT NOT NULL DEFAULT 0, " +
                    "PRIMARY KEY(id)" +
                    ");");
            create.execute();
            create.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<WarehouseDto> findById(int id) {
        try {
            Connection conn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
            PreparedStatement select = conn.prepareStatement("select * from warehouse where id = ?;");
            select.setInt(1, id);
            ResultSet result = select.executeQuery();
            while (result.next()) {
                return Optional.of(mapWarehouse(result));
            }
            select.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean save(WarehouseDto warehouseDto) {
        Optional<WarehouseDto> warehouseDtoOptional = findById(warehouseDto.getId());
        if (warehouseDtoOptional.isPresent()) {
            try {
                Connection conn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
                PreparedStatement update = conn.prepareStatement("update warehouse set name = ?, " +
                        "address_line_1 = ?, address_line_2 = ?, city = ?, " +
                        "state = ?, country = ?, inventory_quantity = ?;");
                update.setString(1, warehouseDto.getName());
                update.setString(2, warehouseDto.getAddress_line_1());
                update.setString(3, warehouseDto.getAddress_line_2());
                update.setString(4, warehouseDto.getCity());
                update.setString(5, warehouseDto.getState());
                update.setString(6, warehouseDto.getCountry());
                update.setInt(7, warehouseDto.getInventory_quantity());
                update.executeUpdate();
                update.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        try {
            Connection conn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
            PreparedStatement insert = conn.prepareStatement("insert into warehouse(id, name, address_line_1, " +
                    "address_line_2, city, state, country, inventory_quantity)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setInt(1, warehouseDto.getId());
            insert.setString(2, warehouseDto.getName());
            insert.setString(3, warehouseDto.getAddress_line_1());
            insert.setString(4, warehouseDto.getAddress_line_2());
            insert.setString(5, warehouseDto.getCity());
            insert.setString(6, warehouseDto.getState());
            insert.setString(7, warehouseDto.getCountry());
            insert.setInt(8, warehouseDto.getInventory_quantity());
            insert.executeUpdate();
            insert.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection conn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
            PreparedStatement delete = conn.prepareStatement("delete from warehouse where id = ?;");
            delete.setInt(1, id);
            delete.execute();
            delete.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public WarehouseCollection getCollection(WarehouseFilter warehouseFilter) {
        WarehouseCollection warehouseCollection = new WarehouseCollection();
        try {
            Connection conn = DriverManager.getConnection(DbUtil.url, DbUtil.user, DbUtil.password);
            StringBuilder select = new StringBuilder("select * from warehouse");
            StringBuilder count = new StringBuilder("select count(*) from warehouse");
            StringBuilder where = new StringBuilder(" where");
            StringBuilder conditions = new StringBuilder();
            if (warehouseFilter.getName() != null) {
                appendAndCondition(where);
                where.append(" name = \'").append(warehouseFilter.getName()).append("\'");
            }
            if (warehouseFilter.getAddress_line_1() != null) {
                appendAndCondition(where);
                where.append(" address_line_1 = \'").append(warehouseFilter.getAddress_line_1()).append("\'");
            }
            if (warehouseFilter.getAddress_line_2() != null) {
                appendAndCondition(where);
                where.append(" address_line_2 = \'").append(warehouseFilter.getAddress_line_2()).append("\'");
            }
            if (warehouseFilter.getCity() != null) {
                appendAndCondition(where);
                where.append(" city = \'").append(warehouseFilter.getCity()).append("\'");
            }
            if (warehouseFilter.getState() != null) {
                appendAndCondition(where);
                where.append(" state = \'").append(warehouseFilter.getState()).append("\'");
            }
            if (warehouseFilter.getCountry() != null) {
                appendAndCondition(where);
                where.append(" country = \'").append(warehouseFilter.getCountry()).append("\'");
            }
            if (warehouseFilter.getInventory_quantity() != 0) {
                appendAndCondition(where);
                where.append(" inventory_quantity = \'").append(warehouseFilter.getInventory_quantity()).append("\'");
            }
            if (warehouseFilter.getSort() != null) {
                conditions.append(" order by ").append(warehouseFilter.getSort());
            }
            if (warehouseFilter.getLimit() != 0) {
                conditions.append(" limit ").append(warehouseFilter.getLimit());
            }
            if (warehouseFilter.getOffset() != 0) {
                conditions.append(" offset ").append(warehouseFilter.getOffset());
            }
            if (!" where".contentEquals(where)) {
                select.append(where);
                count.append(where);
            }
            if (!"".contentEquals(conditions)) {
                select.append(conditions);
            }
            PreparedStatement query = conn.prepareStatement(select.toString());
            ResultSet result = query.executeQuery();
            while (result.next()) {
                WarehouseDto warehouseDto = mapWarehouse(result);
                warehouseCollection.getList().add(warehouseDto);
            }
            query.close();

            PreparedStatement total = conn.prepareStatement(count.toString());
            result = total.executeQuery();
            int records = 0;
            while (result.next()) {
                records = result.getInt(1);
            }
            warehouseCollection.setTotal(records);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return warehouseCollection;
    }

    private void appendAndCondition(StringBuilder where) {
        if (!" where".contentEquals(where)) {
            where.append(" and");
        }
    }
    private WarehouseDto mapWarehouse(ResultSet result) throws SQLException {
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(result.getInt("id"));
        warehouseDto.setName(result.getString("name"));
        warehouseDto.setAddress_line_1(result.getString("address_line_1"));
        warehouseDto.setAddress_line_2(result.getString("address_line_2"));
        warehouseDto.setCity(result.getString("city"));
        warehouseDto.setState(result.getString("state"));
        warehouseDto.setCountry(result.getString("country"));
        warehouseDto.setInventory_quantity(result.getInt("inventory_quantity"));
        return warehouseDto;
    }
}
