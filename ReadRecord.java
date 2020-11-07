import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class to read .ser music records form file. Specify filename to read with
 * extension as CLI argument.
 * 
 * @author Davis Allan & Mike Lasby
 * @since Nov. 7, 2020
 * @version 2.0
 */
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
        try {
            while (true) {
                record = (MusicRecord) input.readObject();
                System.out.println(record.getYear() + " " + record.getSongName() + " " + record.getSingerName() + " "
                        + record.getPurchasePrice());
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

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ReadRecord d = new ReadRecord();
        if (args.length == 0) {
            System.out.print(
                    "Please specify file to read as command line argument eg., java ReadRecord allSongs.ser\nGoodbye\n");
            System.exit(1);
        }
        d.readObjectsFromFile(args[0].toString());
    }
}