package businessLogic;

import dataAccess.OrderItemDAO;
import dataAccess.ProductDAO;
import model.OrderItem;
import model.Product;

import java.util.List;

public class ProductBLL {
    private ProductDAO productDAO;
    private OrderItemDAO orderItemDAO;
    public ProductBLL(){
        this.productDAO=new ProductDAO();
        this.orderItemDAO=new OrderItemDAO();
    }

    /**
     * @param product produs inserat
     */
    public void insertProduct(Product product){
        int productID=productDAO.findID(product);
        if(productID!=-1){
            Product updatedProduct=productDAO.findById(productID);
            updatedProduct.setQuantity(updatedProduct.getQuantity()+product.getQuantity());
            productDAO.update(updatedProduct, updatedProduct.getProductName());
        }else {
            productDAO.insert(product);
            System.out.println(product.getProductName()+" "+product.getPrice()+" "+product);
        }
    }

    /**
     * @param product Produsul a carui ID va fi cautat
     * @return ID-ul produsului cautat
     */
    public int findID(Product product){
        return productDAO.findID(product);
    }

    /**
     * @param id ID-ul produsului care se cauta
     * @return Produsul din baza de date
     */
    public Product getProduct(int id){
        return productDAO.findById(id);
    }

    /**
     * @param product produs sters
     */

    public void deleteProduct(Product product) {
        List<OrderItem> orderItemList = orderItemDAO.findAll();
        int productID = productDAO.findID(product);
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getProductID() == productID) {
                orderItemDAO.delete(orderItem);
                break;
            }
        }
        productDAO.delete(product);
    }
    /**
     * @param product  Produs comandat
     * @param Quantity Cantitate produs comandat
     * @return -1 daca cantitatea produsului nu este suficienta, 1 daca este suficienta
     */
public int updateProduct(Product product, int Quantity) {
    if (product.getQuantity() < Quantity) {
        System.out.println("Cantitate insuficienta");
        return -1;
    } else {
        Product updatedProduct = new Product(product.getProductName(), product.getQuantity() - Quantity, product.getPrice());
       productDAO.update(updatedProduct, updatedProduct.getProductName());
        return 1;
    }
}
}
