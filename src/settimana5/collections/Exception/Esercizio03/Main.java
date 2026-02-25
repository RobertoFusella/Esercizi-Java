package settimana5.collections.Exception.Esercizio03;

import settimana5.collections.Exception.MathOperation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //try con risorse, si occupa in automatico di creare un
        //BufferReader come in questo caso, e fa anche in automatico le operazioni di
        //cleanup (chiudere il buffer e controllare se non è null)
        //un try può avere più catch che gestiscono più exceptions per coprire
        //più casi di errore
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
        processFile(reader);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + args[0]);
        } catch (IOException ex) {
            // Gestione di tutte le eccezioni possibili durante la lettura o l'elaborazione
            System.out.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error processing file: " + ex.getMessage());
        }

//        finally {
//            // Cleanup: chiusura del file, viene eseguito sempre, anche se c'è stata un'eccezione
//            try {
//                System.out.println("Closing File - " + args[0]);
//                if(reader != null) {
//                    reader.close(); // chiusura sicura del file
//                }
//            } catch (Exception ex) {
//                // Gestione di eventuali eccezioni durante la chiusura del file
//                System.out.println("Error closing File");
//            }
//        }
    }

    // Il metodo dichiara 'throws IOException' per indicare che può generare
    // un'eccezione di tipo IOException durante la lettura del file.
    // La gestione dell'eccezione sarà a carico di chi chiama questo metodo.
    private static void processFile(BufferedReader reader) throws IOException {
        // Apertura del file indicato come primo argomento da riga di comando
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null)
            performOperation(inputLine);
    }

    private static void performOperation(String inputLine) {
        String[] parts = inputLine.split(" ");
        MathOperation operation = MathOperation.valueOf(parts[0].toUpperCase());
        int leftVal = valueFromWord(parts[1]);
        int rightVal = valueFromWord(parts[2]);

        int result = execute(operation, leftVal, rightVal);

        System.out.println(inputLine + " = " + result);
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
