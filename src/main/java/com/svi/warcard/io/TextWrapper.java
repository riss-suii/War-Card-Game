package com.svi.warcard.io;

import com.svi.warcard.util.Constants;

import java.util.List;

public class TextWrapper {

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[38;2;181;137;0m";
    /**
     * Prints items separated by 'separator' (plus 'gap'), wrapping lines at the banner width.
     * Continuation lines are indented to line up under the first item.
     * Diamonds and hearts are printed in red.
     */
    public static void print(String prefix, List<String> items, String separator, String gap) {
        String indent = " ".repeat(prefix.length());
        StringBuilder line = new StringBuilder(prefix); // text with colors
        int width = prefix.length();                     // visible width only
        boolean lineHasItem = false;

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            // Every item except the last is followed by a separator, which must also fit.
            int extra = (i < items.size() - 1) ? separator.length() : 0;

            if (!lineHasItem) {
                line.append(colorize(item));
                width += item.length();
                lineHasItem = true;
            } else {
                int candidateWidth = width + separator.length() + gap.length() + item.length();
                if (candidateWidth + extra <= Constants.BANNER_WIDTH) {
                    line.append(separator).append(gap).append(colorize(item));
                    width = candidateWidth;
                } else {
                    System.out.println(line + separator);
                    line = new StringBuilder(indent).append(colorize(item));
                    width = indent.length() + item.length();
                }
            }
        }
        System.out.println(line);
    }

    public static String center(String text) {
        int pad = Math.max(0, (Constants.BANNER_WIDTH - text.length()) / 2);
        return " ".repeat(pad) + text;
    }

    public static String colorize(String item) {
        boolean isRed = item.contains("♦") || item.contains("♥");
        return isRed ? RED + item + RESET : item;
    }
}


