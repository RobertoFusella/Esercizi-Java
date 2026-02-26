package settimana6.Exception.Esercizio04;

import settimana6.Exception.Esercizio05.InvalidStatementException;
import settimana6.Exception.Esercizio05.MathOperation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Try-with-resources: la risorsa dichiarata tra parentesi
        // (BufferedReader) viene creata all'inizio del blocco try
        // e chiusa automaticamente alla fine, anche in caso di eccezione.
        // Non è necessario un blocco finally per il cleanup.
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {

            // Il metodo processFile può lanciare IOException
            // o InvalidStatementException, che verranno intercettate
            // dai blocchi catch sottostanti.
            processFile(reader);

        } catch (FileNotFoundException ex) {

            // Eccezione specifica lanciata se il file indicato in args[0]
            // non esiste o non è accessibile.
            System.out.println("File not found: " + args[0]);

        } catch (IOException ex) {

            // Gestisce errori di input/output durante la lettura del file.
            // Include problemi di lettura, chiusura della risorsa, ecc.
            System.out.println("Error: " + ex.getMessage());

        } catch (InvalidStatementException ex) {

            // Eccezione personalizzata generata durante l'elaborazione
            // di una riga non valida del file.
            System.out.println("Error invalid statement " + ex.getMessage());

            // Se l'eccezione è stata creata tramite exception chaining,
            // viene stampata anche la causa originale.
            if (ex.getCause() != null) {
                System.out.println("Caused by: " + ex.getCause());
            }

        } catch (Exception ex) {

            // Catch generico che intercetta eventuali eccezioni
            // non previste dai blocchi precedenti.
            System.out.println("Error processing file: " + ex.getMessage());
        }
    }

    // Il metodo dichiara 'throws IOException, InvalidStatementException' per indicare che può generare
    // un'eccezione di tipo IOException e InvalidStatementException durante la lettura del file.
    // La gestione dell'eccezione sarà a carico di chi chiama questo metodo.
    private static void processFile(BufferedReader reader) throws IOException, InvalidStatementException {
        // Apertura del file indicato come primo argomento da riga di comando
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null)
            performOperation(inputLine);
    }

    private static void performOperation(String inputLine) throws InvalidStatementException {
        try {
            String[] parts = inputLine.split(" ");
            if (parts.length != 3) {
                throw new InvalidStatementException("Statement must have 3 parts: operation, leftVal, rightVal");
            }
            MathOperation operation = MathOperation.valueOf(parts[0].toUpperCase());
            int leftVal = valueFromWord(parts[1]);
            int rightVal = valueFromWord(parts[2]);

            int result = execute(operation, leftVal, rightVal);

            System.out.println(inputLine + " = " + result);
        }catch (InvalidStatementException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InvalidStatementException("Error processing statement", ex);
        }
    }

    static int execute(MathOperation operation, int leftVal, int rightVal) {
        int result = 0;
        switch (operation) {
            case ADD:
                result = leftVal + rightVal;
                break;
            case SUBTRACT:
                result = leftVal - rightVal;
                break;
            case MULTIPLY:
                result = leftVal * rightVal;
                break;
            case DIVIDE:
                if (rightVal == 0){
//                    IllegalArgumentException ex =
//                            new IllegalArgumentException("Zero rightVal not permitted with divide operation");
//                    throw ex;
                    throw new IllegalArgumentException("Zero rightVal not permitted with divide operation");
                }
                result = leftVal / rightVal;
                break;
        }
        return result;
    }

    static int valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        int value = -1;
        for (int index = 0; index < numberWords.length; index++) {
            if (word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        if (value == -1)
            value = Integer.parseInt(word);

        return value;
    }
}
