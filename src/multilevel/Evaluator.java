package multilevel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator {

    public static boolean isPseudoRandom(String s, int p, int n) {

        boolean repetitions = true;


        boolean equalNumOfReps = true;
        for (int i = 1; i <= n; i++) {
            equalNumOfReps = equalNumOfReps && isEqualNumOfRepetitions(s, i, p, n);
        }

        return isUnique(s, n) && isEqualNumberOfChars(s, p, n) && equalNumOfReps;
    }

    public static boolean isUnique(String s, int n) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Matcher matcher = Pattern.compile("(.){" + n + "}").matcher(s);
        String group = "";
        while (matcher.find()) {
            group = matcher.group();
            if (!map.containsKey(group)) {
                map.put(group, 1);
            } else {
                int value = map.get(group);
                value++;
                map.put(group, value);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                return false;
            }
        }

        return true;

    }

    public static boolean isEqualNumOfRepetitions(String s, int repLength, int p, int n) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Pattern pattern;
        Matcher matcher;

        for (int i = 0; i < p; i++) {
            String ss = Generator.getHexString(i);
            pattern = Pattern.compile("" + ss + "+");
            matcher = pattern.matcher(s);

            while (matcher.find()) {
                if (matcher.group().length() == repLength) {

                    String group = matcher.group();

                    if (!map.containsKey(group)) {
                        map.put(group, 1);
                    } else {
                        int value = map.get(group);
                        value++;
                        map.put(group, value);
                    }

                }

            }
            String key = ss;

        }

        int numOfReps;

        if (repLength == n) {
            numOfReps = 1;
        } else {
            numOfReps = (int) (Math.pow((p - 1), 2) * Math.pow(p, (n - 1 - repLength)) / p);
        }

        return evaluateMap(map, numOfReps);
    }

    public static boolean isEqualNumberOfChars(String s, int p, int n) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));

            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                int value = map.get(c);
                value++;
                map.put(c, value);
            }

        }

        int numOfReps = (int) Math.pow(p, n) / p;

        return evaluateMap(map, numOfReps);

    }

    private static boolean evaluateMap(Map<String, Integer> map, int numOfReps) {
        Iterator it = map.entrySet().iterator();
        Matcher m;

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();

            if (!entry.getValue().equals(numOfReps)) {

                m = Pattern.compile("0+").matcher((String) entry.getKey());
                if (m.matches()) {
                    continue;
                }
                return false;

            }

        }
        return true;
    }

}
