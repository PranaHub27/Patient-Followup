import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class InputStreamToFile1 {

	/**
	 * The default buffer size
	 */
	public static final int DEFAULT_BUFFER_SIZE = 8192;

	public static void main(String[] args) throws IOException {

		URI u = URI.create("https://www.google.com/");
		try (InputStream inputStream = u.toURL().openStream()) {

			File file = new File("c:\\test\\google.txt");

			// copyInputStreamToFile(inputStream, file);

		}

	}

	public static void copyInputStreamToFile(InputStream is, String file) throws IOException {
		try {

			OutputStream os = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int bytesRead;
			// read from is to buffer
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			is.close();
			// flush OutputStream to write any buffered data to file
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}