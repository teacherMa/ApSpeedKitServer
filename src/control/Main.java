package control;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author teacherMa
 * Created on 2017/11/4.
 */
public class Main {
    public static void main(String[] args) {
        try {

            InetAddress address;
            String ip = "0.0.0.0";
            try {
                address = InetAddress.getLocalHost();
                ip = address.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            int port = (int) (10000+Math.random()*55535);

            ControlUi controlUi = new ControlUi();
            controlUi.getJFrame().setIp(ip);
            controlUi.getJFrame().setPort(port);

            EventQueue.invokeLater(controlUi);

            BuildConnectionThread buildConnectionThread = new BuildConnectionThread();
            buildConnectionThread.setPort(port);

            buildConnectionThread.setRefreshList(controlUi.getOnNewTestCompleteImp());

            buildConnectionThread.start();
        } catch (Exception e) {
            System.exit(-1);
        }
    }

}
