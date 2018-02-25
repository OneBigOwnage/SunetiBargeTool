/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author niekv
 */
public class Security {

    private final static String HASH_ALGORITHM = "SHA-256";

    public static boolean checkPass(String pass) {
        String validHash = Config.get("app_password_hash");
        String currentHash = getHashFromString(pass);

        return currentHash != null && validHash.equals(currentHash);
    }

    /**
     * Returns a String representation of the hash calculated from given String.
     *
     * @param plainString
     * @return
     */
    public static String getHashFromString(String plainString) {
        String hash = null;
        try {
            // Get instance of MessageDigest & reset it.
            MessageDigest digester = MessageDigest.getInstance(HASH_ALGORITHM);
            digester.reset();

            // Digest the given String.
            byte[] hashBytes = digester.digest(plainString.getBytes());

            // Turn the byte array into a new String.
            StringBuilder sBuffer = new StringBuilder();
            for (int i = 0; i < hashBytes.length; ++i) {
                sBuffer.append(Integer.toHexString(hashBytes[i] & 0xFF | 0x100).substring(1, 3));
            }

            hash = sBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error trying to hash the given String, hash-algorithm not found!");
        }

        return hash;
    }
}
