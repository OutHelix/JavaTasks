public class task1 {
    public static void main(String[] args) {
        // Примеры вызова функций и вывод результатов
        System.out.println(convert(5));
        System.out.println(fitCalc(15, 1));
        System.out.println(containers(3, 4, 2));
        System.out.println(triangleType(5, 5, 5));
        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(howManyItems(22.0, 1.4, 2.0));
        System.out.println(factorial(5));
        System.out.println(gcd(48, 18));
        System.out.println(ticketSaler(70, 1500));
        System.out.println(tables(5, 2));
    }

    // 1. Функция конвертации галлонов в литры
    public static double convert(int x) {
        return (double)x * 3.785;
    }

    // 2. Функция расчета сожженных калорий во время тренировки
    public static int fitCalc(int x, int y) {
        return x * y;
    }

    // 3. Функция подсчета общего количества товаров на складе
    public static int containers(int x, int y, int z) {
        return x * 20 + y * 50 + z * 100;
    }

    // 4. Функция определения типа треугольника по длинам его сторон
    public static String triangleType(int x, int y, int z) {
        if (x + y > z && x + z > y && y + z > x) {
            if (x == y && y == z) {
                return "equilateral"; // Равносторонний треугольник
            } else {
                return x != y && y != z && z != x ? "different-sided" : "isosceles"; // Разносторонний или равнобедренный
            }
        } else {
            return "not a triangle"; // Не треугольник
        }
    }

    // 5. Функция сравнения чисел с помощью тернарного оператора
    public static int ternaryEvaluation(int first, int second) {
        return first > second ? first : second;
    }

    // 6. Функция подсчета количества пододеяльников, которые можно сшить из определенной длины ткани
    public static int howManyItems(double fabricLength, double itemWidth, double itemLength) {
        return (int)(fabricLength / (itemWidth * itemLength * 2.0));
    }

    // 7. Функция для вычисления факториала числа
    public static int factorial(int num) {
        int output = 1;

        for(int i = 1; i <= num; ++i) {
            output *= i;
        }

        return output;
    }

    // 8. Функция для нахождения наибольшего общего делителя двух чисел
    public static int gcd(int first, int second) {
        return second == 0 ? first : gcd(second, first % second);
    }

    // 9. Функция для подсчета общей выручки от продажи билетов
    public static int ticketSaler(int tickets, int ticketNum) {
        return (int)((double)tickets * ((double)ticketNum - (double)ticketNum * 0.28));
    }

    // 10. Функция определения количества недостающих столов для размещения всех студентов
    public static int tables(int students, int tables) {
        int result = students - tables * 2;
        return Math.max(result, 0);
    }
}
