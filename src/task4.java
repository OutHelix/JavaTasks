import java.util.*;

public class task4 {
    public static void main(String[] args) {
        System.out.println("Task 4.0: " + nonRepeatable("abracadabra"));
        System.out.println("Task 4.1: " + generateBrackets(3));
        System.out.println("Task 4.2: " + binarySystem(3));
        System.out.println("Task 4.3: " + alhabeticRow("abcdjuwx"));
        System.out.println("Task 4.4: " + simbCounter("aaabbcdd"));
        System.out.println("Task 4.5: " + convertToNum("eight"));
        System.out.println("Task 4.6: " + uniqueSubstring("123412324"));
        System.out.println("Task 4.7: " + shortestWay(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println("Task 4.8: " + numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println("Task 4.9: " + switchNums(519, 723));
    }

    /**  --1--
     * Рекурсивная функция, которая удаляет повторяющиеся символы из переданной строки.
     * Возвращает строку, в которой каждый символ встречается только один раз.
     */
    public static String nonRepeatable(String str) {
        StringBuilder result = new StringBuilder();
        Set<Object> charSet = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!charSet.contains(c)) {
                result.append(c); // Добавляет уникальный символ в результат
                charSet.add(c); // Добавляет символ в HashSet, чтобы помнить о его появлении
            }
        }
        return result.toString(); // Возвращает строку с уникальными символами
    }


    /**  --2--
     * Функция, которая генерирует все возможные правильные комбинации пар скобок для заданного числа n.
     * Возвращает список строк с комбинациями скобок.
     */
    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n); // Вызов вспомогательной функции backtrack для генерации комбинаций скобок
        return result;
    }


    /**  --2.5--
     * Вспомогательная рекурсивная функция для генерации комбинаций скобок.
     * Добавляет правильные комбинации в список result.
     */
    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == 2 * max) {
            result.add(current); // Добавляет комбинацию скобок в список, если достигнута нужная длина
            return;
        }
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max); // Рекурсивный вызов для добавления открывающей скобки
        }
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max); // Рекурсивный вызов для добавления закрывающей скобки
        }
    }


    /**  --3--
     * Функция, которая генерирует все возможные бинарные комбинации длины n,
     * в которых не может быть соседствующих нулей.
     * Возвращает строку с бинарными комбинациями.
     */
    public static String binarySystem(int n) {
        List<String> combinations = new ArrayList<>();
        backtrack("", n, combinations); // Генерирует бинарные комбинации и добавляет их в список
        return Arrays.toString(combinations.toArray()); // Возвращает строковое представление массива комбинаций
    }


    /**  --3.5--
     * Вспомогательная рекурсивная функция для генерации бинарных комбинаций.
     * Добавляет комбинации в список combinations.
     */
    public static void backtrack(String combination, int n, List<String> combinations) {
        if (combination.length() == n) {
            combinations.add(combination); // Добавляет комбинацию в список, если достигнута нужная длина
        } else {
            if (!combination.endsWith("0")) {
                backtrack(combination + "0", n, combinations); // Рекурсивный вызов для добавления "0", если предыдущий символ не "0"
            }
            backtrack(combination + "1", n, combinations); // Рекурсивный вызов для добавления "1"
        }
    }


    /** --4--
     * Функция, которая принимает строку и возвращает длину самого длинного последовательного ряда
     * в этой строке. Возвращает самый длинный последовательный ряд в виде строки.
     */
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

    /** --5--
     * Функция, которая принимает строку и подсчитывает количество идущих подряд символов,
     * заменяя соответствующим числом повторяющиеся символы.
     */
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

    /** --6--
     * Функция, принимающая положительное целое число в строковом формате,
     * не превышающее 1000, и возвращающую его целочисленное представление.
     */
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

    /** --7--
     * Функция, принимающая строку цифр, выполняющую поиск
     * подстроки максимальной длины с уникальными элементами.
     */
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

    /** --8--
     * Функция - поисковик наименьшего матричного пути.
     */
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

    /** --9--
     * Функция, принимающая строку, содержащую числа внутри слов.
     * Эти числа представляют расположение слова для новой строящейся строки.
     */
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

    /** --10--
     * Функция, принимающая два числа, которая делает второе число
     * максимально возможным за счет замены своих элементов элементами первого числа.
     */
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
