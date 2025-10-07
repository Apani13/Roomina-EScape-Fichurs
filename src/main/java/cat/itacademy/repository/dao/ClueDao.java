package cat.itacademy.repository.dao;

import cat.itacademy.dto.availableInventory.AvailableClueDTO;
import cat.itacademy.dto.completeInventory.EntityClueDTO;
import cat.itacademy.dto.usedInventory.UsedClueDTO;
import cat.itacademy.model.Clue;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.read.ExistsByName;
import cat.itacademy.repository.crud.read.GetById;
import cat.itacademy.repository.crud.read.GetLast;

import java.sql.SQLException;
import java.util.List;

public interface ClueDao extends Create<Clue>, ExistsByName<Clue>, GetLast<Clue>, GetById<Clue> {
    List<Clue> getAllNames() throws SQLException;
    List<AvailableClueDTO> getAvailableClues() throws SQLException;
    List<EntityClueDTO> getAllCluesNameAndPrice() throws SQLException;
    double getAllPrices() throws SQLException;
    void updateRoomIdClue(int roomId, int clueId) throws SQLException;

    List<UsedClueDTO> getUsedClues() throws SQLException;
}
