package c.hackathon.decentralisedleague.RecyclerModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuyPlayerModel {
    @SerializedName("workflowFunctionID")
    @Expose
    private Integer workflowFunctionID;
    @SerializedName("workflowActionParameters")
    @Expose
    private List<Object> workflowActionParameters = null;

    public Integer getWorkflowFunctionID() {
        return workflowFunctionID;
    }

    public void setWorkflowFunctionID(Integer workflowFunctionID) {
        this.workflowFunctionID = workflowFunctionID;
    }

    public List<Object> getWorkflowActionParameters() {
        return workflowActionParameters;
    }

    public void setWorkflowActionParameters(List<Object> workflowActionParameters) {
        this.workflowActionParameters = workflowActionParameters;
    }
}
