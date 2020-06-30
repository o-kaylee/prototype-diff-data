package dev.sunghyun.prototypediffdata.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HtmlTextDiff {
    private final String oldContent;
    private final String newContent;
}
