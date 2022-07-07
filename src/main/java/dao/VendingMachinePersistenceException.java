package src.main.java.dao;

public class VendingMachinePersistenceException extends Exception{

    // show exceptional message
    public VendingMachinePersistenceException (String message) {
        super(message);
    }
    // show exceptional message with cause
    public VendingMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
