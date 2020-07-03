package dev.sunghyun.prototypediffdata.resource;

import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Translation {
    private final Document doc;
    private final Element rootElement;
    private final Elements sectionElements;

    private Map<String, String[]> translatingItems;

    public Translation(Document doc) throws IOException {
        this.doc = doc;
        this.rootElement = this.doc.getElementById("aplus");  // todo: find the case for element which id is not `aplus`
        this.sectionElements = this.rootElement.select("div.celwidget.aplus-module");
    }

    public HashMap<String, String> extractTexts() {
        HashMap<String, String> textIdMap = new HashMap<>();

        // Consider adding `span` on what condition
        Elements textElements = this.sectionElements.select("h1, h2, h3, h4, h5, h6, p, span");

        for (int i = 0; i < textElements.size(); i++) {
            if (!textElements.get(i).text().isEmpty()) {
                // Declare and define id
                String elementId = "untranslatedText-" + i;

                // Assign id to elements
                textElements.get(i).attr("id", elementId);

                textIdMap.put(elementId, textElements.get(i).text());
            }
        }

        // TODO: 여기서 DB insert, 리턴은 추출한 string만?

        return textIdMap;
    }

    public ArrayList<String> extractImages() {
        Elements imageElements = this.sectionElements.select("img");

        // TODO: Image에 ID 매겨야 한다.
        ArrayList<String> imageSrcs = new ArrayList<>();
        for (Element e: imageElements) {
            imageSrcs.add(e.attr("src"));
        }

        return imageSrcs;
    }
}
