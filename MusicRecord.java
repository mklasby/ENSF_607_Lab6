import java.io.Serializable;

/**
 * A simple class that represents a music record
 */
class MusicRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private int year_recorded;
	private String songName;
	private String singerName;
	private double purchase_price;

	/**
	 * A default constructor that builds a record with blank data
	 */
	public MusicRecord() {
		this(0, "", "", 0.0);
	}

	/**
	 * A constructor that initializes the music records with supplied data.
	 *
	 * @param year   the year
	 * @param song   the song
	 * @param singer the singer
	 * @param value  the value
	 */
	public MusicRecord(int year, String song, String singer, double value) {
		setYear(year);
		setSongName(song);
		setSingerName(singer);
		setPrice(value);
	}

	/**
	 * Sets the data field year_recorded to supplied argument year data.
	 *
	 * @param year the year
	 */
	public void setYear(int year) {
		year_recorded = year;
	}

	/**
	 * Returns the recording year
	 *
	 * @return the year
	 */
	public int getYear() {
		return year_recorded;
	}

	/**
	 * Sets the data field songName to supplied argument song
	 *
	 * @param song the song
	 */
	public void setSongName(String song) {
		songName = song;
	}

	/**
	 * Returns the songName name
	 *
	 * @return the song name
	 */
	public String getSongName() {
		return songName;
	}

	/**
	 * Sets the data field sinterName to supplied argument singer
	 *
	 * @param singer the singer
	 */
	public void setSingerName(String singer) {
		singerName = singer;
	}

	/**
	 * Returns the singer's name
	 *
	 * @return the singer name
	 */
	public String getSingerName() {
		return singerName;
	}

	/**
	 * Sets the data field purchase_price to supplied argument price
	 *
	 * @param value the value
	 */
	public void setPrice(double value) {
		purchase_price = value;
	}

	/**
	 * Returns the price
	 *
	 * @return the purchase price
	 */
	public double getPurchasePrice() {
		return purchase_price;
	}
}
