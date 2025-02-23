
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordVerifier {
    private String correctPasswordHash;

    public PasswordVerifier(String correctPassword) {
        correctPasswordHash = hashPassword(correctPassword);
    }

    public boolean verifyPassword(String enteredPassword) {
        String enteredPasswordHash = hashPassword(enteredPassword);
        return enteredPasswordHash.equals(correctPasswordHash);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
