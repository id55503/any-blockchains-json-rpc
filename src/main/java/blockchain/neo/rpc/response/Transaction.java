package blockchain.neo.rpc.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by DongLei on 2018/6/5.
 */
public class Transaction {
    public String txid;
    public Integer size;
    public String type;
    public Integer version;
    public List<String> attributes;
    public List<TransactionVin> vin;
    public List<TransactionVout> vout;
    @JsonProperty("sys_fee")
    public String sysFee;
    @JsonProperty("net_fee")
    public String netFee;
    public List<Script> scripts;
    public Long nonce;
}
