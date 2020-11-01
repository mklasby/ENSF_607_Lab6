package Ex_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Writes MusicRecord to file 
 * 
 * @author: M. Moussavi, Davis Allan, Mike Lasby
 * @since: Nov. 1, 2020
 * @version: 1.0
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class WriteRecord {

	ObjectOutputStream objectOut = null;
	MusicRecord record = null;
	Scanner stdin = null;
	Scanner textFileIn = null;

	/**
	 * Creates an blank Ex_3.MusicRecord object
	 */
	public WriteRecord() {
		record = new MusicRecord();
	}

	/**
	 * Initializes the data fields of a record object
	 * 
	 * @param year       - year that song was purchased
	 * @param songName   - name of the song
	 * @param singerName - singer's name
	 * @param price      - CD price
	 */
	public void setRecord(int year, String songName, String singerName, double price) {
		record.setSongName(songName);
		record.setSingerName(singerName);
		record.setYear(year);
		record.setPrice(price);
	}

	/**
	 * Opens a file input stream, using the data field textFileIn
	 * 
	 * @param textFileName name of text file to open
	 */
	public void openFileInputStream(String textFileName) {
		try {
			textFileIn = new Scanner(new FileInputStream(textFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Opens an ObjectOutputStream using objectOut data field
	 * 
	 * @param objectFileName name of the object file to be created
	 */
	public void openObjectOutputStream(String objectFileName) {
		try {
			objectOut = new ObjectOutputStream(new FileOutputStream(new File(objectFileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads records from given text file, fills the blank Ex_3.MusicRecord created
	 * by the constructor with the existing data in the text file and serializes
	 * each record object into a binary file
	 */
	public void createObjectFile() {

		while (textFileIn.hasNext()) // loop until end of text file is reached
		{
			int year = Integer.parseInt(textFileIn.nextLine());
			System.out.print(year + "  "); // echo data read from text file

			String songName = textFileIn.nextLine();
			System.out.print(songName + "  "); // echo data read from text file

			String singerName = textFileIn.nextLine();
			System.out.print(singerName + "  "); // echo data read from text file

			double price = Double.parseDouble(textFileIn.nextLine());
			System.out.println(price + "  "); // echo data read from text file

			setRecord(year, songName, singerName, price);
			textFileIn.nextLine(); // read the dashed lines and do nothing

			try {
				this.objectOut.writeObject(this.record);
				objectOut.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		textFileIn.close();
	}

	public static void main(String[] args) throws IOException {

		WriteRecord d = new WriteRecord();

		String textFileName = "Ex_3/someSongs.txt"; // Name of a text file that contains
		// song records

		String objectFileName = "Ex_3/mySongs.ser"; // Name of the binary file to
		// serialize record objects

		d.openFileInputStream(textFileName); // open the text file to read from

		d.openObjectOutputStream(objectFileName); // open the object file to
													// write music records into it

		d.createObjectFile(); // read records from opened text file, and write
								// them into the object file.
	}
}
