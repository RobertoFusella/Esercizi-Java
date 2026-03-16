package Server;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StudentServer {

    private final HttpClient client;
    private final String baseUrl;

    // Costruttore: passiamo l'URL base del server
    public StudentServer(String baseUrl) {
        this.client = HttpClient.newHttpClient();
        this.baseUrl = baseUrl;
    }

    // -----------------------------
    // GET /students
    // -----------------------------
    public String getAllStudents() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/students"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // -----------------------------
    // POST /students
    // -----------------------------
    public String addStudent(String nome, int voto) throws Exception {
        String body = nome + "," + voto;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/students"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // -----------------------------
    // PUT /students?id={id}
    // -----------------------------
    public String updateStudentVoto(int id, int nuovoVoto) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/students?id=" + id))
                .PUT(HttpRequest.BodyPublishers.ofString(String.valueOf(nuovoVoto)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // -----------------------------
    // DELETE /students?id={id}
    // -----------------------------
    public String deleteStudent(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/students?id=" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}