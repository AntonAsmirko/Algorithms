import java.util.ArrayList;
import java.util.Scanner;

public class CountSort {

    public static ArrayList<Integer> array = new ArrayList<>();
    public static  int[] numbersCount = new int[101];
    public static  int[] numbersStartPos = new int[101];

    public static Integer[] countSort(){
        for (int i = 0; i < array.size(); i ++){
            numbersCount[array.get(i)]++;
        }
        int count = 0;
        for (int i = 1; i < numbersCount.length; i ++){
            numbersStartPos[i] = numbersCount[i-1]+count;
            count+=numbersCount[i-1];
        }
        Integer[] result = new Integer[array.size()];
        for (int i = 0; i < result.length; i++){
            int e = array.get(i);
            result[numbersStartPos[e]] = e;
            numbersStartPos[array.get(i)]++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            array.add(scanner.nextInt());
        }
        Integer[] result = countSort();
        for (int i : result) System.out.print(i+" ");
    }
}