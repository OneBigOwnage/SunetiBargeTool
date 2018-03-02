/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

import App.Config;
import CLI.CLIWrapper;
import HelperClasses.Utils;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author niekv
 */
public class PostGresBackup {

    private final static String PG_DUMP_PATH = "C:\\vessel solution\\database\\postgres_db\\bin\\pg_dump.exe";
    private final String[] includedTables;

    public PostGresBackup() {
        this.includedTables = new String[0];
    }

    public PostGresBackup(String[] tables) {
        this.includedTables = tables;
    }

    public void make() {
        CLIWrapper cli = new CLIWrapper(PG_DUMP_PATH);

        Map<String, Object> argVals = new HashMap<>();

        cli.addArgument("--host");
        cli.addArgument("${host}");
        argVals.put("host", Config.getString("db_host"));

        cli.addArgument("--port");
        cli.addArgument("${port}");
        argVals.put("port", Config.getString("db_port"));

        cli.addArgument("--username");
        cli.addArgument("${username}");
        argVals.put("username", Config.getString("db_username"));

        cli.addArgument("--format");
        cli.addArgument("custom");

        cli.addArgument("--blobs");

        cli.addArgument("--file");
        cli.addArgument("${file_name}");
        argVals.put("file_name", "C:\\vessel solution\\etc\\Suneti Barge Tool\\Backups\\file.backup"
        /*+ Utils.getCurrentTimeFormatted("yyyy-MM-dd HH:mm")
        + ".backup"*/);

        for (String table : includedTables) {
            cli.addArgument("--table");
            cli.addArgument("${table_" + table + "}");
            cli.addArgumentValue("table_" + table, table);
        }

        cli.addArgument("bcm");

        argVals.put("PGPASSWORD", Config.getString("db_password"));

        cli.addAllArgumentValues(argVals);

        cli.executeCommand();
    }

}
