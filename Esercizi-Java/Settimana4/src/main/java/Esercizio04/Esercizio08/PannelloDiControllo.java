package Esercizio04.Esercizio08;

import java.util.ArrayList;
import java.util.List;

public class PannelloDiControllo {
    private final List<Allarme> tuttiGliAllarmi = new ArrayList<Allarme>();

    public void aggiungiAllarme(Allarme allarme) {
        allarme.accendiAllarme();
        tuttiGliAllarmi.add(allarme);
    }
    public void stampaGrandeMessaggio() {
        for (Allarme allarme : tuttiGliAllarmi) {
            System.out.println(allarme.riceviNotifica(true));
        }
    }
}
