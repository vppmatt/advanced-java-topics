package model.blockchain;

import model.PhoneCall;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class Block {

        private int id;
        private String previousHash;
        private List<PhoneCall> phoneCalls;
        private int nonce;
        private String hash;
        private Instant timeStamp;

        public Block(int id, List<PhoneCall> phoneCalls, String previousHash) {
            this.previousHash = previousHash;
            /* start of fix */
            //this.phoneCalls = phoneCalls;
            this.phoneCalls = Collections.unmodifiableList(phoneCalls);
            /*end of fix */
            this.id = id;
            timeStamp = Instant.now();
        }

        public void setNonce(int nonce) {
            this.nonce = nonce;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getHash() {
            return hash;
        }

        public String getPreviousHash() {
            return previousHash;
        }

        public List<PhoneCall> getPhoneCalls() {
            return phoneCalls;
        }

        public int getNonce() {
            return nonce;
        }

        public Instant getTimeStamp() {
            return timeStamp;
        }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
            return ("BLOCK [ id :" + id + ", timestamp : " + getTimeStamp() + "]");
    }
}
