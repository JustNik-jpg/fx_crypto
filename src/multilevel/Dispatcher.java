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
        //keysOut();


    }

    /*public static void keysOut() {
        int[] pArray = {3, 5, 7, 13,19};

        String phase = "11111111111111111111";


        int nCeiling = 9;

        for (int p : pArray) {
            *//*if ((p == 5) || (p == 7) || (p == 17) || (p == 19) || (p == 23)) {
                nCeiling--;
            }*//*
            //int finalCeiling = nCeiling;
            Thread t = new Thread(() -> {

                File file = new File("keys" + p + ".txt");
                try (PrintWriter writer = new PrintWriter(file)) {
                    int finalCeiling = 0;

                    if (p == 3) {
                        finalCeiling = 9;
                    } else if (p == 5) {
                        finalCeiling = 8;
                    } else if (p == 7) {
                        finalCeiling = 8;
                    } else if (p == 13) {
                        finalCeiling = 7;
                    } else if (p==19){
                        finalCeiling=6;
                    }


                    for (int n = 3; n < finalCeiling; n++) {

                        ArrayList<String> keys = Generator.getKeys(p, n, phase.substring(0, n));

                        String head = "p=" + p + " n=" + n + " expected: " + Evaluator.eulersFormula(p, n) + ", got: " + keys.size();

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
    }*/

    public static void keyOut(int p, int nCeiling) {
        String phase = "11111111111111111111";


        for (int i = 3; i <= nCeiling; i++) {
            int n = i;
            Thread t = new Thread(() -> {

                File file = new File("keys" + p + "^" + n + ".txt");

                try (PrintWriter writer = new PrintWriter(file)) {

                    ArrayList<String> keys = Generator.getKeys(p, n, phase.substring(0, n));
                    String head = "p=" + p + " n=" + n + " expected:" + Evaluator.eulersFormula(p, n) + ", got:" + keys.size();

                    writer.write(head + "\n");
                    for (String key : keys) {
                        writer.write(key + "\n");
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

