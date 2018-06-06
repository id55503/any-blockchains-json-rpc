package blockchain.net;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;


/**
 * Created by DongLei on 2018/6/5.
 */
public class JsonRPCRequest {
    @JsonProperty("jsonrpc")
    public String version;
    public String method;
    @JsonProperty("params")
    public ArrayNode params;
    public Integer id;

    public JsonRPCRequest() {
    }

    public JsonRPCRequest(String method, ArrayNode params, Integer id) {
        this.method = method;
        this.params = params;
        this.id = id;
        this.version = "2.0";
    }
}
