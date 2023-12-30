import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class task5 {
    public static void main(String[] args) {
        System.out.println("Task 5.0: " + sameLetterPattern("abab", "cdcd")); // 1
        System.out.println("Task 5.1: " + spiderVsFly("H3", "E2")); //2
        System.out.println("Task 5.2: " + digitsCount(4666)); // 3
        System.out.println("Task 5.3: " + totalPoints(new String[] { "trance", "recant" }, "recant")); // 4
        System.out.println("Task 5.4: " + sumsUp(new int[] { 1, 6, 5, 4, 8, 2, 3, 7 })); // 5
        System.out.println("Task 5.5: " + takeDownAverage(new String[] { "95%", "83%", "90%", "87%", "88%", "93%" })); // 6
        System.out.println("Task 5.6: " + caesarCipher("encode", "hello world", 3)); // 7
        System.out.println("Task 5.7: " + setSetup(7, 3)); // 8
        System.out.println("Task 5.8: " + timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // 9
        System.out.println("Task 5.9: " + isNew(123));
    }

    /* Функция sameLetterPattern(String s1, String s2) определяет, имеют ли две строки s1 и s2 о
    динаковые последовательности букв в одном и том же порядке. Она использует хэш-карты для
    отслеживания соответствия символов в обеих строках.
     */

    // -- 1 --
    public static boolean sameLetterPattern(String s1, String s2) {
        // Если длина строки s1 не равна длине строки s2, возвращается значение false.
        if (s1.length() != s2.length()) {
            return false;
        }
        // Создаются хэш-карты s1map и s2map для хранения символов из строк s1 и s2 в качестве ключей и их индексов в качестве значений.
        HashMap<Character, Integer> s1map = new HashMap<>();
        HashMap<Character, Integer> s2map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            // Если значение символа из строки s1 в s1map не равно значению символа из строки s2 в s2map, возвращается значение false.
            if (s1map.get(s1.charAt(i)) != s2map.get(s2.charAt(i))) {
                return false;
            }
            // Присваиваются значения символов из строк s1 и s2 в соответствующие хэш-карты.
            s1map.put(s1.charAt(i), i);
            s2map.put(s2.charAt(i), i);
        }
        // Если ни одно из условий не нарушено, возвращается значение true.
        return true;
    }


    /* Метод spiderVsFly(String spider, String fly) определяет кратчайший путь паука по паутине к мухе.
   Паук и муха представлены строками с символами, обозначающими радиальное положение (от A до H) и кольцо.
   Метод использует циклы и условные операторы для определения пути паука.
*/

    // Метод для получения следующей радиали по часовой стрелке
    static char getNextRadial(char radial) {
        if (radial == 'H') return 'A'; // Если радиаль H, следующая - A
        return (char) (radial + 1); // Иначе, возвращаем следующую радиаль
    }

    // Метод для получения предыдущей радиали по часовой стрелке
    static char getPrevRadial(char radial) {
        if (radial == 'A') return 'H'; // Если радиал A, предыдущая - H
        return (char) (radial - 1); // Иначе, возвращаем предыдущую радиаль
    }

    // Метод для вычисления кратчайшего пути от паука до мухи
    // -- 2 --
    static String spiderVsFly(String spider, String fly) {
        char spiderRing = spider.charAt(1); // Кольцо, на котором находится паук
        char flyRing = fly.charAt(1); // Кольцо, на котором находится муха
        char spiderRadial = spider.charAt(0); // Радиаль, на которой находится паук
        char flyRadial = fly.charAt(0); // Радиаль, на которой находится муха

        int ringDifference = Math.abs(spiderRing - flyRing); // Разница между кольцами
        ArrayList<String> path = new ArrayList<>(); // Создание списка для хранения пути

        // Добавление пути по радиалям до того же кольца, что у мухи
        for (int i = 0; i < ringDifference; i++) {
            if (spiderRing < flyRing) {
                path.add(spiderRadial + "" + spiderRing); // Добавляем путь по радиалям до кольца мухи
                spiderRing++; // Переходим к следующему кольцу
            } else {
                path.add(spiderRadial + "" + spiderRing); // Добавляем путь по радиалям до кольца мухи
                spiderRing--; // Переходим к предыдущему кольцу
            }
        }

        // Добавление пути по радиалям до радиали мухи
        while (spiderRadial != flyRadial) {
            if (spiderRadial < flyRadial) {
                path.add(spiderRadial + "" + spiderRing); // Добавляем путь по радиалям до радиали мухи
                spiderRadial = getNextRadial(spiderRadial); // Переходим к следующей радиали
            } else {
                path.add(spiderRadial + "" + spiderRing); // Добавляем путь по радиалям до радиали мухи
                spiderRadial = getPrevRadial(spiderRadial); // Переходим к предыдущей радиали
            }
        }

        // Добавление пути от паука до мухи на одной радиали
        while (spiderRing != flyRing) {
            if (spiderRing < flyRing) {
                path.add(spiderRadial + "" + spiderRing); // Добавляем путь от паука до мухи на одной радиали
                spiderRing++; // Переходим к кольцу мухи
            } else {
                path.add(spiderRadial + "" + spiderRing); // Добавляем путь от паука до мухи на одной радиали
                spiderRing--; // Переходим к кольцу мухи
            }
        }

        path.add(fly); // Добавляем конечную позицию мухи в путь

        return String.join("-", path); // Формируем строку из списка пути, разделяя элементы дефисами
    }
    //2

    /* Метод digitsCount(long number) используется рекурсивно для определения количества цифр в заданном числе.
    Он осуществляет деление числа на 10 до тех пор, пока число не достигнет значения 0, и подсчитывает количество
    рекурсивных вызовов.
     */
    // -- 3 --
    public static int digitsCount(long num) {
        if (num < 10) {
            return 1; // Если число меньше 10, возвращается 1, что означает наличие одной цифры
        }
        return 1 + digitsCount(num / 10); // Иначе добавляется 1 и вызывается рекурсивно для числа, поделенного на 10
    }


    /* Метод totalPoints(String[] arr, String word) вычисляет общее количество очков, которые можно набрать из списка
    слов arr, используя заданное слово word. Каждая буква в слове arr оценивается определенным количеством очков,
    зависящим от длины слова и уникальности букв в слове.
     */
    // -- 4 --
    public static int totalPoints(String[] arr, String word) {
        int res = 0; // Инициализируем переменную для хранения общего количества очков
        for (int i = 0; i < arr.length; i++) {
            int score = arr[i].length() == 6 ? 54 : arr[i].length() - 2; // Вычисляем количество очков для текущего слова
            for (int j = 0; j < arr[i].length(); j++) { // Проходим по символам текущего слова arr[i]
                if (arr[i].indexOf(arr[i].charAt(j)) == -1) { // Если символ не найден (не уникален)
                    break; // Прерываем цикл, так как слово не уникальное
                }
            }
            res += score; // Увеличиваем общее количество очков на score для текущего слова arr[i]
        }
        return res; // Возвращаем общее количество набранных очков
    }


    /* Метод sumsUp осуществляет поиск пар чисел в массиве arr, суммируясь до значения 8. Если такая пара найдена, она
     добавляется в список res. Для этого используется Map<Integer, Integer> для отслеживания уже просмотренных чисел.
     Для каждого числа в массиве arr проверяется сумма числа и ключа из Map: если она равна 8, то пара чисел добавляется
      в список. Числа в паре добавляются в порядке возрастания.
     */
    // -- 5 --
    public static ArrayList<ArrayList<Integer>> sumsUp(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>(); // Список списков для хранения пар чисел
        int indexRes = 0; // Переменная для отслеживания индекса в res

        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // Map для хранения чисел и их количества

        for (int i = 0; i < arr.length; i++) {
            boolean check = true;
            for (Integer key : map.keySet()) { // Перебор ключей в Map
                if (key + arr[i] == 8) { // Если сумма ключа и текущего элемента arr[i] равна 8
                    ArrayList<Integer> cl = new ArrayList<Integer>(); // Создаем новый список
                    cl.add(0, Math.min(key, arr[i])); // Добавляем минимальное число в пару
                    cl.add(1, Math.max(key, arr[i])); // Добавляем максимальное число в пару
                    res.add(indexRes, cl); // Добавляем список res по индексу indexRes
                    indexRes++; // Увеличиваем индекс на 1
                    map.remove(key, 1); // Удаляем ключ из Map
                    check = false; // Сбрасываем флаг
                    break;
                }
            }
            if (check)
                map.put(arr[i], 1); // Добавляем новый ключ в Map со значением 1
        }
        return res; // Возвращаем список пар чисел
    }

    /* Метод takeDownAverage вычисляет среднее значение чисел, представленных в виде строк с символом процента.
    Затем вычисляется разность между средним значением и 5, умноженная на количество чисел в массиве. Полученное
    значение округляется и приводится к строке с символом процента.
     */
    // -- 6 --
    public static String takeDownAverage(String[] arr) {
        if (arr.length == 0)
            return "0%"; // Проверяем длину массива, возвращаем "0%", если массив пустой
        double sum = 0; // Переменная для суммирования общего количества процентов из массива arr
        for (String str : arr) {
            int number = Integer.parseInt(str.split("%")[0]); // Каждый элемент разделяется на число и символ "%" и преобразуется в тип int
            sum += number; // Добавляем число к сумме
        }
        double avg = sum / arr.length; // Вычисляем среднее значение avg путем деления суммы на количество элементов
        double res = (arr.length + 1) * (avg - 5) - sum; // Вычисляем оценку, которую необходимо получить в следующий раз
        return Integer.toString((int) Math.round(res)) + "%"; // Возвращается строка, состоящая из округленной до целого числа оценки и символа "%"
    }


    /* Метод caesarCipher выполняет шифрование или дешифрование строки str по алгоритму Цезаря с использованием
     заданного сдвига shift. Направление шифрования или дешифрования определяется параметром mode.
     */
    // -- 7 --
    public static String caesarCipher(String mode, String message, int shift) {
        if (Objects.equals(mode, "decode"))
            shift *= -1; // Если режим "decode", изменяем направление сдвига
        message = message.toUpperCase(); // Преобразуем строку в верхний регистр
        String res = "";
        for (int i = 0; i < message.length(); i++) {
            int el = message.codePointAt(i); // Получаем числовое значение символа
            int number = el + shift; // Применяем сдвиг
            if (el >= 65 && el <= 90) { // Проверяем, находится ли символ в диапазоне от A до Z
                if (number < 65) { // Если результат сдвига меньше кода символа 'A'
                    number = 91 - (65 - number); // Возвращаемся в диапазон от 'A' до 'Z'
                } else if (number > 90) { // Если результат сдвига больше кода символа 'Z'
                    number = 64 + (number - 90); // Возвращаемся в диапазон от 'A' до 'Z'
                }
                res += Character.toString((char) number); // Преобразуем число обратно в символ и добавляем к результату
            } else {
                res += Character.toString((char) el); // Если символ не в диапазоне от 'A' до 'Z', добавляем его без изменений
            }
        }
        return res; // Возвращаем результат шифрования/дешифрования
    }


    /* Метод factorial рекурсивно вычисляет факториал числа n.
       Метод setSetup вычисляет количество возможных комбинаций из n элементов по k элементов, используя факториальную
       функцию из предыдущего метода.
    */
    public static int factorial(int n) {
        if (n == 0) {
            return 1; // Возвращаем 1, если n равно 0
        }
        return n * factorial(n - 1); // Рекурсивно вычисляем факториал числа n
    }
    // -- 8 -
    public static int setSetup(int n, int k) {
        if (n < k) {
            return 0; // Если n меньше k, возвращаем 0, так как невозможно составить комбинации
        }
        return factorial(n) / factorial(n - k); // Вычисляем количество возможных комбинаций из n элементов по k элементов
    }


    /* В статическом блоке кода инициализируется Map cityOffsets, где ключами являются названия городов, а значениями -
    смещения относительно времени в Лондоне. Затем в методе timeDifference происходит вычисление разницы во времени
    между городами cityA и cityB на основе переданной временной метки timestamp.
     */

    private static final Map<String, Duration> cityOffsets = new HashMap<>(); // Создается статический словарь cityOffsets

    static {
        cityOffsets.put("Los Angeles", Duration.ofHours(-8));
        cityOffsets.put("New York", Duration.ofHours(-5));
        cityOffsets.put("Caracas", Duration.ofHours(-4).plusMinutes(-30));
        cityOffsets.put("Buenos Aires", Duration.ofHours(-3));
        cityOffsets.put("London", Duration.ofHours(0));
        cityOffsets.put("Rome", Duration.ofHours(1));
        cityOffsets.put("Moscow", Duration.ofHours(3));
        cityOffsets.put("Tehran", Duration.ofHours(3).plusMinutes(30));
        cityOffsets.put("New Delhi", Duration.ofHours(5).plusMinutes(30));
        cityOffsets.put("Beijing", Duration.ofHours(8));
        cityOffsets.put("Canberra", Duration.ofHours(10));
    }

    // -- 9 --
    public static String timeDifference(String cityA, String timestamp, String cityB) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.US); // Создается объект с указанным шаблоном
        // Из строки timestamp методом parse создается объект dateTimeA класса LocalDateTime с использованием созданного ранее formatter
        LocalDateTime dateTimeA = LocalDateTime.parse(timestamp, formatter);

        Duration offsetA = cityOffsets.getOrDefault(cityA, Duration.ZERO); // Получает значения из словаря, если нет - присваивает Duration.ZERO
        LocalDateTime dateTimeB = dateTimeA.plus(offsetA); // Добавляется смещение и получается новый объект

        Duration offsetB = cityOffsets.getOrDefault(cityB, Duration.ZERO);
        dateTimeB = dateTimeB.plus(offsetB);

        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"); // Создается новый объект
        return dateTimeB.format(newFormatter); // Форматируется в строку и возвращается эта строка
    }


    /* В методе isNew проверяется, является ли число num новым. Число считается новым, если каждая следующая цифра,
    начиная с первой, больше или равна предыдущей, за исключением нулевых цифр.
     */
    // -- 10 --
    public static boolean isNew(int num) {
        String str = String.valueOf(num); // Преобразуется число в строку
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) < str.charAt(0)) { // Если текущая цифра не равна нулю и меньше первой цифры строки
                return false; // Возвращается значение false, так как условие не выполнено
            }
        }
        return true; // Возвращается значение true, если каждая последующая цифра больше или равна предыдущей или равна нулю
    }

}