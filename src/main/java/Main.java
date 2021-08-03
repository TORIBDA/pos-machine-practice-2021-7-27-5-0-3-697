import pos.machine.ItemDataLoader;
import pos.machine.PosMachine;

public class Main {
    public static void main(String[] args) {
        PosMachine posMachine = new PosMachine();
        System.out.println(posMachine.printReceipt(ItemDataLoader.loadBarcodes()));
    }
}