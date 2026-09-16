package com.globalmarket.shipping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private static int nextId = 1;

    private final int orderId;
    private final String customerName;
    private final String productName;
    private final double productPrice;
    private final double weightKg;
    private final double distanceKm;
    private ShippingStrategy shippingStrategy;
    private boolean confirmed;

    public Order(String customerName, String productName, double productPrice,
                 double weightKg, double distanceKm, ShippingStrategy shippingStrategy) {
        this.orderId = nextId++;
        this.customerName = customerName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.weightKg = weightKg;
        this.distanceKm = distanceKm;
        this.shippingStrategy = shippingStrategy;
        this.confirmed = false;
    }

    public void setShippingStrategy(ShippingStrategy shippingStrategy) {
        if (confirmed) {
            throw new IllegalStateException("No se puede cambiar la estrategia de una orden ya confirmada.");
        }
        this.shippingStrategy = shippingStrategy;
    }

    public double getShippingCost() {
        return shippingStrategy.calculateCost(weightKg, distanceKm);
    }

    public int getDeliveryDays() {
        return shippingStrategy.estimateDeliveryDays(distanceKm);
    }

    public double getTotalCost() {
        return productPrice + getShippingCost();
    }

    public void confirm() {
        this.confirmed = true;
    }

    public Map<String, String> getSummary() {
        Map<String, String> summary = new LinkedHashMap<>();
        summary.put("Orden #", String.valueOf(orderId));
        summary.put("Cliente", customerName);
        summary.put("Producto", productName);
        summary.put("Precio producto", String.format("$%.2f", productPrice));
        summary.put("Peso", String.format("%.2f kg", weightKg));
        summary.put("Distancia", String.format("%.0f km", distanceKm));
        summary.put("Metodo de envio", shippingStrategy.getName());
        summary.put("Costo de envio", String.format("$%.2f", getShippingCost()));
        summary.put("Dias estimados", String.valueOf(getDeliveryDays()));
        summary.put("TOTAL", String.format("$%.2f", getTotalCost()));
        return summary;
    }

    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Detalle de Orden #").append(orderId).append(" ===\n");
        for (Map.Entry<String, String> entry : getSummary().entrySet()) {
            sb.append(String.format("  %-20s %s%n", entry.getKey() + ":", entry.getValue()));
        }
        sb.append("  Estrategia: ").append(shippingStrategyDescription()).append("\n");
        return sb.toString();
    }

    private String shippingStrategyDescription() {
        return shippingStrategy.getDescription();
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getProductName() { return productName; }
    public double getProductPrice() { return productPrice; }
    public double getWeightKg() { return weightKg; }
    public double getDistanceKm() { return distanceKm; }
    public ShippingStrategy getShippingStrategy() { return shippingStrategy; }
    public boolean isConfirmed() { return confirmed; }
}
