package uitox.eric.ch9;

import java.util.ArrayList;

public class ListOfIntegerArraysForTest extends ArrayList<Integer[]> {

	private static final long serialVersionUID = 1L;

//	{
//		this.add(new Integer[] { 1, 2, 3, -1, 2, 0 });
//		this.add(new Integer[] { 0, 1, 1, -1, 0, 0 });
//		this.add(new Integer[] { -11, 2, -9, -13, -22, -5 });
//	}

	public ListOfIntegerArraysForTest() {
		super(3);
		
		{
			this.add(new Integer[] { 1, 2, 4, -1, 2, 0 });
			this.add(new Integer[] { 0, 1, 1, -1, 0, 0 });
			this.add(new Integer[] { -11, 2, -9, -13, -22, -5 });
		}
	}
}
