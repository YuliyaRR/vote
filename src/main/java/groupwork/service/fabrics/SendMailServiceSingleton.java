package groupwork.service.fabrics;

import groupwork.service.SendMailService;
import groupwork.service.api.ISendMailService;

import java.util.Properties;

public class SendMailServiceSingleton {

    private static Properties properties;

    private volatile static ISendMailService instance;

    private SendMailServiceSingleton() {
    }

    public static void setProperties(Properties properties){
        synchronized (SendMailServiceSingleton.class){
            if (instance != null) {
                throw new IllegalStateException("Нельзя менять настройки, приложение уже запущено");
            }
            SendMailServiceSingleton.properties = properties;
        }
    }

    public static ISendMailService getInstance() {
        if (instance == null){
            synchronized (SendMailServiceSingleton.class){
                if(instance == null) {
                    instance = new SendMailService(properties,
                            SingersServiceSingleton.getInstance(),
                            GenresServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
