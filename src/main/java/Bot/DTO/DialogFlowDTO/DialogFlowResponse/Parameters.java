package Bot.DTO.DialogFlowDTO.DialogFlowResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Parameters {

    private String heroName;

    public Parameters(String heroName) {
        this.heroName = heroName;
    }

    public Parameters() {
        super();
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
