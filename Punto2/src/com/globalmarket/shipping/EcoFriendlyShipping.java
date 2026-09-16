package com.globalmarket.shipping;

public class EcoFriendlyShipping implements ShippingStrategy {

    private static final double COST_PER_KG = 1.20;
    private static final double COST_PER_KM = 0.06;
    private static final double BASE_RATE = 10.00;
    private static final double CO2_PER_KG = 0.1;
    private static final double CO2_PER_KM = 0.02;

    @Override
    public double calculateCost(double weightKg, double distanceKm) {
        return BASE_RATE + (weightKg * COST_PER_KG) + (distanceKm * COST_PER_KM);
    }

    @Override
    public int estimateDeliveryDays(double distanceKm) {
        if (distanceKm < 500) return 2;
        if (distanceKm < 2000) return 4;
        return 7;
    }

    public double calculateCO2Emissions(double weightKg, double distanceKm) {
        return (weightKg * CO2_PER_KG) + (distanceKm * CO2_PER_KM);
    }

    @Override
    public String getName() {
        return "Ecologico (Neutral en CO2)";
    }

    @Override
    public String getDescription() {
        return "Transporte terrestre y electrico para minimizar emisiones de CO2.";
    }
}
