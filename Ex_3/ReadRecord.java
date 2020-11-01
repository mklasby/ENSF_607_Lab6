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
            input = new ObjectInputStream(new FileInputStream(Fname));
        } catch (IOException ioException) {
            System.err.println("Error opening file.");
        }

        /*
         * The following loop is supposed to use readObject method of ObjectInputSteam
         * to read a Ex_3.MusicRecord object from a binary file that contains several
         * records. Loop should terminate when an EOFException is thrown.
         */
<<<<<<< HEAD
        try{
            while (true) {
        }
        
            Object thisObj = null;
            try {
                thisObj = input.readObject();

            } catch (EOFException e) {
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
=======
        try {
            while (true) {
                record = (MusicRecord) input.readObject();
                System.out.println(record.getYear() + " " + record.getSongName() + " "
                                    + record.getSingerName() + " " + record.getPurchasePrice());
>>>>>>> 1006eceaf260b01d94b8d97eb7baffaf6b7f907b
            }
        } catch (EOFException e) {
            System.out.println("End of file reached");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // END OF METHOD

    public static void main(String[] args) {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("Ex_3/mySongs.ser");
    }
}