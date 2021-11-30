package model.blockchain;

import config.Config;
import model.PhoneCall;
import utilities.BlockChainUtils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class BlockChain {

    private List<Block> blocks;

    public BlockChain() {
        blocks = new CopyOnWriteArrayList<>();
    }

    public void addBlock(Block block) throws BlockValidationException {
        String lastHash = "0";

        synchronized (this) {
            if (blocks.size() > 0) {
                lastHash = blocks.get(blocks.size() - 1).getHash();
            }

            if (!lastHash.equals(block.getPreviousHash())) {
                throw new BlockValidationException();
            }

            if (!BlockChainUtils.validateBlock(block)) {
                throw new BlockValidationException();
            }

            blocks.add(block);
        }
    }

    public void printAndValidate() {
        String lastHash = "0";
        for (Block block : blocks) {
            System.out.println("Block " + block.getId() + " ");
            System.out.println(block.getPhoneCalls());

            if (block.getPreviousHash().equals(lastHash)) {
                System.out.print("Last hash matches ");
            } else {
                System.out.print("Last hash doesn't match ");
            }

            if (BlockChainUtils.validateBlock(block)) {
                System.out.println("and hash is valid");
            } else {
                System.out.println("and hash is invalid");
            }

            lastHash = block.getHash();

        }
    }

    public String getLastHash() {
        if (blocks.size() > 0)
            return blocks.get(blocks.size() - 1).getHash();
        return "0";
    }

    public int getSize() {
        return blocks.size();
    }

    public class BlockSummary {
        public int id;
        public String hash;
        public String content;
        public String time;
        public BlockSummary(int id, String hash, String content, String time) {
            this.id = id;
            this.hash = hash;
            this.content = content;
            this.time = time;
        }
    }

    public List<BlockSummary> getBlocksSummary() {
        return blocks.stream().map(block -> {
            String content = block.getPhoneCalls().get(0).getId() + " - " +
                    block.getPhoneCalls().get(block.getPhoneCalls().size() - 1).getId();
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM )
                            .withLocale(Config.locale )
                            .withZone( ZoneId.systemDefault() );
            String time = formatter.format( block.getTimeStamp() );
            return new BlockSummary(block.getId(), block.getHash(), content, time);
        }).collect(Collectors.toList());
    }

    public List<PhoneCall> getPhoneCallsForBlock(String blockId) {
        return blocks.get(Integer.parseInt(blockId)).getPhoneCalls();
    }
}


