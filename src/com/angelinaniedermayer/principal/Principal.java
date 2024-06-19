package com.angelinaniedermayer.principal;

import com.angelinaniedermayer.modelos.ConsultarCambio;
import com.angelinaniedermayer.modelos.Exchange;
import com.angelinaniedermayer.modelos.Moneda;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarCambio consulta = new ConsultarCambio();
        System.out.println("BIENVENIDO AL CONVERSOR DE MONEDAS");
        while (true) {
            System.out.println("*********************************");
            System.out.println("1) Dolar =>> Peso Argentino");
            System.out.println("2) Peso Argentino =>> Dólar");
            System.out.println("3) Dolar =>> Real Brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dolar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dolar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida");
            System.out.println("*********************************");
            var exchange = lectura.nextLine();
            if (exchange.equalsIgnoreCase("7")) {
                break;
            }

            try {
                String baseCurrency;
                String targetCurrency;
                switch (exchange) {
                    case "1":
                        baseCurrency = "USD";
                        targetCurrency = "ARS";
                        break;
                    case "2":
                        baseCurrency = "ARS";
                        targetCurrency = "USD";
                        break;
                    case "3":
                        baseCurrency = "USD";
                        targetCurrency = "BRL";
                        break;
                    case "4":
                        baseCurrency = "BRL";
                        targetCurrency = "USD";
                        break;
                    case "5":
                        baseCurrency = "USD";
                        targetCurrency = "COP";
                        break;
                    case "6":
                        baseCurrency = "COP";
                        targetCurrency = "USD";
                    default:
                        System.out.println("Opción inválida");
                        continue;
                }
                LocalDateTime op_time = LocalDateTime.now();
                DateTimeFormatter format_time = DateTimeFormatter.ofPattern("[dd-MM-yyyy HH:mm:ss]");
                String formatted_op_time = op_time.format(format_time);
                Moneda chosenExchange = consulta.consultarMoneda(baseCurrency);

                System.out.println("Ingrese el valor que desea convertir: ");
                double amount = Double.parseDouble(lectura.nextLine());
                double converted_amount = chosenExchange.convert_currency(targetCurrency, amount);

                System.out.println("El valor " + amount + "[" + baseCurrency + "]" + "corresponde al valor final de =>>> " + converted_amount + "[" + targetCurrency + "]");
                Exchange operation_save = new Exchange(formatted_op_time, baseCurrency, targetCurrency, amount, converted_amount);
                operation_save.save();

                System.out.println("*** En caso de querer ver el resumen de operaciones ingrese 8, sino presione Enter ***");
                String viewRecords = lectura.nextLine();
                if (viewRecords.equalsIgnoreCase("8")) {
                    Exchange.displayAllRecords();
                }

            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }
        lectura.close();
    }
}
