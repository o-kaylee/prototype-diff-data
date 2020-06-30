package dev.sunghyun.prototypediffdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAppController {
    @GetMapping("/")
    public String index(Model model) {
        String defaultOldText = "I am the very model of a modern Major-General, I've information vegetable, animal, and mineral, I know the kings of England, and I quote the fights historical, From Marathon to Waterloo, in order categorical.";
        String defaultNewText = "I am the very model of a cartoon individual, My animation's comical, unusual, and whimsical, I'm quite adept at funny gags, comedic theory I have read, From wicked puns and stupid jokes to anvils that drop on your head.";

        model.addAttribute("defaultOldText", defaultOldText);
        model.addAttribute("defaultNewText", defaultNewText);

        return "index";
    }
}