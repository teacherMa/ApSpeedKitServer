import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * @author teacherMa
 * Created on 2017/11/4.
 */
public class BuildConnectionThread extends Thread {
    private static final String PORT = "12345";

    private ExecutorService mExecutorService;

    BuildConnectionThread(ExecutorService executorService) {
        mExecutorService = executorService;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT));
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("new connection");
                mExecutorService.execute(new TestSpeedRunnable(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
