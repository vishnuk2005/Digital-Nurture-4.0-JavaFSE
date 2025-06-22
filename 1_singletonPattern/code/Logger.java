

public class Logger {
    private static Logger instance;
    
    private Logger() {
        System.out.println("Initialized");
    }
    
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void log(String message) {
        System.out.println("Log: " + message);
    }
    
    public void error(String message) {
        System.out.println("Error: " + message);
    }
}


class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        
        System.out.println("Same: " + (logger1 == logger2));
        
        logger1.log("Started");
        logger2.error("Failed");
        
        new Thread(() -> {
            Logger logger3 = Logger.getInstance();
            logger3.log("Thread");
        }).start();
    }
}