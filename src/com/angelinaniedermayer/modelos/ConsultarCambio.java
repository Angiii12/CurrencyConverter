package com.angelinaniedermayer.modelos;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarCambio {
    public Moneda consultarMoneda(String moneda) {
        URI link = URI.create("https://v6.exchangerate-api.com/v6/8ba29ac5ab58098132527cba/latest/" + moneda);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(link)
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            //System.out.println("Respuesta JSON: " + responseBody);


            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se encontró la moneda");
        }
    }
}
