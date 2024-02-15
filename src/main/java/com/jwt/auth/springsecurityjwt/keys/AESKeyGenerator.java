/**
 * @author Cloyd Van Secuya
 *
 * <p>This class generates a given key, based on the size given by the user input.</p>
 *
 * <p>
 *     <u>Key sizes:</u>
 *     <ul>
 *         <li>128-bits</li>
 *         <li>192-bits</li>
 *         <li>256-bits</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     256-bits of key size is needed in the JWTs.
 * </p>
 */
package com.jwt.auth.springsecurityjwt.keys;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESKeyGenerator {

    private String SECRET_KEY;

    public AESKeyGenerator(int keySize) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(keySize);
            SecretKey secretKey = keyGen.generateKey();
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            SECRET_KEY = encodedKey;
        } catch (NoSuchAlgorithmException e) { e.printStackTrace();}
    }

    public String getSecretKey() {
        return SECRET_KEY;
    }
}
