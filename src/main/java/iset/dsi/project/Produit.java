package iset.dsi.project;

public class Produit {
    private String name ;
    private String price ;
    //private String image ;

    public Produit() {

    }

    public Produit(String nomProduit, String price) {
        this.name = nomProduit;
        this.price = price;
        //this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /*public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }*/
}
