package com.mario.projects.attendance.web.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by mario1oreo on 2017/6/23.
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Map<String,Object> model) {

        return "index";
    }
}
