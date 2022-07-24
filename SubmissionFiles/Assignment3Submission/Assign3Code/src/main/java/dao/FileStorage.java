package dao;

import java.io.IOException;

public interface FileStorage {

    //no loadFromFile as auditStorage never needs to load the transactions into the program
    void SaveToFile() throws IOException;
    //Not sure if there's anything more to add to the interface, will see what can be refactored later

}
