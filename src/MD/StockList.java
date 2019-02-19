package MD;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private Map<String, StockItem> list;

    public StockList() {
        this.list = new HashMap<>();
    }

    public int addStock(StockItem stockItem) {
        if (stockItem != null) {
            StockItem newStock = list.getOrDefault(stockItem.getName(), stockItem);
            if (newStock != stockItem) {
                stockItem.adjustQuantity(newStock.avaibleQuantity());
            }
            list.put(stockItem.getName(), stockItem);
            return stockItem.avaibleQuantity();
        }
        return 0;
    }

    public int sellStock(String name, int quantity){
        StockItem inStock= list.get(name);
        if((inStock != null) && (quantity>0)){
            return inStock.finaliseStock(quantity);
        }
        return 0;
    }

    public int reserveStock(String item, int quantity){
        StockItem inStock= list.get(item);
        if((inStock != null) && (quantity>0)){
            return inStock.reservedStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String item, int quantity){
        StockItem inStock= list.get(item);
        if((inStock != null) &&(quantity>0)){
            return inStock.unreserved(quantity);
        }
        return 0;
    }

    public StockItem get(String name){
        return list.get(name);
    }

    public Map<String, StockItem> Prices(){
        Map<String, StockItem> prices= new LinkedHashMap<>();
        for(Map.Entry<String, StockItem> price: list.entrySet()){
            prices.put(price.getKey(), price.getValue());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
       String word= "\nStock List\n";
       double totalCost= 0.0;
       for(Map.Entry<String, StockItem> lista: list.entrySet()){
           StockItem stockItem= lista.getValue();
           double value= stockItem.getPrice()+stockItem.avaibleQuantity();

           word= word+ stockItem+". Avaible items: "+stockItem.avaibleQuantity()+"" +
                   ". Value: "+String.format("%.2f", value+"\n");
           totalCost+= value;
       }
       return word+". Total value is "+totalCost;
    }
}
