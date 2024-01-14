package by.sep.dto;

public class ProductDto {
    private String id;
    private String name;
    private String description;
    private int durationInMonth;

    public ProductDto() {
    }

    public ProductDto(String id, String name, String description, int durationInMonth) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationInMonth = durationInMonth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInMonth() {
        return durationInMonth;
    }

    public void setDurationInMonth(int durationInMonth) {
        this.durationInMonth = durationInMonth;
    }
}
