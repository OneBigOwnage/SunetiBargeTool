/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.Commands;
import App.Controller;
import Database.DatabaseHelper;
import Database.Query;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author niekv
 */
public class BargeInfoModel extends BaseModel {

    /**
     * Enumeration to have constants for the barge data.
     */
    public enum bargeData {
        BARGE_NAME,
        ENI_NUMBER,
        BROKER_NAME,
        QUALITY_MANAGER_NAME,
        OWNER_NAME,
        CURRENT_VERSION_KEY,
        CURRENT_VERSION_FOLDER,
        LAST_BCM_UPDATES,
        HIGHEST_VERSION_FOLDER,
        VERSION_FOLDER_COUNT,
        VERSION_IN_BAT;
    };

    /**
     * Default constructor for the BargeInfoModel class.
     *
     * @param controller The controller that you want to use to initialize this
     * model.
     */
    public BargeInfoModel(Controller controller) {
        super(controller);
    }

    public Map<bargeData, String> fetchData() {
        Map<bargeData, String> dataMap = new TreeMap<>();

        dataMap.put(bargeData.ENI_NUMBER, getEniNumber());
        dataMap.put(bargeData.BARGE_NAME, getBargeName());
        dataMap.put(bargeData.BROKER_NAME, getBrokerName());
        dataMap.put(bargeData.QUALITY_MANAGER_NAME, getQualityManagerName());
        dataMap.put(bargeData.OWNER_NAME, getOwnerName());

        dataMap.put(bargeData.CURRENT_VERSION_KEY, getCurrentVersionKey());
        dataMap.put(bargeData.CURRENT_VERSION_FOLDER, getCurrentVersionFolder());
        dataMap.put(bargeData.LAST_BCM_UPDATES, getLastBcmUpdatesVersion());

        dataMap.put(bargeData.VERSION_IN_BAT, "Not found"/*getVersionInBat()*/);
        dataMap.put(bargeData.HIGHEST_VERSION_FOLDER, getHighestVersionFolder());
        dataMap.put(bargeData.VERSION_FOLDER_COUNT, getVersionFolderCount());

        return dataMap;
    }

    /**
     * Method to retrieve the barge name from the database.
     *
     * @return
     */
    public String getBargeName() {
        Query bargeNameQuery = new Query(
                "SELECT barge_name "
                + "FROM bcm_settings "
                + "ORDER BY last_backup_datetime DESC "
                + "LIMIT 1;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(bargeNameQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

    public String getBrokerName() {
        Query brokerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sbb_clients "
                + "ORDER BY last_sync_datetime DESC "
                + "LIMIT 1;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(brokerNameQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

    public String getQualityManagerName() {
        Query qualityManagerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sqs_clients sqs "
                + "JOIN bcm_settings bcm "
                + "ON sqs.bcm_client_id = bcm.bcm_client_id "
                + "WHERE bcm.manager_sqs_client_id = sqs_client_id;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(qualityManagerNameQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

    public String getOwnerName() {
        Query ownerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sqs_clients sqs "
                + "JOIN bcm_settings bcm "
                + "ON sqs.bcm_client_id = bcm.bcm_client_id "
                + "WHERE bcm.owner_sqs_client_id = sqs_client_id;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(ownerNameQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

    public String getCurrentVersionKey() {
        Query currentVersionKeyQuery = new Query(
                "SELECT current_version_key "
                + "FROM bcm_settings;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(currentVersionKeyQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

    public String getCurrentVersionFolder() {
        Query currentVersionFolderQuery = new Query(
                "SELECT current_version_folder_name "
                + "FROM bcm_settings;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(currentVersionFolderQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

    public String getVersionFolderCount() {
        File installFolder = new File("C:\\vessel solution\\");

        // Get the install folder from the database. If there is none found,
        // the default of 'C:\vessel solution\' will be used.
        Query installFolderStringQuery = new Query(
                "SELECT installation_folder_path "
                + "FROM bcm_settings;");

        String installFolderString = (String) DatabaseHelper.getSingleResultFromQuery(installFolderStringQuery);

        // If the return value of the query is not null, the installation folder is taken from the database.
        if (installFolderString != null && new File(installFolderString).exists()) {
            installFolder = new File(installFolderString);
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
        return Integer.toString(versionFolders.size());
    }

    public String getHighestVersionFolder() {
        return Commands.highestVersionFolder.getName();
    }

    public String getEniNumber() {
        Query eniNumberQuery = new Query(
                "SELECT eni_number "
                + "FROM bcm_settings;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(eniNumberQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

    public String getLastBcmUpdatesVersion() {
        Query lastUpdateQuery = new Query(
                "SELECT version_key "
                + "FROM bcm_updates "
                + "ORDER BY update_sequence DESC "
                + "LIMIT 1;");

        Object obj = DatabaseHelper.getSingleResultFromQuery(lastUpdateQuery);

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return "Not found";
        }
    }

}
