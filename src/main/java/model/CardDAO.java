package model;

import databean.Card;
import databean.UserBets;
import org.genericdao.*;

public class CardDAO extends GenericDAO<Card> {


    public CardDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(Card.class, tableName, cp);
    }



    public Card[] getItems() throws RollbackException {
        Card[] items = match();

        return items;
    }
}
