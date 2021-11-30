package restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import data.DataContainer;
import io.javalin.Javalin;

public class RestServer implements Runnable {

    private DataContainer dataContainer;

    public RestServer(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Override
    public void run() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Javalin javalin = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();
                }
        ).start(8123);

        javalin.get("/health-check", ctx -> ctx.result("Ok"));
        javalin.get("/incoming_transactions", ctx -> ctx.result(objectMapper.writeValueAsString(dataContainer.getIncomingCalls())));
        javalin.get("/active_block", ctx -> ctx.result(objectMapper.writeValueAsString(dataContainer.getActiveBlock())));
        javalin.get("/blockchain", ctx -> ctx.result(objectMapper.writeValueAsString(dataContainer.getBlockChain().getBlocksSummary())));
        javalin.get("/block_detail/:blockId", ctx -> {
            System.out.println("block id " + ctx.pathParam("blockId"));
            ctx.result(objectMapper.writeValueAsString(dataContainer.getBlockChain().getPhoneCallsForBlock(ctx.pathParam("blockId"))));
        });

        System.out.println("*** RestServer thread is running");

    }
}
