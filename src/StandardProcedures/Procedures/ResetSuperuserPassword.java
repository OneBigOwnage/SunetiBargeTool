/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StandardProcedures.Procedures;

import Database.Database;
import Database.Query;

/**
 *
 * @author niekv
 */
public class ResetSuperuserPassword extends StandardProcedures.StandardProcedure {

    public ResetSuperuserPassword() {
        super(
                "Reset superuser password",
                "Resets the password of the Suneti superuser account.",
                new String[] {},
                2.5
        );
    }

    @Override
    public void performProcedure() {
        sendFeedback("Preparing query.");
        
        Query query = new Query("WITH affected_row AS (\n" +
        "	UPDATE sec_user_password\n" +
        "	SET password_value = '/piD7V2qf7Ia6b9p6n4sgA==', end_date = NOW() + INTERVAL '2 years'\n" +
        "	WHERE user_password_id = (\n" +
        "		SELECT user_password_id\n" +
        "		FROM sec_user_password pw\n" +
        "		JOIN sec_user usr\n" +
        "		ON pw.user_id = usr.user_id\n" +
        "		WHERE user_name = 'superuser'\n" +
        "		ORDER BY end_date DESC\n" +
        "		LIMIT 1\n" +
        "	)\n" +
        "	RETURNING user_id, user_password_id\n" +
        ")\n" +
        "DELETE FROM sec_user_password USING affected_row\n" +
        "WHERE sec_user_password.user_id = affected_row.user_id\n" +
        "	AND sec_user_password.user_password_id != affected_row.user_password_id;");
        
        sendFeedback("Creating database connection.");
        sendFeedback("Executing query...");
        
        Object result = Database.getInstance().executeQuery(query);
        
        if (result instanceof Integer) {
            sendFeedback("Password was successfully reset!");
        } else {
            sendFeedback("Password could not be reset!\n" + result);
        }
    }

    @Override
    public boolean isProblemFixed() {
        // Do some problem checking here.
        // Return true or false, based on whether or not the issue is fixed.
        return false;
    }

    // TODO: Make a way to return to user WHAT part is not fixed or what
    // caused the issue to not be fixed by executing the StandardProcedure
    // TODO: Some way to output a value (String or number etc...) back to the
    // user. This should be a standardised and easy to use / understand way.
}
