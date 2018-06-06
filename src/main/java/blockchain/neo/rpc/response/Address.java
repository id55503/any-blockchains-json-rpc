package blockchain.neo.rpc.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DongLei on 2018/6/5.
 */
public class Address {
    public String address;
    @JsonProperty("haskey")
    public String hasKey;
    public String label;
    @JsonProperty("watchonly")
    public boolean watchOnly;
}
