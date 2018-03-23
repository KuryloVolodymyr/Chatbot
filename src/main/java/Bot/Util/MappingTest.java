package Bot.Util;

import java.util.List;

public class MappingTest {
    public String name;
    public List<MappingTestInner> inner;

    public MappingTest(String name, List<MappingTestInner> inner) {
        this.name = name;
        this.inner = inner;
    }

    public MappingTest() {
        super();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"name\": \""+name+"\",\n" +
                "\t\"inner"+
                "\t"+inner.toString()+
                "}";
    }
}
