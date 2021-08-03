package pos.machine;
import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> items = convertToItems(barcodes);
        return "";
    }

    private List<Item> convertToItems(List<String> barcodes) {
        LinkedList<Item> items = new LinkedList<Item>();
        barcodes = new ArrayList<>(new LinkedHashSet<>(barcodes));
        //load items here
        //Check per barcode in barcodes
        //push the items on the list of items retrieved based on barcode
        //return items
        return items;
    }
}
