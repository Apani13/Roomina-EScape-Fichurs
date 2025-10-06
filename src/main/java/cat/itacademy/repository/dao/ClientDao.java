package cat.itacademy.repository.dao;

import cat.itacademy.model.Client;
import cat.itacademy.repository.crud.Create;
import cat.itacademy.repository.crud.Delete;
import cat.itacademy.repository.crud.read.ExistsByName;
import cat.itacademy.repository.crud.read.GetById;
import cat.itacademy.repository.crud.read.GetLast;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends Create<Client>, Delete<Client>, ExistsByName<Client>, GetLast<Client>, GetById<Client> {
    List<Client> findAll() throws SQLException;
}
