package dev.sunghyun.prototypediffdata.controller;

import dev.sunghyun.prototypediffdata.resource.HtmlTextDiff;
import dev.sunghyun.prototypediffdata.resource.TextDiff;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class TextDiffController {
    @PostMapping(path = "/text", produces = MediaType.TEXT_HTML_VALUE)
    public String getTextDiff(@RequestBody TextDiff textDiff, Model model) {
        HtmlTextDiff result = textDiff.getHtmlTextDiff();

        model.addAttribute("diffText", result);

        return "textDiff";
    }
}
