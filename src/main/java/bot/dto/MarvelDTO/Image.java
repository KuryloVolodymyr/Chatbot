package bot.dto.MarvelDTO;

public class Image {
    private String path;
    private String extension;

    public Image(String path, String extension){
        this.path = path;
        this.extension = extension;
    }

    public Image (){
        super();
    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
