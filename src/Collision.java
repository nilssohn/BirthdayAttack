import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author put your name here
 * performs a collision search (the birthday attack)
 * for given two strings x1 and x2,
 */
public class Collision{

    /**
     * collision serach funciton
     * @return an array of strings containing two modified messages
     * that have the same hash output
     */
    public String[] collisionSearch(){
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
        int BYTE_NUM = OUTPUT_BITS/8;
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
            zeros += "0";
        }
        return zeros+x;
    }
}