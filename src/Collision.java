import  java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


/**
 * @author Nils Sohn
 * performs a collision search (the birthday attack)
 * for given two strings x1 and x2,
 */
public class Collision{

    int outputSize;
    int count = 0;
    String message1;
    String message2;

    HashMap<Long, String> hashMessage1 = new HashMap<Long, String>();
    HashMap<Long, String> hashMessage2 = new HashMap<Long, String>();

    public Collision(int outputSize, String message1, String message2) {
        this.outputSize = outputSize;
        this.message1 = message1;
        this.message2 = message2;
    }

    /**
     * collision serach funciton
     * @return an array of strings containing two modified messages
     * that have the same hash output
     */
    public String[] collisionSearch(){
        String[] collisionPair;

        collisionPair = mapHash("");
        return collisionPair;
    }

    private String[] mapHash(String concatStr) {
        if (concatStr.length() >= 25) {
            return null;
        }

        String message = message1 + concatStr;
        long hash = hash(message);

        if (!hashMessage2.containsKey(hash)) {
            hashMessage1.put(hash, message);
        } else {
            return new String[] {message, hashMessage2.get(hash)};
        }

        message = message2 + concatStr;
        hash = hash(message2 + concatStr);
        if (!hashMessage1.containsKey(hash)) {
            hashMessage2.put(hash, message);
        } else {
            return new String[] {hashMessage1.get(hash), message};
        }

        String[] collisionPair = mapHash(concatStr + " ");

        if (collisionPair != null) {
            return collisionPair;
        }

        collisionPair = mapHash(concatStr + "\t");

        if (collisionPair != null) {
            return collisionPair;
        }

        return null;
    }

    /**
     * A hash function made for collision resistance test
     * Output length is 40 bits ( = 5 bytes)
     * Collision search requires O(2^(20)) computations.
     * @param msg message to be hashed
     * @return a a positive number less than 2^(40) output length is 40 bits)
     */
    public long hash(String msg) {
        byte[] output = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(msg.getBytes());
            output = md.digest(); //20 bytes is too long for our purpose
        } catch (NoSuchAlgorithmException x) {
            x.printStackTrace(System.err);
        }
        int BYTE_NUM = outputSize /8;
        String binaryStr = "";
        for(int i=0; i<BYTE_NUM; i++ ){
            binaryStr += formattedStr(output[i]);
        }
        return Long.parseLong(binaryStr, 2);
    }

    /**
     * Converts a byte to a binary string of 8 bits.
     * @param num a byte to be converted to a a binary string
     * @return a binary string with length 8
     */
    private String formattedStr(byte num){
        String x = Integer.toBinaryString(Byte.toUnsignedInt(num));
        String zeros = "";
        for(int i=0; i< Byte.SIZE-x.length(); i++){ //padding 0s
            zeros.equals("0");
        }
        return zeros+x;
    }
}