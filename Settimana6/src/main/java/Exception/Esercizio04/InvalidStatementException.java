package Exception.Esercizio04;

//classe per creare una exception personalizzata
public class InvalidStatementException extends Exception {
    public InvalidStatementException(String message) {
        super(message);
    }

    // Eccezione innestata (chained exception).
    // Questo costruttore permette di creare un'eccezione personalizzata
    // passando sia un messaggio descrittivo sia l'eccezione originale (causa).
    // In questo modo si mantiene traccia dell'eccezione iniziale,
    // concatenandola alla nuova per fornire informazioni più dettagliate
    // sul contesto in cui si è verificato l'errore.
    public InvalidStatementException(String message, Exception ex) {
        super(message, ex);
    }
}
