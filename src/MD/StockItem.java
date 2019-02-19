package MD;

import java.util.Objects;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityInStock;
    private int reserved;

    public StockItem(String name, double price, int quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.reserved=0;
    }

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityInStock =0;
        this.reserved=0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int avaibleQuantity() {
        return quantityInStock -reserved;
    }

    public int getReserved() {
        return reserved;
    }

    public void setPrice(double price){
        if(price>0){
            this.price= price;
        }
    }

    public void adjustQuantity(int quantity){
        int newQuantity= this.quantityInStock +quantity;
        if(newQuantity>=0){
            this.quantityInStock = newQuantity;
        }
    }

    public int reservedStock(int quantityToReserve){
        if(quantityToReserve<=avaibleQuantity()){
            reserved+=quantityToReserve;
            return quantityInStock;
        }
        return 0;
    }

    public int unreserved(int quantityToUnreserved){
        if(quantityToUnreserved<=reserved){
            reserved-=quantityToUnreserved;
        }
        return 0;
    }

    public int finaliseStock(int quantity){
        if(quantity<=reserved){
            quantityInStock-=quantity;
            reserved-=quantity;
            return quantity;
        }
        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        String stockItem = ((StockItem) o).getName();
        return this.name.equals(stockItem);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name+": "+this.price+". Quantity: "+this.quantityInStock+
                ". Reserved: "+this.reserved;
    }

    @Override
    public int compareTo(StockItem stockItem) {
        if(this== stockItem){
            return 0;
        }
        else if(stockItem != null){
            return this.name.compareTo(stockItem.getName());
        }
        throw new NullPointerException();
    }
}
