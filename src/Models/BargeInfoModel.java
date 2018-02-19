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
        HIGHEST_VERSION_FOLDER,
        VERSION_FOLDER_COUNT
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
        dataMap.put(bargeData.VERSION_FOLDER_COUNT, Integer.toString(getVersionFolderCount()));
        dataMap.put(bargeData.HIGHEST_VERSION_FOLDER, getHighestVersionFolder());

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

        return (String) DatabaseHelper.getSingleResultFromQuery(bargeNameQuery);
    }

    public String getBrokerName() {
        Query brokerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sbb_clients "
                + "ORDER BY last_sync_datetime DESC "
                + "LIMIT 1;");

        return (String) DatabaseHelper.getSingleResultFromQuery(brokerNameQuery);
    }

    public String getQualityManagerName() {
        Query qualityManagerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sqs_clients sqs JOIN bcm_settings bcm ON sqs.bcm_client_id = bcm.bcm_client_id "
                + "WHERE bcm.manager_sqs_client_id = sqs_client_id;");
        return (String) DatabaseHelper.getSingleResultFromQuery(qualityManagerNameQuery);
    }

    public String getOwnerName() {
        Query ownerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sqs_clients sqs JOIN bcm_settings bcm ON sqs.bcm_client_id = bcm.bcm_client_id "
                + "WHERE bcm.owner_sqs_client_id = sqs_client_id;");

        return (String) DatabaseHelper.getSingleResultFromQuery(ownerNameQuery);
    }

    public String getCurrentVersionKey() {
        Query currentVersionKeyQuery = new Query(
                "SELECT current_version_key "
                + "FROM bcm_settings;");

        return (String) DatabaseHelper.getSingleResultFromQuery(currentVersionKeyQuery);
    }

    public String getCurrentVersionFolder() {
        Query currentVersionFolderQuery = new Query(
                "SELECT current_version_folder_name "
                + "FROM bcm_settings;");
        return (String) DatabaseHelper.getSingleResultFromQuery(currentVersionFolderQuery);
    }

    public int getVersionFolderCount() {
        File installFolder = new File("C:\\vessel solution\\");

        // Get the install folder from the database. If there is none found,
        // the default of 'C:\vessel solution\' will be used.
        Query installFolderStringQuery = new Query(
                "SELECT installation_folder_path "
                + "FROM bcm_settings;");

        String installFolderString = (String) DatabaseHelper.getSingleResultFromQuery(installFolderStringQuery);

        // If the return value of the query is not null, the installation folder is taken from the database.
        if (installFolderString != null) {
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
        return versionFolders.size();
    }

    public String getHighestVersionFolder() {
        return Commands.highestVersionFolder.getName();
    }

    public String getEniNumber() {
        Query eniNumberQuery = new Query(
                "SELECT eni_number "
                + "FROM bcm_settings;");

        return (String) DatabaseHelper.getSingleResultFromQuery(eniNumberQuery);
    }
}
