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
        String[] collisionPair = new String[2];
        boolean collisionNotFound = true;

        StringBuilder m1 = new StringBuilder();
        StringBuilder m2 = new StringBuilder();
        Long hash1;
        Long hash2;
        m1.append(message1);
        m2.append(message2);

        while (collisionNotFound) {
            hash1 = hash(m1.toString());
            if (!hashMessage2.containsKey(hash1)) {
                hashMessage1.put(hash1, m1.toString());
                m1.append(" ");
            } else {
                collisionPair[0] = m1.toString();
                collisionPair[1] = hashMessage2.get(hash1);
                collisionNotFound = false;
                continue;
            }

            hash2 = hash(m2.toString());
            if (!hashMessage1.containsKey(hash2)) {
                hashMessage2.put(hash2, m2.toString());
                m2.append(" ");
            } else {
                collisionPair[0] = hashMessage1.get(hash2);
                collisionPair[1] = m2.toString();
                 
                collisionNotFound = false;
            }
        }

        return collisionPair;
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