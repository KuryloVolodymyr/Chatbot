package bot.dto.MarvelDTO;

import java.util.Date;
import java.util.List;

public class CharacterResults {
    private Long id;
    private String name;
    private String description;
    private Date modified;
    private String resourceURI;
    private List<Urls> urls;
    private Thumbnail thumbnail;
    private Comics comics;
    private Stories stories;
    private Events events;
    private Series series;

    public CharacterResults(Long id, String name, String description, Date modified, String resourceURI, List<Urls> urls,
                            Thumbnail thumbnail, Comics comics, Stories stories, Events events, Series series){
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.resourceURI = resourceURI;
        this.urls = urls;
        this.thumbnail = thumbnail;
        this.comics = comics;
        this.stories = stories;
        this.events = events;
        this.series = series;
    }

    public CharacterResults(){
        super();
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }

    public Comics getComics() {
        return comics;
    }

    public Date getModified() {
        return modified;
    }

    public Events getEvents() {
        return events;
    }

    public List<Urls> getUrls() {
        return urls;
    }

    public Long getId() {
        return id;
    }

    public Stories getStories() {
        return stories;
    }

    public Series getSeries() {
        return series;
    }

    public String getDescription() {
        return description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public void setStories(Stories stories) {
        this.stories = stories;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setUrls(List<Urls> urls) {
        this.urls = urls;
    }
}
