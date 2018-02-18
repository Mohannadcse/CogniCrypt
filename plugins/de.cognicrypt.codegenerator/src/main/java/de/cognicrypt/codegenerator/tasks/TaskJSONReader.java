package de.cognicrypt.codegenerator.tasks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.cognicrypt.codegenerator.Activator;
import de.cognicrypt.codegenerator.Constants;
import de.cognicrypt.codegenerator.utilities.Utils;

public class TaskJSONReader {

	private static volatile List<Task> tasks;
	
	/**
	 * Getter method for tasks. 
	 * @return List of Tasks in JSON file
	 */
	public static List<Task> getTasks() {

		if (TaskJSONReader.tasks == null) {
			try {
				final BufferedReader reader = new BufferedReader(new FileReader(Utils.getResourceFromWithin(Constants.jsonTaskFile)));
				final Gson gson = new Gson();
				TaskJSONReader.tasks = gson.fromJson(reader, new TypeToken<List<Task>>() {}.getType());

			} catch (final FileNotFoundException e) {
				Activator.getDefault().logError(e);
			}
		}

		return TaskJSONReader.tasks;
	}

}