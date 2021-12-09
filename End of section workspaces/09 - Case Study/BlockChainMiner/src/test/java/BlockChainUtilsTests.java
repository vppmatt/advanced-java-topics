import model.CallStatus;
import model.PhoneCall;
import model.blockchain.Block;
import model.blockchain.HashResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import utilities.BlockChainUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlockChainUtilsTests {

    @Test
    public void testCalculateHash() {
        String testString ="02021-12-09T15:39:01.541226Z91600001[PhoneCall{id=1, caller=37848, recipient=80, start=2021-12-09T15:38:46.946168900, end=2021-12-09T15:38:56.948168100, status=INVALID_NUMBER}, PhoneCall{id=2, caller=46297, recipient=81853, start=2021-12-09T15:38:47.861313300, end=2021-12-09T15:38:57.861313300, status=INVALID_NUMBER}, PhoneCall{id=3, caller=51671, recipient=47511, start=2021-12-09T15:38:49.174473800, end=2021-12-09T15:38:59.174473800, status=ENGAGED}, PhoneCall{id=4, caller=61891, recipient=81368, start=2021-12-09T15:38:50.508926900, end=2021-12-09T15:39:00.508926900, status=INVALID_NUMBER}, PhoneCall{id=5, caller=30482, recipient=43218, start=2021-12-09T15:38:50.948186700, end=2021-12-09T15:39:00.948186700, status=INVALID_NUMBER}, PhoneCall{id=6, caller=65150, recipient=9848, start=2021-12-09T15:38:51.194258, end=2021-12-09T15:39:01.194258, status=ENGAGED}]";
        String expectedHash = "11e1b8695f3f4caa05e4d48807529855ec7fdb0cde01a0c2e458eed8951c670c";

        String result = BlockChainUtils.calculateHash( testString);
        assertEquals(expectedHash, result);
    }

    @Test
    public void testMining() {

        List<PhoneCall> phoneCalls = new ArrayList<>();
        phoneCalls.add( new PhoneCall(12345,67890,
                LocalDateTime.of(2017,6,4,15,22),
                LocalDateTime.of(2017,6,4,15,27), CallStatus.ANSWERED));

        Block block = spy(new Block(1, phoneCalls, "1234"));

        LocalDate date = LocalDate.parse("9999-12-31");
        Instant instant = date.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();

        doReturn(instant).when(block).getTimeStamp();

        HashResult result = BlockChainUtils.mineBlock(block, 5 , 0, 1100000);

        System.out.println(result.getNonce());
        System.out.println(result.getHash());

        assertEquals("000006b10eff5731861ce0781677230bbe610a7552191b30e9c71ad155e040e8", result.getHash());
        assertEquals(1087088, result.getNonce());
    }
}
