package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;


public class Model {
    private CardDAO cardDAO;
    private FootballMatchDAO footballMatchDAO;
    private UserBetsDAO userBetsDAO;
    private UserCredentialDAO userCredentialDAO;
    private UserInfoDAO userInfoDAO;
    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL    = config.getInitParameter("jdbcURL");

            ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);

            cardDAO = new CardDAO(pool,"card");
            userBetsDAO = new UserBetsDAO(pool,"user_bets");
            userCredentialDAO = new UserCredentialDAO(pool,"user_credential");
            footballMatchDAO = new FootballMatchDAO(pool,"football_matches");
            userInfoDAO = new UserInfoDAO(pool, "user_info");

        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }



    public CardDAO getCardDAO() {
        return cardDAO;
    }

    public FootballMatchDAO getFootballMatchDAO() {
        return footballMatchDAO;
    }

    public UserBetsDAO getUserBetsDAO() {
        return userBetsDAO;
    }

    public UserCredentialDAO getUserCredentialDAO() {
        return userCredentialDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }
}
