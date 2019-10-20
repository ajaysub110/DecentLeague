package c.hackathon.decentralisedleague.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserInfo {

    @SerializedName("currentUser")
    @Expose
    private CurrentUser currentUser;
    @SerializedName("capabilities")
    @Expose
    private Capabilities capabilities;

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public class Capabilities {

        @SerializedName("canUploadApplication")
        @Expose
        private Boolean canUploadApplication;
        @SerializedName("canUploadContractCode")
        @Expose
        private Boolean canUploadContractCode;
        @SerializedName("canModifyRoleAssignments")
        @Expose
        private Boolean canModifyRoleAssignments;
        @SerializedName("canProvisionUser")
        @Expose
        private Boolean canProvisionUser;
        @SerializedName("canUpgradeWorkbench")
        @Expose
        private Boolean canUpgradeWorkbench;

        public Boolean getCanUploadApplication() {
            return canUploadApplication;
        }

        public void setCanUploadApplication(Boolean canUploadApplication) {
            this.canUploadApplication = canUploadApplication;
        }

        public Boolean getCanUploadContractCode() {
            return canUploadContractCode;
        }

        public void setCanUploadContractCode(Boolean canUploadContractCode) {
            this.canUploadContractCode = canUploadContractCode;
        }

        public Boolean getCanModifyRoleAssignments() {
            return canModifyRoleAssignments;
        }

        public void setCanModifyRoleAssignments(Boolean canModifyRoleAssignments) {
            this.canModifyRoleAssignments = canModifyRoleAssignments;
        }

        public Boolean getCanProvisionUser() {
            return canProvisionUser;
        }

        public void setCanProvisionUser(Boolean canProvisionUser) {
            this.canProvisionUser = canProvisionUser;
        }

        public Boolean getCanUpgradeWorkbench() {
            return canUpgradeWorkbench;
        }

        public void setCanUpgradeWorkbench(Boolean canUpgradeWorkbench) {
            this.canUpgradeWorkbench = canUpgradeWorkbench;
        }

    }

    public class CurrentUser {

        @SerializedName("userID")
        @Expose
        private Integer userID;
        @SerializedName("externalID")
        @Expose
        private String externalID;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("emailAddress")
        @Expose
        private String emailAddress;
        @SerializedName("userChainMappings")
        @Expose
        private List<UserChainMapping> userChainMappings = null;

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }

        public String getExternalID() {
            return externalID;
        }

        public void setExternalID(String externalID) {
            this.externalID = externalID;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public List<UserChainMapping> getUserChainMappings() {
            return userChainMappings;
        }

        public void setUserChainMappings(List<UserChainMapping> userChainMappings) {
            this.userChainMappings = userChainMappings;
        }

    }
    public class UserChainMapping {

        @SerializedName("userChainMappingID")
        @Expose
        private Integer userChainMappingID;
        @SerializedName("userID")
        @Expose
        private Integer userID;
        @SerializedName("connectionID")
        @Expose
        private Integer connectionID;
        @SerializedName("chainIdentifier")
        @Expose
        private String chainIdentifier;
        @SerializedName("chainBalance")
        @Expose
        private Object chainBalance;

        public Integer getUserChainMappingID() {
            return userChainMappingID;
        }

        public void setUserChainMappingID(Integer userChainMappingID) {
            this.userChainMappingID = userChainMappingID;
        }

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }

        public Integer getConnectionID() {
            return connectionID;
        }

        public void setConnectionID(Integer connectionID) {
            this.connectionID = connectionID;
        }

        public String getChainIdentifier() {
            return chainIdentifier;
        }

        public void setChainIdentifier(String chainIdentifier) {
            this.chainIdentifier = chainIdentifier;
        }

        public Object getChainBalance() {
            return chainBalance;
        }

        public void setChainBalance(Object chainBalance) {
            this.chainBalance = chainBalance;
        }

    }



}
