package model;

import databean.UserCredential;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

public class UserCredentialDAO extends GenericDAO<UserCredential> {
    public UserCredentialDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserCredential.class, tableName, cp);
    }
}
