package com.example.adminpanel.controller;

import com.example.adminpanel.auth.JwtTokenUtil;
import com.example.adminpanel.model.LoginModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.AdminUserRepository;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private CommanUtil commanUtil;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseModel login(@RequestBody LoginModel loginModel) {
        ResponseModel responseModel;

        try {
            //Load the userdetail of the user where emailID = "Example@gmail.com"
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginModel.getEmailId(),
                            loginModel.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            Admin adminuser = adminUserRepository.findByEmailid(loginModel.getEmailId()).get(0);
            final String token = jwtTokenUtil.generateToken(authentication);

            //set the Bearer token to AdminUser data
            adminuser.setSession_token(token);
            //update the adminuser to database with token And ExpirationDate
            adminUserRepository.save(adminuser);

            responseModel = commanUtil.create(Message.LOGIN_SUCCESS,
                    adminuser,
                    HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            logger.info("AdminService Login BadCredentialsException ================== {}", ex.getMessage());
            responseModel = commanUtil.create(Message.LOGIN_ERROR,
                    null,
                    HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            logger.info("AdminService Login Exception ================== {}", e.getMessage());
            responseModel = commanUtil.create(Message.DELETE_STATUS_ERROR,
                    null,
                    HttpStatus.BAD_REQUEST);
        }
        return responseModel;
    }

}
