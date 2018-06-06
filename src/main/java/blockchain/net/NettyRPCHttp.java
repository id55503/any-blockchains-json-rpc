package blockchain.net;

import static org.asynchttpclient.Dsl.*;

import com.fasterxml.jackson.databind.JsonNode;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by DongLei on 2018/6/4.
 */
public class NettyRPCHttp {

    public static final AbstractHttpProvider NETTY_RPC_HTTP = new AbstractHttpProvider() {
        private final AsyncHttpClient asyncHttpClient = asyncHttpClient(config());
        @Override
        public BiFunction<String, String, CompletableFuture<String>> httpPost() {
            return (url, jsonNode) -> asyncHttpClient.preparePost(url).setBody(jsonNode).execute()
                    .toCompletableFuture().thenApply(Response::getResponseBody);
        }

        @Override
        public Function<String, CompletableFuture<String>> httpGet() {
            return url -> asyncHttpClient.preparePost(url).execute()
                    .toCompletableFuture().thenApply(Response::getResponseBody);
        }
    };

}
