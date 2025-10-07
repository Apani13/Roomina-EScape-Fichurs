package cat.itacademy.repository.dao;

import cat.itacademy.dto.availableInventory.AvailableItemDTO;
import cat.itacademy.dto.completeInventory.EntityItemDTO;
import cat.itacademy.dto.usedInventory.UsedItemDTO;
import cat.itacademy.model.Item;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.read.ExistsByName;
import cat.itacademy.repository.crud.read.GetById;
import cat.itacademy.repository.crud.read.GetLast;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends Create<Item>, ExistsByName<Item>, GetById<Item>, GetLast<Item> {
    List<AvailableItemDTO> getAvailableItems() throws SQLException;
    int getTotalAvailableItemsCount() throws SQLException;
    List<EntityItemDTO> getAllItemsNameAndPrice() throws SQLException;
    double getAllPrices() throws SQLException;
    void assignItemToRoom(int roomId, int itemId, int quantity) throws SQLException;

    void updateStock(int itemId, int stock) throws SQLException;

    List<UsedItemDTO> getUsedItems() throws SQLException;

    int getTotalUsedItemsCount() throws SQLException;
}
