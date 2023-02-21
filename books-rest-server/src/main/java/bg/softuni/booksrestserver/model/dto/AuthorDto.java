package bg.softuni.booksrestserver.model.dto;

public class AuthorDto {
    private String name;

    public AuthorDto() {

    }

    public String getName() {
        return name;
    }

    public AuthorDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorDto{");
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
