package sorting.utils;

public class ConsoleUtils {

    /**
     * Prints an animated progress bar in the terminal.
     * Automatically adds a newline when progress reaches 100%.
     */
    public static void printProgressBar(int current, int total) {
        int barLength = 40; // Length of the progress bar in characters
        int percent = (int) (((double) current / total) * 100);
        int filledLength = (int) (barLength * current / total);

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("=");
            } else if (i == filledLength) {
                bar.append(">"); // The leading edge of the bar
            } else {
                bar.append(" ");
            }
        }
        bar.append("] ").append(percent).append("% (").append(current).append("/").append(total).append(")");

        // \r moves the cursor back to the start of the line to overwrite it
        System.out.print("\r" + bar.toString());

        // Add a newline when progress reaches 100%
        if (current == total) {
            System.out.println();
        }
    }
} 