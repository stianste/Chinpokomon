package pokemon;

import javafx.scene.image.ImageView;
import nature.Nature;
import stages.Stages;

public abstract class Pokemon {
	public static int pokemonNameMaxLength = 15;
	
	private double EXP = 0;
	private String sprite;
	private String vsSprite;
	private String backSprite;
	private Stats stats;
	private int height;
	private int width;
	private int
	hpEV = 0, atkEV = 0, defEV = 0, spAtkEV = 0, spDefEV = 0, spdEV = 0;
	Nature nature;
	
	private double[] evYield;
	private String name;
	
	private int level;

	
	public Pokemon(String name, int level, Stats stats, Nature nature, double[] evY, 
			String vsSprite, String backSprite, int width, int height){
		this.level = level;
		this.stats = stats;
		setName(name);
		this.nature = nature;
		this.vsSprite = vsSprite;
		this.backSprite = backSprite;
		this.height = height;
		this.width = width;
	}
	public double getHeight(){
		return (double) height;
	}
	public double getWidth(){
		return (double) width;
	}
	public ImageView getVsSprite(){
		return new ImageView(Stages.fileStringGif(this.vsSprite));
	}
	public ImageView getBackSprite(){
		return new ImageView(Stages.fileStringGif(this.backSprite));
	}
	@Override
	public String toString(){
		return ("Name: " + name +" " + getStats().toString());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.length() > pokemonNameMaxLength){
			this.name = name.substring(0,pokemonNameMaxLength+1);
		}else{
			this.name = name;
		}
	}
	public Stats getStats(){
		return stats.getStats(this);
	}

	public int getHPEV() {
		return hpEV;
	}

	public int getLevel() {
		return level;
	}

	public double getNature(int i) {
		return nature.getNatureYield(i);
	}

	public int getAtkEV() {
		return atkEV;
	}

	public int getDefEV() {
		return defEV;
	}

	public int getSpAtkEV() {
		return spAtkEV;
	}

	public int getSpDefEV() {
		return spDefEV;
	}

	public int getSpdEV() {
		return spdEV;
	}
	public ImageView getSprite() {
		return getVsSprite();
	}
}
