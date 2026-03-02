package com.esercizioMaven;

public class clienteClient {
    static void main(String[] args) {
    //in questo progetto WebServiceClient non abbiamo il file della classe Cliente
    //ma dato che abbiamo creato delle dipendenze tra il Client e il Model possiamo
    //comunque creare la classe perchè i file/risorse sono condivisi tra i progetti
    Cliente cliente = new Cliente();
    }
}
