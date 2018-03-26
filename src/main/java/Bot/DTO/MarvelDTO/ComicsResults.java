package Bot.DTO.MarvelDTO;


import java.sql.Date;
import java.util.List;

public class ComicsResults {
    private Long id;
    private Long digitalId;
    private String title;
    private Double issueNumber;
    private String variantDescription;
    private String decription;
    private String modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private Long pageCount;
    private List<TextObjects> textObjects;
    private String resourceURI;
    private List<Urls> urls;
    private Series series;
    private List<Variants> variants;
    private List<Collections> collections;
    private List<CollectedIssues> collectedIssues;
    private List<ComicsDate> dates;
    private List<Price> prices;
    private Thumbnail thumbnail;
    private List<Image> images;
    private Creator creators;
    private Character characters;
    private Stories stories;
    private Events events;

    public ComicsResults(Long id, Long digitalId, String title, Double issueNumber, String variantDescription, String decription,
                         String modified,String isbn, String upc, String diamondCode, String ean, String issn, String format,
                         Long pageCount, List<TextObjects> textObjects, String resourceURI, List<Urls> urls, Series series,
                         List<Variants> variants, List<Collections> collections, List<CollectedIssues> collectedIssues,
                         List<ComicsDate> dates, List<Price> prices, Thumbnail thumbnail, List<Image> images, Creator creators,
                         Character characters, Stories stories, Events events){
        this.id = id;
        this.digitalId = digitalId;
        this.title = title;
        this.issueNumber = issueNumber;
        this.variantDescription = variantDescription;
        this.decription = decription;
        this.modified = modified;
        this.isbn = isbn;
        this.upc = upc;
        this.diamondCode = diamondCode;
        this.ean = ean;
        this.issn = issn;
        this.format = format;
        this.pageCount = pageCount;
        this.textObjects = textObjects;
        this.resourceURI = resourceURI;
        this.urls = urls;
        this.series = series;
        this.variants = variants;
        this.collections = collections;
        this.collectedIssues = collectedIssues;
        this.dates = dates;
        this.prices = prices;
        this.thumbnail = thumbnail;
        this.images = images;
        this.creators = creators;
        this.characters = characters;
        this.stories = stories;
        this.events = events;
    }

    public ComicsResults(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getModified() {
        return modified;
    }

    public Double getIssueNumber() {
        return issueNumber;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public List<Urls> getUrls() {
        return urls;
    }

    public Series getSeries() {
        return series;
    }

    public List<CollectedIssues> getCollectedIssues() {
        return collectedIssues;
    }

    public List<TextObjects> getTextObjects() {
        return textObjects;
    }

    public Long getDigitalId() {
        return digitalId;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public String getDecription() {
        return decription;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public List<Collections> getCollections() {
        return collections;
    }

    public String getEan() {
        return ean;
    }

    public String getFormat() {
        return format;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIssn() {
        return issn;
    }

    public String getUpc() {
        return upc;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public Stories getStories() {
        return stories;
    }

    public Events getEvents() {
        return events;
    }

    public Character getCharacters() {
        return characters;
    }

    public Creator getCreators() {
        return creators;
    }

    public List<ComicsDate> getDates() {
        return dates;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setUrls(List<Urls> urls) {
        this.urls = urls;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public void setDigitalId(Long digitalId) {
        this.digitalId = digitalId;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public void setIssueNumber(Double issueNumber) {
        this.issueNumber = issueNumber;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public void setTextObjects(List<TextObjects> textObjects) {
        this.textObjects = textObjects;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStories(Stories stories) {
        this.stories = stories;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public void setCharacters(Character characters) {
        this.characters = characters;
    }

    public void setCollectedIssues(List<CollectedIssues> collectedIssues) {
        this.collectedIssues = collectedIssues;
    }

    public void setCollections(List<Collections> collections) {
        this.collections = collections;
    }

    public void setCreators(Creator creators) {
        this.creators = creators;
    }

    public void setDates(List<ComicsDate> dates) {
        this.dates = dates;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }
}
