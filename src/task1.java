public class task1 {
    public static void main(String[] args) {
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

    public static double convert(int x) {
        return (double)x * 3.785;
    }

    public static int fitCalc(int x, int y) {
        return x * y;
    }

    public static int containers(int x, int y, int z) {
        return x * 20 + y * 50 + z * 100;
    }

    public static String triangleType(int x, int y, int z) {
        if (x + y > z && x + z > y && y + z > x) {
            if (x == y && y == z) {
                return "isosceles";
            } else {
                return x != y && y != z && z != x ? "different-sided" : "equilateral";
            }
        } else {
            return "not a triangle";
        }
    }

    public static int ternaryEvaluation(int first, int second) {
        return first > second ? first : second;
    }

    public static int howManyItems(double fabricLength, double itemWidth, double itemLength) {
        return (int)(fabricLength / (itemWidth * itemLength * 2.0));
    }

    public static int factorial(int num) {
        int output = 1;

        for(int i = 1; i <= num; ++i) {
            output *= i;
        }

        return output;
    }

    public static int gcd(int first, int second) {
        return second == 0 ? first : gcd(second, first % second);
    }

    public static int ticketSaler(int tickets, int ticketNum) {
        return (int)((double)tickets * ((double)ticketNum - (double)ticketNum * 0.28));
    }

    public static int tables(int students, int tables) {
        int result = students - tables * 2;
        return Math.max(result, 0);
    }
}