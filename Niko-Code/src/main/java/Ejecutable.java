
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;








public class Ejecutable {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String mensaje = "Hola, mundo!";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] mensajeBytes = mensaje.getBytes();
        md.update(mensajeBytes);
        byte[] hash = md.digest();
        System.out.println("Hash SHA-256 del mensaje: " + bytesToHex(hash));
    }
    
    
       public  String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convertir el hash en una cadena de caracteres hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
}



  
    
     
    
       
