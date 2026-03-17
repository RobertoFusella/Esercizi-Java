package Server;

import Service.StudentService;
import Model.Student;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class StudentHttp {

    private final StudentService studentService;

    public StudentHttp(StudentService service) {
        this.studentService = service;
    }

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/students", exchange -> {

            String method = exchange.getRequestMethod();

            // ---------------- GET ----------------
            if (method.equalsIgnoreCase("GET")) {

                StringBuilder response = new StringBuilder();
                studentService.getAll().forEach(s -> response.append(s.toString()).append("\n"));

                exchange.getResponseHeaders().add("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, response.length());

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.toString().getBytes());
                }

                // ---------------- POST ----------------
            } else if (method.equalsIgnoreCase("POST")) {

                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String[] parts = body.split(",");

                if (parts.length < 2) {
                    exchange.sendResponseHeaders(400, 0);
                    return;
                }

                String nome = parts[0];
                int voto = Integer.parseInt(parts[1]);

                studentService.addStudent(new Student(nome, voto));

                String response = "Studente aggiunto";
                exchange.getResponseHeaders().add("Content-Type", "text/plain");
                exchange.sendResponseHeaders(201, response.length());

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }

                // ---------------- PUT ----------------
            } else if (method.equalsIgnoreCase("PUT")) {

                String query = exchange.getRequestURI().getQuery();

                if (query == null || !query.contains("id=")) {
                    exchange.sendResponseHeaders(400, 0);
                    return;
                }

                int id = Integer.parseInt(query.split("=")[1]);

                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                int nuovoVoto = Integer.parseInt(body);

                studentService.editVoto(id, nuovoVoto);

                String response = "Voto aggiornato";
                exchange.getResponseHeaders().add("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, response.length());

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }

                // ---------------- DELETE ----------------
            } else if (method.equalsIgnoreCase("DELETE")) {

                String query = exchange.getRequestURI().getQuery();

                if (query == null || !query.contains("id=")) {
                    exchange.sendResponseHeaders(400, 0);
                    return;
                }

                int id = Integer.parseInt(query.split("=")[1]);

                studentService.delete(id);

                String response = "Studente eliminato";
                exchange.getResponseHeaders().add("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, response.length());

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        server.start();
        System.out.println("Server avviato su porta 8000");
    }
}