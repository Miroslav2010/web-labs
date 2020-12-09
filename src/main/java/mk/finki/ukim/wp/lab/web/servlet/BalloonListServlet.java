package mk.finki.ukim.wp.lab.web.servlet;

import mk.finki.ukim.wp.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BalloonListServlet", urlPatterns = "/servlet/search")
public class BalloonListServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonListServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        String text=(String)req.getParameter("text");
        if(text!= null && !text.isEmpty()){
           try{
               if(text.length()==1){
                   context.setVariable("listBalloons", this.balloonService.searchByType(text));
               }else {
                   context.setVariable("listBalloons", this.balloonService.searchByNameOrDescription(text));
               }
           }
           catch(Exception e){
                 String message = e.getMessage();
           }

        }
        else
        {
            context.setVariable("listBalloons", this.balloonService.listAll());
        }
        this.springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String balloonColor=(String)req.getParameter("color");
        req.getSession().setAttribute("chosenColor",balloonColor);
        resp.sendRedirect("/selectBalloon");


    }
}