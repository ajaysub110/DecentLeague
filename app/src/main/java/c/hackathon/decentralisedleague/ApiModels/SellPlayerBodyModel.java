package c.hackathon.decentralisedleague.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SellPlayerBodyModel {
    @SerializedName("workflowFunctionId")
    @Expose
    private Integer workflowFunctionId;
    @SerializedName("workflowActionParameters")
    @Expose
    private List<WorkflowActionParameter> workflowActionParameters = null;

    public Integer getWorkflowFunctionId() {
        return workflowFunctionId;
    }

    public void setWorkflowFunctionId(Integer workflowFunctionId) {
        this.workflowFunctionId = workflowFunctionId;
    }

    public List<WorkflowActionParameter> getWorkflowActionParameters() {
        return workflowActionParameters;
    }

    public void setWorkflowActionParameters(List<WorkflowActionParameter> workflowActionParameters) {
        this.workflowActionParameters = workflowActionParameters;
    }

    public static class WorkflowActionParameter {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private String value;

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

    }
}
