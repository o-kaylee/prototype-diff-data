package dev.sunghyun.prototypediffdata.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import name.fraser.neil.plaintext.diff_match_patch;

import java.util.LinkedList;

@Data
@RequiredArgsConstructor
public class TextDiff {
    diff_match_patch dmp = new diff_match_patch();

    private final String text1;
    private final String text2;

    public LinkedList<diff_match_patch.Diff> getDiff() {
        LinkedList<diff_match_patch.Diff> diff = this.dmp.diff_main(this.text1, this.text2);

        dmp.diff_cleanupSemantic(diff);

        return diff;
    }
}
