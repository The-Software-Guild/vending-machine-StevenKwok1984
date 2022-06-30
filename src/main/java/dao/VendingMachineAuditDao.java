package src.main.java.dao;

public interface VendingMachineAuditDao{
    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
