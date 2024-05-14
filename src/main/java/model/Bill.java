package model;
/**
 * Inregistrare ce reprezintă o factura pentru o comanda de produse.
 */
public record Bill(int orderId, String clientName, String productName, double price, int quantity) {}
