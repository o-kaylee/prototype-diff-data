package dev.sunghyun.prototypediffdata.resource;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Data
public class Translation {
    private final Document doc;
    private final Element rootElement;
    private final Elements childElements;

    private Map<String, String[]> translatingItems;

    public Translation(Document doc) throws IOException {
        this.doc = doc;
        this.rootElement = this.doc.getElementById("aplus");  // todo: find the case for element which id is not `aplus`
        this.childElements = this.rootElement.select("div.celwidget.aplus-module");
    }

//    private String[] extractTexts() {
//        Elements textElements = new Elements();
//
//        // Consider adding `span` on what condition
//        for (Element el: this.childElements) {
//            el.select("h1, h2, h3, h4, h5, h6, p").attr("class", "harpa");
//
//
//            textElements.addAll(el.select("h1, h2, h3, h4, h5, h6, p"));
//        }
//
//        // TODO: 여기서 DB insert, 리턴은 추출한 string만?
//
//        ;
//    }
}
