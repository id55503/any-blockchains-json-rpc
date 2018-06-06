package blockchain.neo.rpc.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by DongLei on 2018/6/5.
 */
public class BlockData {
    public String hash;
    public Integer size;
    public Integer version;
    @JsonProperty("previousblockhash")
    public String previousBlockHash;
    public String merkleroot;
    public Long time;
    public Integer index;
    public String nonce;
    @JsonProperty("nextconsensus")
    public String nextConsensus;
    public Script script;
    public List<Transaction> tx;
    public Integer confirmations;
    @JsonProperty("nextblockhash")
    public String nextBlockHash;
}
