package com.algorand.algosdk.transaction;

import com.algorand.algosdk.crypto.Address;
import com.algorand.algosdk.crypto.TEALProgram;
import com.algorand.algosdk.logic.StateSchema;
import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonPropertyOrder(alphabetic=true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApplicationTransactionParams {
    public enum OnCompletion {
        NoOpOC(0, "noop"),
        OptInOC(1, "optin"),
        CloseOutOC(2, "closeout"),
        ClearStateOC(3, "clearstate"),
        UpdateApplicationOC(4, "update"),
        DeleteApplicationOC(5, "delete");

        private final int serializedValue;
        private final String serializedName;

        OnCompletion(int serializeValue, String serializedName) {
            this.serializedValue = serializeValue;
            this.serializedName = serializedName;
        }

        @JsonCreator
        public OnCompletion String(String name) {
            for(OnCompletion oc : values()) {
                if (oc.serializedName.equalsIgnoreCase(name)) {
                    return oc;
                }
            }
            return null;
        }

        @JsonCreator
        public OnCompletion String(int value) {
            for(OnCompletion oc : values()) {
                if (oc.serializedValue == value) {
                    return oc;
                }
            }
            return null;
        }

        @JsonValue
        public int toValue() {
            return serializedValue;
        }

        @Override
        public String toString() {
            return serializedName;
        }
    }

    @JsonProperty("apid")
    public Long applicationId;

    @JsonProperty("apan")
    public OnCompletion onCompletion;

    @JsonProperty("apaa")
    public List<byte[]> applicationArgs;

    @JsonProperty("apat")
    public List<Address> accounts;

    @JsonProperty("apfa")
    public List<Long> foreignApps;

    @JsonProperty("apls")
    public StateSchema localStateSchema;

    @JsonProperty("apgs")
    public StateSchema globalStateSchema;

    @JsonProperty("apap")
    public TEALProgram approvalProgram;

    @JsonProperty("apsu")
    public TEALProgram clearStateProgram;
}
