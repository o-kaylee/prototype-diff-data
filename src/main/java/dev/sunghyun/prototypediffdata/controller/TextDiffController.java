package dev.sunghyun.prototypediffdata.controller;

import dev.sunghyun.prototypediffdata.resource.HtmlTextDiff;
import dev.sunghyun.prototypediffdata.resource.TextDiff;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TextDiffController {
    @PostMapping(path = "/text", produces = MediaType.TEXT_HTML_VALUE)
    public String getTextDiff(@RequestParam(defaultValue = "false") boolean isSemantic, @RequestParam String oldText, @RequestParam String newText, Model model) {
        TextDiff textDiff = new TextDiff(oldText, newText);
        HtmlTextDiff result = textDiff.getHtmlTextDiff(isSemantic);

        double similarity = textDiff.getSimilarity();

        model.addAttribute("similarity", Math.round(similarity * 100) / 100.0);
        model.addAttribute("diffText", result);

        return "textDiff";
    }
}
