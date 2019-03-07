package bot.dto.MarvelDTO;

public class Thumbnail {
    private String path;
    private String extension;

    public Thumbnail(String path, String extension){
        this.path = path;
        this.extension = extension;
    }

    public Thumbnail(){
        super();
    }

    public String getExtension() {
        return extension;
    }

    public String getPath() {
        return path;
    }

    public void setExtension(String extention) {
        this.extension = extention;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
