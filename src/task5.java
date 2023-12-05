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

    /* Метод sameLetterPattern(String s1, String s2) определяет, являются ли две строки s1 и s2 строками, в которых
    повторяется одна и та же буква в одном и том же порядке. Он использует хэш-карты для отслеживания соответствия
     символов в обеих строках.
     */
    // -- 1 --
    public static boolean sameLetterPattern(String s1, String s2)
    {
        if(s1.length() != s2.length()) { //если длина строки s1 не равна длине строки s2, то возвращает false.
            return false;
        }
        //HashMap - s1map и s2map, которые будут хранить символы строки s1 и s2 в качестве ключей и их индексы в качестве значений.
        HashMap<Character, Integer> s1map = new HashMap<>();
        HashMap<Character, Integer> s2map = new HashMap<>();

        for(int i = 0; i < s1.length(); i++) {
            //если значение с символом из строки s в smap не равно значению с символом из строки s2 в s2map, то метод возвращает false.
            if(s1map.get(s1.charAt(i)) != s2map.get(s2.charAt(i))) return false;
            s1map.put(s1.charAt(i), i);//если совпадают
            s2map.put(s2.charAt(i), i);
        }
        return true;
    }

    /* Метод spiderVsFly(String spider, String fly) определяет кратчайший путь паука на паутине к мухе.
    Паук и муха представлены в виде строк с символами, представляющими радиальное расположение (от A до H) и
    радиальное кольцо. Метод использует циклы и условные операторы для определения пути паука.
     */

    // Метод для получения следующей радиали в часовой стрелке
    static char getNextRadial(char radial) {
        if (radial == 'H') return 'A'; // Если радиаль H, то следующая - A
        return (char) (radial + 1); // Иначе, возвращаем следующую радиаль
    }

    // Метод для получения предыдущей радиали в часовой стрелке
    static char getPrevRadial(char radial) {
        if (radial == 'A') return 'H'; // Если радиал A, то предыдущая - H
        return (char) (radial - 1); // Иначе, возвращаем предыдущую радиаль
    }

    // Метод для вычисления кратчайшего пути от паука до мухи
    // -- 2 --
    static String spiderVsFly(String spider, String fly) {
        char spiderRing = spider.charAt(1); // Кольцо, на котором находится паук
        char flyRing = fly.charAt(1); // Кольцо, на котором находится муха
        char spiderRadial = spider.charAt(0); // Радиал, на которой находится паук
        char flyRadial = fly.charAt(0); // Радиал, на которой находится муха

        int ringDifference = Math.abs(spiderRing - flyRing); // Разница между кольцами
        ArrayList<String> path = new ArrayList<>(); // Создание списка для хранения пути

        // Добавление пути по радиалам до того же кольца, что у мухи
        for (int i = 0; i < ringDifference; i++) {
            if (spiderRing < flyRing) {
                path.add(spiderRadial + "" + spiderRing); // Добавляем путь по радиалам до кольца мухи
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

    /* Метод digitsCount(long number) рекурсивно определяет количество цифр в заданном числе. Он использует деление
    числа на 10 до тех пор, пока число не станет равным 0, и считает количество рекурсивных вызовов.
     */
    // -- 3 --
    public static int digitsCount(long num) {
        if (num < 10) {
            return 1;
        }
        return 1 + digitsCount(num / 10);
    }

    /* Метод totalPoints(String[] arr, String word) вычисляет общее количество очков, которые можно набрать из
    списка слов arr с использованием заданного слова word. Каждая буква в слове arr оценивается определенным
    количеством очков, которое зависит от длины слова и уникальности букв в слове.
     */
    // -- 4 --
    public static int totalPoints(String[] arr, String word) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int score = arr[i].length() == 6 ? 54 : arr[i].length() - 2; // вычисляем количество очков для текущего слова
            for (int j = 0; j < arr[i].length(); j++) { // проходим по символам текущего слова arr[i]
                if (arr[i].indexOf(arr[i].charAt(j)) == -1) { //если символ не найден
                    break;
                }
            }
            res += score; // увеличиваем общее количество очков на score для текущего слова arr[i]
        }
        return res;
    }

    /* В методе sumsUp происходит поиск пар чисел в массиве arr, которые в сумме дают 8. Если такая пара найдена,
    она добавляется в список res. Для этого используется Map<Integer, Integer> для хранения уже просмотренных чисел,
    и проверяется каждое число массива arr, если сумма числа и ключа из Map равна 8, то пара добавляется в список.
    Числа в паре добавляются в порядке возрастания.
     */
    // -- 5 --
    public static ArrayList<ArrayList<Integer>> sumsUp(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>(); // список списков для хранения пар чисел
        int indexRes = 0; // для отслеживания индекса в res

        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // для хранения чисел и их количества

        for (int i = 0; i < arr.length; i++) {
            boolean check = true;
            for (Integer key : map.keySet()) { //по ключам Map
                if (key + arr[i] == 8) { // eсли сумма ключа и текущего элемента arr[i] равна 8
                    ArrayList<Integer> cl = new ArrayList<Integer>(); // новый список
                    cl.add(0, Math.min(key, arr[i]));
                    cl.add(1, Math.max(key, arr[i]));
                    res.add(indexRes, cl); // добавляем список res по индексу indexRes
                    indexRes++; // увеличиваем индекс на 1
                    map.remove(key, 1); // удаление ключа из Map
                    check = false; //
                    break;
                }
            }
            if (check)
                map.put(arr[i], 1); // добавляем новый ключ в Map со значением 1
        }
        return res;
    }
    /* В методе takeDownAverage вычисляется среднее значение чисел, которые представлены в виде строк с символом процента.
    Затем вычисляется разность между средним значением и 5, умноженная на количество чисел в массиве.
    Полученное значение округляется и приводится к строке с символом процента.
     */
    // -- 6 --
    public static String takeDownAverage(String[] arr) {
        if (arr.length == 0)//проверяем длину массива
            return "0%";
        double sum = 0;//будет суммироватся общее количество процентов из массива arr
        for (String str : arr) {
            int number = Integer.parseInt(str.split("%")[0]);//каждый элемент разделяется с помощью метода split("%") на число и знак "%" и преобразуем в тип int
            sum += number;//добавляем
        }
        double avg = sum / arr.length;//вычисляется среднее значение avg путем деления суммы sum на длину массива arr
        double res = (arr.length + 1) * (avg - 5) - sum;//вычисляется оценка, которую необходимо получитья в следующий раз
        return Integer.toString((int) Math.round(res)) + "%";//возвращается строка, состоящая из округленной до целого числа оценки и символа "%"
    }

    /* В методе caesarCipher происходит шифрование или дешифрование строки str по алгоритму Цезаря с использованием
    заданного сдвига shift. В зависимости от значения параметра mode определяется направление шифрования или дешифрования.
     */
    // -- 7 --
    public static String caesarCipher(String mode, String message, int shift) {
        if (Objects.equals(mode, "decode"))
            shift *= -1;
        message = message.toUpperCase();
        String res = "";
        for (int i = 0; i < message.length(); i++) {
            int el = message.codePointAt(i); //числовое значение
            int number = el + shift;
            if (el >= 65 && el <= 90) { // проверка на нахождения символа от A до Z
                if (number < 65) { // Если результат сдвига меньше кода символа 'A'
                    number = 91 - (65 - number); // Возвращаемся в диапазон от 'A' до 'Z'
                } else if (number > 90) { // Если результат сдвига больше кода символа 'Z'
                    number = 64 + (number - 90); // Возвращаемся в диапазон от 'A' до 'Z'
                }
                res += Character.toString((char) number);
            } else {
                res += Character.toString((char) el);
            }
        }

        return res;
    } // 7

    /* В методе factorial рекурсивно вычисляется факториал числа n.
    В методе setSetup вычисляется количество возможных комбинаций из n элементов по k элементов, используя факториальную
    функцию из предыдущего метода.
     */
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    // -- 8 --
    public static int setSetup(int n, int k) {
        if (n < k) {
            return 0;
        }
        return factorial(n) / factorial(n - k);
    } // 8

    /* В статическом блоке кода инициализируется Map cityOffsets, где ключами являются названия городов,
    а значениями - смещения относительно времени в Лондоне. Затем в методе timeDifference происходит вычисление
    разницы во времени между городами cityA и cityB на основе переданной временной метки timestamp.
     */
    private static final Map<String, Duration> cityOffsets = new HashMap<>();//создается статический словарь cityOffsets

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.US);//объект с указанным шаблоном
        // Из строки timestamp методом parse собирает объект dateTimeA класса LocalDateTime с использованием созданного ранее formatter.
        LocalDateTime dateTimeA = LocalDateTime.parse(timestamp, formatter);

        Duration offsetA = cityOffsets.getOrDefault(cityA, Duration.ZERO);//получает значения из словаря, если нет то присваивает Duration.ZERO
        LocalDateTime dateTimeB = dateTimeA.plus(offsetA);//добовляется смещение и получается новый объект

        Duration offsetB = cityOffsets.getOrDefault(cityB, Duration.ZERO);
        dateTimeB = dateTimeB.plus(offsetB);

        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");//новый объект
        return dateTimeB.format(newFormatter);//форматируется в строку и выводится эта строка
    }

    /* В методе isNew проверяется, является ли число num новым. Число считается новым, если каждая следующая цифра,
    начиная с первой, больше или равна предыдущей, за исключением нулевых цифр.
     */
    // -- 10 --
    public static boolean isNew(int num) {
        String str = String.valueOf(num);//преобразуется в строку
        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i) != '0' && str.charAt(i) < str.charAt(0)) {//если текущий символ не равен нулю и меньше первого символа строки
                return false;//то возвращается значение false
            }
        }
        return true;
    }
}