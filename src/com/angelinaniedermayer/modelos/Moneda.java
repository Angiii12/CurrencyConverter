package com.angelinaniedermayer.modelos;

import java.util.Map;

public record Moneda(String result,
                     String base_code,
                     Map<String, Double> conversion_rates
) {

    public Map<String, Double> conversion_rate() {
        return conversion_rates;
    }

    public double convert_currency(String target_currency, double amount){
        if(!conversion_rates.containsKey(target_currency)){
            throw new IllegalArgumentException("Moneda de destino no encontrada");
        }
        return amount * conversion_rates.get(target_currency);
    }
}
