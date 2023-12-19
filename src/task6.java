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
        a = a.toLowerCase().replaceAll("[^a-z]", "");
        b = b.toLowerCase().replaceAll("[^a-z]", "");
        int[] setB = new int[26];
        for (char c : b.toCharArray()) setB[c - 97]++;

        for (int i = 0; i <= a.length() - b.length(); i++) {
            int[] setA = new int[26];
            for (char c : a.substring(i, i + b.length()).toCharArray()) setA[c - 97]++;
            if (Arrays.equals(setA, setB)) {
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
            list.addAll(collect(str.substring(n), n));
            return list.stream().sorted().collect(Collectors.toList());
        }
    }

    // -- 3 --

    public static String nicoCipher(String message, String key) {
        int keyLength = key.length();
        int extraSpaces = (keyLength - (message.length() % keyLength)) % keyLength;
        message += new String(new char[extraSpaces]).replace("\0", " ");

        Integer[] order = new Integer[keyLength];
        for (int i = 0; i < keyLength; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Character.compare(key.charAt(a), key.charAt(b)));

        char[] result = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            int columnIndex = order[i % keyLength];
            int rowIndex = i / keyLength;
            result[i] = message.charAt(rowIndex * keyLength + columnIndex);
        }

        return new String(result);
    }

    // -- 4 --

    public static int[] twoProduct(int[] arr, int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int m : arr) {
            int target = n / m;
            if (n % m == 0 && set.contains(target))
                return new int[] { target, m };
            set.add(m);
        }
        return new int[] {};
    }

    // -- 5 --

    public static int[] isExact(int f, int m, int n) {
        return (f < n) ? isExact(f * (m + 1), m + 1, n) : new int[] { f, m };
    }

    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);
        return (res[0] == n) ? res : new int[] {};
    }

    // -- 6 --

    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');
        if (startBracket != -1) {
            String repeating = frac.substring(startBracket + 1, frac.length() - 1);
            frac = frac.substring(0, startBracket) + repeating.repeat(9 - repeating.length());
        }
        double a = Double.parseDouble(frac);
        int div = 2;
        while (Math.ceil(a * div) - a * div > 0.000001) div++;
        return (int) Math.ceil(a * div) + "/" + div;
    }

    // -- 7 --

    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0, strIndex = 0;

        while (strIndex < str.length()) {
            int p = pi.charAt(piIndex++) - '0';
            int n = Math.min(p, str.length() - strIndex);
            res += str.substring(strIndex, strIndex + n);
            strIndex += n;
            if (strIndex < str.length()) res += ' ';
            else
                while (n++ < p) res += res.charAt(res.length() - 1);
        }
        return res;
    }

    // -- 8 --

    public static String generateNonconsecutive(String str) {
        Pattern part = Pattern.compile("^( [\\-+*/] )*(\\()*(-*\\d+)(\\))*");
        boolean start = true;
        Node currentNode = new Node("+");
        currentNode.setLeft(new Node("0"));
        int parLevel = 0;

        for (int i = 0; i < str.length();) {
            Matcher matcher = part.matcher(str).region(i, str.length());

            if (!matcher.find()){
                System.out.println("Syntax error");
                return null;
            }

            if (matcher.group(1) == null){
                if (!start){
                    System.out.println("Syntax error");
                    return null;
                }
                currentNode.setRight(new Node(matcher.group(3)));
                start = false;
                i = matcher.end();
                if (!(matcher.group(2) == null)){
                    parLevel++;
                }
                continue;
            }
            Node opNode = new Node(String.valueOf(matcher.group(1).charAt(1)));
            Node numNode = new Node(matcher.group(3));
            opNode.setRight(numNode);
            if (parLevel > 0){
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/'){
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                }
                else {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                    currentNode = opNode;
                }
            }
            if (parLevel == 0){
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/'){
                    opNode.setLeft(currentNode.right);

                    currentNode.setRight(opNode);
                }
                else {
                    opNode.setLeft(currentNode);
                    currentNode = opNode;
                }
            }

            if (!(matcher.group(2) == null)){
                currentNode = opNode;
                parLevel++;
            }
            if (!(matcher.group(4) == null)){
                if (!(currentNode.root == null)){
                    currentNode = currentNode.root;
                }
                parLevel--;
            }
            i = matcher.end();
        }
        try {
            double res = currentNode.getUltimateRoot().computeNode();
            if (res % 1 == 0){
                return String.valueOf((int) res);
            }
            return String.valueOf(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    static class Node {
        String value;
        Node left;
        Node root;
        Node right;


        public void setLeft(Node node){
            this.left = node;
            node.root = this;
        }

        public void setRight(Node node){
            this.right = node;
            node.root = this;
        }

        public Node getUltimateRoot(){
            if (root != null){
                return root.getUltimateRoot();
            }
            return this;
        }

        public double computeNode() throws Exception {
            if ("+-*/".contains(value)){
                double num1 = left.computeNode();
                double num2 = right.computeNode();

                switch (value){
                    case "+":
                        return num1 + num2;
                    case "-":
                        return num1 - num2;
                    case "*":
                        return num1 * num2;
                    case "/":
                        if (num2 == 0){
                            throw new ArithmeticException("Division by zero");
                        }
                        return num1 / num2;
                    default:
                        throw new Exception("Syntax error");
                }

            }
            return Double.parseDouble(value);
        }

        Node(String value) {
            this.value = value;
            this.root = null;
            right = null;
            left = null;
        }
    }


    // -- 9 --

    public static String isValid(String str) {
        int[] charCounts = new int[26];
        for (char c : str.toCharArray()) {
            charCounts[c - 'a']++;
        }
        int prevCount = -1;
        int removals = 0;
        for (int count : charCounts) {
            if (count > 0) {
                if (prevCount == -1) {
                    prevCount = count;
                } else if (prevCount != count) {
                    removals += Math.abs(prevCount - count);
                    if (removals > 1) return "NO";
                }
            }
        }
        return "YES";
    }

    // -- 10 --

    public static String findLCS(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        StringBuilder lcs = new StringBuilder();

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = s1.length(), j = s2.length();

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }
}