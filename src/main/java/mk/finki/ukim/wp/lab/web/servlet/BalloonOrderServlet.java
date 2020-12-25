package mk.finki.ukim.wp.lab.web.servlet;
import mk.finki.ukim.wp.lab.models.User;
import mk.finki.ukim.wp.lab.service.BalloonService;
import mk.finki.ukim.wp.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BalloonOrderServlet", urlPatterns = "/servlet/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {
    private final BalloonService balloonService;
    private final OrderService orderService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine,OrderService orderService) {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());


        this.springTemplateEngine.process("deliveryInfo.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String color=(String) req.getSession().getAttribute("chosenColor");
        String size=(String) req.getSession().getAttribute("chosenSize");
        orderService.placeOrder(color,size,req.getRemoteUser());
        resp.sendRedirect("/servlet/ConfirmationInfo");
    }
}
