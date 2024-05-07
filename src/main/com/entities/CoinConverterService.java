package main.com.entities;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CoinConverterService {

    public static void main(String[] args) {
        System.out.println(currencyApi("BRL", "ARS"));
    }
    public static Double currencyApi(String moedaOrigem, String moedaDestino) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/5a51f4cbfafb9fedf4cb6113/pair/" + moedaOrigem + "/" + moedaDestino))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                String json = response.body();
                CoinFormat retorn = gson.fromJson(json, CoinFormat.class);
                return retorn.conversion_rate();
            } else {
                System.err.println("Erro ao obter taxa de câmbio. Código de status: " + response.statusCode());
                return null;
            }
        } catch (IOException e) {
            System.err.println("Erro de E/S ao conectar à API: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            System.err.println("Thread interrompida durante a conexão à API: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
