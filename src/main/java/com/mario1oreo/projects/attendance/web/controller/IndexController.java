package com.mario1oreo.projects.attendance.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
