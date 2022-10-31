package products;

public class Mouse extends Product {
	//mouse inherits from product
    private int buttons;//private attribute


    public Mouse(int barcode, String name, String type, String brand, String colour, String connectivity,
                 int quantity_in_stock, float original_cost, float retail_price, String buttons) {
        super(barcode, name, type, brand, colour, connectivity, quantity_in_stock, original_cost, retail_price,buttons);
        this.buttons= Integer.parseInt(buttons);
    }
//function to access private attribute
    public int getButtons() {
        return buttons;
    }
}

