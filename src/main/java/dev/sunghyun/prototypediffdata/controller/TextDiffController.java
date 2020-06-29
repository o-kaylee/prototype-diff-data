package dev.sunghyun.prototypediffdata.controller;

import dev.sunghyun.prototypediffdata.resource.TextDiff;
import name.fraser.neil.plaintext.diff_match_patch;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;

@Controller
public class TextDiffController {
    @PostMapping(path = "/text", produces = MediaType.TEXT_HTML_VALUE)
    public String getTextDiff(@RequestBody TextDiff textDiff, Model model) {
        LinkedList<diff_match_patch.Diff> diff = textDiff.getDiff();

        model.addAttribute("diffText", diff);

        return "textDiff";
    }
}
