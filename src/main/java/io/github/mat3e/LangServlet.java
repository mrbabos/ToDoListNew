package io.github.mat3e;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang", urlPatterns = {"/api/lang"})
public class LangServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(LangServlet.class);


    private LangRepository repository;
    private ObjectMapper mapper;

    @SuppressWarnings("unused")
    public LangServlet(){
        this(new LangRepository(), new ObjectMapper());
    }

    public LangServlet(LangRepository repository, ObjectMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        logger.info("got request with parameter " + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(),repository.findAll());


    }
}
