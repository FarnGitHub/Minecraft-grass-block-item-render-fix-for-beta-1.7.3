package farn.grassblockitemfix;

import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.Properties;

public class Config {

    public static int grassColor = -8602261;
    public static boolean useColorFromBiome = true;
    public static double humid = 0.5D;
    public static double temp = 1D;

    private static final Properties props = new Properties();
    private static final File propsFile = new File(Minecraft.getMinecraftDir(), "grassItemRenderFix.cfg");
    public static boolean init = false;

    public static void load() {
        init = true;
        if(!propsFile.exists()) {
            write();
        }

        try {
            props.load(new FileReader(propsFile));
            grassColor = parseGrassColor();
            useColorFromBiome = parseBoolean("biome_colorizer", true);
            humid = parseDouble("humid", 0.5);
            temp = parseDouble("temp", 1.0);
        } catch(IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public static void write() {
        try(PrintWriter out = new PrintWriter(new FileWriter(propsFile))) {
            out.println("# Static color for grass block inventory render");
            out.println("# Value not used when biome_colorizer is enabled");
            out.println("static_grass_color=" + grassColor);
            out.println("#");
            out.println("# Use the color from the grass colorizer instead of static color value");
            out.println("# Color are control by humidity and temperature");
            out.println("biome_colorizer" + useColorFromBiome);
            out.println("humid=" + humid);
            out.println("temp=" + temp);
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public static int parseGrassColor() {
        try {
            return Integer.parseInt(props.getProperty("static_grass_color", "-8602261"));
        } catch (Exception e) {
            return -8602261;
        }
    }

    public static double parseDouble(String key, double def) {
        try {
            return Double.parseDouble(props.getProperty(key, Double.toString(def)));
        } catch (Exception e) {
            return def;
        }
    }

    public static boolean parseBoolean(String key, boolean def) {
        try {
            return props.getProperty(key, Boolean.toString(def)).equals("true");
        } catch (Exception e) {
            return def;
        }
    }
}
