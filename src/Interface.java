/*
 * BinarySearchTree represents a binary search tree.
 *
 * @author jocelynchang
 */
public class Interface {
	
	/*
	 * A method that returns a record with attributes derived from the typeData parameter and label.
	 * 
	 * @return  record with specified attributes
	 */
	private static Record findTypeData(String label, String typeData) {
		
		char first = typeData.charAt(0);
		int type;
		String data;
		Key k;
		Record rec;
		
		// checks if it's a sound file
		if (first == '-') {
			data = typeData.substring(1);
			type = 3;
		} else if (first == '+') {
			// checks if it's a music file
			data = typeData.substring(1);
			type = 4;
		} else if (first == '*') {
			// checks if it's a voice file
			data = typeData.substring(1);
			type = 5;
		} else if (first == '/') {
			// checks if it's a French translation
			data = typeData.substring(1);
			type = 2;
		} else {
			// checks if it's an animated image file
			if (typeData.endsWith(".gif")) {
				data = typeData;
				type = 7;
			} else if (typeData.endsWith(".jpg")) {
				// checks if it's an image file
				data = typeData;
				type = 6;
			} else if (typeData.endsWith(".html")) {
				// checks if it's an URL of a web page
				data = typeData;
				type = 8;
			} else {
				// if it's a string containing a definition of label
				data = typeData;
				type = 1;
			}
		}
		
		// create and return record
		k = new Key(label, type);
		rec = new Record(k, data);
		return rec;
	}
	
	public static void main(String[] args) {
		
		BSTDictionary dict = new BSTDictionary();
		String inputFile = args[0];
		
		// try reading in file name
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			String labelLine, typeData;
			labelLine = in.readLine();
			
			// reach each line until end of file is reached, insert the text into dict using findTypeData
			while (labelLine != null) {
				labelLine = labelLine.toLowerCase();
				typeData = in.readLine();
				if (typeData == null) {
					break;
				}
				dict.put(findTypeData(labelLine, typeData));
				labelLine = in.readLine();
			}
			
		} catch (Exception e) {
			System.out.println("Invalid file");
		}
	
		// prompt and read user command
		StringReader keyboard = new StringReader();
		String line = keyboard.read("Enter next command: ");
		String label = " ";
		Record rec;

		while (!line.equals("exit")) {
			
			if (line.startsWith("define")) {
				// find record with matching label and print the data
				label = line.substring(7);
				rec = dict.get(new Key(label, 1));
				
				if (rec != null) {
					System.out.println(rec.getDataItem());
				} else {
					System.out.printf("The word %s is not in the ordered dictionary\n", label);
				}
			} else if (line.startsWith("translate")) {
				// find record with matching label and print the data
				label = line.substring(10);
				rec = dict.get(new Key(label, 2));
				
				if (rec != null) {
					System.out.println(rec.getDataItem());
				} else {
					System.out.printf("There is no definition for the word %s\n", label);
				}
			} else if (line.startsWith("sound")) {
				// find record with matching label and play the audio file stored in data
				label = line.substring(6);
				rec = dict.get(new Key(label, 3));
				
				if (rec != null) {
					try {
						SoundPlayer sound = new SoundPlayer();
						sound.play(rec.getDataItem());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.printf("There is no sound file for %s\n", label);
				}
			} else if (line.startsWith("play")) {
				// find record with matching label and play the audio file stored in data
				label = line.substring(5);
				rec = dict.get(new Key(label, 4));
				
				if (rec != null) {
					try {
						SoundPlayer sound = new SoundPlayer();
						sound.play(rec.getDataItem());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.printf("There is no music file for %s\n", label);
				}
			} else if (line.startsWith("say")) {
				// find record with matching label and play the audio file stored in data
				label = line.substring(4);
				rec = dict.get(new Key(label, 5));
				
				if (rec != null) {
					try {
						SoundPlayer sound = new SoundPlayer();
						sound.play(rec.getDataItem());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.printf("There is no voice file for %s\n", label);
				}
			} else if (line.startsWith("show")) {
				// find record with matching label and show the image file stored in data
				label = line.substring(5);
				rec = dict.get(new Key(label, 6));
				
				if (rec != null) {
					try {
						PictureViewer pic = new PictureViewer();
						pic.show(rec.getDataItem());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.printf("There is no image file for %s\n", label);
				}
			} else if (line.startsWith("animate")) {
				// find record with matching label and show the image file stored in data
				label = line.substring(8);
				rec = dict.get(new Key(label, 7));
				
				if (rec != null) {
					try {
						PictureViewer pic = new PictureViewer();
						pic.show(rec.getDataItem());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.printf("There is no animated image file for %s\n", label);
				}
			} else if (line.startsWith("browse")) {
				// find record with matching label and show the web page whose URL is stored in data
				label = line.substring(7);
				rec = dict.get(new Key(label, 8));
				
				if (rec != null) {
					try {
						ShowHTML page = new ShowHTML();
						page.show(rec.getDataItem());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.printf("There is no webpage called %s\n", label);
				}
			} else if (line.startsWith("delete")) {
				// remove the record object with specified key
				
				// split the line into different variables holding the key's attributes
				String[] words = line.split(" ");
				String w = words[1];
				int k = Integer.parseInt(words[2]);
				Key key = new Key(w, k);
				
				try {
					dict.remove(key);
				} catch (Exception e) {
					System.out.printf("No record in the ordered dictionary has key (%s, %d)\n", w, k);
				}
			} else if (line.startsWith("add")) {
				// add the record object with specified parameters
				
				// split the line into different variables holding the record's attributes
				String[] words = line.split(" ");
				String w = words[1];
				int t = Integer.parseInt(words[2]);
				String c = words[3];
				Record record = new Record(new Key(w, t), c);
				
				try {
					dict.put(record);
				} catch (Exception e) {
					System.out.printf("A record with the given key (%s, %d) is already in the ordered dictionary\n", w, t);
				}
			} else if (line.startsWith("list")) {
				// print the labels of all record objects in dict that start with the specified prefix
				String prefix = line.substring(5);
				boolean found = false, done = false;
				
				// start from smallest node
				Record curr = dict.smallest(), first = null;
				
				// finds the first match, iterates through dict until found is true
				while (curr != null && found != true) {
					curr = dict.successor(curr.getKey());
					if (curr != null) {
						// checks if it's a match and assign it as first
						if (curr.getKey().getLabel().startsWith(prefix)) {
							first = curr;
							found = true;
						}
					}
				}
				
				// if there was a match
				if (found == true) {
					
					// prints out all the matching 
					while (curr != null && done == false) {
						if (curr.getKey().getLabel().startsWith(prefix)) {
							if (curr != first) {
								System.out.print(", ");
							} 
							System.out.print(curr.getKey().getLabel());
						} else {
							// when the node is no longer a match
							done = true;
							System.out.print("\n");
						}
						curr = dict.successor(curr.getKey());
					}
				}
				
				if (found == false) {
					System.out.printf("No label attributes in the ordered dictionary start with prefix %s\n", prefix);
				}
			} else if (line.startsWith("first")) {
				// finds the node with the smallest key
				Record record = dict.smallest();
				System.out.printf("%s, %d, %s\n", record.getKey().getLabel(), record.getKey().getType(), record.getDataItem());
			} else if (line.startsWith("last")) {
				// finds the node with the biggest key
				Record record = dict.largest();
				System.out.printf("%s, %d, %s\n", record.getKey().getLabel(), record.getKey().getType(), record.getDataItem());
			} else {
				// if the command is not accounted for
				System.out.println("Invalid command");
			}
			line = keyboard.read("Enter next command: ");
		}
		
	}
}
