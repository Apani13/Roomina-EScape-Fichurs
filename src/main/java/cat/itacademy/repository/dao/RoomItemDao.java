package cat.itacademy.repository.dao;

import cat.itacademy.model.RoomItem;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.Delete;

import java.sql.SQLException;
import java.util.List;

public interface RoomItemDao extends Create<RoomItem> {

    void insertOrUpdate(RoomItem roomItem) throws SQLException;

    void deleteRoomItem(int roomId, int itemId) throws SQLException;
    Integer getQuantity(int roomId, int itemId) throws SQLException;
    List<RoomItem> getAllByRoomId(int roomId) throws SQLException;
}
