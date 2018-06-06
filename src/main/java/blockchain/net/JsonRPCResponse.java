package blockchain.net;

import blockchain.neo.rpc.response.Error;

/**
 * Created by DongLei on 2018/6/5.
 */
public class JsonRPCResponse<T> {
    public String jsonrpc;
    public Integer id;
    public T result;
    public Error error;

    public JsonRPCResponse() {
        this.jsonrpc = "2.0";
    }
}
