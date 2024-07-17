package org.brisa.controllerimdb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.System.out;

public class Application {

    public static void main(String[] args) throws Exception {
        // Fazer uma conexição HTTP e b uscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_0ot0yvm";
        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        out.println(body);

    }
}
