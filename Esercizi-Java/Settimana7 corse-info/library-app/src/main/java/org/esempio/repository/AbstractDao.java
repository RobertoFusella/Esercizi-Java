package org.esempio.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe base (astratta) per i DAO (Data Access Object)
// Serve come classe comune da cui erediteranno tutte le classi che accedono al database
public abstract class AbstractDao {

    // Metodo protetto che restituisce una connessione al database
    // protected significa che può essere usato dalle classi figlie (che estendono AbstractDao)
    // throws SQLException indica che il metodo può generare un errore SQL
    protected Connection getConnection() throws SQLException {

        // URL di connessione al database MySQL
        // jdbc:mysql -> indica che si usa il driver JDBC per MySQL
        // localhost -> il database si trova sulla stessa macchina
        // 3306 -> porta standard di MySQL
        // library_db -> nome del database
        String url = "jdbc:mysql://localhost:3306/library_db";

        // username per accedere al database
        // in questo caso si usa l'utente root
        String username = "root";

        // password dell'utente root del database
        String password = "pass";

        // DriverManager è una classe di Java che gestisce le connessioni ai database
        // getConnection crea e restituisce una connessione usando url, username e password
        return DriverManager.getConnection(url, username, password);
    }
}
