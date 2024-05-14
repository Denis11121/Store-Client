package model;

public class Orders {
    int clientID;
    /**
     * Constructorul clasei Orders
     *
     * @param id ID-ul comenzii
     */
    public Orders(int id){
        this.clientID=id;
    }
    /**
     * Getter pentru ID-ul clientului
     *
     * @return ID-ul clientului
     */
    public int getClientID() {
        return clientID;
    }
}
