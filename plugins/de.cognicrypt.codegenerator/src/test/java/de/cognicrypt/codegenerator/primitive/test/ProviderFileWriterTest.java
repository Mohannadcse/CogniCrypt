package de.cognicrypt.codegenerator.primitive.test;
 
 import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import de.cognicrypt.codegenerator.Constants;
import de.cognicrypt.codegenerator.primitive.providerUtils.ProviderFile;
import de.cognicrypt.codegenerator.utilities.Utils;
 
 public class ProviderFileWriterTest {
 
 	ProviderFile providerFile = new ProviderFile("test provider");
 	String dirJar = "src/test/resources/test.jar";
 	boolean elementExists=false;
 	
 
 
 	@Test
	public void compilteJavaFileTest() {
		File testJavaFile = Utils.getResourceFromWithin(Constants.testPrimitverFolder + "testJava.java");
		providerFile.compileFile(testJavaFile);
		File testClassFile = Utils.getResourceFromWithin(Constants.testPrimitverFolder + "testJava.class");
		assertTrue(testClassFile.exists());
	}
 	
// 	private boolean fileExists(File[] files, String element) {
// 		boolean elementExists=false;
// 		for (int i = 0; i < files.length; i++  ) {
// 			if (files[i].isFile()) {
// 				if (files[i].getName().equals(element))
// 					elementExists=true;
// 			}
// 		}
// 
// 		return elementExists;
// 	}
 	
 
 	

 
 	
 
 }