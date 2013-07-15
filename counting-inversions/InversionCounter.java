import java.io.IOException;


public class InversionCounter {

	/**
     * The main class. Should be invoked with -file=filename option.
     */
	public static void main(String[] args) {
		try {
			new InversionCounter().countInversions(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void countInversions(String[] args) throws IOException {
		
	}

}
