package multilevel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Generator {

    public static Map<String, Integer> getKeysMap(int p, int n, String phase) {
        Map<String, Integer> periods = new HashMap<String, Integer>();
        int[] intArr = new int[n];
        intArr[0] = 0;




        System.out.println(Arrays.toString(intArr));
        for (int i = 0; i < Math.pow(p, n); i++) {
            String s = "";
            for (int j = 0; j < intArr.length; j++) {
                s += getHexString(intArr[j]);

            }
            System.out.println(s+" "+i);
            periods.put(s, generatePeriod(p, n, s, phase).length());

            if (intArr[n - 1] == p - 1) {

                intArr[n - 1] = 0;
                int j = 1;
                for (; j < n-1; j++) {
                    if (intArr[n - 1 - j] == p - 1) {

                        intArr[n - 1 - j] = 0;
                    } else {
                        break;
                    }
                }
                intArr[n - 1 - j]++;
                continue;

            }

            intArr[n - 1]++;


        }

        System.out.println(periods);

        System.out.println("--------------------------------------");

        Iterator it = periods.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (((int) (entry.getValue())) == ((Math.pow(p, n)) - 1)) {
                boolean b = Evaluator.isPseudoRandom(generatePeriod(p, n, (String) entry.getKey(), phase), p, n);
                System.out.println(entry.getKey() + " " + b);
                if (!b) {
                    it.remove();
                }

            } else {
                it.remove();
            }
        }

        System.out.println(periods + " " + periods.size());

        return periods;
    }


    public static String generatePeriod(int p, int n, String key, String phase) {
        int length = (int) Math.pow(p, n) - 1;

        String period = phase;

        for (int i = 0; i < length; i++) {
            int temp = 0;
            for (int j = 0; j < key.length(); j++) {
                String s1 = "";
                String s2 = "";

                s1 += period.charAt(period.length() - j - 1);
                s2 += key.charAt(key.length() - j - 1);

                temp += Generator.parseHex(s1) * Generator.parseHex(s2);

            }
            int iii = temp % p;
            period += Generator.getHexString(temp % p);
            if (period.endsWith(phase) && (period.length() > phase.length() * 2)) {
                period = period.substring(0, period.length() - phase.length());
                break;
            }

        }

        return period;

    }

    public static String getHexString(int i) {
        String[] sa = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        if ((i > 35) || (i < 0)) {
            return Integer.toHexString(i).toUpperCase();
        }


        return sa[i];
    }

    public static int parseHex(String s) {

        switch (s) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "0":
                return 0;
            case "A":
                return 10;
            case "B":
                return 11;
            case "C":
                return 12;
            case "D":
                return 13;
            case "E":
                return 14;
            case "F":
                return 15;
            case "G":
                return 16;
            case "H":
                return 17;
            case "I":
                return 18;
            case "J":
                return 19;
            case "K":
                return 20;
            case "L":
                return 21;
            case "M":
                return 22;
            case "N":
                return 23;
            case "O":
                return 24;
            case "P":
                return 25;
            case "Q":
                return 26;
            case "R":
                return 27;
            case "S":
                return 28;
            case "T":
                return 29;
            case "U":
                return 30;
            case "V":
                return 31;
            case "W":
                return 32;
            case "X":
                return 33;
            case "Y":
                return 34;
            case "Z":
                return 35;
        }
        return -1;
    }
}
