package com.twoolab.app.monitoring;

import com.twoolab.app.miners.AntMiner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author yeesheng on 23/02/2018
 * @project antmonitorapp
 */

@WebServlet(
        name = "MonitoringServlet",
        urlPatterns = {"/monitoring"})
public class MonitoringServlet extends HttpServlet {
    public static final Optional<String> refreshRate = Optional.ofNullable(System.getenv("ANT_REFRESH"));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action != null){
            // custom action e.g: add miner, delete miner, search miner details
        } else {
            forwardListMiners(req, resp, MonitoringService.getMinersInfo(MonitoringService.minerList));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new ServletException("Not yet implemented\n");
    }

    private void forwardListMiners(HttpServletRequest req, HttpServletResponse resp, List minerList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-miners.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("minerList", minerList);
        resp.setIntHeader("Refresh", Integer.valueOf(refreshRate.orElse("10")));
        dispatcher.forward(req, resp);
    }
}
