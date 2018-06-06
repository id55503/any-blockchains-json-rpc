package blockchain.neo.rpc.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by DongLei on 2018/6/5.
 */
public class ReturnValue {
    public String state;
    @JsonProperty("gas_consumed")
    public String gasConsumed;
    public List<NeoType> stack;
}
