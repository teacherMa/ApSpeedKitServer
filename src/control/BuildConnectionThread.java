package control;

import model.OnNewTestComplete;
import model.TestSpeedRunnable;
import model.bean.Connection;
import view.RefreshList;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author teacherMa
 * Created on 2017/11/4.
 */
public class BuildConnectionThread extends Thread implements OnNewTestComplete{
    private static final String INFO_PORT = "12346";
    private ExecutorService mExecutorService;
    private RefreshList mRefreshList;
    private DefaultListModel<Connection> mModel;
    private int mPort;

    public BuildConnectionThread() {
        mExecutorService = Executors.newCachedThreadPool();
        mModel = new DefaultListModel<>();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(mPort);
            ServerSocket infoSocket = new ServerSocket(Integer.parseInt(INFO_PORT));
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Socket clientInfo = infoSocket.accept();
                if (clientSocket.getInetAddress().toString().equals(clientInfo.getInetAddress().toString())) {
                    System.out.println("new connection");
                    TestSpeedRunnable testSpeedRunnable = new TestSpeedRunnable(clientSocket, clientInfo);
                    testSpeedRunnable.setTestComplete(BuildConnectionThread.this);
                    mExecutorService.execute(testSpeedRunnable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNewTestComplete(Connection newResult) {
        if (mRefreshList == null) {
            return;
        }
        synchronized (this) {
            int size = mModel.size();
            for (int i = 0; i < size; i++) {
                Connection connection = mModel.get(i);
                if (connection.getIp().equals(newResult.getIp())) {
                    mModel.remove(i);
                    mModel.add(i, newResult);
                    mRefreshList.refresh(mModel);
                    return;
                }
            }
            mModel.addElement(newResult);
            mRefreshList.refresh(mModel);
        }
    }

    void setRefreshList(RefreshList refreshList) {
        mRefreshList = refreshList;
    }

    public void setPort(int port) {
        mPort = port;
    }
}
