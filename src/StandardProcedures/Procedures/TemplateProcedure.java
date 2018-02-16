/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StandardProcedures.Procedures;

/**
 *
 * @author niekv
 */
public class TemplateProcedure extends StandardProcedures.StandardProcedure {

    public TemplateProcedure() {
        super(
                // Title
                "Template Procedure, to get the user's login name",
                // Description
                "The template class for standard procedures in the Barge Tool app.\n"
                + "This procedure shows the login name(s) that are not equal to 'superuser'.",
                // -----
                new String[]{
                    // Warnings:
                    "This procedure shows the user's login name in plain text.",
                    // -----
                    "This is a second warning of the template procedure."});
    }

    @Override
    public void performProcedure() {
        // Do some method delegation here....
        // and some more here...
        // and then the last here...
        // then return
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
