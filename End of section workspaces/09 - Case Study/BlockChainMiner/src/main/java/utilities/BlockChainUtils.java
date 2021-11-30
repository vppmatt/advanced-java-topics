package utilities;

import model.blockchain.Block;
import model.blockchain.HashResult;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BlockChainUtils {

    //Note - it is fine to call static methods from multiple threads simultaneously
    //as long as there is no shared state.

    public static String calculateHash(String data) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] rawHash = digest.digest(data.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < rawHash.length; i++) {
                String hex = Integer.toHexString(0xff & rawHash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    public static HashResult mineBlock(Block block, int difficultyLevel, int startNonce, int endNonce) {
        String hash = new String(new char[difficultyLevel]).replace("\0", "X");
        String target = new String(new char[difficultyLevel]).replace("\0", "0");

        int nonce = startNonce;
        while (!hash.substring(0,difficultyLevel).equals(target) && nonce < endNonce) {
            nonce++;
            String dataToEncode = block.getPreviousHash() + block.getTimeStamp().toString() + nonce + block.getPhoneCalls();
            hash = calculateHash(dataToEncode);
        }

        if (hash.substring(0,difficultyLevel).equals(target)) {
            HashResult hashResult = new HashResult();
            hashResult.foundAHash(hash, nonce);
            return hashResult;
        }
        else {
            return null;
        }

    }

    public static boolean validateBlock(Block block) {
        String dataToEncode = block.getPreviousHash() + block.getTimeStamp().toString() + block.getNonce() + block.getPhoneCalls();
        String checkHash = calculateHash(dataToEncode);
        return (checkHash.equals(block.getHash()));
    }
}
