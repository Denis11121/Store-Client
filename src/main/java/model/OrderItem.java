package model;

public class OrderItem {
    int productID;
    int productQuantity;
    int orderID;

    /**
     * Constructorul default al clasei OrderItem
     */
    public OrderItem(){}
    /**
     * Construcotrul clasei OrderItem
     *
     * @param productID       ID-ul produsului comandat
     * @param productQuantity Cantitatea comandata
     * @param orderID         Numarul comenzii
     */
    public OrderItem(int productID, int productQuantity, int orderID) {
        this.productID = productID;
        this.productQuantity = productQuantity;
        this.orderID = orderID;
    }
    /**
     * Getter pentru ID-ul produsului
     *
     * @return ID-ul produsului
     */
    public int getProductID() {
        return productID;
    }
    /**
     * Getter pentru cantitatea produsului
     *
     * @return Cantitatea produsului
     */
    public int getProductQuantity() {
        return productQuantity;
    }
    /**
     * Setter pentru cantitatea produsului
     *
     * @param productQuantity Cantitatea produsului
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    /**
     * Getter pentru numarul comenzii
     *
     * @return Numarul comenzii
     */
    public int getOrderID() {
        return orderID;
    }

  }
