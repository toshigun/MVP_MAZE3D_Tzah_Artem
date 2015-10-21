package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyModelTest {

	
	@Test
	public void test() {
		
		MyModel modelTest = new MyModel();
		
		//fetchData null test
		modelTest.fetchData(null, null, null);
		assertSame(modelTest.DataBase, modelTest.DataBase);
		
		//name null test
		modelTest.fetchData("notNull", null, null);
		assertSame(modelTest.DataBase, modelTest.DataBase);
		
		//fetchData 0 test
		modelTest.fetchData("0", "0", "0");
		assertSame(modelTest.DataBase, modelTest.DataBase);
		
		//error in syntax command
		modelTest.fetchData("error", "0", "0");
		assertSame(modelTest.DataBase, modelTest.DataBase);
	}

	
	
}
