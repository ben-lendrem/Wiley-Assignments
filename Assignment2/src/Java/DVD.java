package Java;

import java.util.Objects;
import java.util.Scanner;

enum DVDAttribute {
    TITLE,
    RELEASE,
    AGE,
    DIR,
    STUDIO,
    NOTE
}

public class DVD {
    private String title = null;
    private String releaseDate = "01/01/1800";
    private String ageRating = "U";
    private String director = null;
    private String studio = null;
    private String note = null;

    public DVD() {}

    public DVD(String title, String releaseDate, String ageRating,
               String director, String studio, String note) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.ageRating = ageRating;
        this.director = director;
        this.studio = studio;
        this.note = note;
    }



    public void Edit(DVDAttribute in, Scanner input) throws RuntimeException {
        switch (in) {
            case TITLE:
                System.out.print("Please enter a new title: ");
                this.title = Input.getUserString(input);
                break;
            case RELEASE:
                System.out.print("Please enter a new release date (format dd/mm/yyyy): ");
                this.releaseDate = Input.getUserString(input);
                break;
            case AGE:
                System.out.print("Please enter a new age rating: ");
                this.ageRating = Input.getUserString(input);
                break;
            case DIR:
                System.out.print("Please enter a new director: ");
                this.director = Input.getUserString(input);
                break;
            case STUDIO:
                System.out.print("Please enter a new studio: ");
                this.studio = Input.getUserString(input);
                break;
            case NOTE:
                System.out.print("Please enter a new user note: ");
                this.note = Input.getUserString(input);
        }
        System.out.println("Edit complete! New DVD details:\n " + this);
    }
    @Override
    public String toString() {
        return "{ " +
                "title: '" + title + '\'' +
                ", releaseDate: '" + releaseDate + '\'' +
                ", ageRating: '" + ageRating + '\'' +
                ", director: '" + director + '\'' +
                ", studio: '" + studio + '\'' +
                ", note: '" + note + '\'' +
                " }";
    }

    /*
    Get methods for variables (Set is never done outside of DVD so no setters)
     */

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public String getDirector() {
        return director;
    }

    public String getStudio() {
        return studio;
    }

    public String getNote() {
        return note;
    }

    public boolean equalsNull() {
        DVD nullDVD = new DVD();
        return (Objects.equals(this.title, nullDVD.title) &&
                Objects.equals(this.releaseDate, nullDVD.releaseDate) &&
                Objects.equals(this.ageRating, nullDVD.ageRating) &&
                Objects.equals(this.director, nullDVD.director) &&
                Objects.equals(this.studio, nullDVD.studio) &&
                Objects.equals(this.note, nullDVD.note)); //Objects.equals was suggested
    }
}