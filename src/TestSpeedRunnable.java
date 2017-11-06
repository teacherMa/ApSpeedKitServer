import java.io.*;
import java.net.Socket;

/**
 * @author teacherMa
 * Created on 2017/11/4.
 */
public class TestSpeedRunnable implements Runnable {
    private Socket mClientSocket;
    private Socket mInfoSocket;

    public TestSpeedRunnable(Socket clientSocket, Socket infoSocket) {
        mClientSocket = clientSocket;
        mInfoSocket = infoSocket;
    }

    @Override
    public void run() {
        int testTime;
        int bufferSize;
        try {
            System.out.println("tcp test start");
            //Stream used to test speed between client and server
            InputStream inputStream = mClientSocket.getInputStream();
            OutputStream outputStream = mClientSocket.getOutputStream();

            //Stream used to transport information between client and server
            InputStream infoInputStream = mInfoSocket.getInputStream();
            OutputStream infoOutputStream = mInfoSocket.getOutputStream();

            PrintStream infoPrinter = new PrintStream(infoOutputStream);

            BufferedReader infoReader = new BufferedReader(new InputStreamReader(infoInputStream));
            testTime = Integer.parseInt(infoReader.readLine());
            bufferSize = Integer.parseInt(infoReader.readLine());

            int onceRecSize;
            int serverTcpTotalRecSize = 0;
            byte[] buffer = new byte[bufferSize];
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= testTime
                    && (onceRecSize = inputStream.read(buffer)) != -1) {
                serverTcpTotalRecSize += onceRecSize;
            }

            infoPrinter.println(serverTcpTotalRecSize);

            System.out.println("tcp rec " + serverTcpTotalRecSize);

            startTime = System.currentTimeMillis();
            while (testTime > (System.currentTimeMillis() - startTime)) {
                outputStream.write(buffer);
            }
            mClientSocket.shutdownOutput();

            System.out.println("tcp send finished");

            int clientTotalRecSize = Integer.parseInt(infoReader.readLine());
            System.out.println("tcp client rec " + clientTotalRecSize);

            System.out.println("tcp test finished");

            System.out.println("udp test start");

            mClientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
