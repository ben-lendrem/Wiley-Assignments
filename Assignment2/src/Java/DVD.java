package Java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DVD {
    private String title = null;
    private String releaseDate = "01/01/1900";
    private String ageRating = "U";
    private String director = null;
    private String studio = null;
    private String note = null;

    public DVD() {}

    public DVD(boolean userInit) {
        if (userInit) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.printf("Enter DVD title: ");
                this.title = input.nextLine();
                System.out.printf("Enter DVD release date:\n" +
                        "Day: ");
                String day = input.nextLine();
                System.out.printf("Month: ");
                String month = input.nextLine();
                System.out.printf("Year: ");
                String year = input.nextLine();
                this.releaseDate = (day + "/" + month + "/" + year);
                System.out.printf("Enter DVD age rating: ");
                this.ageRating = input.nextLine();
                System.out.printf("Enter DVD director: ");
                this.director = input.nextLine();
                System.out.printf("Enter DVD studio: ");
                this.studio = input.nextLine();
                System.out.printf("Enter DVD note: ");
                this.note = input.nextLine();
                System.out.printf("DVD entered successfully! New DVD details: \n%s", this.toString());
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("You value you entered was invalid! DVD creation unsuccessful.");

            }

        }
    }

    public DVD(String title, String releaseDate, String ageRating,
               String director, String studio, String note) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.ageRating = ageRating;
        this.director = director;
        this.studio = studio;
        this.note = note;
    }

    private void ResetValues() { //called if DVD creation failed
        this.title = null;
        this.releaseDate = "01/01/1900";
        this.ageRating = "U";
        this.director = null;
        this.studio = null;
        this.note = null;
    }


    public void EditDVD() {

    }

    @Override
    public String toString() {
        return "DVD{" +
                "title: '" + title + '\'' +
                ", releaseDate: '" + releaseDate + '\'' +
                ", ageRating: '" + ageRating + '\'' +
                ", director: '" + director + '\'' +
                ", studio: '" + studio + '\'' +
                ", note: '" + note + '\'' +
                '}';
    }
}