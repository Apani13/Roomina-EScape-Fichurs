package cat.itacademy.repository.dao;

import cat.itacademy.dto.availableInventory.AvailableRoomDTO;
import cat.itacademy.dto.completeInventory.EntityRoomDTO;
import cat.itacademy.dto.usedInventory.UsedRoomDTO;
import cat.itacademy.model.Clue;
import cat.itacademy.model.Item;
import cat.itacademy.model.Room;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.read.ExistsByName;
import cat.itacademy.repository.crud.read.GetById;
import cat.itacademy.repository.crud.read.GetLast;

import java.sql.SQLException;
import java.util.List;

public interface RoomDao extends Create<Room>, ExistsByName<Room>, GetLast<Room>, GetById<Room> {

    List<Room> getRoomsWithClues()throws SQLException;
    void removeClueFromRoom(int id, int clueId) throws SQLException;
    void removeItemFromRoom(int itemId, int intemId) throws SQLException;

    void increaseItemStock(int itemId, int quantity) throws SQLException;

    List<AvailableRoomDTO> getAvailableRooms() throws SQLException;
    List<EntityRoomDTO> getAllRoomsNameAndPrice() throws SQLException;
    double getAllPrices() throws SQLException;
    List<Clue> getCluesByRoomId(int roomId) throws SQLException;
    List<Item> getItemsByRoomId(int itemId) throws SQLException;

    List<UsedRoomDTO> getUsedRooms() throws SQLException;
}
