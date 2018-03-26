package Bot.DTO.MarvelDTO;

import java.util.List;

public class ComicsData {
    private Long offset;
    private Long limit;
    private Long total;
    private Long count;
    private List<ComicsResults> results;

    public ComicsData(Long offset, Long limit, Long total, Long count, List<ComicsResults> results){
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public ComicsData(){
        super();
    }

    public Long getTotal() {
        return total;
    }

    public Long getOffset() {
        return offset;
    }

    public Long getLimit() {
        return limit;
    }

    public Long getCount() {
        return count;
    }

    public List<ComicsResults> getResults() {
        return results;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setResults(List<ComicsResults> results) {
        this.results = results;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
