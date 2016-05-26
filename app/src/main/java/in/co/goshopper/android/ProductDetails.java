package in.co.goshopper.android;



/**
 * Created by Anand Soni on 24-05-2016.
 */

public class ProductDetails {
    private String ProductName, shop,Price,dist;




    public ProductDetails(String ProductName, String Price, String shop, String dist) {
        this.ProductName = ProductName;
        this.shop = shop;
        this.Price = Price;
        this.dist=dist;
    }



    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String name) {
        this.ProductName = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getshop() {
        return shop;
    }

    public void setshop(String shop) {
        this.shop = shop;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }


}
