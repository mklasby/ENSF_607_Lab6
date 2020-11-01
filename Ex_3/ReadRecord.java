package Ex_3;

import java.io.EOFException;

/**
* Started by M. Moussavi
* March 2015
* Completed by: STUDENT(S) NAME
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {

    private ObjectInputStream input;

    /**
     * opens an ObjectInputStream using a FileInputStream
     */
    private void readObjectsFromFile(String name) {
        MusicRecord record;

        try {
            input = new ObjectInputStream(new FileInputStream(name));
        } catch (IOException ioException) {
            System.err.println("Error opening file.");
        }

        /*
         * The following loop is supposed to use readObject method of ObjectInputSteam
         * to read a Ex_3.MusicRecord object from a binary file that contains several
         * records. Loop should terminate when an EOFException is thrown.
         */
        while (true) {
            Object thisObj = null;
            try {
                thisObj = input.readObject();
            } catch (EOFException e) {
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            thisObj = (MusicRecord) thisObj;
        }

        //
        // // TO BE COMPLETED BY THE STUDENTS
        //
        //
        // } // END OF WHILE
        // }
        // ADD NECESSARY catch CLAUSES HERE

    } // END OF METHOD

    public static void main(String[] args) {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("mySongs.ser");
    }
}