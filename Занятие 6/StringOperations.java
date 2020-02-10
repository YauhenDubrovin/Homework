package example;

public class StringOperations {

    public String concatTwo(String str1, String str2) {
        return str1 + str2;
    }

    public String concat(String str1, String str2, String str3) {
        return str1.concat(str2).concat(str3);
    }

    public int length(String str) {
        return str.length();
    }

    public boolean isEqual(String str1, String str2) {
        return str1.equals(str2);
    }

    public boolean start(String str1, String str2) {
        return str1.startsWith(str2);
    }

    public boolean end(String str1, String str2) {
        return str1.endsWith(str2);
    }

    public String replace(String str, String regex, String replacement) {
        return str.replaceAll(regex, replacement);
    }

    public String deleteSpaces(String str) {
        return str.trim();
    }
}
