package com.apitest.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

//    @RequestMapping("/error")
//    public String handleError() {
//    	log.info("Erreur ");
//        return "error";
//    }

}