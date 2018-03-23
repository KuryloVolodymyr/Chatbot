package Bot.Util;

public class MappingTestInner {
    public long hello;
    public String how;

    public MappingTestInner(long hello, String how) {
        this.hello = hello;
        this.how = how;
    }

    public MappingTestInner(){
        super();
    }

    public long getHello() {
        return hello;
    }

    public String getHow() {
        return how;
    }

    @Override
    public String toString() {
        return "\t{\n" +
                "\t\t\t\"hello\": \""+hello+"\",\n" +
                "\t\t\t\"how\": \""+how+"\"\n" +
                "\t\t}";
    }
}
