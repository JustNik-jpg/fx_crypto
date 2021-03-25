package multilevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


public class Dispatcher {
    public static void main(String... args) {
        //ArrayList<String> list = Generator.getKeys(31, 3, "111");
        //System.out.println(list + "\n" + list.size());
        keysOut();

    }

    public static void keysOut() {
        int[] pArray = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};

        String phase = "11111111111111111111";


        int nCeiling = 9;

        for (int p : pArray) {
            if ((p == 5) || (p == 7) || (p == 17) || (p == 19) || (p == 23)) {
                nCeiling--;
            }
            int finalCeiling = nCeiling;
            Thread t = new Thread(() -> {

                File file = new File("keys" + p + ".txt");
                try (PrintWriter writer = new PrintWriter(file)) {

                    writer.write("Starting " + p + "...\n");
                    for (int n = 3; n < finalCeiling; n++) {
                        String head = "p=" + p + " n=" + n;
                        ArrayList<String> keys = Generator.getKeys(p, n, phase.substring(0, n));

                        writer.write(head + "\n");
                        for (String key : keys) {
                            writer.write(key + "\n");
                        }

                    }
                    writer.flush();

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                    return;
                }


            });
            t.start();

        }
    }

}

