package bot.dto.Broadcast;

import java.util.Arrays;
import java.util.List;

public class Labels {
    private String operator;
    private List<Long> values;

    public Labels() {
        this.operator = "OR";
        this.values = Arrays.asList(1855101247917002L,
                1919874554697634L);
    }

    public List<Long> getValues() {
        return values;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setValues(List<Long> values) {
        this.values = values;
    }

    public String getOperator() {
        return operator;
    }
}
