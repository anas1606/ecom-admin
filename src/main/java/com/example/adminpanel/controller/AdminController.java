package com.example.adminpanel.controller;

import com.example.adminpanel.auth.JwtTokenUtil;
import com.example.adminpanel.model.LoginModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.AdminUserRepository;
import com.example.adminpanel.service.JwtUserDetailService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Admin;
import com.example.commanentity.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("ping")
    public String ping() {
        return "Pinging Admin... Success";
    }

    @PostMapping("/login")
    public ResponseModel login(@RequestBody LoginModel loginModel) {
        ResponseModel responseModel;
        try {
            //Authenticate with emailID and Pswd
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmailId(), loginModel.getPassword()));
            //Load the userdetail of the user where emailID = "Example@gmail.com"
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginModel.getEmailId(), "ADMIN");

            Admin adminuser = adminUserRepository.findByEmailIDAndStatus(userDetails.getUsername(), Status.ACTIVE);
            final String token = jwtTokenUtil.generateToken(userDetails);

            //set the Bearer token to AdminUser data
            adminuser.setSessionToken(token);
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
