package com.globalmarket.shipping;

public class LockerPickupShipping implements ShippingStrategy {

    private static final double FLAT_RATE_SMALL = 4.99;
    private static final double FLAT_RATE_MEDIUM = 7.99;
    private static final double FLAT_RATE_LARGE = 12.99;
    private static final double SURCHARGE_PER_KM = 0.01;
    private static final int BASE_DELIVERY_DAYS = 3;

    @Override
    public double calculateCost(double weightKg, double distanceKm) {
        double flatRate;
        if (weightKg <= 5) {
            flatRate = FLAT_RATE_SMALL;
        } else if (weightKg <= 15) {
            flatRate = FLAT_RATE_MEDIUM;
        } else {
            flatRate = FLAT_RATE_LARGE;
        }
        return flatRate + (distanceKm * SURCHARGE_PER_KM);
    }

    @Override
    public int estimateDeliveryDays(double distanceKm) {
        return BASE_DELIVERY_DAYS;
    }

    @Override
    public String getName() {
        return "Punto de Recogida (Casillero)";
    }

    @Override
    public String getDescription() {
        return "Tarifa plana segun tamano del paquete. Recogida en centro de distribucion cercano.";
    }
}
