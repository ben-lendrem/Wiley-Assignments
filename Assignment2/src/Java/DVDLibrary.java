package Java;

import java.util.ArrayList;
import java.util.Iterator;

public class DVDLibrary {
    private ArrayList<DVD> library;

    public DVDLibrary(String filepath) {
        if (filepath != null) {
            library = LoadFromFile(filepath);
        } else {
            library = new ArrayList<DVD>();
        }
    }

    private void AddDVD() {
        //Get user input for each DVD attribute
        //Check that that input is valid

        //If all input is valid, create DVD object
        //if not, output that creation was invalid

    }

    private boolean RemoveDVD(String byTitle) {
        //search through library
        Iterator<DVD> it = library.iterator();
        while (it.hasNext()) {
            //if DVD found with title, remove from arraylist & return true
            if (it.next().getTitle().equals(byTitle)) {
                it.remove();
                System.out.println("The DVD " + byTitle + " has been removed from the library!");
                return true;
            }
        }

        //if no DVD found, output no DVD with that name and return false

        return false;
    }

    private boolean RemoveDVD(int index) {
        //remove DVD at index provided
        //if index out of bounds, throw error
        library.remove(index);
        return true;
    }

    private boolean EditDVD(DVD in) {
        //Ask user which attribute they want to edit
        //Call DVD.Edit(DVDAttribute)


        return false;
    }

    private void PrintAllDVDs() {
        //Call DVDLibrary.toString (override)
    }

    private void PrintDVD(String byTitle) {
        //Search for DVD with matching title
        //if found call DVD.toString
    }

    private void PrintDVD(DVD in) {
        //call in.toString
    }

    private DVD SearchDVD(String byTitle) {
        //find the DVD with title matching
        //return if found
        //throw error if not

        return new DVD();
    }

    private ArrayList<DVD> LoadFromFile(String filepath) {
        //TODO File I/O
        return new ArrayList<DVD>();
    }

    private void SaveToFile(String filepath) {
        //TODO File I/O
    }

    private void PrintMenu() {
        //Print main menu with options for functionality + quitting the program
    }

    @Override
    public String toString() { //TODO write override
        return "DVDLibrary{" +
                "library=" + library +
                '}';
    }
}
