import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class task2 {
    public static void main(String[] args) {
        System.out.println(duplicateChars("Donald"));
        System.out.println(getInitials("Gamma Betta"));
        System.out.println(differenceEvenOdd(new int[]{44, 32, 86, 19}));
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 5}));
        System.out.println(Arrays.toString(indexMult(new int[]{3, 3, -2, 408, 3, 31})));
        System.out.println(reverse("The quick brown fox."));
        System.out.println(Tribonacci(7));
        System.out.println(pseudoHash(6));
        System.out.println(botHelper("Hello, I’m under the water, please help me"));
        System.out.println(isAnagram("listen", "silent"));
    }

    // Функция проверяет наличие повторяющихся символов в строке
    public static boolean duplicateChars(String word) {
        int len = word.length();
        for (int x = 0; x < len - 1; x++) {
            char y = word.toLowerCase().charAt(x);
            for (int z = 0; z < len; z++) {
                // Проверяем, есть ли символ, равный текущему символу (игнорируем регистр),
                // и он не находится на текущей позиции
                if ((y == word.charAt(z)) && (x != z)) {
                    return true; // Если повторяющийся символ найден, возвращаем true
                }
            }
        }
        return false; // Если повторяющихся символов нет, возвращаем false
    }

    public static String getInitials(String fio) {
        StringBuilder initials = new StringBuilder();
        String[] words = fio.split(" ");
        for (String word : words) {
            initials.append(Character.toUpperCase(word.charAt(0)));
        }
        return initials.toString();
    }
    // Функция возвращает разницу между суммой четных и нечетных чисел массива
    public static int differenceEvenOdd(int[] array) {
        int chet = 0;
        int nechet = 0;
        for (int num : array) {
            if (num % 2 == 0) {
                chet += num; // Суммируем четные числа
            } else {
                nechet += num; // Суммируем нечетные числа
            }
        }
        return Math.max(chet, nechet) - Math.min(chet, nechet); // Возвращаем разницу между суммами
    }

    // Функция проверяет наличие числа равного среднему арифметическому в массиве
    public static boolean equalToAvg(int[] arr) {
        int max = 0;
        for (int num : arr) {
            max += num;
        }
        double middle = (double) max / arr.length; // Вычисляем среднее арифметическое
        for (int num : arr) {
            if (num == middle) {
                return true; // Если есть элемент равный среднему арифметическому, возвращаем true
            }
        }
        return false; // Если такого элемента нет, возвращаем false
    }

    // Функция возвращает массив, в котором каждое число умножено на индекс этого числа
    public static int[] indexMult(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * i; // Каждый элемент массива умножается на свой индекс
        }
        return arr; // Возвращается массив
    }

    // Функция возвращает строку в обратном порядке
    public static String reverse(String text) {
        StringBuilder reversed = new StringBuilder();
        for (int i = text.length() - 1; i != 0; i--) {
            reversed.append(text.charAt(i)); // Добавляем символы в обратном порядке в StringBuilder
        }
        return reversed.toString(); // Возвращаем строку
    }

    public static int Tribonacci(int num) {
        if (num == 0 || num == 1) {
            return 0; // Если num равно 0 или 1, возвращаем 0
        } else if (num == 2) {
            return 1; // Если num равно 2, возвращаем 1
        }
        int a = 0, b = 0, c = 0, d;
        for (int i = 3; i <= num - 1; i++) {
            d = a + b + c; // Вычисляем следующий элемент последовательности
            a = b; // Обновляем значения для следующей итерации
            b = c;
            c = d;
        }
        return c; // Возвращаем последний элемент последовательности
    }

    // Функция генерирует квази-хэш заданной длины
    public static String pseudoHash(int len) {
        String hashString = "abcdef0123456789";
        Random rand = new Random();
        StringBuilder hash = new StringBuilder();

        if (len <= 0) {
            return ""; // Если длина меньше или равна 0, возвращаем пустую строку
        } else {
            for (int i = 0; i <= len - 1; i++) {
                int num = rand.nextInt(hashString.length());
                hash.append(hashString.charAt(num)); // Добавляем случайный символ к строке
            }
        }
        return hash.toString(); // Возвращаем сгенерированную строку
    }

    // Функция ищет слово "help" в строке
    public static String botHelper(String input) {
        String[] words = input.split(" ");
        for (String word : words) {
            if (Objects.equals(word.toLowerCase(), "help")) {
                return "Calling for a staff member"; // Если найдено слово "help", возвращаем соответствующий ответ
            }
        }
        return "Keep waiting"; // Если слово "help" не найдено, возвращаем другой ответ
    }

    // Функция проверяет, являются ли две строки анаграммами
    public static boolean isAnagram(String text1, String text2) {
        text1 = text1.toLowerCase().replaceAll("\\s", "");
        text2 = text2.toLowerCase().replaceAll("\\s", "");

        if (text1.length() != text2.length()) {
            return false; // Если длины строк отличаются, они не могут быть анаграммами
        }

        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        Arrays.sort(chars1); // Сортируем символы в строках
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2); // Возвращаем результат сравнения отсортированных строк
    }

}
