package dev.sunghyun.prototypediffdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAppController {
    private final String appMode;

    @Autowired
    public WebAppController(Environment environment) {
        this.appMode = environment.getProperty("app-mode");
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mode", this.appMode);

        return "index";
    }
}
