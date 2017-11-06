import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author teacherMa
 * Created on 2017/11/4.
 */
public class TestSpeedRunnable implements Runnable {
    private Socket mClientSocket;
    private static final int PACKAGE_SIZE = 1024;
    private static final int DOWN_TIME = 1000;

    TestSpeedRunnable(Socket clientSocket) {
        mClientSocket = clientSocket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[PACKAGE_SIZE];
        try {
            InputStream inputStream = mClientSocket.getInputStream();
            int recBufferSize;
            int totalRecSize = 0;
            while ((recBufferSize = inputStream.read(buffer)) != -1) {
                totalRecSize += recBufferSize;
            }
            System.out.println("Rec finished: "+totalRecSize);
            OutputStream outputStream = mClientSocket.getOutputStream();
            outputStream.write(Kit.intToByteArray(totalRecSize));
            long startTime = System.currentTimeMillis();
            while (DOWN_TIME > (System.currentTimeMillis() - startTime)) {
                outputStream.write(buffer);
            }
            mClientSocket.shutdownOutput();
            System.out.println("Send finished");
            mClientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
