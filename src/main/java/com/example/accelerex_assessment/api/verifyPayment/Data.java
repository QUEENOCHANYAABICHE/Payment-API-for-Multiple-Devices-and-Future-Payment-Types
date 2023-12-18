
package com.example.accelerex_assessment.api.verifyPayment;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "domain",
    "status",
    "reference",
    "amount",
    "message",
    "gateway_response",
    "created_at",
    "channel",
    "currency",
    "ip_address",
    "metadata",
    "log",
    "fees",
    "fees_split",
    "authorization",
    "customer",
    "plan",
    "split",
    "order_id",
    "paidAt",
    "requested_amount",
    "pos_transaction_data",
    "source",
    "fees_breakdown",
    "transaction_date",
    "plan_object",
    "subaccount"
})
@Getter
@Setter
public class Data {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("status")
    private String status;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("message")
    private Object message;
    @JsonProperty("gateway_response")
    private String gatewayResponse;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("ip_address")
    private String ipAddress;
    @JsonProperty("metadata")
    private String metadata;
    @JsonProperty("log")
    private Log log;
    @JsonProperty("fees")
    private Integer fees;
    @JsonProperty("fees_split")
    private Object feesSplit;
    @JsonProperty("authorization")
    private Authorization authorization;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("plan")
    private Object plan;
    @JsonProperty("split")
    private Split split;
    @JsonProperty("order_id")
    private Object orderId;
    @JsonProperty("paidAt")
    private String paidAt;
    @JsonProperty("requested_amount")
    private Integer requestedAmount;
    @JsonProperty("pos_transaction_data")
    private Object posTransactionData;
    @JsonProperty("source")
    private Object source;
    @JsonProperty("fees_breakdown")
    private Object feesBreakdown;
    @JsonProperty("transaction_date")
    private String transactionDate;
    @JsonProperty("plan_object")
    private PlanObject planObject;
    @JsonProperty("subaccount")
    private Subaccount subaccount;

}
