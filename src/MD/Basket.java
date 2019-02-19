package MD;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list= new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity){
        if((item != null) && (quantity>0)){
            int inBasket= list.getOrDefault(item, 0);
            list.put(item, inBasket+quantity);
            return inBasket;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity){
        if((item != null)&&(quantity>0)){
            int inBasket= list.getOrDefault(item, 0);
            int newQuantity= inBasket-quantity;

            if(newQuantity >0){
                list.put(item, newQuantity);
                return quantity;
            }
            else if(newQuantity == 0){
                list.remove(item);
                return quantity;
            }
        }
        return 0;
    }

    public void cleanBasket(){
        this.list.clear();
    }

    public Map<StockItem, Integer> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {

        String words= "Basket name "+name+" contains "+this.list.size()+"\n";
        double costs= 0.0;
        for(Map.Entry<StockItem, Integer> lista: list.entrySet()){
            words= words+ lista.getKey()+". "+lista.getValue()+".\n";
            costs= costs+lista.getKey().getPrice()*lista.getValue();
        }
        return words+"\n Costs are: "+costs;

    }
}
