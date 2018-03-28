package Bot.DTO.DialogFlowDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Parameters {

    @JsonProperty("MarvelHeroes")
    private List<String> marvelHeloes;

    public Parameters(List<String> marvelHeloes) {
        this.marvelHeloes = marvelHeloes;
    }

    public Parameters() {
        super();
    }

    public List<String> getMarvelHeloes() {
        return marvelHeloes;
    }

    public void setMarvelHeloes(List<String> marvelHeloes) {
        this.marvelHeloes = marvelHeloes;
    }
}
