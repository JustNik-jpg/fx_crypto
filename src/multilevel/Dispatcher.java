package multilevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


public class Dispatcher {
    public static void main(String... args) {
        //ArrayList<String> list = Generator.getKeys(13, 4, "1111");
        //System.out.println(list + "\n" + list.size());
        keysOut();

    }

    public static void keysOut() {
        int[] pArray = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        File file = new File("keys.txt");
        String phase = "11111111111111111111";


        try (PrintWriter writer = new PrintWriter(file)) {

            int nCeiling = 9;

            for (int p : pArray) {
                for (int n = 3; n < nCeiling; n++) {
                    String head = "p=" + p + " n=" + n;
                    ArrayList<String> keys = Generator.getKeys(p, n, phase.substring(0, n));

                    writer.write(head + "\n");
                    for (String key : keys) {
                        writer.write(key + "\n");
                    }

                }



            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return;
        }

    }

}

