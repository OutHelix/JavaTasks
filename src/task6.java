import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class task6 {
    public static void main(String[] args) {
        // & 1
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        // * worldevolvesin

        // & 2
        System.out.println(collect("intercontinentalisationalism", 6));
        // * ["ationa", "interc", "ntalis", "ontine"]

        // & 3
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        // * yowmledrovlvsnieesrh

        // & 4
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 5, 15}, 45)));
        // * [9, 5]

        // & 5
        System.out.println(Arrays.toString(isExact(6)));
        // * [6, 3]

        // & 6
        System.out.println(fractions("0.(6)"));
        // * "2/3"

        // & 7
        System.out.println(pilish_string("33314444"));
        // *  333 1 4444

        // & 8
        System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)"));
        // *  -17

        // & 9
        System.out.println(isValid("aabbcd"));
        // *  NO

        // & 10
        System.out.println(findLCS("abcd", "bd"));
        // *  "bd"
    }

    // -- 1 --

    public static String hiddenAnagram(String a, String b) {
        a = a.toLowerCase().replaceAll("[^a-z]", "");       // приводят строки a и b к нижнему регистру и удаляют все символы, которые не являются буквами алфавита.
        b = b.toLowerCase().replaceAll("[^a-z]", "");       // Таким образом, мы убираем пробелы, знаки препинания и прочие символы, оставляя только буквы.

        int[] setB = new int[26]; // 26 букв
        for (char c : b.toCharArray()) setB[c - 97]++; // проходимся по массиву и увеличиваем значение для соотв буквы

        for (int i = 0; i <= a.length() - b.length(); i++) {
            int[] setA = new int[26];
            // для хранения букв в подстроке a текущей длины, равной длине строки b.
            for (char c : a.substring(i, i + b.length()).toCharArray()) setA[c - 97]++; // заполняет временный массив setA для текущей подстроки a
            if (Arrays.equals(setA, setB)) { // если массивы равны возвращаем подстроку
                return a.substring(i, i + b.length());
            }
        }
        return "notfound";
    }

    // -- 2 --

    public static List<String> collect(String str, int n) {
        if (str.length() < n) {
            return new ArrayList<>();
        } else {
            List<String> list = new ArrayList<>();
            list.add(str.substring(0, n));
            list.addAll(collect(str.substring(n), n)); //рекурсивно добавляем
            return list.stream().sorted().collect(Collectors.toList());
        }
    }

    // -- 3 --

    public static String nicoCipher(String message, String key) {
        int keyLength = key.length(); //длина ключа
        int extraSpaces = (keyLength - (message.length() % keyLength)) % keyLength; // вычисляет количество дополнительных пробелов, которые необходимо добавить к сообщению

        message += new String(new char[extraSpaces]).replace("\0", " "); // добавляем дополнительные пробелы к сообщению.

        Integer[] order = new Integer[keyLength];
        for (int i = 0; i < keyLength; i++) { // заполняем массив
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Character.compare(key.charAt(a), key.charAt(b))); // сортируем порядок столбцов

        char[] result = new char[message.length()];
        for (int i = 0; i < message.length(); i++) { // проходемся по всем символам
            int columnIndex = order[i % keyLength]; // индекс столбца
            int rowIndex = i / keyLength; // индекс строки
            result[i] = message.charAt(rowIndex * keyLength + columnIndex); // записываем символ
        }

        return new String(result);
    }

    // -- 4 --

    public static int[] twoProduct(int[] arr, int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int m : arr) {
            int target = n / m; // Вычисляем второе число, которое нужно для произведения n
            if (n % m == 0 && set.contains(target)) // Проверяем, делится ли n на текущее число m и существует ли в HashSet числовая пара для произведения n
                return new int[] { target, m }; // Если да, возвращаем числовую пару
            set.add(m); // Добавляем текущее число m в HashSet
        }
        return new int[] {}; // Если не найдено соответствующей пары, возвращаем пустой массив
    }

    // -- 5 --

    public static int[] isExact(int f, int m, int n) {
        return (f < n) ? isExact(f * (m + 1), m + 1, n) : new int[] { f, m };
        // Если текущее значение факториала f меньше n, функция вызывает саму себя с увеличенными значениями f и m + 1, чтобы продолжить увеличивать факториал.
    }

    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);
        return (res[0] == n) ? res : new int[] {};
        // Равен ли рассчитанный факториал res[0] числу n? Если да, возвращает массив из значений факториала и m, иначе возвращает пустой массив {}.
    }

    // -- 6 --

    public static String fractions(String frac) {
        int startBracket = frac.indexOf('('); // определяет индекс открывающей скобки
        if (startBracket != -1) {  // если есть скоба
            String repeating = frac.substring(startBracket + 1, frac.length() - 1); // извлекает повторяющуюся часть дроби из скобок
            frac = frac.substring(0, startBracket) + repeating.repeat(9 - repeating.length()); // продлевает до 9
        }
        double a = Double.parseDouble(frac); // преобразуем строку в число с плав точкой
        int div = 2;
        while (Math.ceil(a * div) - a * div > 0.000001) div++;
        // выполняет цикл, чтобы найти знаменатель дроби, увеличивая делитель div до тех пор, пока разница между округленным значением не будет меньше 0.000001.
        return (int) Math.ceil(a * div) + "/" + div;
    }

    // -- 7 --

    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", ""); // строка с пи
        int piIndex = 0, strIndex = 0;

        while (strIndex < str.length()) {
            int p = pi.charAt(piIndex++) - '0'; // извлекаю цифру
            int n = Math.min(p, str.length() - strIndex);
            // Определяет длину текущего слова, которое будет добавлено к результату. Это минимум между текущей цифрой числа Пи и оставшейся длиной входной строки.
            res += str.substring(strIndex, strIndex + n); // добавляет к результату слово, взятое из входной строки, соответствующее текущей цифре числа Пи.
            strIndex += n; // увеличиваем индекс входной строки
            if (strIndex < str.length()) res += ' '; // доавляем пробел
            else
                while (n++ < p) res += res.charAt(res.length() - 1);
                // если длина текущего слова меньше, чем соответствующая цифра числа Пи, повторяет последний символ слова, чтобы сделать его равным требуемой длине.
        }
        return res;
    }

    // -- 8 --

    public static String generateNonconsecutive(String str) {
        // Регулярное выражение для сопоставления операторов и операндов
        Pattern part = Pattern.compile("^( [\\-+*/] )*(\\()*(-*\\d+)(\\))*");
        boolean start = true;
        Node currentNode = new Node("+"); // Создание корневого узла с оператором "+"
        currentNode.setLeft(new Node("0")); // Установка левого узла с операндом "0"
        int parLevel = 0; // Переменная для отслеживания уровня скобок

        for (int i = 0; i < str.length();) {
            Matcher matcher = part.matcher(str).region(i, str.length()); // Создание Matcher для текущей подстроки

            // Если не найдено совпадение с регулярным выражением, выход с ошибкой синтаксиса
            if (!matcher.find()) {
                System.out.println("Syntax error");
                return null;
            }

            // Проверка наличия оператора в текущем сопоставлении
            if (matcher.group(1) == null) {
                // Если это не начальное сопоставление, выход с ошибкой синтаксиса
                if (!start) {
                    System.out.println("Syntax error");
                    return null;
                }
                // Установка правого узла текущего узла с числом из сопоставления
                currentNode.setRight(new Node(matcher.group(3)));
                start = false;
                i = matcher.end();
                // Увеличение уровня скобок, если есть открывающая скобка
                if (!(matcher.group(2) == null)) {
                    parLevel++;
                }
                continue;
            }

            Node opNode = new Node(String.valueOf(matcher.group(1).charAt(1))); // Создание узла оператора
            Node numNode = new Node(matcher.group(3)); // Создание узла операнда
            opNode.setRight(numNode); // Установка правого узла для оператора

            // Обработка операций с учетом уровня скобок
            if (parLevel > 0) {
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/') {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                } else {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                    currentNode = opNode;
                }
            }
            if (parLevel == 0) {
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/') {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                } else {
                    opNode.setLeft(currentNode);
                    currentNode = opNode;
                }
            }

            // Увеличение уровня скобок, если есть открывающая скобка
            if (!(matcher.group(2) == null)) {
                currentNode = opNode;
                parLevel++;
            }
            // Уменьшение уровня скобок, если есть закрывающая скобка
            if (!(matcher.group(4) == null)) {
                if (!(currentNode.root == null)) {
                    currentNode = currentNode.root;
                }
                parLevel--;
            }
            i = matcher.end(); // Перемещение к следующему сопоставлению
        }

        try {
            // Вычисление значения корневого узла
            double res = currentNode.getUltimateRoot().computeNode();
            // Возврат значения, округленного до целого числа, если оно целое
            if (res % 1 == 0) {
                return String.valueOf((int) res);
            }
            return String.valueOf(res); // Возврат значения с плавающей точкой
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Обработка и вывод сообщения об ошибке
            return null;
        }
    }

    // Класс Node для представления узлов в дереве выражения
    static class Node {
        // Поля класса Node
        String value;
        Node left;
        Node root;
        Node right;

        // Методы класса Node

        // Установка левого узла и установка текущего узла в качестве его родителя
        public void setLeft(Node node) {
            this.left = node;
            node.root = this;
        }

        // Установка правого узла и установка текущего узла в качестве его родителя
        public void setRight(Node node) {
            this.right = node;
            node.root = this;
        }

        // Получение конечного корневого узла для текущего узла
        public Node getUltimateRoot() {
            if (root != null) {
                return root.getUltimateRoot();
            }
            return this;
        }

        // Вычисление значения узла (рекурсивно)
        public double computeNode() throws Exception {
            // Если узел содержит оператор (+, -, *, /)
            if ("+-*/".contains(value)) {
                // Вычисление значения для левого и правого поддеревьев
                double num1 = left.computeNode();
                double num2 = right.computeNode();

                // Выполнение операции в соответствии с оператором
                switch (value) {
                    case "+":
                        return num1 + num2;
                    case "-":
                        return num1 - num2;
                    case "*":
                        return num1 * num2;
                    case "/":
                        // Обработка деления на ноль
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        return num1 / num2;
                    default:
                        throw new Exception("Syntax error");
                }
            }
            // Если узел содержит число, преобразовать его и вернуть
            return Double.parseDouble(value);
        }

        // Конструктор класса Node
        Node(String value) {
            this.value = value;
            this.root = null;
            this.right = null;
            this.left = null;
        }
    }



    // -- 9 --

    public static String isValid(String str) {
        int[] charCounts = new int[26]; // Создание массива для подсчета частоты символов
        for (char c : str.toCharArray()) {
            charCounts[c - 'a']++; // Увеличение счетчика для символа c | код a = 97
        }
        int prevCount = -1; // Предыдущее количество встреч символов
        int removals = 0; // Количество символов, которые необходимо удалить
        for (int count : charCounts) {
            if (count > 0) {
                if (prevCount == -1) {
                    prevCount = count; // Инициализация prevCount, если еще не инициализирован
                } else if (prevCount != count) {
                    removals += Math.abs(prevCount - count); // Подсчет несоответствий частот символов
                    if (removals > 1) return "NO"; // Если необходимо удалить более одного символа, возвращается "NO"
                }
            }
        }
        return "YES"; // Если строка удовлетворяет условиям, возвращается "YES"
    }

    // -- 10 --

    public static String findLCS(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1]; // Создание массива для хранения длин LCS
        StringBuilder lcs = new StringBuilder(); // Создание объекта StringBuilder для хранения LCS

        // Начало заполнения массива dp для поиска LCS
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // Если символы равны, увеличиваем длину LCS
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Иначе выбираем максимальное значение из двух предыдущих
                }
            }
        }
        // Конец заполнения массива dp

        int i = s1.length(), j = s2.length();

        // Нахождение самой длинной общей подпоследовательности
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1)); // Если символы совпадают, добавляем его в LCS
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--; // Иначе двигаемся к ячейке с более большим значением в массиве dp
            } else {
                j--;
            }
        }

        return lcs.reverse().toString(); // Возвращаем LCS в обратном порядке (так как мы добавляли символы в конец)
    }
}