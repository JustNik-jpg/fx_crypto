package multilevel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Generator {

    public static ArrayList<String> getKeys(int p, int n, String phase) {
        ArrayList<String> periods = new ArrayList<>();
        Map<String, String> pairs = new HashMap<>();

        int[] intArr = new int[n];
        intArr[0] = 0;
        String period = "";

        while (true) {
            String s = "";

            // Generating key from array
            for (int i : intArr) {
                s += getHexString(i);
            }
            //--------------------------


            System.out.println(s);

            // If key generates period of appropriate length and passes evaluation it's added in map
            period = generatePeriod(p, n, s, phase);

            if ((period.length() == ((Math.pow(p, n)) - 1)) && (Evaluator.isPseudoRandom(period, p, n))) {
                periods.add(s);

                int pair = Evaluator.startingPair(intArr[0], p);
                if (pair != -1) {
                    pairs.putIfAbsent(intArr[0] + "", pair + "");

                    String key2 = generatePairKey(s, pair, p);

                    periods.add(key2);

                }

            }


            //--------------------------------------------------------------------------------------
            // Key increment
            if (intArr[n - 1] == p - 1) {

                intArr[n - 1] = 0;
                int j = 1;
                for (; j < n - 1; j++) { // Checks every next value for reaching it's ceiling
                    // If value reached it's max value - reset it and check next value in next iteration
                    if (intArr[n - 1 - j] == p - 1) {
                        intArr[n - 1 - j] = 0;
                    } else {
                        // If value isn't at it's max value - no update needed - break
                        break;
                    }
                }

                int nextValueIndex = n - 1 - j;

                // After resetting max values increment next
                intArr[nextValueIndex]++;

                //Checking if first index was incremented
                if (nextValueIndex == 0) {
                    // Checking if max value of possible keys reached
                    if (intArr[0] == p) {
                        break;
                    }
                    // Checking if starting value keys was already found
                    for (Map.Entry pair : pairs.entrySet()) {
                        if (intArr[0] == Integer.parseInt((String) pair.getValue())) {
                            intArr[0]++;
                            break;
                        }
                    }
                }

                continue;

            }

            intArr[n - 1]++;

        }

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

            period += Generator.getHexString(temp % p);
            if (period.endsWith(phase) && (period.length() > phase.length() * 2)) {
                period = period.substring(0, period.length() - phase.length());
                break;
            }

        }

        return period;

    }

    public static String generatePairKey(String key, int pairStart, int p) {
        String result = getHexString(pairStart);
        int k = p - pairStart;

        for (int i = 1; i < key.length(); i++) {
            int a0 = parseHex(String.valueOf(key.charAt(key.length() - i)));
            result += getHexString((k * a0) % p);

        }

        return result;
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

        return switch (s) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "0" -> 0;
            case "A" -> 10;
            case "B" -> 11;
            case "C" -> 12;
            case "D" -> 13;
            case "E" -> 14;
            case "F" -> 15;
            case "G" -> 16;
            case "H" -> 17;
            case "I" -> 18;
            case "J" -> 19;
            case "K" -> 20;
            case "L" -> 21;
            case "M" -> 22;
            case "N" -> 23;
            case "O" -> 24;
            case "P" -> 25;
            case "Q" -> 26;
            case "R" -> 27;
            case "S" -> 28;
            case "T" -> 29;
            case "U" -> 30;
            case "V" -> 31;
            case "W" -> 32;
            case "X" -> 33;
            case "Y" -> 34;
            case "Z" -> 35;
            default -> -1;
        };
    }
}
