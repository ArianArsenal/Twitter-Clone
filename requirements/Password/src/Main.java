public class Main {
    public static boolean passwordChecker(String str){
        boolean upperCaseFlag = false;
        boolean lowerCaseFlag = false;
        char ch;
        if (str.length() < 8)
            return false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isLowerCase(ch))
                lowerCaseFlag = true;
            if (Character.isUpperCase(ch))
                upperCaseFlag = true;
        }
        return (lowerCaseFlag && upperCaseFlag);
    }
    public static void main(String[] args) {
    }
}