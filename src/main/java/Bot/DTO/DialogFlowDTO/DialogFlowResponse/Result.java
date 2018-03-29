package Bot.DTO.DialogFlowDTO.DialogFlowResponse;

import java.util.List;

public class Result {
    private String source;
    private String resolvedQuery;
    private String speech;
    private String action;
    private Boolean actionIncomplete;
    private Parameters parameters;
    private List<Context> contexts;
    private Metadata metadata;
    private Fulfillment fulfillment;
    private Double score;

    public Result(String source, String resolvedQuery, String speech, String action, Boolean actionIncomplete, Parameters parameters,
                  List<Context> contexts, Metadata metadata, Fulfillment fulfillment, Double score){
        this.source = source;
        this.resolvedQuery = resolvedQuery;
        this.speech = speech;
        this.action = action;
        this.actionIncomplete = actionIncomplete;
        this.parameters = parameters;
        this.contexts = contexts;
        this.metadata = metadata;
        this.fulfillment = fulfillment;
        this.score = score;
    }

    public Result(){
        super();
    }

    public String getSpeech() {
        return speech;
    }

    public Boolean getActionIncomplete() {
        return actionIncomplete;
    }

    public Double getScore() {
        return score;
    }

    public Fulfillment getFulfillment() {
        return fulfillment;
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public String getAction() {
        return action;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public String getSource() {
        return source;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActionIncomplete(Boolean actionIncomplete) {
        this.actionIncomplete = actionIncomplete;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    public void setFulfillment(Fulfillment fulfillment) {
        this.fulfillment = fulfillment;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public void setResolvedQuery(String resolvedQuery) {
        this.resolvedQuery = resolvedQuery;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
