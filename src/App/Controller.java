/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Models.BargeInfoModel;
import Models.BaseModel;
import Models.ProcedureModel;
import Models.SQLModel;
import UI.BargeInfoView;
import UI.ConfigView;
import UI.LogView;
import UI.LoginView;
import UI.SQLView;
import UI.SideBar;
import UI.StandardProcedureView;
import UiHelpers.UiLib;
import UI.UserInterface;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import sunetibargetool.Config;
import sunetibargetool.Config.Model;
import sunetibargetool.Config.View;

/**
 *
 * @author niekv
 */
public class Controller {

    protected UserInterface userInterface;
    protected Map<View, JPanel> viewList = new TreeMap<>();
    protected Map<Model, BaseModel> modelList = new TreeMap<>();

    public Controller() {
        this.userInterface = new UserInterface();
        this.userInterface.setLocationRelativeTo(null);
        this.userInterface.setVisible(true);
        initModels();
        initViews();
        fixUserInterface();
    }

    /**
     * Shows the showLoginView screen.
     */
    public void showLoginView() {
        this.userInterface.setPanel(viewList.get(Config.View.LOGIN_VIEW), BorderLayout.CENTER);
    }

    /**
     * Method called after successful showLoginView. Initializes the side bar.
     */
    public void afterLogin() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get(Config.View.SIDE_BAR), BorderLayout.WEST);
        this.userInterface.setPanel(viewList.get(Config.View.BARGE_INFO_VIEW), BorderLayout.CENTER);
    }

    /**
     * Shows the screen with information about the barge/solution.
     */
    public void showBargeInfoView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get(Config.View.BARGE_INFO_VIEW), BorderLayout.CENTER);
    }

    /**
     * Shows the configuration screen
     */
    public void showConfigView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get(Config.View.CONFIG_VIEW), BorderLayout.CENTER);
    }

    public void showSQLView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get(Config.View.SQL_VIEW), BorderLayout.CENTER);
    }

    public void showLogView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get(Config.View.LOG_VIEW), BorderLayout.CENTER);
    }

    public void showStandardProcedureView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get(Config.View.STANDARD_PROCEDURE_VIEW), BorderLayout.CENTER);
    }

    private void fixUserInterface() {
        List<Component> componentList = new ArrayList<>();

        for (Map.Entry<View, JPanel> view : viewList.entrySet()) {
            componentList.addAll(UiLib.getAllComponents(view.getValue()));
        }

        for (Component component : componentList) {
            if (component instanceof JButton) {
                UiLib.UIButtonHelper((JButton) component);
            } else if (component instanceof JPanel && ((JPanel) component).getBorder() instanceof TitledBorder) {
                UiLib.UITitledBorderHelper((JPanel) component);
            }
        }

    }

    private void initModels() {
        modelList.put(Config.Model.BARGE_INFO_MODEL, new BargeInfoModel(this));
        modelList.put(Config.Model.SQL_MODEL, new SQLModel(this));
        modelList.put(Config.Model.PROCEDURE_MODEL, new ProcedureModel(this));
    }

    private void initViews() {
        viewList.put(Config.View.LOG_VIEW, new LogView(this));
        viewList.put(Config.View.BARGE_INFO_VIEW, new BargeInfoView(this, (BargeInfoModel) modelList.get(Config.Model.BARGE_INFO_MODEL)));
        viewList.put(Config.View.LOGIN_VIEW, new LoginView(this));
        viewList.put(Config.View.SQL_VIEW, new SQLView(this, (SQLModel) modelList.get(Config.Model.SQL_MODEL)));
        viewList.put(Config.View.SIDE_BAR, new SideBar(this));
        viewList.put(Config.View.CONFIG_VIEW, new ConfigView(this));
        viewList.put(Config.View.STANDARD_PROCEDURE_VIEW, new StandardProcedureView(this, (ProcedureModel) modelList.get(Config.Model.PROCEDURE_MODEL)));
    }

    public void log(String text) {
        ((LogView) viewList.get(Config.View.LOG_VIEW)).appendToLog(text);
    }

}
