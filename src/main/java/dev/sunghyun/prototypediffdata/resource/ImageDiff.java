package dev.sunghyun.prototypediffdata.resource;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageDiff {
    private final BufferedImage expected;
    private final BufferedImage actual;

    public ImageDiff(BufferedImage expected, BufferedImage actual) {
        this.expected = expected;
        this.actual = actual;
    }

    public ImageDiff(MultipartFile expected, MultipartFile actual) throws IOException {
        this.expected = ImageIO.read(new ByteArrayInputStream(expected.getBytes()));
        this.actual = ImageIO.read(new ByteArrayInputStream(actual.getBytes()));
    }

    public double getSimilarity() {
        float differencePercent = ImageComparisonUtil.getDifferencePercent(this.expected, this.actual);

        return 100.0 - differencePercent;
    }

    public BufferedImage getDiff() {
        ImageComparison imageComparison = new ImageComparison(expected, actual);

        // TODO: 세세하게 설정할 수 있는 항목: https://romankh3.github.io/image-comparison/#usage
        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();

        return imageComparisonResult.getResult();
    }
}
