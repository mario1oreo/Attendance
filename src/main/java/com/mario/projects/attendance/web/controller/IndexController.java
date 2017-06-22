package com.mario.projects.attendance.web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by mario1oreo on 2017/6/23.
 */
@ComponentScan
@Configuration
@RestController
@RequestMapping("/")
public class IndexController {

    public String index(Map<String,Object> model) {

        return "index";
    }
}
