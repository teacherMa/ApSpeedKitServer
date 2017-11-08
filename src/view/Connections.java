package view;

import model.bean.Connection;

import javax.swing.*;

/**
 * @author teacherMa
 * Created on 2017/11/8.
 */
public class Connections implements RefreshList {
    private DefaultListModel<Connection> mAllConnections;
    private JList<Connection> mJList;
    private JScrollPane mScrollPane;
    private static final int VISIBLE_ROW_COUNT = 10;


    public Connections() {
        showOnScreen();
    }

    private void showOnScreen(){
        mAllConnections = new DefaultListModel<>();
        mJList = new JList<>(mAllConnections);
        mScrollPane = new JScrollPane(mJList);
        mJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mJList.setVisibleRowCount(VISIBLE_ROW_COUNT);
        mJList.setModel(mAllConnections);
    }

    public DefaultListModel<Connection> getAllConnections() {
        return mAllConnections;
    }

    public void setAllConnections(DefaultListModel<Connection> allConnections) {
        mAllConnections = allConnections;
    }

    public JList<Connection> getJList() {
        return mJList;
    }

    public void setJList(JList<Connection> JList) {
        mJList = JList;
    }

    public JScrollPane getScrollPane() {
        return mScrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        mScrollPane = scrollPane;
    }

    @Override
    public void refresh(DefaultListModel<Connection> newData) {
        mJList.setModel(newData);
    }
}
