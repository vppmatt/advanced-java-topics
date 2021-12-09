package mining;

import data.DataContainer;

public class ChainManagerFactory {

    private static ChainManager instance;

    public static ChainManager getChainManager() {
        if(instance == null) {
            instance = new ChainManager(DataContainer.getInstance());
        }
        return instance;
    }

}
