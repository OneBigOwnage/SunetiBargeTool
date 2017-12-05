/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Models.BargeInfoModel;
import Models.BaseModel;
import UI.BargeInfoView;
import UI.ConfigView;
import UI.LogView;
import UI.LoginView;
import UI.SQLView;
import UI.SideBar;
import UI.UILib;
import UI.UserInterface;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
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
    
    private void fixUserInterface() {
        List<Component> componentList = new ArrayList<>();
        
        for (Map.Entry<String, JPanel> view : viewList.entrySet()) {
            componentList.addAll(UILib.getAllComponents(view.getValue()));
        }

        for (Component component : componentList) {
            if (component instanceof JButton) {
                UILib.UIButtonHelper((JButton) component);
            } else if (component instanceof JPanel && ((JPanel)component).getBorder() instanceof TitledBorder) {
                UILib.UITitledBorderHelper((JPanel) component);
            }
        }

    }

    private void initModels() {
        modelList.put("BargeInfoModel", new BargeInfoModel(this));
    }

    private void initViews() {
        viewList.put("BargeInfoView", new BargeInfoView(this, (BargeInfoModel) modelList.get("BargeInfoModel")));
        viewList.put("LogView", new LogView(this));
        viewList.put("LoginView", new LoginView(this));
        viewList.put("SQLView", new SQLView(this));
        viewList.put("SideBar", new SideBar(this));
        viewList.put("ConfigView", new ConfigView(this));
    }

    public void log(String text) {
        ((LogView) viewList.get("logView")).appendToLog(text);
    }

}
