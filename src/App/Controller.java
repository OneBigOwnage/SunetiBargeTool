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

/**
 *
 * @author niekv
 */
public class Controller {

    protected UserInterface userInterface;
    protected Map<String, JPanel> viewList = new TreeMap<>();
    protected Map<String, BaseModel> modelList = new TreeMap<>();

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
        this.userInterface.setPanel(viewList.get("LoginView"), BorderLayout.CENTER);
    }

    /**
     * Method called after successful showLoginView. Initializes the side bar.
     */
    public void afterLogin() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get("SideBar"), BorderLayout.WEST);
        this.userInterface.setPanel(viewList.get("BargeInfoView"), BorderLayout.CENTER);
    }

    /**
     * Shows the screen with information about the barge/solution.
     */
    public void showBargeInfoView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get("BargeInfoView"), BorderLayout.CENTER);
    }

    /**
     * Shows the configuration screen
     */
    public void showConfigView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get("ConfigView"), BorderLayout.CENTER);
    }

    public void showSQLView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get("SQLView"), BorderLayout.CENTER);
    }

    public void showLogView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get("LogView"), BorderLayout.CENTER);
    }

    public void showStandardProcedureView() {
        this.userInterface.clearPanel(BorderLayout.CENTER);
        this.userInterface.setPanel(viewList.get("ProcedureView"), BorderLayout.CENTER);
    }

    private void fixUserInterface() {
        List<Component> componentList = new ArrayList<>();

        for (Map.Entry<String, JPanel> view : viewList.entrySet()) {
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
        modelList.put("BargeInfoModel", new BargeInfoModel(this));
        modelList.put("SQLModel", new SQLModel(this));
        modelList.put("ProcedureModel", new ProcedureModel(this));
    }

    private void initViews() {
        viewList.put("LogView", new LogView(this));
        viewList.put("BargeInfoView", new BargeInfoView(this, (BargeInfoModel) modelList.get("BargeInfoModel")));
        viewList.put("LoginView", new LoginView(this));
        viewList.put("SQLView", new SQLView(this, (SQLModel) modelList.get("SQLModel")));
        viewList.put("SideBar", new SideBar(this));
        viewList.put("ConfigView", new ConfigView(this));
        viewList.put("ProcedureView", new StandardProcedureView(this, (ProcedureModel) modelList.get("ProcedureModel")));
    }

    public void log(String text) {
        ((LogView) viewList.get("LogView")).appendToLog(text);
    }

}
