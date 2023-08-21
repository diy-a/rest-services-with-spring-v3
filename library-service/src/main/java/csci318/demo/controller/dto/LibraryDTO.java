package csci318.demo.controller.dto;

public class LibraryDTO {

    private String libraryName;
    private String location;

    public LibraryDTO() {

    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
