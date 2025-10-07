package cat.itacademy.repository.dao;

import cat.itacademy.dto.availableInventory.AvailableEscapeRoomDTO;
import cat.itacademy.dto.completeInventory.CompleteInventoryDTO;
import cat.itacademy.dto.completeInventory.EntityEscapeRoomDTO;
import cat.itacademy.dto.usedInventory.UsedEscapeRoomDTO;
import cat.itacademy.model.EscapeRoom;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.Delete;
import cat.itacademy.repository.crud.read.ExistsByName;
import cat.itacademy.repository.crud.read.GetById;
import cat.itacademy.repository.crud.read.GetLast;

import java.sql.SQLException;
import java.util.List;

public interface EscapeRoomDao extends Create<EscapeRoom>, Delete<EscapeRoom>, ExistsByName<EscapeRoom>, GetLast<EscapeRoom>, GetById<EscapeRoom> {
    void addRoomToEscapeRoom(int escapeRoomId, int roomId) throws SQLException;

    List<UsedEscapeRoomDTO> getUsedEscapeRooms() throws SQLException;

    List<AvailableEscapeRoomDTO> findAll() throws SQLException;
    List<EntityEscapeRoomDTO> findAllComplete() throws SQLException;
    List<EscapeRoom> getRoomsByEscapeRoomId(int escapeRoomID) throws SQLException;
    void updateEscapeRoomIdRoom(int escapeRoomId, int roomId) throws SQLException;
    void removeRoomFromEscapeRoom(int escapeRoomId, int roomId) throws SQLException;
}
