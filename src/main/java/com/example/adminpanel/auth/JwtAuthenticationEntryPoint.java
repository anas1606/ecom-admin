package com.example.adminpanel.auth;

import com.example.adminpanel.model.ResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse hsr1,
                         AuthenticationException authException) throws IOException {
        ResponseModel rs = new ResponseModel();
        rs.setData(null);
        rs.setMessage("Unauthorized Request Please Log in again.");
        rs.setStatus(HttpStatus.UNAUTHORIZED);
        rs.setStatusCode(401);
        hsr1.setContentType("application/json");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(rs);

        hsr1.getWriter().write(json);
        hsr1.setStatus(200);
    }
}
