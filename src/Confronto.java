import java.util.Scanner;

public class Confronto {
   private int primoValore;
   private int secondoValore;

   public void controllo(int primoValore,int secondoValore){
        if(primoValore > secondoValore)
            System.out.println(primoValore + " è maggiore di " + secondoValore);
        else if (secondoValore > primoValore)
            System.out.println(secondoValore + " è maggiore di " + primoValore);
        else if(primoValore == secondoValore || secondoValore == primoValore)
            System.out.println("i numeri sono uguali");
   }
}
