package com.wcq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/topage")
public class PageController {

    @RequestMapping("/{page}")
    public String topage(@PathVariable String page){
        return page;
    }
}
