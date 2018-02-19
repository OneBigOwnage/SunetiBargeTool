/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Models.BackupModel;
import Models.BargeInfoModel;
import Models.BaseModel;
import Models.ProcedureModel;
import Models.SQLModel;
import UI.BackupView;
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
        this.userInterface.setupSidebar(viewList.get(View.SIDE_BAR), BorderLayout.WEST);
        fixUserInterface();
    }

    /**
     * Shows the Login View.
     */
    public void showLoginView() {
        this.userInterface.showView(viewList.get(View.LOGIN_VIEW));
    }

    /**
     * Method called after successfully logging in. Also shows the side bar.
     */
    public void afterLogin() {
        this.userInterface.showSidebar(true);
        this.userInterface.showView(viewList.get(View.BARGE_INFO_VIEW));
    }

    /**
     * Shows the screen with information about the barge/solution.
     */
    public void showBargeInfoView() {
        this.userInterface.showView(viewList.get(View.BARGE_INFO_VIEW));
    }

    /**
     * Shows the configuration screen
     */
    public void showConfigView() {
        this.userInterface.showView(viewList.get(View.CONFIG_VIEW));
    }

    public void showSQLView() {
        this.userInterface.showView(viewList.get(View.SQL_VIEW));
    }

    public void showLogView() {
        this.userInterface.showView(viewList.get(View.LOG_VIEW));
    }

    public void showBackupView() {
        this.userInterface.showView(viewList.get(View.BACKUP_VIEW));
    }

    public void showStandardProcedureView() {
        this.userInterface.showView(viewList.get(View.STANDARD_PROCEDURE_VIEW));
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
        modelList.put(Model.BARGE_INFO_MODEL, new BargeInfoModel(this));
        modelList.put(Model.SQL_MODEL, new SQLModel(this));
        modelList.put(Model.PROCEDURE_MODEL, new ProcedureModel(this));
        modelList.put(Model.BACKUP_MODEL, new BackupModel(this));
    }

    private void initViews() {
        viewList.put(View.LOG_VIEW, new LogView(this));
        viewList.put(View.BARGE_INFO_VIEW, new BargeInfoView(this, (BargeInfoModel) modelList.get(Model.BARGE_INFO_MODEL)));
        viewList.put(View.LOGIN_VIEW, new LoginView(this));
        viewList.put(View.SQL_VIEW, new SQLView(this, (SQLModel) modelList.get(Model.SQL_MODEL)));
        viewList.put(View.SIDE_BAR, new SideBar(this));
        viewList.put(View.CONFIG_VIEW, new ConfigView(this));
        viewList.put(View.STANDARD_PROCEDURE_VIEW, new StandardProcedureView(this, (ProcedureModel) modelList.get(Model.PROCEDURE_MODEL)));
        viewList.put(View.BACKUP_VIEW, new BackupView(this, (BackupModel) modelList.get(Model.BACKUP_MODEL)));
    }

    public void log(String text) {
        ((LogView) viewList.get(View.LOG_VIEW)).appendToLog(text);
    }

    /**
     * Retrieves a view by Config.View.
     *
     * @param viewName Name of the view you want to retrieve, in Config.View
     * enumeration format.
     * @return
     */
    public JPanel getView(View viewName) {
        return viewList.get(viewName);
    }

    /**
     * Retrieves a model by Config.Model.
     *
     * @param modelName Name of the model you want to retrieve, in Config.Model
     * enumeration format.
     * @return
     */
    public BaseModel getModel(Model modelName) {
        return modelList.get(modelName);
    }

}
