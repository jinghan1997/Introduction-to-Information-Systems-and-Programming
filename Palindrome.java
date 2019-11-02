public class Palindrome {

    public static boolean isPalindrome (char[] S) {
        return isPalindromeHelper(S, S);
    }

    public static boolean isPalindromeHelper (char[] S, char[] newAA) {
        if (newAA.length == 1){
            return true;
        } else if (newAA.length == 2){
            if (newAA[0] == newAA[1]){
                return true;
            } else {
                return false;
            }
        } else if (newAA[0] == newAA[newAA.length - 1]){
            char[] newA = new char[newAA.length - 2];
            for (int i = 1; i<newAA.length - 1;i++){
                newA[i-1] = newAA[i];
            }
            return isPalindromeHelper(S, newA);
        }
        return false;
    }
}
