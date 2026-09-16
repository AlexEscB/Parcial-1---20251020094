package com.globalmarket.shipping;

public class MaritimeEconomicShipping implements ShippingStrategy {

    private static final double COST_PER_KG = 0.50;
    private static final double COST_PER_KM = 0.02;
    private static final double BASE_RATE = 5.00;
    private static final int BASE_DELIVERY_DAYS = 15;

    @Override
    public double calculateCost(double weightKg, double distanceKm) {
        return BASE_RATE + (weightKg * COST_PER_KG) + (distanceKm * COST_PER_KM);
    }

    @Override
    public int estimateDeliveryDays(double distanceKm) {
        return BASE_DELIVERY_DAYS + (int)(distanceKm / 500);
    }

    @Override
    public String getName() {
        return "Economico (Maritimo)";
    }

    @Override
    public String getDescription() {
        return "Envio maritimo de bajo costo. Tiempo de entrega extendido (semanas).";
    }
}
