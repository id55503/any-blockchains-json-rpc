package blockchain.neo.rpc.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by DongLei on 2018/6/5.
 */
public class AccountState {
    public String version;
    @JsonProperty("script_hash")
    public String scriptHash;
    public Boolean frozen;
    public List<String> votes;
    public List<Asset> balances;
}
