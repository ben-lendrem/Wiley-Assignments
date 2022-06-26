package Java;

import Java.Errors.InvalidInputException;

import java.util.InputMismatchException;
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

    private void ResetValues() { //Resets values of DVD to their defaults
        this.title = null;
        this.releaseDate = "01/01/1900";
        this.ageRating = "U";
        this.director = null;
        this.studio = null;
        this.note = null;
    }


    public void Edit(DVDAttribute in) throws RuntimeException {

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
    Get/Set methods for variables
     */

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    private void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAgeRating() {
        return ageRating;
    }

    private void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getDirector() {
        return director;
    }

    private void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    private void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    private void setNote(String note) {
        this.note = note;
    }
}