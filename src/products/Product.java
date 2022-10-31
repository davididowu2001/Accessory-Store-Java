package products;

public class Product {
	//private attributes

    private int barcode;
    private String name;
    private String type;
    private String brand;
    private String colour;
    private String connectivity;
    private int quantity_in_stock;
    private float original_cost;
    private float retail_price;
    private String additional_info;
    private int quantity_bought;

     public Product(int barcode, String name,String type, String brand, String colour, String connectivity, int quantity_in_stock,
             float original_cost,float retail_price,String additional_info){

         this.barcode=barcode;
         this.name=name;
         this.type=type;
         this.brand=brand;
         this.colour=colour;
         this.connectivity=connectivity;
         this.quantity_in_stock=quantity_in_stock;
         this.original_cost=original_cost;
         this.retail_price=retail_price;
         this.additional_info=additional_info;
       

     }
     //functions to access private attributes

    public int getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public float getOriginal_cost() {
        return (int) original_cost;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public float getRetail_price() {
        return retail_price;
    }

    public String getColour() {
        return colour;
    }

    public String getConnectivity() {
        return connectivity;
    }
    public String getAdditional_info() {
        return additional_info;
    }
    public int quantity_bought(){
        return quantity_bought;
    }
    
    public void  set_quantity_bought(int new_quantity){
    	
        this.quantity_bought = new_quantity;
    }
    public void set_new_quantity(int q){
        this.quantity_in_stock = q;
        System.out.println(this.quantity_in_stock);
    }
}
