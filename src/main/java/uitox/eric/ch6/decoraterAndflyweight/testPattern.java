package uitox.eric.ch6.decoraterAndflyweight;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class testPattern {

	public static void main(String[] args) throws IOException {
//		decoratorPattern();
	}
	
	public static void decoratorPattern() throws IOException {
		final File f = new File("target.txt");
		final FileOutputStream fos = new FileOutputStream(f);
		final BufferedOutputStream bos = new BufferedOutputStream(fos);
		final ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeBoolean(true);
		oos.writeInt(42);
		oos.writeObject(new ArrayList<Integer>());
		
		oos.flush();
		oos.close();
		bos.close();
		fos.close();
	}
	
	@Test
	public void sameIntegerInstances() {
		final Integer a = Integer.valueOf(56);
		final Integer b = Integer.valueOf(56);
		
		assertSame(a, b);
	}
}
