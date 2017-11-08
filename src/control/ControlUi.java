package control;

import view.MainFrame;
import view.RefreshList;

import javax.swing.*;

/**
 * @author teacherMa
 * Created on 2017/11/8.
 */
public class ControlUi implements Runnable {
    private MainFrame mJFrame;

    public ControlUi() {
        mJFrame = new MainFrame();
    }

    @Override
    public void run() {
        mJFrame.setTitle("ApSpeedKitServer");
        mJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mJFrame.setVisible(true);
    }

    public RefreshList getOnNewTestCompleteImp(){
        return mJFrame.getConnections();
    }

    public MainFrame getJFrame() {
        return mJFrame;
    }
}
