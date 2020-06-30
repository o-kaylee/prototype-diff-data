package dev.sunghyun.prototypediffdata.controller;

import dev.sunghyun.prototypediffdata.resource.HtmlTextDiff;
import dev.sunghyun.prototypediffdata.resource.TextDiff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
public class TextDiffController {
    @PostMapping(path = "/text", produces = MediaType.TEXT_HTML_VALUE)
    public String getTextDiff(@RequestParam(defaultValue = "false") boolean isSemantic, @RequestParam String oldText, @RequestParam String newText, Model model) {
        log.debug("Received two strings: " + oldText + ", " + newText + " with semantic option: " + isSemantic);
        TextDiff textDiff = new TextDiff(oldText, newText);
        HtmlTextDiff result = textDiff.getHtmlTextDiff(isSemantic);

        model.addAttribute("diffText", result);

        return "textDiff";
    }
}
