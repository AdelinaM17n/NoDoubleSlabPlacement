package io.github.maheevil.nodoubleslab;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NoDoubleSlabPlacementMod implements ModInitializer {
	private static boolean placeDoubleSlabs;
	private static final File configFile = new File(new File(Minecraft.getInstance().gameDirectory, "config"), "nodoubleslab.txt");

	@Override
	public void onInitialize() {
		try{
			loadFromFile();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("all")
	private static void loadFromFile() throws IOException{
		if(configFile.exists() && configFile.isFile() && configFile.canRead()){
			Scanner reader = new Scanner(configFile);
			placeDoubleSlabs = !reader.nextLine().equalsIgnoreCase("false");
		}else {
			placeDoubleSlabs = true;
			configFile.createNewFile();
		}
		saveToFile();
	}

	@SuppressWarnings("all")
	public static void saveToFile() throws IOException{
		configFile.delete();
		FileWriter fw = new FileWriter(configFile);
		fw.write(String.valueOf(placeDoubleSlabs));
		fw.close();
	}

	public static void setToggleValue(boolean bool){
		placeDoubleSlabs = bool;
		try{
			saveToFile();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}

	public static boolean getToggleValue(){
		return placeDoubleSlabs;
	}

}
