package model;

import databean.FootballMatch;
import databean.UserBets;
import org.genericdao.*;

import java.util.ArrayList;
import java.util.Arrays;

public class FootballMatchDAO extends GenericDAO<FootballMatch> {
    public FootballMatchDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(FootballMatch.class, tableName, cp);
    }

    public FootballMatch[] getItems() throws RollbackException {
        FootballMatch[] items = match();
        return items;
    }


    public FootballMatch[] getItems(String status) throws RollbackException {
        FootballMatch[] items = match(MatchArg.equals("status",status));
        return items;
    }


    public FootballMatch[] getItems(String cup, String status) throws RollbackException {
        FootballMatch[] items = match(MatchArg.equals("cupName",cup));
        ArrayList<FootballMatch> arrayList = new ArrayList();
        for (int i = 0; i < items.length; i++) {
            if (items[i].getStatus().equals(status)){
                System.out.println(items[i]);
                arrayList.add(items[i]);
            }
        }
        FootballMatch[] itemss = new FootballMatch[arrayList.size()];
        for (int i = 0; i < itemss.length; i++) {
            itemss[i] = arrayList.get(i);
        }
        return itemss;
    }

}
