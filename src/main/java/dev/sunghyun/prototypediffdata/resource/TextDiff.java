package dev.sunghyun.prototypediffdata.resource;

import lombok.RequiredArgsConstructor;
import name.fraser.neil.plaintext.diff_match_patch;

import java.util.LinkedList;

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

    public double getSimilarity() {
        LinkedList<diff_match_patch.Diff> diffs = this.dmp.diff_main(this.text1, this.text2);

        int levenshteinD = dmp.diff_levenshtein(diffs);
        if (levenshteinD == 0) return 100.0;
        int lengthOfLongerText = Math.max(this.text1.length(), this.text2.length());

        return 100.0 - ((double) levenshteinD / (double) lengthOfLongerText) * 100;
    }

    public HtmlTextDiff getHtmlTextDiff(boolean isSemantic) {
        LinkedList<diff_match_patch.Diff> diffs = this.dmp.diff_main(this.text1, this.text2);

        if (isSemantic) {
            dmp.diff_cleanupSemantic(diffs);
        }

        StringBuilder oldContent = new StringBuilder();
        StringBuilder newContent = new StringBuilder();

        for (diff_match_patch.Diff diff: diffs) {
            // convert to percent-encoded
            String text = (diff.text.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\n", "<br>"));

            if (diff.operation == diff_match_patch.Operation.DELETE) {
                oldContent.append("<del style='background:#ffe6e6;'>").append(text).append("</del>");
            }
            else if (diff.operation == diff_match_patch.Operation.INSERT) {
                newContent.append("<ins style='background: #e6ffe6;'>").append(text).append("</ins>");
            }
            else {
                oldContent.append("<span>").append(text).append("</span>");
                newContent.append("<span>").append(text).append("</span>");
            }
        }
        return new HtmlTextDiff(oldContent.toString(), newContent.toString());
    }
}
