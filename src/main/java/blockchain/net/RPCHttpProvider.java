package blockchain.net;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by DongLei on 2018/6/2.
 */
public class RPCHttpProvider {
    private final BiFunction<String, String, CompletableFuture<String>> rpcHttpPostClient;
    private final Function<String, CompletableFuture<String>> rpcHttpGetClient;
    private final ObjectMapper mapper;

    public RPCHttpProvider(Function<String, CompletableFuture<String>> rpcHttpGetClient, BiFunction<String, String, CompletableFuture<String>> rpcHttpPostClient, ObjectMapper mapper) {
        this.rpcHttpGetClient = rpcHttpGetClient;
        this.rpcHttpPostClient = rpcHttpPostClient;
        this.mapper = mapper;
    }

    public <T> CompletableFuture<T> httpPost(String url, JsonRPCRequest jsonRPCRequest, TypeReference<T> typeReference) {
        String requestJsonString;
        try {
            requestJsonString = mapper.writeValueAsString(jsonRPCRequest);
        } catch (Exception e) {
            return CompletableFuture.supplyAsync(() -> {
                throw new CompletionException(e);
            });
        }
        return rpcHttpPostClient.apply(url, requestJsonString).thenApply(jsonString -> {
            try {
                System.out.println(jsonString);
                return mapper.readValue(jsonString, typeReference);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }

    public <T> CompletableFuture<T> httpGet(String url, Class<T> glass) {
        return rpcHttpGetClient.apply(url).thenApply(jsonString -> {
            try {
                return mapper.readValue(jsonString, glass);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        });
    }
}
