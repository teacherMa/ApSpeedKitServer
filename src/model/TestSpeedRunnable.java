package model;

import model.bean.Connection;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;

/**
 * @author teacherMa
 * Created on 2017/11/4.
 */
public class TestSpeedRunnable implements Runnable {
//    private static final String PORT = "12345";
//    private static final String INFO_PORT = "12346";
    private Socket mClientSocket;
    private Socket mInfoSocket;

    private OnNewTestComplete mTestComplete;

    public TestSpeedRunnable(Socket clientSocket, Socket infoSocket) {
        mClientSocket = clientSocket;
        mInfoSocket = infoSocket;
    }

    public void setTestComplete(OnNewTestComplete testComplete) {
        mTestComplete = testComplete;
    }

    @Override
    public void run() {
        int testTime;
        int bufferSize;
//        int udpTimeOut;
//        int udpPort;
//        int clientPort;
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
//            udpTimeOut = Integer.parseInt(infoReader.readLine());
//            udpPort = Integer.parseInt(infoReader.readLine());
//            clientPort = Integer.parseInt(infoReader.readLine());

            int onceRecSize;
            int serverTcpTotalRecSize = 0;
            int clientTotalRecSize;
            byte[] buffer = new byte[bufferSize];

            System.out.println("tcp rec start");

            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime <= testTime
                    && (onceRecSize = inputStream.read(buffer)) != -1) {
                serverTcpTotalRecSize += onceRecSize;
            }

            System.out.println("tcp rec complete");

            infoPrinter.println(serverTcpTotalRecSize);

            System.out.println("tcp server rec " + serverTcpTotalRecSize);

            System.out.println("tcp send start");

            startTime = System.currentTimeMillis();
            while (testTime > (System.currentTimeMillis() - startTime)) {
                outputStream.write(buffer);
            }
            mClientSocket.shutdownOutput();

            System.out.println("tcp send finished");

            clientTotalRecSize = Integer.parseInt(infoReader.readLine());
            System.out.println("tcp client rec " + clientTotalRecSize);

            Connection curConnection = new Connection(mClientSocket.getInetAddress().toString(),
                    Integer.toString(mClientSocket.getPort()),
                    new DecimalFormat("###.##").format(serverTcpTotalRecSize/1024.0f/1024/testTime*1000)+"MB/s",
                    new DecimalFormat("###.##").format(clientTotalRecSize/1024.0f/1024/testTime*1000)+"MB/s");

            if (null != mTestComplete) {
                mTestComplete.onNewTestComplete(curConnection);
            }

            mClientSocket.close();
//            System.out.println("tcp test finished");
//
//            System.out.println("udp test start");
//
//            DatagramSocket udpSocket = new DatagramSocket(udpPort);
//
//            DatagramPacket packet = new DatagramPacket(new byte[bufferSize], bufferSize);
//
//            int udpServerTotalRecSize = 0;
//            int udpClientTotalRecSize;
//
//            udpSocket.setSoTimeout(udpTimeOut + testTime);
//
//            System.out.println("udp rec start");
//
//            startTime = System.currentTimeMillis();
//            try {
//                while (System.currentTimeMillis() - startTime <= testTime) {
//                    udpSocket.receive(packet);
//                    udpServerTotalRecSize += packet.getData().length;
//                }
//            }catch (SocketTimeoutException ignored){
//                ignored.printStackTrace();
//            }
//
//            System.out.println("udp rec complete");
//
//            System.out.println("udp rec " + udpServerTotalRecSize);
//
//            infoPrinter.println(udpServerTotalRecSize);
//
//            InetAddress clientUdpAddress = packet.getAddress();
//            packet = new DatagramPacket(buffer,0,bufferSize,clientUdpAddress,clientPort);
//
//            System.out.println("udp send start");
//
//            startTime = System.currentTimeMillis();
//            while (System.currentTimeMillis() - startTime <= testTime) {
//                udpSocket.send(packet);
//                packet.setLength(bufferSize);
//            }
//
//            System.out.println("udp send complete");
//            udpClientTotalRecSize = Integer.parseInt(infoReader.readLine());
//            System.out.println("udp client rec " + udpClientTotalRecSize);
//            udpSocket.close();
            mInfoSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
