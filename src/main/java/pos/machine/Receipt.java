package pos.machine;

import java.util.List;

public class Receipt {
    List<Item> items;
    int totalPrice;

    public Receipt() {}

    public List<Item> getItems() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
