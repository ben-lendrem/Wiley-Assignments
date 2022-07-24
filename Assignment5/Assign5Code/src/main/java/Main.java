import controller.VendingController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        //Create a link between the main function and the xml config
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //from the xml config, get the bean corresponding to the controller tag
        VendingController controlDriver = appContext.getBean("controller", VendingController.class);
        //run the system
        controlDriver.RunVendingMachine();
    }
}


