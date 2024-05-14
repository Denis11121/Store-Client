package model;

public class Product {
    private String productName;
    private int quantity;
    private double price;

    public Product() {}
    /**
     * Constructorul clasei Product
     *
     * @param productName Numele produsului
     * @param quantity    Cantitatea produsului
     * @param price       Pretul produsului
     */
    public Product(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    /**
     * Getter pentru numele produsului
     *
     * @return Numele produsului
     */
    public String getProductName() {
        return productName;
    }
    /**
     * Setter pentru numele produsului
     *
     * @param productName Numele produsului
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * Getter pentru cantitatea produsului
     *
     * @return Cantitatea produsului
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Setter pentru cantitatea produsului
     *
     * @param quantity Cantitatea produsului
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Getter pentru pretul produsului
     *
     * @return Pretul produsului
     */
    public double getPrice() {
        return price;
    }
    /**
     * Setter pentru pretul produsului
     *
     * @param price Pretul produsului
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
