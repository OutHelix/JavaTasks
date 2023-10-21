import java.util.*;

public class task4 {
    public static void main(String[] args) {
        System.out.println("Task 4.0: " + nonRepeatable("abracadabra"));
        System.out.println("Task 4.1: " + generateBrackets(2));
        System.out.println("Task 4.2: ");
        System.out.println("Task 4.3: " + alphabeticRow("abcdjuwx"));
        System.out.println("Task 4.4: " + countSimb("aaabbcdd"));
        System.out.println("Task 4.5: " + convertToNum("five hundred sixty seven"));
        System.out.println("Task 4.6: " + uniqueSubstring("77897898"));
        System.out.println("Task 4.7: " + shortestWay(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println("Task 4.8: " + numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println("Task 4.9: " + switchNums(519, 723));
    }

    //   --- 1
    public static String nonRepeatable(String str) {
        if (str.length() <= 1) {
            return str;
        }

        if (str.substring(1).contains(str.substring(0, 1))) {
            return nonRepeatable(str.substring(1));
        } else {

            return str.charAt(0) + nonRepeatable(str.substring(1));
        }
    }

    //   --- 2
    public static List<String> generateBrackets(int input) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, input);
        return result;
    }

    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        // Базовый случай: если длина текущей комбинации скобок достигла 2n, добавляем её в результат
        if (current.length() == 2 * max) {
            result.add(current);
            return;
        }

        // Если количество открывающих скобок меньше n, можем добавить открывающую скобку
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);

        }

        // Если количество закрывающих скобок меньше количества открывающих, можем добавить закрывающую скобку
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }
    //   --- 3


    //   --- 4
    public static String alphabeticRow(String input) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String max = "";
        for (int x = 0; x <= input.length(); x++) {
            for (int y = x; y <= input.length(); y++) {
                if (alphabet.contains(input.substring(x, y)) || alphabet.toUpperCase().contains(input.substring(x, y))) {
                    if (input.substring(x, y).length() > max.length()) {
                        max = input.substring(x, y);
                    }
                }
            }
        }
        return max;
    }

    //   --- 5
    public static String countSimb(String input) {
        StringBuilder output = new StringBuilder();
        Map<Character, Integer> simb = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char a = input.charAt(i);
            simb.put(a, simb.getOrDefault(a, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(simb.entrySet());
        list.sort(Map.Entry.comparingByValue());
        for (Map.Entry<Character, Integer> entry : list) {
            output.append(entry.getKey()).append(entry.getValue());
        }
        return output.toString();
    }

    //   --- 6
    public static Integer convertToNum(String input) {
        Map<String, Integer> numbers = new HashMap<>();
        numbers.put("one", 1);
        numbers.put("two", 2);
        numbers.put("three", 3);
        numbers.put("four", 4);
        numbers.put("five", 5);
        numbers.put("six", 6);
        numbers.put("seven", 7);
        numbers.put("eight", 8);
        numbers.put("nine", 9);
        numbers.put("ten", 10);
        numbers.put("eleven", 11);
        numbers.put("twelve", 12);
        numbers.put("thirteen", 13);
        numbers.put("fourteen", 14);
        numbers.put("fifteen", 15);
        numbers.put("sixteen", 16);
        numbers.put("seventeen", 17);
        numbers.put("eighteen", 18);
        numbers.put("nineteen", 19);
        numbers.put("twenty", 20);
        numbers.put("thirty", 30);
        numbers.put("forty", 40);
        numbers.put("fifty", 50);
        numbers.put("sixty", 60);
        numbers.put("seventy", 70);
        numbers.put("eighty", 80);
        numbers.put("ninety", 90);
        numbers.put("hundred", 100);
        numbers.put("thousand", 1000);

        String[] words = input.toLowerCase().split(" ");
        int result = 0;
        int tempResult = 0;
        for (String word : words) {
            int numberValue = numbers.get(word);

            if (numberValue >= 100) {
                tempResult *= numberValue;
            } else {
                tempResult += numberValue;
            }
        }
        return result + tempResult;
    }

    //   --- 7
    public static String uniqueSubstring(String input) {
        String alphabet = "0123456789";
        String word = "";
        loop:
        for (int x = 0; x <= input.length(); x++) {
            for (int y = x; y <= input.length(); y++) {
                if (alphabet.contains(input.substring(x, y))) {
                    if (word.length() < input.substring(x, y).length()) {
                        word = input.substring(x, y);
                    }
                }
            }
        }
        return word;
    }

    //   --- 8
    public static int shortestWay(int[][] input) {
        int n = input.length;
        int[][] output = new int[n][n];

        output[0][0] = input[0][0];
        for (int i = 1; i < n; i++) {
            output[i][0] = output[i - 1][0] + input[i][0];
        }
        for (int j = 1; j < n; j++) {
            output[0][j] = output[0][j - 1] + input[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                output[i][j] = Math.min(output[i - 1][j], output[i][j - 1]) + input[i][j];
            }
        }

        return output[n - 1][n - 1];
    }
    //   --- 9
    public static String numericOrder(String input) {
        StringBuilder output = new StringBuilder();
        String[] str = input.split(" ");
        for (int i = 1; i <= str.length; i++) {
            for (String word : str) {
                if (word.contains(String.valueOf(i))) {
                    output.append(word.replaceAll("\\d", "")).append(" ");
                }
            }
        }
        return output.toString();
    }
    //   --- 10
    public static int switchNums(int num1, int num2) {
        char[] num1Char = String.valueOf(num1).toCharArray();
        char[] num2Char = String.valueOf(num2).toCharArray();


        Arrays.sort(num1Char);
        char[] new1Char = new char[num1Char.length];
        for (int i = 0; i < num1Char.length; i++) {
            new1Char[num1Char.length - 1 - i] = num1Char[i];
        }

        for (char c : new1Char) {
            for (int y = 0; y < num2Char.length; y++) {
                if (c > num2Char[y]) {
                    num2Char[y] = c;
                    break;
                }
            }
        }
        return Integer.parseInt(String.valueOf(num2Char));
    }
}