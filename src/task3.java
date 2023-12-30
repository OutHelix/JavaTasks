import java.util.ArrayList;
import java.util.Arrays;

public class task3 {
    public static void main(String[] args) {
        System.out.println(replaceVowels("apple"));
        System.out.println(stringTransform("hello"));
        System.out.println(doesBlockFit(1, 3, 5, 4, 5));
        System.out.println(numCheck(52));
        System.out.println(countRoots(new int[] {1, -3, 2}));
        System.out.println(salesData(new String[][]{{"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop2", "Shop3", "Shop4"}, {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}}));
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(waveForm(new int[] {3, 2, 4, 2, 7, 5}));
        System.out.println(commonVovel("Hello world"));
        System.out.println(commonVovel("Actions speak louder than words"));
        System.out.println(dataScience(new int[][] {{1, 2, 3, 4, 5},{6, 7, 8, 9, 10},{5, 5, 5, 5, 5},{7, 4, 3, 14, 2},
                {1, 0, 11, 10, 1}}));
    }

    public static String replaceVowels(String input) {
        return input.toLowerCase().replaceAll("[aeioyu]", "*");
    }

    public static String stringTransform(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String str_char = String.valueOf(input.charAt(i));
            if (i < input.length() - 1 && input.charAt(i) == input.charAt(i + 1)) {
                // Если символы подряд одинаковые, добавляем 'Double' и символ в верхнем регистре
                output.append("Double").append(str_char.toUpperCase());
                i++;
            } else {
                // Иначе добавляем символ без изменений
                output.append(str_char);
            }
        }
        return output.toString(); // Возвращаем преобразованную строку
    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        int s = w * h; // Вычисляем площадь отверстия
        // Проверяем, помещается ли блок по каждой из сторон отверстия
        return a * b <= s || b * c <= s || c * a <= s;
    }

    public static boolean numCheck(int input) {
        int num, sumSquare = 0;
        int doubleInput = input;

        // Перебираем цифры числа и считаем сумму квадратов
        while (input > 0) {
            num = input % 10; // Получаем последнюю цифру числа
            sumSquare += num * num; // Добавляем квадрат цифры к сумме
            input /= 10; // Переходим к следующей цифре числа
        }

        return sumSquare % 2 == doubleInput % 2; // Возвращаем результат сравнения четности суммы квадратов и исходного числа
    }

    public static int countRoots(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];
        int D = b * b - 4 * a * c;
        if (D < 0) {return 0;}
        if (D == 0) {return 1;}
        return 2;
    }

    public static ArrayList<String> salesData(String[][] all) {
        ArrayList<String> products = new ArrayList<>();
        int maxShops = 0;

        // Находим максимальное количество магазинов, в которых продавали товары
        for (String[] shops : all) {
            if (shops.length > maxShops) {
                maxShops = shops.length;
            }
        }

        // Добавляем в список продукты, проданные в магазинах с максимальным количеством
        for (String[] shops : all) {
            if (shops.length == maxShops) {
                products.add(shops[0]); // Добавляем первый элемент (продукт) из каждого массива магазина
            }
        }
        return products; // Возвращаем список продуктов
    }

    public static boolean validSplit(String input) {
        String[] splited = input.split(" "); // Разбиваем строку на слова по пробелу
        int counter = 0;

        // Проверяем, можно ли разбить строку так, чтобы каждое слово начиналось с последней буквы предыдущего слова
        for (int i = 0; i <= splited.length - 2; i++) {
            if (splited[i].charAt(splited[i].length() - 1) == splited[i + 1].charAt(0)) {
                counter += 1; // Увеличиваем счетчик, если последняя буква одного слова равна первой букве следующего
            }
        }
        return counter == splited.length - 1; // Проверяем, все ли слова начинаются с последней буквы предыдущего
    }

    public static boolean waveForm(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            // Проверяем, соответствует ли элемент массива условиям волнообразной последовательности
            if (!(arr[i - 1] > arr[i] && arr[i] < arr[i + 1] || arr[i - 1] < arr[i] && arr[i] > arr[i + 1])) {
                return false; // Если не соответствует, возвращаем false
            }
        }
        return true; // Если последовательность удовлетворяет условиям волнообразности, возвращаем true
    }

    public static char commonVovel(String input) {
        String symbols = "aeiou"; // Строка с гласными буквами
        int[] count = new int[5]; // Массив для подсчета вхождений каждой гласной

        // Проходим по каждому символу в строке и считаем количество вхождений каждой гласной
        for (char symbol : input.toCharArray()) {
            symbol = Character.toLowerCase(symbol); // Приводим символ к нижнему регистру
            if (symbols.indexOf(symbol) != -1) {
                count[symbols.indexOf(symbol)]++; // Увеличиваем счетчик для найденной гласной
            }
        }

        int max = 0;
        // Находим индекс гласной с максимальным количеством вхождений
        for (int i = 0; i < count.length - 1; i ++) {
            if (count[i] > count[max]) {
                max = i;
            }
        }
        return symbols.charAt(max); // Возвращаем наиболее часто встречающуюся гласную
    }

    public static String dataScience(int[][] input) {
        int maximum = 0;

        // Изменяем каждый элемент массива на среднее арифметическое элементов столбца
        for (int x = 0; x < input[0].length; x++) {
            for (int y = 0; y < input[0].length; y++) {
                maximum += input[y][x]; // Вычисляем сумму элементов столбца
            }
            input[x][x] = maximum / (input[0].length); // Заменяем элемент столбца на среднее значение
            maximum = 0; // Сбрасываем сумму для следующего столбца
        }

        return Arrays.deepToString(input); // Возвращаем измененный массив в виде строки
    }
}
