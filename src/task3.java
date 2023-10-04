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
                output.append("Double").append(str_char.toUpperCase());
                i++;
            } else {
                output.append(str_char);
            }
        }
        return output.toString();
    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        int s = w * h;
        return a * b <= s || b * c <= s || c * a <= s;
    }

    public static boolean numCheck(int input) {
        int num, sumsqare = 0;
        int doubleinput = input;
        while (input > 0) {
            num = input % 10;
            sumsqare += num * num;
            input /= 10;
        }
        return sumsqare % 2 == doubleinput % 2;
    }

    public static int countRoots(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];
        int D = b * b - 4 * a * c;
        if (D < 0) {return 0;}
        if (D == 0) {return 1;}
        return 2;
    }

    public static ArrayList<String> salesData(String[][] all) {
        int maxshops = 0;
        for (String[] shop : all) {
            if (shop.length > maxshops) {maxshops = shop.length;}
        }
        ArrayList<String> products = new ArrayList<>();
        for (String[] shops : all) {
            if (shops.length == maxshops) {products.add(shops[0]);}
        }
        return products;
    }

    public static boolean validSplit(String input) {
        String[] splited = input.split(" ");
        int counter = 0;
        for (int i = 0; i <= splited.length - 2; i++) {
            if (splited[i].charAt(splited[i].length() - 1) == splited[i + 1].charAt(0)) {
                counter += 1;
            }
        }
        return counter == splited.length - 1;
    }

    public static boolean waveForm(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (!(arr[i - 1] > arr[i] && arr[i] < arr[i + 1] || arr[i - 1] < arr[i] && arr[i] > arr[i + 1])) {
                return false;
            }
        }

        return true;
    }

    public static char commonVovel(String input) {
        String simbols = "aeiou";
        int[] count = new int[5];
        for (char simb : input.toCharArray()) {
            simb = Character.toLowerCase(simb);
            if (simbols.indexOf(simb) != -1) {
                count[simbols.indexOf(simb)]++;
            }
        }
        int max = 0;
        for (int i = 0; i < count.length - 1; i ++) {
            if (count[i] > count[max]) {
                max = i;
            }
        }
        return simbols.charAt(max);
    }

    public static String dataScience(int[][] input) {
        int maximum = 0;
        for (int x = 0; x < input[0].length; x++) {
            for (int y = 0; y < input[0].length; y++) {
                maximum += input[y][x];
            }
            input[x][x] = maximum/(input[0].length);
            maximum = 0;
        }

        return Arrays.deepToString(input);
    }
}
