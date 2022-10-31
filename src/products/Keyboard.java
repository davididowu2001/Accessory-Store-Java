package products;

public class Keyboard extends Product{
	//keyboard inherits from product
    private String layout;//private attributes

    public Keyboard(int barcode, String name, String type, String brand, String colour,
                    String connectivity, int quantity_in_stock, float original_cost,
                    float retail_price, String layout) {
        super(barcode, name, type, brand, colour, connectivity, quantity_in_stock,
                original_cost, retail_price,layout);

        this.layout=layout;
    }



  //function to access private attribute
    public String getLayout() {
        return layout;
    }
}

