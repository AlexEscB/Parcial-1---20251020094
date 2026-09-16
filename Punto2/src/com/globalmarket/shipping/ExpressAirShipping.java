package com.globalmarket.shipping;

public class ExpressAirShipping implements ShippingStrategy {

    private static final double COST_PER_KG = 5.00;
    private static final double COST_PER_KM = 0.15;
    private static final double BASE_RATE = 25.00;

    @Override
    public double calculateCost(double weightKg, double distanceKm) {
        return BASE_RATE + (weightKg * COST_PER_KG) + (distanceKm * COST_PER_KM);
    }

    @Override
    public int estimateDeliveryDays(double distanceKm) {
        if (distanceKm < 1000) return 1;
        if (distanceKm < 5000) return 2;
        return 3;
    }

    @Override
    public String getName() {
        return "Express (Aereo)";
    }

    @Override
    public String getDescription() {
        return "Envio aereo prioritario con aerolineas asociadas. Maxima velocidad, costo elevado.";
    }
}
