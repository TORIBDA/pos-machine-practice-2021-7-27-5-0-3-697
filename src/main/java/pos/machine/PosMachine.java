package pos.machine;
import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> items = convertToItems(barcodes);
        //String tempVal = "";
        /*for (Item itemVal:items) {
            System.out.println("Name: "+ itemVal.getName() +
                    " Unit Price: " + itemVal.getUnitPrice() +
                    " Quantity: " + itemVal.getQuantity());
        }*/

        return "";
    }

    private List<Item> convertToItems(List<String> barcodes) {
        LinkedList<Item> items = new LinkedList<Item>();
        List<ItemInfo> itemInfo = loadAllItemsInfo();
        barcodes = new ArrayList<>(new LinkedHashSet<>(barcodes));
        //load items here
        //Check per barcode in barcodes
        //push the items on the list of items retrieved based on barcode
        //return items
        for (String barcode : barcodes) {
            Item itemValue = new Item();
            for (ItemInfo itemInfoVal : itemInfo) {
                if (barcode.equals(itemInfoVal.getBarcode())) {
                    itemValue.setName(itemInfoVal.getName());
                    itemValue.setUnitPrice(itemInfoVal.getPrice());
                    itemValue.setQuantity(retrieveItemCount(barcode));
                }
            }
            items.add(itemValue);
        }
        return items;
    }

    private List<ItemInfo> loadAllItemsInfo(){
        return ItemDataLoader.loadAllItemInfos();
    }

    private int retrieveItemCount(String currentItemBarcode) {
        return Collections.frequency(ItemDataLoader.loadBarcodes(), currentItemBarcode);
    }
}
