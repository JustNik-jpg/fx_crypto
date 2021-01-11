package multilevel;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Dispatcher {
    public static void main(String... args) {


        int p = 13;
        int n = 3;
        //String key = "A2B";
        String phase = "111";

        //String s = Generator.generatePeriod(p,n,"2C3",phase);
        //System.out.println("----------------------------Starting----------------------------");
        //System.out.println("Period: "+s+"\n"
        //        +"It's length: "+s.length());
        //System.out.println("----------------------------------------------------------------");
        //System.out.println("Repetitions of single char in a row: "+Evaluator.isEqualNumOfRepetitions(s,1,p,n));
        //System.out.println("Repetitions of two char in a row: "+Evaluator.isEqualNumOfRepetitions(s,2,p,n));
        //System.out.println("Repetitions of three char in a row: "+Evaluator.isEqualNumOfRepetitions(s,3,p,n));
        //System.out.println("Total number of each char: "+Evaluator.isEqualNumberOfChars(s,p,n));
        //System.out.println("Three char repetitions: "+Evaluator.isPseudoRandom(s));
        //System.out.println("Final result: "+Evaluator.isSuitable(s,p,n));

        //Map<String, Integer> periods = Generator.getKeysMap(p, n, phase);
        //System.out.println(periods+"\n"+periods.size());

        /*String s1 = Generator.generatePeriod(p,n,"62B","111");
        System.out.println(s1);
        System.out.println(Evaluator.isPseudoRandom(s1,p,n));
        System.out.println(Evaluator.isUnique(s1));
        System.out.println(Evaluator.isEqualNumberOfChars(s1,p,n));
        System.out.println(Evaluator.isEqualNumOfRepetitions(s1,1,p,n));
        System.out.println(Evaluator.isEqualNumOfRepetitions(s1,2,p,n));
        System.out.println(Evaluator.isEqualNumOfRepetitions(s1,3,p,n));

        String s2 = Generator.generatePeriod(p,n,"62B","1B1");
        System.out.println(s2);
        System.out.println(Evaluator.isPseudoRandom(s2,p,n));
        System.out.println(Evaluator.isUnique(s2));
        System.out.println(Evaluator.isEqualNumberOfChars(s2,p,n));
        System.out.println(Evaluator.isEqualNumOfRepetitions(s2,1,p,n));
        System.out.println(Evaluator.isEqualNumOfRepetitions(s2,2,p,n));
        System.out.println(Evaluator.isEqualNumOfRepetitions(s2,3,p,n));*/


        /*Map<String, Integer> periods = new HashMap<String, Integer>();
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < p; k++) {

                }

            }

        }*/

        System.out.println(Generator.getKeysMap(17,4,"1111"));
    }

}
