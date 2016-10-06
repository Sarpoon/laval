package util;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.basket.ItemDescription;

public class ItemsCollection {

	Map<String, ItemDescription> collection = null;

	private ItemsCollection() {
	}

	private static ItemsCollection INSTANCE = new ItemsCollection();

	public static ItemsCollection getInstance() {
		return INSTANCE;
	}

	private void loadFromCsv() {
		this.collection = new HashMap<String, ItemDescription>();

		String csvFile = "ressources/items.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] splittedLine = line.split(cvsSplitBy);
				String type = splittedLine[0];
				Color color = new Color(Integer.parseInt(splittedLine[2]),
						Integer.parseInt(splittedLine[3]),
						Integer.parseInt(splittedLine[4]));
				String description = splittedLine[1];
				double price = Double.parseDouble(splittedLine[5]);
				String imagePath = splittedLine[6];
				int radius = Integer.parseInt(splittedLine[7]);
				ItemDescription itemDescription = new ItemDescription(type,
						description, color, price, imagePath, radius);
				collection.put(type, itemDescription);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ItemDescription getItem(String type) {
		if (collection == null) {
			loadFromCsv();
		}
		ItemDescription property = collection.get(type);
		return property;
	}

	public List<String> getTypes() {
		if (collection == null) {
			loadFromCsv();
		}
		return new ArrayList<String>(collection.keySet());
	}
}
