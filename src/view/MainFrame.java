package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author teacherMa
 * Created on 2017/11/8.
 */
public class MainFrame extends JFrame {
    private int mPort;
    private String mIp;
    private Connections mConnections;
    private JLabel mInfoJLabel;
    private JLabel mTitleJLabel;

    public MainFrame() throws HeadlessException {
        setLayout(new GridLayout(2, 1));

        mInfoJLabel = new JLabel("本机IP: " + mIp + " ,端口号：" + mPort, JLabel.CENTER);
        mInfoJLabel.setFont(new Font("宋体",Font.BOLD, 54));
        add(mInfoJLabel);

//        mTitleJLabel = new JLabel("请将本机IP和端口号告诉学生", JLabel.CENTER);
//        mTitleJLabel.setFont(new Font("宋体",Font.BOLD, 12));
//        add(mTitleJLabel);

        mConnections = new Connections();
        add(mConnections.getScrollPane());

        pack();
    }

    public Connections getConnections() {
        return mConnections;
    }

    public void setPort(int port) {
        mPort = port;
        mInfoJLabel.setText("本机IP: " + mIp + " ,端口号：" + mPort);
    }

    public void setIp(String ip) {
        mIp = ip;
        mInfoJLabel.setText("本机IP: " + mIp + " ,端口号：" + mPort);
    }
}
