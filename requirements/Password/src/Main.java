public class Main {

    /**
     * Function to check if the password has all the conditions
     * @param str is a password that wants to be checked
     * @return  boolean as if the given password has the conditions or not
     */
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

        //test

        String pass = "Arian2004m";
        if(passwordChecker(pass)){
            System.out.println("valid");
        }
        else System.out.println("invalid");


    }
}