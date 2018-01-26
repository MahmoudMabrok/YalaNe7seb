package model;

/**
 *
 * @author mo3tamed
 *
 *must class have a getMethod to each variable (to able to add it to obvervableLIst )
 */
public class Item {

    int id =0;
    String descrption  ;
    double price ;
    String user ;

    public int getId() {
        return id;
    }

    public Item(String descrption, double price, String user , int id ) {
        this.descrption = descrption;
        this.price = price;
        this.user = user;
      this.id = id  ;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
