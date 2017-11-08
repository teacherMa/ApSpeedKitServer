package model.bean;

/**
 * @author teacherMa
 * Created on 2017/11/8.
 */
public class Connection {
    private String mIp;
    private String mPort;
    private String mTcpUpwardSpeed;
    private String mTcpDownSpeed;

    public Connection(String ip, String port, String tcpUpwardSpeed, String tcpDownSpeed) {
        mIp = ip;
        mPort = port;
        mTcpUpwardSpeed = tcpUpwardSpeed;
        mTcpDownSpeed = tcpDownSpeed;
    }

    public Connection() {
    }

    @Override
    public String toString() {
        return "IP: " + mIp + " Port: " + mPort + " upward: " + mTcpUpwardSpeed + " down: " + mTcpDownSpeed;
    }

    public String getIp() {
        return mIp;
    }

    public void setIp(String ip) {
        mIp = ip;
    }

    public String getPort() {
        return mPort;
    }

    public void setPort(String port) {
        mPort = port;
    }

    public String getTcpUpwardSpeed() {
        return mTcpUpwardSpeed;
    }

    public void setTcpUpwardSpeed(String tcpUpwardSpeed) {
        mTcpUpwardSpeed = tcpUpwardSpeed;
    }

    public String getTcpDownSpeed() {
        return mTcpDownSpeed;
    }

    public void setTcpDownSpeed(String tcpDownSpeed) {
        mTcpDownSpeed = tcpDownSpeed;
    }
}
