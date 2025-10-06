package cat.itacademy.repository.dao;

import cat.itacademy.model.EscapeRoom;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.Delete;
import cat.itacademy.repository.crud.read.ExistsByName;
import cat.itacademy.repository.crud.read.GetById;
import cat.itacademy.repository.crud.read.GetLast;

import java.sql.SQLException;
import java.util.List;

public interface EscapeRoomDao extends Create<EscapeRoom>, Delete<EscapeRoom>, ExistsByName<EscapeRoom>, GetLast<EscapeRoom>, GetById<EscapeRoom> {
    List<EscapeRoom> findAll() throws SQLException;
    List<EscapeRoom> getRoomsByEscapeRoomId(int escapeRoomID) throws SQLException;
    void updateEscapeRoomIdRoom(int escapeRoomId, int roomId) throws SQLException;
    void removeRoomFromEscapeRoom(int roomId) throws SQLException;

}
