package com.alura.imersao.java;



import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI address = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(address)
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        //Parsear os dados que interessam (titulo, poster, rating)
        Parser parser = new Parser();
        List< Map<String, String> > listMovies = parser.parse(body);
        System.out.println(listMovies.size());
        System.out.println(listMovies.get(0));

        //Exibir e manipular
        //listMovies.forEach(t -> System.out.println(t));
        for (Map<String,String> filme : listMovies) {
            System.out.println(filme.get("title") + " " + filme.get("imDbRating"));
        }

    }
}