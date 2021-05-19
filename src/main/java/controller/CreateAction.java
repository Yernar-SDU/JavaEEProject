package controller;

import databean.FootballMatch;
import formbean.NewMatchForm;
import model.FootballMatchDAO;
import model.Model;
import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateAction extends Action{
    private FormBeanFactory<NewMatchForm> formBean = new FormBeanFactory<>(NewMatchForm.class);
    private FootballMatchDAO footballMatchDAO;

    CreateAction(Model model){
        this.footballMatchDAO = model.getFootballMatchDAO();
    }

    @Override
    public String getName() {
        return "create.do";
    }

    @Override
    public String performGet(HttpServletRequest request) {
        request.setAttribute("form", new NewMatchForm());
        return "admin.jsp";
    }

    @Override
    public String performPost(HttpServletRequest request) {
        NewMatchForm form = formBean.create(request);


        try {
            request.setAttribute("form", form);

            if(form.hasValidationErrors()){
                return "admin.jsp";
            }


            FootballMatch match = new FootballMatch();
            match.setFootballClubName1(form.getFootballClubName1());
            match.setFootballClubName2(form.getFootballClubName2());
            match.setCoef1(Double.parseDouble(form.getCoef1()));
            match.setCoef2(Double.parseDouble(form.getCoef2()));
            match.setCoef3(Double.parseDouble(form.getCoef3()));
            match.setCupName(form.getCupName());
            match.setStatus(form.getStatus());
            match.setScore1(0);
            match.setScore2(0);

            footballMatchDAO.create(match);

            request.setAttribute("form", new NewMatchForm());
            return "create.do";
        }catch (RollbackException e) {
            e.printStackTrace();
        }

        request.setAttribute("form", new NewMatchForm());
        return "create.do";
    }
}
