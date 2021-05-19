package model;

import databean.UserInfo;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

public class UserInfoDAO extends GenericDAO<UserInfo> {
    public UserInfoDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserInfo.class, tableName, cp);
    }
}
