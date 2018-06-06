package blockchain.neo.rpc.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DongLei on 2018/6/5.
 */
public class ValidAddressResult {
    public String address;
    @JsonProperty("isvalid")
    public Boolean isValid;
}
