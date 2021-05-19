package model;

import databean.UserBets;
import org.genericdao.*;

import java.util.ArrayList;

public class UserBetsDAO extends GenericDAO<UserBets> {
    public UserBetsDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserBets.class, tableName, cp);
    }

    public UserBets[] getItems() throws RollbackException {
        UserBets[] items = match();
        return items;
    }

    public UserBets[] getItems(String email) throws RollbackException {
        UserBets[] items = match(MatchArg.equals("email",email));

        return items;
    }
}
