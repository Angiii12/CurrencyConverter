package com.angelinaniedermayer.modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Exchange {
    String time_exhange_operation;
    String base_curr;
    String target_curr;
    double amount;
    double converted_amount;
    static final File records = new File("exchangeRecords.json");;

    public Exchange(String formattedOpTime, String baseCurrency, String targetCurrency, double amount, double convertedAmount) {
        this.time_exhange_operation = formattedOpTime;
        this.base_curr = baseCurrency;
        this.target_curr = targetCurrency;
        this.amount = amount;
        this.converted_amount = convertedAmount;
    }

    public void save(){
        try {
            List<Exchange> exchanges = readRecords();
            exchanges.add(this);

            FileWriter records_write = new FileWriter(this.records);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String to_save = gson.toJson(exchanges);
            records_write.write(to_save);
            records_write.close();
        } catch (IOException e) {
            System.out.println("No se pudo cargar en el archivo");;
        }
    }

    public static List<Exchange> readRecords() {
        List<Exchange> exchanges = new ArrayList<>();
        try {
            if (records.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(records));
                Gson gson = new Gson();
                Exchange[] savedExchanges = gson.fromJson(reader, Exchange[].class);
                if (savedExchanges != null) {
                    for (Exchange exchange : savedExchanges) {
                        exchanges.add(exchange);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        }
        return exchanges;
    }

    public static void displayAllRecords() {
        List<Exchange> exchanges = readRecords();
        if (exchanges.isEmpty()) {
            System.out.println("No hay registros para mostrar.");
        } else {
            for (Exchange exchange : exchanges) {
                System.out.println(exchange);
            }
        }
    }


    @Override
    public String toString() {
        return "Fecha y hora: " + time_exhange_operation +
                ", Moneda base: " + base_curr +
                ", Moneda de destino: " + target_curr +
                ", Cantidad: " + amount +
                ", Cantidad convertida: " + converted_amount;
    }


}
