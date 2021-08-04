package pos.machine;
import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> items = convertToItems(barcodes);
        Receipt receipt = calculateReceipt(items);
        return generateReceipt(receipt);
    }

    private List<Item> convertToItems(List<String> barcodes) {
        LinkedList<Item> items = new LinkedList<Item>();
        List<ItemInfo> itemInfo = loadAllItemsInfo();
        barcodes = new ArrayList<>(new LinkedHashSet<>(barcodes));
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

    private Receipt calculateReceipt(List<Item> itemsList) {
        Receipt receipt = new Receipt();
        receipt.setItems(calculateItemsSubtotal(itemsList));
        receipt.setTotalPrice(calculateTotalPrice(itemsList));
        return receipt;
    }

    private List<Item> calculateItemsSubtotal(List<Item> itemsList) {
        for(Item itemValue : itemsList)
        {
            itemValue.setSubtotal(itemValue.getQuantity()*itemValue.getUnitPrice());
        }
        return itemsList;
    }

    private int calculateTotalPrice(List<Item> itemsList) {
        int grandTotal = 0;
        for(Item itemValue : itemsList)
        {
            grandTotal += itemValue.getSubtotal();
        }
        return grandTotal;
    }

    private String generateReceipt(Receipt receipt) {
        String itemsDetail = generateItemsDetail(receipt);
        String receiptValue = "";
        return ("***<store earning no money>Receipt***\n" + itemsDetail + "----------------------\n" +
                "Total: " + receipt.getTotalPrice() + " (yuan)\n" +
                "**********************");
    }

    private String generateItemsDetail(Receipt receipt) {
        String itemsDetail = "";
        for (Item itemValue : receipt.getItems())
        {
            itemsDetail += "Name: "+ itemValue.getName() +
                    ", Quantity: " + itemValue.getQuantity() +
                    ", Unit price: " + itemValue.getUnitPrice() + " (yuan)" +
                    ", Subtotal: " + itemValue.getSubtotal() + " (yuan)\n";
        }
        return itemsDetail;
    }
}
