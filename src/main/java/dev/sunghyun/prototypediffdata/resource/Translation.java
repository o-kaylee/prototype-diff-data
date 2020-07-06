package dev.sunghyun.prototypediffdata.resource;

import dev.sunghyun.prototypediffdata.common.TranslateExceptionCode;
import dev.sunghyun.prototypediffdata.common.TranslateException;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class Translation {
    private static final String[] ROOT_ELEMENTS_ID = {"aplus", "promotions_feature_div"};

    private final Document doc;
    private final Element rootElement;
    private final Elements sectionElements;

    private Map<String, String[]> translatingItems;

    public Translation(Document doc) {
        this.doc = doc;
        found: {
            for (String rootElementId : ROOT_ELEMENTS_ID) {
                if (this.doc.getElementById(rootElementId) != null) {
                    this.rootElement = this.doc.getElementById(rootElementId);
                    break found;
                }
            }
            throw new TranslateException(TranslateExceptionCode.HTML_DOCUMENT_NOT_EXIST);
        }

        this.sectionElements = this.rootElement.select("div.celwidget");
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

    public HashMap<String, String> extractPopoverTexts() {
        HashMap<String, String> textIdMap = new HashMap<>();

        Elements popoverTextElements = this.sectionElements.select("span[data-inline-content]");

        int elementNumericId = 0;
        for (Element popoverTextElement: popoverTextElements) {
            Document popoverContent = Jsoup.parse(popoverTextElement.attr("data-inline-content"));
            Elements popoverTexts = popoverContent.getElementsByTag("p");

            for (Element popoverText: popoverTexts) {
                String elementId = "untranslatedPopoverText-" + elementNumericId;

                // Assign id to elements
                popoverText.attr("id", elementId);

                textIdMap.put(elementId, popoverText.text());

                elementNumericId++;
            }

//            if (!popoverTextElements.get(i).text().isEmpty()) {
//                // Declare and define id
//                String elementId = "untranslatedPopoverText-" + i;
//
//                // Assign id to elements
//                popoverTextElements.get(i).attr("id", elementId);
//
//                textIdMap.put(elementId, textElements.get(i).text());
//            }
        }

        return textIdMap;
    }
}

