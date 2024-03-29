package dev.sunghyun.prototypediffdata.controller;

import dev.sunghyun.prototypediffdata.resource.Translation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


@Controller
public class TranslateController{
    @PostMapping(value = "/translate", produces = MediaType.TEXT_HTML_VALUE)
    public String startTranslation(@RequestPart MultipartFile htmlFile, Model model)  throws IOException {
        InputStream is = htmlFile.getInputStream();

        Document doc = Jsoup.parse(is, "UTF-8", "");

        // Import style and scripts
        Element head = doc.head();
        Elements js = doc.body().select("script[type=text/javascript]");
        model.addAttribute("amznStyles", head.outerHtml());
        model.addAttribute("amznScripts", js.outerHtml());

        Translation translation = new Translation(doc);

        // Translation - Text
        HashMap<String, String> untranslatedText = translation.extractTexts();
        model.addAttribute("previousContent", translation.getRootElement().outerHtml());
        model.addAttribute("untranslatedText", untranslatedText);

        // Translation - Image
        HashMap<String, String> untranslatedImageSrcs = translation.extractImages();
        model.addAttribute("untranslatedImageSrcs", untranslatedImageSrcs);

        // TODO: Prepare for popover text translation
        HashMap<String, String> untranslatedPopoverTexts = translation.extractPopoverTexts();
        model.addAttribute("untranslatedPopoverTexts", untranslatedPopoverTexts);

        return "translation";
    }
}
