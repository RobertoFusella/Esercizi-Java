package Java.array.introduction;
import java.util.*;
public class Main {
static void main() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Inserisci lunghezza array:");
    int n = scan.nextInt();
    int [] a = new int [3];
    System.out.println("Inserisci un 3 numeri: ");
    for (int i=0; i<a.length; i++){
        a[i]=scan.nextInt();
    }
    scan.close();

    for (int i = 0; i < a.length; i++) {
        System.out.println(a[i]);
    }
}
}