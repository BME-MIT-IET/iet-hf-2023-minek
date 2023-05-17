package szoftlab.main;

/**
 * Custom logger class. Helps information printing and debugging.
 */
public class Log {
    /**
     * Indent of the log output
     */
    public static int indent = 0;
    /**Log output file*/
    public static String outputFile = "";

    /**
     * Prints a message to the console, indented by the "static indent" amount.
     * @param text The message to print.
     */
    public static void print(String text){
        if(outputFile.equals("")){
            System.out.print("\t".repeat(indent)+text);
        } else{
            try{
                java.io.FileWriter fw = new java.io.FileWriter(outputFile, true);
                fw.write("\t".repeat(indent)+text);
                fw.close();
            } catch(Exception e){
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    /**
     * Prints a message to the console with endline, indented by the "static indent" amount.
     * @param text The message to print.
     */
    public static void println(String text){
        print(text+"\n");
    }

    /**
     * Prints a message to the console, indented by the given amount.
     * @param text The message to print.
     * @param withIndent The amount of indentation.
     */
    public static void print(String text,int withIndent){
        if(outputFile.equals("")){
            System.out.print("\t".repeat(withIndent)+text);
        } else{
            try{
                java.io.FileWriter fw = new java.io.FileWriter(outputFile, true);
                fw.write("\t".repeat(withIndent)+text);
                fw.close();
            } catch(Exception e){
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    /**
     * Prints a message to the console with endline, indented by the given amount.
     * @param text The message to print.
     * @param withIndent The amount of indentation.
     */
    public static void println(String text,int withIndent){
        print(text+"\n",withIndent);
    }

    /**Log block start.*/
    public static void blockStart(String text){
        println(text);
        indent++;
    }

    /**Log block end.*/
    public static void blockEnd(String text){
        indent--;
        println(text);
    }

    /**Log set output file.*/
    public static void setOutputFile(String outputFile) {
        Log.outputFile = outputFile;
    }

}
