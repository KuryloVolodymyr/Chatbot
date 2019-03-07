package bot.dto.DialogFlowDTO.DialogFlowResponse;

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
