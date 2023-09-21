public class task3 {
    public static void main(String[] args) {
        System.out.println(replaceVowels("apple"));
        System.out.println(stringTransform("hello"));
        System.out.println(doesBlockFit(1, 3, 5, 4, 5));
        System.out.println(numCheck(52));
        System.out.println(countRoots(new int[] {1, -3, 2}));
        System.out.println(countRoots(new int[] {2, 5, 2}));
        System.out.println(countRoots(new int[] {1, -6, 9}));
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
}
