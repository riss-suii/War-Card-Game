package com.svi.warcard.util;

import java.util.List;

/**
 * Console formatting helpers: ANSI colors, aligned column printing, and fixed-width banners.
 *
 * @author Chariss Borlaza
 * @since 1.0
 */
public class TextWrapper {

    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String ORANGE = "\u001B[38;2;255;165;0m";
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";

    /**
     * Prints items in aligned columns. The separator sits right after each item,
     * padding goes after the separator, and wrapped rows are indented under the prefix.
     */
    public static void print(String prefix, List<String> items, String separator, String gap) {
        if (items.isEmpty()) {
            System.out.println(prefix);
            return;
        }

        String indent = " ".repeat(prefix.length());
        int itemWidth = items.stream().mapToInt(String::length).max().orElse(0);
        int cellWidth = itemWidth + separator.length();          // item + comma
        int available = Constants.BANNER_WIDTH - prefix.length();
        int cols = Math.max(1, (available + gap.length()) / (cellWidth + gap.length()));

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            boolean last = i == items.size() - 1;
            boolean rowEnd = (i % cols == cols - 1) || last;
            String tail = last ? "" : separator;                 // no comma after the final item

            if (i % cols == 0) {
                System.out.print(i == 0 ? prefix : indent);
            }

            if (rowEnd) {
                System.out.println(colorize(item) + tail);       // no trailing spaces at row end
            } else {
                // Pad using plain-text lengths so ANSI color codes don't break alignment.
                int pad = cellWidth - item.length() - tail.length();
                System.out.print(colorize(item) + tail + " ".repeat(pad) + gap);
            }
        }
    }

    /**
     * Builds a centered title, always exactly BANNER_WIDTH wide.
     */
    public static String banner(String title) {
        String padded = " " + title + " ";
        int total = Math.max(0, Constants.BANNER_WIDTH - padded.length());
        int left = total / 2;
        int right = total - left;
        return "=".repeat(left) + padded + "=".repeat(right);
    }

    /**
     * Builds a divider line.
     */
    public static String rule() {
        return "=".repeat(Constants.BANNER_WIDTH);
    }

    /**
     * Pads text on the left so it appears centered within the banner width.
     */
    public static String center(String text) {
        int pad = Math.max(0, (Constants.BANNER_WIDTH - text.length()) / 2);
        return " ".repeat(pad) + text;
    }

    /**
     * Colors the text red when it contains a red suit symbol (hearts or diamonds).
     */
    public static String colorize(String item) {
        boolean isRed = item.contains("♦") || item.contains("♥");
        return isRed ? RED + item + RESET : item;
    }
}