package org.esempio.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe astratta generica (T = tipo dell'oggetto che vogliamo ottenere, es. Book)
public abstract class JdbcQueryTemplate<T> extends AbstractDao {

    // Costruttore vuoto
    public JdbcQueryTemplate() {}

    // Metodo generico che esegue una query e restituisce una lista di oggetti T
    public List<T> queryForList(String sql) {

        // Lista che conterrà i risultati convertiti in oggetti Java
        List<T> items = new ArrayList<>();

        try (
                // apre connessione al database (ereditato da AbstractDao)
                Connection con = getConnection();

                // crea uno Statement per eseguire la query SQL
                Statement stmt = con.createStatement();

                // esegue la query e ottiene il risultato (ResultSet)
                ResultSet rset = stmt.executeQuery(sql);) {

            // scorre tutte le righe del risultato
            while (rset.next()) {

                // per ogni riga chiama mapItem (definito dalle classi figlie)
                // che trasforma la riga in un oggetto T (es. Book)
                items.add(mapItem(rset));
            }

        } catch (SQLException sqe) {
            // gestione errore SQL
            sqe.printStackTrace();
        }

        // restituisce la lista di oggetti
        return items;
    }

    // Metodo astratto: ogni classe figlia DEVE implementarlo
    // Serve per convertire una riga del ResultSet in un oggetto Java
    public abstract T mapItem(ResultSet rset) throws SQLException;

}