package dev.sunghyun.prototypediffdata.controller;

import dev.sunghyun.prototypediffdata.resource.ImageDiff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Slf4j
@Controller
public class ImageDiffController {
    @PostMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public String getImageDiff(@RequestPart MultipartFile oldImage, @RequestPart MultipartFile newImage, Model model) throws IOException {
        log.debug("Received two images: " + oldImage.getName() + ", " + newImage.getName());
        ImageDiff imageDiff = new ImageDiff(oldImage, newImage);

        double similarity = imageDiff.getSimilarity();
        BufferedImage result = imageDiff.getDiff();

        // HTML 렌더링하기 위해 BufferedImage을 Base64-encoded string으로 변환하는 작업
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(result, "png", output);

        model.addAttribute("similarity", similarity);
        model.addAttribute("result", Base64.getEncoder().encodeToString(output.toByteArray()));

        return "imageDiff";
    }
}
