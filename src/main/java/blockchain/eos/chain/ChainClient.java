package blockchain.eos.chain;

import blockchain.net.RPCHttpProvider;
import blockchain.eos.chain.response.ChainInfo;

import java.util.concurrent.CompletableFuture;

/**
 * Created by DongLei on 2018/6/2.
 */
public class ChainClient {

    private String url;
    private RPCHttpProvider rpcHttpProvider;

    public ChainClient(String url, RPCHttpProvider rpcHttpProvider) {
        this.url = url;
        this.rpcHttpProvider = rpcHttpProvider;
    }

    public CompletableFuture<ChainInfo> getChainInfo() {
        return rpcHttpProvider.httpGet(url + "/chain/get_info", ChainInfo.class);
    }

}
