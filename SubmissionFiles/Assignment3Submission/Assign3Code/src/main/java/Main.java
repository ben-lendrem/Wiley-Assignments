import controller.VendingController;


public class Main {
    public static void main(String[] args) {
        VendingController controlDriver = new VendingController("vendingItems.txt", "vendingAuditLog.txt");
        controlDriver.RunVendingMachine();
    }
}
