package de.cognicrypt.codegenerator.taskintegrator.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

import de.cognicrypt.codegenerator.Constants;
import de.cognicrypt.codegenerator.taskintegrator.models.ClaferFeature;
import de.cognicrypt.codegenerator.taskintegrator.models.ClaferModel;
import de.cognicrypt.codegenerator.taskintegrator.models.ClaferProperty;

public class ClaferFeatureTest {

	public static final String testFileFolder = "src/test/resources/taskintegrator/";

	@Test
	public final void testPropertyAmount() {
		ArrayList<ClaferProperty> featureProperties = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			featureProperties.add(new ClaferProperty("featureProperty" + String.valueOf(i), "propertyType"));
		}
		ClaferFeature claferFeature = new ClaferFeature(Constants.FeatureType.CONCRETE, "testFeature", "");
		claferFeature.setFeatureProperties(featureProperties);

		assertEquals(claferFeature.getFeatureProperties(), featureProperties);
	}

	@Test
	public final void testClaferFeatureToString() throws IOException {
		// file to test against
		String expectedFilename = testFileFolder + "testFile1.cfr";

		// programmatically created Clafer feature
		ClaferFeature cfrFeature = new ClaferFeature(Constants.FeatureType.ABSTRACT, "Algorithm", "");
		cfrFeature.getFeatureProperties().add(new ClaferProperty("securityLevel", "Security"));

		// generate file from ClaferFeature instance
		String actualFilename = testFileFolder + "testFile1_tmp.cfr";
		Path actualPath = Paths.get(actualFilename);

		Charset charset = Charset.forName("UTF-8");
		String s = cfrFeature.toString();
		try (BufferedWriter writer = Files.newBufferedWriter(actualPath, charset)) {
			writer.write(s, 0, s.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		// compare the files
		Object[] expectedLines = Files.readAllLines(Paths.get(expectedFilename)).toArray();
		Object[] actualLines = Files.readAllLines(Paths.get(actualFilename)).toArray();
		assertArrayEquals(expectedLines, actualLines);
	}

	@Test
	public final void testGetInheritedProperties() {
		ClaferModel claferModel = new ClaferModel();
		ClaferFeature featureA = new ClaferFeature(Constants.FeatureType.ABSTRACT, "Z", "");

		ArrayList<ClaferProperty> propertiesA = new ArrayList<ClaferProperty>();
		ClaferProperty expectedProperty = new ClaferProperty("prop", "integer");
		propertiesA.add(expectedProperty);
		featureA.setFeatureProperties(propertiesA);

		ClaferFeature featureB = new ClaferFeature(Constants.FeatureType.ABSTRACT, "Y", "Z");
		ClaferFeature featureC = new ClaferFeature(Constants.FeatureType.CONCRETE, "X", "Y");

		claferModel.add(featureA);
		claferModel.add(featureB);
		claferModel.add(featureC);

		ArrayList<ClaferProperty> inheritedPropertiesC = featureC.getInheritedProperties(claferModel);
		assertTrue(inheritedPropertiesC.contains(expectedProperty));

		ArrayList<ClaferProperty> inheritedPropertiesB = featureB.getInheritedProperties(claferModel);
		assertTrue(inheritedPropertiesB.contains(expectedProperty));

		ArrayList<ClaferProperty> inheritedPropertiesA = featureA.getInheritedProperties(claferModel);
		assertTrue(inheritedPropertiesA.contains(expectedProperty));
	}

	@Test
	public final void testGetDependencies() {
		ClaferFeature feature = new ClaferFeature(Constants.FeatureType.ABSTRACT, "Security", "Enum -> integer");
		Set<String> dependencies = feature.getDependencies();
		assertEquals(2, dependencies.size());
	}

	@AfterClass
	public final static void deleteFiles() throws IOException {
		// gather all files to be deleted
		ArrayList<String> temporaryFiles = new ArrayList<>();
		temporaryFiles.add(testFileFolder + "testFile1_tmp.cfr");
		
		// generate the paths and delete the files
		for (String filename : temporaryFiles) {
			Path path = Paths.get(filename);
			Files.delete(path);
		}
		
	}

}