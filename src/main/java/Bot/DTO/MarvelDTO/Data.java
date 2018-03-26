package Bot.DTO.MarvelDTO;

import java.util.List;

public class Data {
    private Long offset;
    private Long limit;
    private Long total;
    private Long count;
    private List<CharacterResults> results;

    public Data(Long offset, Long limit, Long total, Long count, List<CharacterResults> results) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public Data(){
        super();
    }

    public List<CharacterResults> getResults() {
        return results;
    }

    public Long getCount() {
        return count;
    }

    public Long getLimit() {
        return limit;
    }

    public Long getOffset() {
        return offset;
    }

    public Long getTotal() {
        return total;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public void setResults(List<CharacterResults> results) {
        this.results = results;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
