package view;

import model.bean.Connection;

import javax.swing.*;

/**
 * @author teacherMa
 * Created on 2017/11/8.
 */
public interface RefreshList {
    void refresh(DefaultListModel<Connection> newData);
}
