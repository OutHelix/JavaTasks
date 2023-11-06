import java.util.*;

public class task4 {
    public static void main(String[] args) {
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));
        System.out.println(generateBrackets(1));
        System.out.println(generateBrackets(2));
        System.out.println(generateBrackets(3));
        System.out.println(Arrays.toString(binarySystem(3)));
        System.out.println(Arrays.toString(binarySystem(4)));
        System.out.println(alhabeticRow("abcdjuwx"));
        System.out.println(alhabeticRow("klmabzyxw"));
        System.out.println(simbCounter("aaabbcdd"));
        System.out.println(simbCounter("vvvvaajaaaaa"));
        System.out.println(convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));
        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));
        System.out.println(shortestWay(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(shortestWay(new int[][]{{2, 7, 3}, {1, 4, 8}, {4, 5, 9}}));
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));
    }
    public static String nonRepeatable(String str) {
        StringBuilder result = new StringBuilder();
        Set<Object> charSet = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!charSet.contains(c)) {
                result.append(c);
                charSet.add(c);
        }
    }
        return result.toString();
    }
    public static List<String> generateBrackets(int n) {
        List<String> result= new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }
    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == 2 * max) {
            result.add(current);
            return;
        }
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result,current + ")", open, close + 1, max);
        }
    }
    // 3
    public static String[] binarySystem(int n) {
        List<String> combinations = new ArrayList<>();
        backtrack("", n, combinations);
        String[] s = new String[combinations.size()];
        int i = 0;
        for (String el : combinations) {
            s[i] = el;
            i++;
        }
        return s;
    }
    public static void backtrack(String combination, int n, List<String> combinations) {
        if (combination.length() == n) {
            combinations.add(combination);
        } else {
// Проверяем, можно ли добавить 0 к текущей комбинации
            if (!combination.endsWith("0")) {
                backtrack(combination + "0", n, combinations);
            }
// Всегда можно добавить 1
            backtrack(combination + "1", n, combinations);
        }
    }

    //4
    public static String alhabeticRow(String str) {
        String result = "";
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < str.length() - 1; i++) {
            current.append(str.charAt(i));

            if (str.charAt(i) > str.charAt(i + 1)) { //если текущий символ больше следующего
                if (current.length() > result.length()) { // если длина текущей > результата
                    result = current.toString();
                }
                current = new StringBuilder();
            }
        }

        // добавляем последний символ строки
        current.append(str.charAt(str.length() - 1));

        // проверяем наибольшую последовательность
        if (current.length() > result.length()) {
            result = current.toString();
        }

        return result;
    }
    public static String simbCounter(String str) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> charCounts = new HashMap<>();

        for (char c : str.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1); // добавляем в качестве ключа, значения увеличиваем на 1
        }

        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(charCounts.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            result.append(entry.getKey());
            result.append(entry.getValue());
        }

        return result.toString();
    }
    public static int convertToNum(String numberString) {
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("zero", 0);
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
        numberMap.put("ten", 10);
        numberMap.put("eleven", 11);
        numberMap.put("twelve", 12);
        numberMap.put("thirteen", 13);
        numberMap.put("fourteen", 14);
        numberMap.put("fifteen", 15);
        numberMap.put("sixteen", 16);
        numberMap.put("seventeen", 17);
        numberMap.put("eighteen", 18);
        numberMap.put("nineteen", 19);
        numberMap.put("twenty", 20);
        numberMap.put("thirty", 30);
        numberMap.put("forty", 40);
        numberMap.put("fifty", 50);
        numberMap.put("sixty", 60);
        numberMap.put("seventy", 70);
        numberMap.put("eighty", 80);
        numberMap.put("ninety", 90);
        numberMap.put("hundred", 100);

        String[] words = numberString.split(" ");
        int current = 0;

        for (String word : words) {
            int value = numberMap.get(word);
            if (value == 100) {
                current = current * value;
            } else {
                current += value;
            }
        }
        return current;
    }
    public static String uniqueSubstring(String num) {
        String nums = "0123456789";
        String result = "";
        for (int x = 0; x <= num.length(); x++) {
            for (int y = x; y <= num.length(); y++) {
                if (nums.contains(num.substring(x, y))) {
                    if (result.length() < num.substring(x, y).length()) {
                        result = num.substring(x, y);
                    }
                }
            }
        }
        return result;
    }
    public static int shortestWay(int[][] matrix) {
        int n = matrix.length; // Длина матрицы
        int[][] result = new int[n][n]; // создаём матрицу с такой же длинной как и matrix
        result[0][0] = matrix[0][0]; // присваиваем значения первого элемента матрицы к результату
        for (int i = 1; i < n; i++) { //столбцы
            result[i][0] = result[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < n; j++) { // строки
            result[0][j] = result[0][j - 1] + matrix[0][j];
        }
        for (int c = 1; c < n; c++) {
            for (int k = 1; k < n; k++) {
                result[c][k] = Math.min(result[c - 1][k], result[c][k - 1]) + matrix[c][k];
            }
        }
        return result[n - 1][n - 1];
    }
    public static String numericOrder(String str) {
        StringBuilder result = new StringBuilder();
        String[] string = str.split(" ");
        for (int i = 1; i <= string.length; i++) {
            for (String word : string) {
                if (word.contains(String.valueOf(i))) {
                    result.append(word.replaceAll("\\d", "")).append(" ");
                }
            }
        }
        return result.toString();
    }
    public static int switchNums(int n1, int n2) {
        char[] n1Char = String.valueOf(n1).toCharArray();
        char[] n2Char = String.valueOf(n2).toCharArray();

        Arrays.sort(n1Char);
        char[] newChar = new char[n1Char.length];
        for (int i = 0; i < n1Char.length; i++) {
            newChar[n1Char.length - 1 - i] = n1Char[i];
        }
        for (char j : newChar) {
            for (int c = 0; c < n2Char.length; c++) { //для каждого символа j в newChar ищем первую позицию в массиве n2Char, в которой символ меньше j, и заменяет этот символ в n2Char на j
                if (j > n2Char[c]) {
                    n2Char[c] = j;
                    break;
                }
            }
        }
        return Integer.parseInt(String.valueOf(n2Char));
    }
}
