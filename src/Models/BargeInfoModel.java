/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Commands;
import App.Controller;
import App.Database;
import App.Query;
import java.io.File;
import java.io.FileFilter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author niekv
 */
public class BargeInfoModel extends BaseModel {

    public enum bargeData {
        BARGE_NAME,
        ENI_NUMBER,
        BROKER_NAME,
        QUALITY_MANAGER_NAME,
        OWNER_NAME,
        CURRENT_VERSION_KEY,
        CURRENT_VERSION_FOLDER,
        HIGHEST_VERSION_FOLDER,
        VERSION_FOLDER_COUNT
    };

    /**
     *
     * @param controller
     */
    public BargeInfoModel(Controller controller) {
        super(controller);
    }

    public Map<bargeData, String> fetchData() {
        Map<bargeData, String> dataMap = new TreeMap<>();

        dataMap.put(bargeData.ENI_NUMBER, Integer.toString(getEniNumber()));
        dataMap.put(bargeData.BARGE_NAME, getBargeName());
        dataMap.put(bargeData.BROKER_NAME, getBrokerName());
        dataMap.put(bargeData.QUALITY_MANAGER_NAME, getQualityManagerName());
        dataMap.put(bargeData.OWNER_NAME, getOwnerName());
        dataMap.put(bargeData.CURRENT_VERSION_KEY, getCurrentVersionKey());
        dataMap.put(bargeData.CURRENT_VERSION_FOLDER, getCurrentVersionFolder());
        dataMap.put(bargeData.VERSION_FOLDER_COUNT, Integer.toString(getVersionFolderCount()));
        dataMap.put(bargeData.HIGHEST_VERSION_FOLDER, getHighestVersionFolder());

        return dataMap;
    }

    public String getBargeName() {
        Database db = Database.getInstance();

        Query bargeNameQuery = new Query(
                "SELECT barge_name "
                + "FROM bcm_settings "
                + "ORDER BY last_backup_datetime DESC "
                + "LIMIT 1;");

        Object bargeNameResult = db.executeQuery(bargeNameQuery);

        String bargeName = null;

        if (bargeNameResult instanceof ResultSet) {
            ResultSet bargeNameResultSet = (ResultSet) bargeNameResult;
            try {
                if (bargeNameResultSet.next()) {
                    bargeName = bargeNameResultSet.getString("barge_name");
                }
            } catch (SQLException ex) {
            }
        }

        return bargeName;
    }

    public String getBrokerName() {
        Database db = Database.getInstance();

        Query brokerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sbb_clients "
                + "ORDER BY last_sync_datetime DESC "
                + "LIMIT 1;");

        Object brokerNameResult = db.executeQuery(brokerNameQuery);

        String brokerName = null;

        if (brokerNameResult instanceof ResultSet) {
            ResultSet brokerNameResultSet = (ResultSet) brokerNameResult;
            try {
                if (brokerNameResultSet.next()) {
                    brokerName = brokerNameResultSet.getString("client_name");
                }
            } catch (SQLException ex) {
            }
        }

        return brokerName;
    }

    public String getQualityManagerName() {
        Database db = Database.getInstance();

        Query qualityManagerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sqs_clients sqs JOIN bcm_settings bcm ON sqs.bcm_client_id = bcm.bcm_client_id "
                + "WHERE bcm.manager_sqs_client_id = sqs_client_id;");

        Object qualityManagerNameResult = db.executeQuery(qualityManagerNameQuery);

        String qualityManagerName = null;

        if (qualityManagerNameResult instanceof ResultSet) {
            ResultSet qualityManagerNameResultSet = (ResultSet) qualityManagerNameResult;
            try {
                if (qualityManagerNameResultSet.next()) {
                    qualityManagerName = qualityManagerNameResultSet.getString("client_name");
                }
            } catch (SQLException ex) {
            }
        }

        return qualityManagerName;
    }

    public String getOwnerName() {
        String ownerName = null;

        Database db = Database.getInstance();

        Query ownerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sqs_clients sqs JOIN bcm_settings bcm ON sqs.bcm_client_id = bcm.bcm_client_id "
                + "WHERE bcm.owner_sqs_client_id = sqs_client_id;");

        Object ownerNameResult = db.executeQuery(ownerNameQuery);

        if (ownerNameResult instanceof ResultSet) {
            ResultSet ownerNameResultSet = (ResultSet) ownerNameResult;
            try {
                if (ownerNameResultSet.next()) {
                    ownerName = ownerNameResultSet.getString("client_name");
                }
            } catch (SQLException ex) {
            }
        }

        return ownerName;
    }

    public String getCurrentVersionKey() {
        return "NOT IMPLEMENTED YET";
    }

    public String getCurrentVersionFolder() {
        return "NOT IMPLEMENTED YET";
    }

    public int getVersionFolderCount() {
        // create return number.
        File installFolder = new File("C:\\vessel solution\\");

        // Get the install folder from the database. If there is none found,
        // the default of 'C:\vessel solution\' will be used.
        Database db = Database.getInstance();

        Query installFolderStringQuery = new Query(
                "SELECT installation_folder_path "
                + "FROM bcm_settings;");

        Object installFolderStringResult = db.executeQuery(installFolderStringQuery);

        if (installFolderStringResult instanceof ResultSet) {
            ResultSet installFolderStringResultSet = (ResultSet) installFolderStringResult;
            try {
                if (installFolderStringResultSet.next()) {
                    String installFolderString = installFolderStringResultSet.getString("installation_folder_path");
                    installFolder = new File(installFolderString);
                }
            } catch (SQLException ex) {
            }
        }

        // Create a filefilter to only match folders that contain a file named 'servoy_runtime.jar'.
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                // If the file is not a directory, can instantly return false.
                if (!file.isDirectory()) {
                    return false;
                }

                File[] filesInDir = file.listFiles();
                // Return true if the folder contains a file with the name 'servoy_runtime.jar', 
                // this is the best way to determine whether or not a folder is a version folder.
                for (File f : filesInDir) {
                    if (f.getName().equals("servoy_runtime.jar")) {
                        return true;
                    }
                }
                return false;
            }
        };

        // Get all folders in the install folder, that match the previously made filefilter.
        List<File> versionFolders = Arrays.asList(installFolder.listFiles(fileFilter));

        // The size of the versionFolders list, is the amount of version folders in the install folder.
        return versionFolders.size();
    }

    public String getHighestVersionFolder() {
        return Commands.highestVersionFolder.getName();
    }

    public int getEniNumber() {
        int eni = -1;

        Database db = Database.getInstance();

        Query brokerNameQuery = new Query(
                "SELECT eni_number "
                + "FROM bcm_settings ");

        Object eniNumberResult = db.executeQuery(brokerNameQuery);

        if (eniNumberResult instanceof ResultSet) {
            ResultSet eniNumberResultSet = (ResultSet) eniNumberResult;
            try {
                if (eniNumberResultSet.next()) {
                    eni = Integer.parseInt(eniNumberResultSet.getString("eni_number"));
                }
            } catch (SQLException ex) {
            }
        }

        return eni;
    }
}
