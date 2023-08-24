package br.com.finco.finco_api.common.security;

import org.mindrot.jbcrypt.BCrypt;

public class Cryptor {

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(
                Integer.parseInt("12")
        ));
    }

    public static boolean compare(String value, String encryptedValue) {
        return BCrypt.checkpw(value, encryptedValue);
    }
}
