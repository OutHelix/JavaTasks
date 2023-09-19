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
    public static boolean duplicateChars(String word) {
        int len = word.length();
        for (int x = 0; x < len - 1; x++) {
            char y = word.toLowerCase().charAt(x);
            for (int z = 0; z < len; z++) {
                if ((y == word.charAt(z)) && (x != z)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static String getInitials(String fio) {
        StringBuilder initials = new StringBuilder();
        String[] words = fio.split(" ");
        for (String word : words) {
            initials.append(Character.toUpperCase(word.charAt(0)));
        }
        return initials.toString();
    }
    public static int differenceEvenOdd(int[] array) {
        int chet = 0;
        int nechet = 0;
        for (int num : array) {
            if (num % 2 == 0) {
                chet += num;
            } else {
                nechet += num;
            }
        }
        return Math.max(chet, nechet) - Math.min(chet, nechet);
    }
    public static boolean equalToAvg(int[] arr) {
//        int max = Arrays.stream(arr).sum();
        int max = 0;
        for (int num : arr) {
            max += num;
        }
        double middle = (double) max / arr.length;
        for (int num : arr) {
            if (num == middle) {
                return true;
            }
        }
        return false;
    }
    public static int[] indexMult(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * i;
        }
        return arr;
    }
    public static String reverse(String text) {
        StringBuilder reversed = new StringBuilder();
        for (int i = text.length() - 1; i != 0; i--) {
            reversed.append(text.charAt(i));
        }
        return reversed.toString();
    }
    public static int Tribonacci(int num) {
        if (num == 0 || num == 1) {
            return 0;
        } else if (num == 2) {
            return 1;
        }
        int a = 0, b = 0, c = 0, d;
        for (int i = 3; i <= num - 1; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }
    public static String pseudoHash(int len) {
        String hashString = "abcdef0123456789";
        Random rand = new Random();
        StringBuilder hash = new StringBuilder();

        if (len <= 0) {
            return "";
        } else {
            for (int i = 0; i <= len - 1; i++) {
                int num = rand.nextInt(hashString.length());
                hash.append(hashString.charAt(num));
            }
        }
        return hash.toString();
    }
    public static String botHelper (String input) {
        String[] words = input.split(" ");
        for (String word : words) {
            if (Objects.equals(word.toLowerCase(), "help")) {
                return "Calling for a staff member";
            }
        }
        return "Keep waiting";
    }
    public static boolean isAnagram(String text1, String text2) {
        text1 = text1.toLowerCase().replaceAll("\\s", "");
        text2 = text2.toLowerCase().replaceAll("\\s", "");

        if (text1.length() != text2.length()) {
            return false;
        }

        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }
}
