package c.hackathon.decentralisedleague.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllMarketPlacePlayers {

    @SerializedName("nextLink")
    @Expose
    private String nextLink;
    @SerializedName("contracts")
    @Expose
    private List<Contract> contracts = null;

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public class Parameter {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("workflowFunctionParameterId")
        @Expose
        private Integer workflowFunctionParameterId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getWorkflowFunctionParameterId() {
            return workflowFunctionParameterId;
        }

        public void setWorkflowFunctionParameterId(Integer workflowFunctionParameterId) {
            this.workflowFunctionParameterId = workflowFunctionParameterId;
        }

    }

    public class Transaction {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("connectionId")
        @Expose
        private Integer connectionId;
        @SerializedName("transactionHash")
        @Expose
        private String transactionHash;
        @SerializedName("blockID")
        @Expose
        private Integer blockID;
        @SerializedName("blockNumber")
        @Expose
        private Object blockNumber;
        @SerializedName("blockHash")
        @Expose
        private Object blockHash;
        @SerializedName("from")
        @Expose
        private String from;
        @SerializedName("to")
        @Expose
        private Object to;
        @SerializedName("value")
        @Expose
        private Integer value;
        @SerializedName("isAppBuilderTx")
        @Expose
        private Boolean isAppBuilderTx;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getConnectionId() {
            return connectionId;
        }

        public void setConnectionId(Integer connectionId) {
            this.connectionId = connectionId;
        }

        public String getTransactionHash() {
            return transactionHash;
        }

        public void setTransactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
        }

        public Integer getBlockID() {
            return blockID;
        }

        public void setBlockID(Integer blockID) {
            this.blockID = blockID;
        }

        public Object getBlockNumber() {
            return blockNumber;
        }

        public void setBlockNumber(Object blockNumber) {
            this.blockNumber = blockNumber;
        }

        public Object getBlockHash() {
            return blockHash;
        }

        public void setBlockHash(Object blockHash) {
            this.blockHash = blockHash;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public Object getTo() {
            return to;
        }

        public void setTo(Object to) {
            this.to = to;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Boolean getIsAppBuilderTx() {
            return isAppBuilderTx;
        }

        public void setIsAppBuilderTx(Boolean isAppBuilderTx) {
            this.isAppBuilderTx = isAppBuilderTx;
        }

    }

    public class ContractProperty {

        @SerializedName("workflowPropertyId")
        @Expose
        private Integer workflowPropertyId;
        @SerializedName("value")
        @Expose
        private String value;

        public Integer getWorkflowPropertyId() {
            return workflowPropertyId;
        }

        public void setWorkflowPropertyId(Integer workflowPropertyId) {
            this.workflowPropertyId = workflowPropertyId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public class ContractAction {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("provisioningStatus")
        @Expose
        private Integer provisioningStatus;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("parameters")
        @Expose
        private List<Parameter> parameters = null;
        @SerializedName("workflowFunctionId")
        @Expose
        private Integer workflowFunctionId;
        @SerializedName("transactionId")
        @Expose
        private Integer transactionId;
        @SerializedName("workflowStateId")
        @Expose
        private Integer workflowStateId;
        @SerializedName("requestId")
        @Expose
        private Object requestId;
        @SerializedName("eventId")
        @Expose
        private Object eventId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getProvisioningStatus() {
            return provisioningStatus;
        }

        public void setProvisioningStatus(Integer provisioningStatus) {
            this.provisioningStatus = provisioningStatus;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public List<Parameter> getParameters() {
            return parameters;
        }

        public void setParameters(List<Parameter> parameters) {
            this.parameters = parameters;
        }

        public Integer getWorkflowFunctionId() {
            return workflowFunctionId;
        }

        public void setWorkflowFunctionId(Integer workflowFunctionId) {
            this.workflowFunctionId = workflowFunctionId;
        }

        public Integer getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(Integer transactionId) {
            this.transactionId = transactionId;
        }

        public Integer getWorkflowStateId() {
            return workflowStateId;
        }

        public void setWorkflowStateId(Integer workflowStateId) {
            this.workflowStateId = workflowStateId;
        }

        public Object getRequestId() {
            return requestId;
        }

        public void setRequestId(Object requestId) {
            this.requestId = requestId;
        }

        public Object getEventId() {
            return eventId;
        }

        public void setEventId(Object eventId) {
            this.eventId = eventId;
        }

    }

    public class Contract {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("provisioningStatus")
        @Expose
        private Integer provisioningStatus;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("connectionID")
        @Expose
        private Integer connectionID;
        @SerializedName("ledgerIdentifier")
        @Expose
        private String ledgerIdentifier;
        @SerializedName("deployedByUserId")
        @Expose
        private Integer deployedByUserId;
        @SerializedName("workflowId")
        @Expose
        private Integer workflowId;
        @SerializedName("requestId")
        @Expose
        private String requestId;
        @SerializedName("contractCodeId")
        @Expose
        private Integer contractCodeId;
        @SerializedName("contractProperties")
        @Expose
        private List<ContractProperty> contractProperties = null;
        @SerializedName("transactions")
        @Expose
        private List<Transaction> transactions = null;
        @SerializedName("contractActions")
        @Expose
        private List<ContractAction> contractActions = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProvisioningStatus() {
            return provisioningStatus;
        }

        public void setProvisioningStatus(Integer provisioningStatus) {
            this.provisioningStatus = provisioningStatus;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getConnectionID() {
            return connectionID;
        }

        public void setConnectionID(Integer connectionID) {
            this.connectionID = connectionID;
        }

        public String getLedgerIdentifier() {
            return ledgerIdentifier;
        }

        public void setLedgerIdentifier(String ledgerIdentifier) {
            this.ledgerIdentifier = ledgerIdentifier;
        }

        public Integer getDeployedByUserId() {
            return deployedByUserId;
        }

        public void setDeployedByUserId(Integer deployedByUserId) {
            this.deployedByUserId = deployedByUserId;
        }

        public Integer getWorkflowId() {
            return workflowId;
        }

        public void setWorkflowId(Integer workflowId) {
            this.workflowId = workflowId;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public Integer getContractCodeId() {
            return contractCodeId;
        }

        public void setContractCodeId(Integer contractCodeId) {
            this.contractCodeId = contractCodeId;
        }

        public List<ContractProperty> getContractProperties() {
            return contractProperties;
        }

        public void setContractProperties(List<ContractProperty> contractProperties) {
            this.contractProperties = contractProperties;
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
        }

        public List<ContractAction> getContractActions() {
            return contractActions;
        }

        public void setContractActions(List<ContractAction> contractActions) {
            this.contractActions = contractActions;
        }

    }

}
