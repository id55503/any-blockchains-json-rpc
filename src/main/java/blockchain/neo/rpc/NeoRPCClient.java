package blockchain.neo.rpc;

import blockchain.neo.rpc.response.*;
import blockchain.net.JsonRPCRequest;
import blockchain.net.JsonRPCResponse;
import blockchain.net.NettyRPCHttp;
import blockchain.net.RPCHttpProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by DongLei on 2018/6/5.
 */
public class NeoRPCClient {

    public static final String CURRENCY_ID_NEO = "c56f33fc6ecfcd0c225c4ab356fee59390af8560be0e930faebe74a6daff7c9b";
    public static final String CURRENCY_ID_GAS = "602c79718b16e442de58778e148d0b1084e3b2dffd5de6b7b16cee7969282de7";

    private String url;
    private RPCHttpProvider rpcHttpProvider;
    private final ObjectMapper mapper;

    public NeoRPCClient(RPCHttpProvider rpcHttpProvider, String url, ObjectMapper mapper) {
        this.rpcHttpProvider = rpcHttpProvider;
        this.url = url;
        this.mapper = mapper;
    }

    public CompletableFuture<JsonRPCResponse<AccountState>> getAccountState(String address, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getaccountstate", mapper.createArrayNode().add(address), id)
                , new TypeReference<JsonRPCResponse<AccountState>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Balance>> getBalance(String address, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getbalance", mapper.createArrayNode().add(address), id)
                , new TypeReference<JsonRPCResponse<Balance>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<String>> getNewAddress(Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getnewaddress", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<String>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<List<Address>>> getListAddress(Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("listaddress", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<List<Address>>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Transaction>> sendfrom(Integer id, String currencyId, String from, String to, String value, String fee) {
        ArrayNode list = mapper.createArrayNode().add(currencyId).add(from).add(to).add(value);
        if (fee != null) {
            list = list.add(fee);
        }
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("sendfrom", list, id)
                , new TypeReference<JsonRPCResponse<Transaction>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<String>> getBestBlockHash(Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getbestblockhash", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<String>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<BlockData>> getBlockByHash(String blockHash, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getblock", mapper.createArrayNode().add(blockHash).add(1), id)
                , new TypeReference<JsonRPCResponse<BlockData>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<BlockData>> getBlockByIndex(Integer index, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getblock", mapper.createArrayNode().add(index).add(1), id)
                , new TypeReference<JsonRPCResponse<BlockData>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Integer>> getBlockCount(Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getblockcount", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<Integer>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<String>> getBlockHash(Integer index, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getblockhash", mapper.createArrayNode().add(index), id)
                , new TypeReference<JsonRPCResponse<String>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Integer>> getBlockSysFee(Integer index, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getblocksysfee", mapper.createArrayNode().add(index), id)
                , new TypeReference<JsonRPCResponse<Integer>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Integer>> getConnectionCount(Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getconnectioncount", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<Integer>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<List<String>>> getRawMempool(Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getrawmempool", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<List<String>>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Transaction>> getRawTransaction(String hash, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getrawtransaction", mapper.createArrayNode().add(hash).add(1), id)
                , new TypeReference<JsonRPCResponse<Transaction>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<UnSpent>> getTransactionOut(String hash, Integer outIndex, Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("gettxout", mapper.createArrayNode().add(hash).add(outIndex), id)
                , new TypeReference<JsonRPCResponse<UnSpent>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Version>> getVersion(Integer id) {
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("getversion", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<Version>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<ReturnValue>> invoke(Integer id, String scriptHash, NeoType... params) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(scriptHash);
        for (NeoType param : params) {
            arrayNode.add(mapper.convertValue(param, JsonNode.class));
        }
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("invoke", arrayNode, id)
                , new TypeReference<JsonRPCResponse<ReturnValue>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<ReturnValue>> invokeFunction(Integer id, String scriptHash, String method, NeoType... params) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(scriptHash);
        arrayNode.add(method);
        for (NeoType param : params) {
            arrayNode.add(mapper.convertValue(param, JsonNode.class));
        }
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("invokefunction", arrayNode, id)
                , new TypeReference<JsonRPCResponse<ReturnValue>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<ReturnValue>> invokeScript(Integer id, String scriptHash) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(scriptHash);
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("invokescript", arrayNode, id)
                , new TypeReference<JsonRPCResponse<ReturnValue>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Transaction>> sendMany(Integer id, String changeAddress, SendTransaction... transactions) {
        ArrayNode arrayNode = mapper.createArrayNode();
        for (SendTransaction sendTransaction : transactions) {
            arrayNode.add(mapper.convertValue(sendTransaction, JsonNode.class));
        }
        arrayNode.add(changeAddress);
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("sendmany", mapper.createArrayNode(), id)
                , new TypeReference<JsonRPCResponse<Transaction>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Boolean>> sendRawTransaction(Integer id, String hex) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(hex);
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("sendrawtransaction", arrayNode, id)
                , new TypeReference<JsonRPCResponse<Boolean>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<ValidAddressResult>> getVaildAddressResult(Integer id, String address) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(address);
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("validateaddress", arrayNode, id)
                , new TypeReference<JsonRPCResponse<ValidAddressResult>>() {
                });
    }

    public CompletableFuture<JsonRPCResponse<Transaction>> sendToAddress(Integer id, String asset, String address, Integer value, Integer fee) {
        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add(asset);
        arrayNode.add(address);
        arrayNode.add(value);
        if (fee != null) {
            arrayNode.add(fee);
        }
        return rpcHttpProvider.httpPost(url, new JsonRPCRequest("sendtoaddress", arrayNode, id)
                , new TypeReference<JsonRPCResponse<Transaction>>() {
                });
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        NeoRPCClient neoRPCClient = new NeoRPCClient(new RPCHttpProvider(NettyRPCHttp.NETTY_RPC_HTTP.httpGet(), NettyRPCHttp.NETTY_RPC_HTTP.httpPost(), objectMapper), "http://localhost:30333", objectMapper);
//        neoRPCClient.getAccountState("ATJHDrGpq1dpuptpcboU3fTJ7aDtWRxgLs", 3)
//                .whenComplete((response, throwable) -> {
//                    if(throwable != null){
//                        throwable.printStackTrace();
//                    }
//                    System.out.println(response.toString());
//                });
//        neoRPCClient.getBalance(CURRENCY_ID_GAS, 1)
//                .whenComplete((response, throwable) -> {
//                    if (throwable != null) {
//                        throwable.printStackTrace();
//                    }
//                    System.out.println(response.toString());
//                });
//        neoRPCClient.getNewAddress(1)
//                .whenComplete((response, throwable) -> {
//                    if (throwable != null) {
//                        throwable.printStackTrace();
//                    }
//                    System.out.println(response.result);
//                });
//                neoRPCClient.getListAddress(1)
//                        .whenComplete((response, throwable) -> {
//                            if (throwable != null) {
//                                throwable.printStackTrace();
//                            }
//                            System.out.println(response.result);
//                        });

//        neoRPCClient.sendfrom(1, CURRENCY_ID_NEO, "AVKCdVkkS1bwaN9iZpvugZ5AzNMGJQVfdH", "ATJHDrGpq1dpuptpcboU3fTJ7aDtWRxgLs", "1", null)
//                .whenComplete(((listJsonRPCResponse, throwable) -> {
//                    if (throwable != null) {
//                        throwable.printStackTrace();
//                    }
//                    System.out.println(listJsonRPCResponse.result);
//                }));

//        String jsonString = "{\"jsonrpc\":\"2.0\",\"id\":1,\"result\":{\"txid\":\"0x09a2f8f79ceb301d9d77387fb5cb146c8a30eb98b516bcf2a35ce7ec81b52b72\",\"size\":262,\"type\":\"ContractTransaction\",\"version\":0,\"attributes\":[],\"vin\":[{\"txid\":\"0x20b425f5c08277822755c03b65ae5c03786ce5e26a042aeb2b4deb5879e3cd79\",\"vout\":1}],\"vout\":[{\"n\":0,\"asset\":\"0x602c79718b16e442de58778e148d0b1084e3b2dffd5de6b7b16cee7969282de7\",\"value\":\"1\",\"address\":\"ARuorcJj6mwj8WUtShWu7JAjjSLUm3NejH\"},{\"n\":1,\"asset\":\"0x602c79718b16e442de58778e148d0b1084e3b2dffd5de6b7b16cee7969282de7\",\"value\":\"38\",\"address\":\"AVKCdVkkS1bwaN9iZpvugZ5AzNMGJQVfdH\"}],\"sys_fee\":\"0\",\"net_fee\":\"0\",\"scripts\":[{\"invocation\":\"40f8ecc1ddcb810a4d14ccb40f303b511c16b395573f6abf55398616f288c118399dfbaab05fa49ad672280477b5cee5ae5a7fabaa5cfcf5665c5111ccb07080a7\",\"verification\":\"210375cd1f547dbfb5a41e7c0e1ad961d99fc7bc9a815cfb3f299ddcdf6835dc323cac\"}]}}";
//        final ObjectMapper mapper = new ObjectMapper();
//        JsonRPCResponse<AccountState> rpcResponse = mapper.readValue(jsonString, new TypeReference<JsonRPCResponse<Transaction>>() {
//        });
//        System.out.println(mapper.writeValueAsString(rpcResponse));
    }

}
