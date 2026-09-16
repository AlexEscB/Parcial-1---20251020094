package com.globalmarket.shipping;

public interface ShippingStrategy {
    double calculateCost(double weightKg, double distanceKm);
    int estimateDeliveryDays(double distanceKm);
    String getName();
    String getDescription();
}
