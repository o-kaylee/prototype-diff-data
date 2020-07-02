package dev.sunghyun.prototypediffdata;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

@Slf4j
public class PrototypeTranslatorRunner {
    public static void main(String[] args) throws IOException {
        File input = new File("/Users/a1101400/Workspace/prototype-diff-data/src/main/resources/static/sample/huggies.html");

        Document doc = Jsoup.parse(input, "UTF-8");
        Element rootElement = doc.getElementById("aplus");

        log.debug("Element #aplus has been found.");

        Elements childElements = rootElement.select("div.celwidget.aplus-module");

        log.debug(childElements.size() + " elements have been found. Analyzing...");

        Elements elementsWithText = getElementsWithText(childElements);
        Elements elementsWithImage = getElementsWithImage(childElements);
        Elements popoverElements = getPopoverElements(childElements);

        log.debug(elementsWithText.size() + " elements with text have been found.");
        log.debug(elementsWithImage.size() + " elements with image have been found.");
        log.debug(popoverElements.size() + " popover elements have been found.");

        System.out.println(rootElement.outerHtml());
    }

    private static Elements getElementsWithText(Elements descriptionElements) {
        Elements returnValue = new Elements();

        for (Element el: descriptionElements) returnValue.addAll(el.select("h1, h2, h3, h4, h5, h6, p"));

        return returnValue;
    }

    private static Elements getElementsWithImage(Elements descriptionElements) {
        Elements returnValue = new Elements();

        for (Element el: descriptionElements) returnValue.addAll(el.getElementsByTag("img"));

        return returnValue;
    }

    private static Elements getPopoverElements(Elements descriptionElements) {
        Elements returnValue = new Elements();

        for (Element el: descriptionElements) returnValue.addAll(el.select("span[data-inline-content]"));

        return returnValue;
    }
}
