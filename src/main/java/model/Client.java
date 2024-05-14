package model;

public class Client {
    private String name;
    private String address;

    /**
     * Constructorul default al clasei Client
     */
    public Client(){}
    /**
     * Constructorul clasei Client
     *
     * @param name    Campul setat pentru nume
     * @param address Campul setat pentru adresa
     */
    public Client(String name, String address){
        this.setName(name);
        this.setAddress(address);
    }
    /**
     * Getter pentru nume
     *
     * @return Numele clientului
     */
    public String getName() {
        return name;
    }
    /**
     * Setter pentru nume
     *
     * @param name Numele clientului
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter pentru adresa
     *
     * @return Adresa clientului
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter pentru adresa
     *
     * @param address Adresa clientului
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
