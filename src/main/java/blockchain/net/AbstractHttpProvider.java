package blockchain.net;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by DongLei on 2018/6/4.
 */
public abstract class AbstractHttpProvider {

    public abstract BiFunction<String, String, CompletableFuture<String>> httpPost();

    public abstract Function<String, CompletableFuture<String>> httpGet();
}
