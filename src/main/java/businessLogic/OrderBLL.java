package businessLogic;

import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.OrderItemDAO;
import model.Bill;
import model.Client;
import model.OrderItem;
import model.Orders;
import model.Product;

import java.io.FileWriter;
import java.io.IOException;

public class OrderBLL {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private ClientDAO clientDAO;
    private ProductBLL productBLL;
    private ClientBLL clientBLL;

    public OrderBLL() {
        orderDAO = new OrderDAO();
        orderItemDAO = new OrderItemDAO();
        clientDAO = new ClientDAO();
        productBLL = new ProductBLL();
        clientBLL = new ClientBLL();
    }

    /**
     * @param client          Client care face o comanda
     * @param product         Produs comandat
     * @param productQuantity Cantitatea de produse comandata
     */

    public void insertOrder(Client client, Product product, int productQuantity) {
        try {
            FileWriter myWriter = new FileWriter("bill.txt");

            Orders order = new Orders(clientDAO.findID(client));
            int orderID = orderDAO.findID(order);
            int productID = productBLL.findID(product);
            if (productID != -1) {
                Product existingProduct = productBLL.getProduct(productID);
                if (orderID == -1) {
                    if (existingProduct.getQuantity() > productQuantity) {
                        orderDAO.insert(order);
                        orderID = orderDAO.findID(order);
                        OrderItem orderItem = new OrderItem(productID, productQuantity, orderID);
                        orderItemDAO.insert(orderItem);
                        productBLL.updateProduct(existingProduct, productQuantity);
                        ///BILL
                        myWriter.write("Client: "+ client.getName() + "\n" +"Adresa: "+ client.getAddress() + "\n" + "Produs: "+ product.getProductName() + "\n" +"Pret: "+ product.getPrice() + "\n" +"Cantitate: "+ product.getQuantity() + "\n");
                        myWriter.write("Pret total: " + product.getPrice() * (double) product.getQuantity());
                    }
                } else {
                    OrderItem orderItem = new OrderItem(productID, productQuantity, orderID);
                    if (productBLL.updateProduct(existingProduct, productQuantity) == 1) {
                        int orderItemID = orderItemDAO.findID(orderItem);
                        if (orderItemID == -1) {
                            {
                                orderItemDAO.insert(orderItem);
                              myWriter.write("Client: "+ client.getName() + "\n" +"Adresa: "+ client.getAddress() + "\n" + "Produs: "+ product.getProductName() + "\n" +"Pret: "+ product.getPrice() + "\n" +"Cantitate: "+ product.getQuantity() + "\n");
                                myWriter.write("Pret total: " + product.getPrice() * (double) product.getQuantity());
                            }
                        } else {
                            OrderItem existingOrderItem = orderItemDAO.findById(orderItemID);
                            existingOrderItem.setProductQuantity(productQuantity + existingOrderItem.getProductQuantity());
                            orderItemDAO.update(existingOrderItem, String.valueOf(orderItemID));
                         myWriter.write("Client: "+ client.getName() + "\n" +"Adresa: "+ client.getAddress() + "\n" + "Produs: "+ product.getProductName() + "\n" +"Pret: "+ product.getPrice() + "\n" +"Cantitate: "+ product.getQuantity() + "\n");
                            myWriter.write("Pret total: " + product.getPrice() * (double) product.getQuantity());
                        }
                    }
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * @param client clientul a carui numar de comanda se gaseste
     * @return Numarul comenzii
     */
    public int findOrderID(Client client) {
        return orderDAO.findID(new Orders(clientDAO.findID(client)));
    }
}
