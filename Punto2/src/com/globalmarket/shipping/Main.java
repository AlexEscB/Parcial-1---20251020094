package com.globalmarket.shipping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final List<ShippingStrategy> strategies = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initStrategies();

        boolean running = true;
        while (running) {
            printHeader("GLOBALMARKET - Plataforma de Envios");
            System.out.println("  1. Crear nueva orden");
            System.out.println("  2. Cambiar metodo de envio de una orden");
            System.out.println("  3. Ver resumen de una orden");
            System.out.println("  4. Ver todas las ordenes");
            System.out.println("  5. Comparar metodos de envio");
            System.out.println("  6. Confirmar y finalizar orden");
            System.out.println("  0. Salir");
            System.out.print("\n  Opcion: ");

            int option = readInt();

            switch (option) {
                case 1: createOrder(); break;
                case 2: changeShippingMethod(); break;
                case 3: viewOrderSummary(); break;
                case 4: viewAllOrders(); break;
                case 5: compareMethods(); break;
                case 6: confirmOrder(); break;
                case 0: running = false; break;
                default: System.out.println("\n  Opcion no valida.\n");
            }
        }

        System.out.println("\n  Gracias por usar GlobalMarket. Hasta luego!\n");
        scanner.close();
    }

    private static void initStrategies() {
        strategies.add(new MaritimeEconomicShipping());
        strategies.add(new ExpressAirShipping());
        strategies.add(new EcoFriendlyShipping());
        strategies.add(new LockerPickupShipping());
    }

    private static void createOrder() {
        printHeader("NUEVA ORDEN");

        System.out.print("  Nombre del cliente: ");
        String customer = scanner.nextLine().trim();

        System.out.print("  Nombre del producto: ");
        String product = scanner.nextLine().trim();

        System.out.print("  Precio del producto ($): ");
        double price = readDouble();

        System.out.print("  Peso del paquete (kg): ");
        double weight = readDouble();

        System.out.print("  Distancia de envio (km): ");
        double distance = readDouble();

        System.out.println("\n  Seleccione metodo de envio:");
        printStrategiesList();

        System.out.print("  Opcion: ");
        int strategyIdx = readInt();

        if (strategyIdx < 1 || strategyIdx > strategies.size()) {
            System.out.println("\n  Metodo no valido. Se asigna envio economico por defecto.");
            strategyIdx = 1;
        }

        ShippingStrategy strategy = strategies.get(strategyIdx - 1);
        Order order = new Order(customer, product, price, weight, distance, strategy);
        orders.add(order);

        System.out.println("\n  Orden #" + order.getOrderId() + " creada exitosamente.");
        System.out.println(order.getDetailedInfo());
    }

    private static void changeShippingMethod() {
        printHeader("CAMBIAR METODO DE ENVIO");

        if (orders.isEmpty()) {
            System.out.println("  No hay ordenes creadas.");
            return;
        }

        Order order = selectOrder();
        if (order == null) return;

        if (order.isConfirmed()) {
            System.out.println("\n  La orden #" + order.getOrderId() + " ya esta confirmada. No se puede cambiar.");
            return;
        }

        System.out.println("\n  Metodo actual: " + order.getShippingStrategy().getName());
        System.out.println("  Costo actual de envio: $" + String.format("%.2f", order.getShippingCost()));
        System.out.println("  Tiempo estimado: " + order.getDeliveryDays() + " dias\n");

        System.out.println("  Seleccione nuevo metodo de envio:");
        printStrategiesList();

        System.out.print("  Opcion: ");
        int strategyIdx = readInt();

        if (strategyIdx < 1 || strategyIdx > strategies.size()) {
            System.out.println("\n  Metodo no valido.");
            return;
        }

        ShippingStrategy oldStrategy = order.getShippingStrategy();
        ShippingStrategy newStrategy = strategies.get(strategyIdx - 1);
        order.setShippingStrategy(newStrategy);

        double oldCost = oldStrategy.calculateCost(order.getWeightKg(), order.getDistanceKm());
        double newCost = newStrategy.calculateCost(order.getWeightKg(), order.getDistanceKm());

        System.out.println("\n  Metodo cambiado de \"" + oldStrategy.getName() + "\" a \"" + newStrategy.getName() + "\"");
        System.out.println("  Costo anterior: $" + String.format("%.2f", oldCost));
        System.out.println("  Costo nuevo:    $" + String.format("%.2f", newCost));
        System.out.println("  Diferencia:     $" + String.format("%+.2f", newCost - oldCost));
        System.out.println("  Dias estimados: " + order.getDeliveryDays() + " dias");
    }

    private static void viewOrderSummary() {
        printHeader("RESUMEN DE ORDEN");

        if (orders.isEmpty()) {
            System.out.println("  No hay ordenes creadas.");
            return;
        }

        Order order = selectOrder();
        if (order == null) return;

        System.out.println(order.getDetailedInfo());
    }

    private static void viewAllOrders() {
        printHeader("TODAS LAS ORDENES");

        if (orders.isEmpty()) {
            System.out.println("  No hay ordenes creadas.");
            return;
        }

        for (Order order : orders) {
            String status = order.isConfirmed() ? "[CONFIRMADA]" : "[PENDIENTE]";
            System.out.printf("  Orden #%d - %s - %s - Envio: %s - $%.2f - %d dias %s%n",
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getProductName(),
                    order.getShippingStrategy().getName(),
                    order.getTotalCost(),
                    order.getDeliveryDays(),
                    status);
        }
        System.out.println();
    }

    private static void compareMethods() {
        printHeader("COMPARAR METODOS DE ENVIO");

        System.out.print("  Peso del paquete (kg): ");
        double weight = readDouble();

        System.out.print("  Distancia de envio (km): ");
        double distance = readDouble();

        System.out.println();
        System.out.printf("  %-35s %12s %12s%n", "METODO", "COSTO", "DIAS");
        System.out.println("  " + "-".repeat(62));

        for (ShippingStrategy strategy : strategies) {
            double cost = strategy.calculateCost(weight, distance);
            int days = strategy.estimateDeliveryDays(distance);
            System.out.printf("  %-35s %11s %9d%n", strategy.getName(), "$" + String.format("%.2f", cost), days);
        }

        System.out.println();
    }

    private static void confirmOrder() {
        printHeader("CONFIRMAR ORDEN");

        if (orders.isEmpty()) {
            System.out.println("  No hay ordenes creadas.");
            return;
        }

        Order order = selectOrder();
        if (order == null) return;

        if (order.isConfirmed()) {
            System.out.println("\n  La orden #" + order.getOrderId() + " ya esta confirmada.");
            return;
        }

        System.out.println("\n  Resumen de la orden a confirmar:");
        System.out.println(order.getDetailedInfo());
        System.out.print("  ¿Confirmar orden? (s/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("s") || confirm.equals("si")) {
            order.confirm();
            System.out.println("\n  Orden #" + order.getOrderId() + " confirmada exitosamente!");
        } else {
            System.out.println("\n  Confirmacion cancelada.");
        }
    }

    // ========== Helpers ==========

    private static Order selectOrder() {
        System.out.println("\n  Ordenes disponibles:");
        for (Order order : orders) {
            String status = order.isConfirmed() ? "[CONFIRMADA]" : "[PENDIENTE]";
            System.out.printf("    %d. %s - %s %s%n", order.getOrderId(), order.getCustomerName(), order.getProductName(), status);
        }
        System.out.print("  Seleccione numero de orden: ");
        int id = readInt();

        for (Order order : orders) {
            if (order.getOrderId() == id) return order;
        }
        System.out.println("\n  Orden no encontrada.");
        return null;
    }

    private static void printStrategiesList() {
        for (int i = 0; i < strategies.size(); i++) {
            ShippingStrategy s = strategies.get(i);
            System.out.printf("    %d. %s - %s%n", i + 1, s.getName(), s.getDescription());
        }
    }

    private static void printHeader(String title) {
        System.out.println("\n======================================");
        System.out.println("  " + title);
        System.out.println("======================================");
    }

    private static int readInt() {
        try {
            int val = Integer.parseInt(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double readDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
