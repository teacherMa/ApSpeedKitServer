import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author teacherMa
 * Created on 2017/11/4.
 */
public class ServerConnection {
    public static void main(String[] args) {
        ExecutorService executors = Executors.newCachedThreadPool();
        try {
            new BuildConnectionThread(executors).start();
        }catch (Exception e){
            System.exit(-1);
        }
    }

}
