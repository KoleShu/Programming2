import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;
public class Main {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        // set the chooser to the project src directory
        chooser.setCurrentDirectory(target.toFile());
        ArrayList<Object> chosenFile = new ArrayList<Object>( );
        try  // Code that might trigger the exception goes here
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                inFile = new Scanner(target);

                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    chosenFile.add(line);
                    System.out.println(line);
                }

                inFile.close();
            }
            else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file! Terminating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
        ShortWordFilter swf = new ShortWordFilter( );
        ArrayList<Object> results = collectAll(chosenFile, swf);
        System.out.println(results);
    }

    public static ArrayList<Object> collectAll(
            ArrayList<Object> objects, Filter f) {

        ArrayList<Object> filtered = new ArrayList<Object>();
        for (Object obj : objects) {
            if (f.accept(obj)) {
                filtered.add(obj);
            }
        }
        return filtered;
    }
}