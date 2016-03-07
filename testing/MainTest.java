package testing;

import nature.Adamant;
import pokemon.Charmander;

public class MainTest {
	public static void main(String[] args){
		Charmander cm = new Charmander( 5, new Adamant());
		System.out.println(cm);
	}
}
