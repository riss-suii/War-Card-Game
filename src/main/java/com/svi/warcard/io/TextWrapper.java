package com.svi.warcard.io;

import com.svi.warcard.util.Constants;

import java.util.List;

public class TextWrapper {

    /**
     * Prints items separated by 'separator' (plus 'gap'), wrapping lines at the banner width.
     * Continuation lines are indented to line up under the first item.
     */
    public static void print(String prefix, List<String> items, String separator, String gap) {
        String indent = " ".repeat(prefix.length());
        StringBuilder line = new StringBuilder(prefix);
        boolean lineHasItem = false;

        for (int i = 0; i < items.size(); i++) {
            // Every item except the last is followed by a separator, which must also fit.
            int extra = (i < items.size() - 1) ? separator.length() : 0;
            if (!lineHasItem) {
                line.append(items.get(i));
                lineHasItem = true;
            } else {
                String candidate = line + separator + gap + items.get(i);
                if (candidate.length() + extra <= Constants.banner_width) {
                    line = new StringBuilder(candidate);
                } else {
                    System.out.println(line + separator);
                    line = new StringBuilder(indent + items.get(i));
                }
            }
        }
        System.out.println(line);
    }
}
