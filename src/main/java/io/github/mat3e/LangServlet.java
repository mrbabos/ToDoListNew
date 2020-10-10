package io.github.mat3e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private  static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private HelloService service;

    @SuppressWarnings("unused")
    public  HelloServlet(){
        this(new HelloService());
    }

    public HelloServlet(HelloService service){
        this.service = service;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        logger.info("got request with parameter " + req.getParameterMap());
        var name = req.getParameter(NAME_PARAM);
        var param = req.getParameter(LANG_PARAM);
        resp.getWriter().write(service.prepareGreeting(name,param));

    }
}
