import java.util.ArrayList;

public class Permutation {
    private final String in;
    private ArrayList<String> a = new ArrayList<String>();
  
    Permutation(final String str){
        in = str;
    }

    public void permute(){
        char[] charArr = in.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            permuteHelper(swap(charArr, i, 0),0);
        }       
    }
    
    public void permuteHelper(char[] arr, int index){
        String word = new String(arr);
        if (!a.contains(word)){
                a.add(word);
            }

        for (int i = index; i < arr.length; i++) {
            permuteHelper(swap(arr, i, index),index+1);
        }

    }

    public ArrayList<String> getA(){
        return a;
    }
    
    public char[] swap(char[] toSwap, int a, int b){
        char[] newArr = new char[toSwap.length];
        for (int i=0;i<toSwap.length;i++){
            newArr[i] = toSwap[i];
        }
        newArr[a] = toSwap[b];
        newArr[b] = toSwap[a];
        return newArr;
    }
}
