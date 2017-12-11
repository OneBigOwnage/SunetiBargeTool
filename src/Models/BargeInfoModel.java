/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import App.CommandLineWrapper;
import App.Controller;
import App.Database;
import App.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author niekv
 */
public class BargeInfoModel extends BaseModel {


    /**
     *
     * @param controller
     */
    public BargeInfoModel(Controller controller) {
        super(controller);
    }

    public Map<String, String> fetchData() {
        Map<String, String> dataMap = new TreeMap<>();

        dataMap.put("bargeName", getBargeName());
        dataMap.put("brokerName", getBrokerName());
        dataMap.put("qualityManagerName", getQualityManagerName());
        dataMap.put("ownerName", getOwnerName());
        
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
        Database db = Database.getInstance();

        Query ownerNameQuery = new Query(
                "SELECT client_name "
                + "FROM bcm_sqs_clients sqs JOIN bcm_settings bcm ON sqs.bcm_client_id = bcm.bcm_client_id "
                + "WHERE bcm.owner_sqs_client_id = sqs_client_id;");

        Object ownerNameResult = db.executeQuery(ownerNameQuery);

        String ownerName = null;

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
}
