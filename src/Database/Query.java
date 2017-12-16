/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author niekv
 */
public class Query {

    private String queryString;
    
    public Query(String query) {
        this.queryString = query;
    }
    
    public String getQueryString() {
        return this.queryString;
    }
    /*
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Query)) {
            return false;
        }

        String thisQueryString = this.getQueryString();
        String otherQueryString = ((Query) obj).getQueryString();
        
        if (thisQueryString.equals(otherQueryString)) {
            return true;
        } else {
            return false;
        }
    }
    */
}
