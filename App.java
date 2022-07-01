import src.main.java.controller.VendingMachineController;
import src.main.java.dao.VendingMachineAuditDao;
import src.main.java.dao.VendingMachineAuditDaoFileImpl;
import src.main.java.dao.VendingMachineDao;
import src.main.java.dao.VendingMachineDaoFileImpl;
import src.main.java.service.VendingMachineServiceLayer;
import src.main.java.service.VendingMachineServiceLayerImpl;
import src.main.java.ui.UserIO;
import src.main.java.ui.UserIOConsoleImpl;
import src.main.java.ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineDao dao = new VendingMachineDaoFileImpl("VendingMachine.txt");
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);

        VendingMachineController controller = new VendingMachineController(view, service);

        controller.run();
    }
}
