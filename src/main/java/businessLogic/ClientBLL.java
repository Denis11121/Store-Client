package businessLogic;

import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.OrderItemDAO;
import model.Client;
import model.OrderItem;
import model.Orders;

import java.util.List;

public class ClientBLL {
    private ClientDAO clientDAO;
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    public ClientBLL() {
        clientDAO=new ClientDAO();
        orderDAO=new OrderDAO();
        orderItemDAO=new OrderItemDAO();
    }
    /**
     * @param client client inserat
     */
    public void insertClient(Client client){
        clientDAO.insert(client);
    }


     public int findID(Client client){
        return clientDAO.findID(client);
    }



    /**
     * @param client client sters din baza de date
     */
    public void deleteClient(Client client){
        List<OrderItem> orderItemList=orderItemDAO.findAll();
        List<Orders> ordersList=orderDAO.findAll();
        Orders delete=null;
        int clientID=clientDAO.findID(client);
        int orderID=-1;
        for(Orders orders: ordersList){
            if(orders.getClientID()==clientID){
                orderID=orderDAO.findID(orders);
                delete=orders;
                break;
            }
        }
        if(orderID!=-1){
            for(OrderItem orderItem:orderItemList){
                if(orderItem.getOrderID()==orderID){
                    orderItemDAO.delete(orderItem);
                }
            }
            orderDAO.delete(delete);
        }
        clientDAO.delete(client);
    }

}
