package apps;

import java.util.List;

//not yet implemented
//import model.Table;
//import tables.BinaryTable;

//sandbox class to test the Binary table algorithm
public class Sandbox {
			
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("A list of my favorite movies, with the diretor, my personal percent, and the overall rating on Rotten Tomatoes (critics)");
		
		//2-ary constructor
		Table books = new BinaryTable("Movie Ratings", List.of("Title", "Author", "My Rating", "Overall Rating"));
		books.clear();
		books.put("Five Nights at Freddy's", List.of("Emma Tammi", 85, 32.0));
		books.put("Monsters University", List.of("Dan Scanlon", 80, 80.0));
		books.put("Wicked", List.of("Jon M Chu", 86, 88.0));
		
		//1-ary constructor
		Table books2 = new BinaryTable("Book Ratings");
		books2.put("The Amazing Spiderman-2", List.of("Marc Webb", 91, 50.0));
		books2.put("Across the Spiderverse", List.of("Joaquim Dos Santos", 89, 95.0));
		
		//print out the table
		System.out.println(books.toString());
		
		
		System.out.println("My Favorite Video Games, with the Genre and the Amount of Hours I've Spent Playing Them");
		
		//2-ary constructor
		Table videoGames = new BinaryTable("Favorite Video Games", List.of("Title", "Genre", "Hours Spent"));
		videoGames.clear();
		videoGames.put("Animal Crossing", List.of("Simulation", 4305));
		videoGames.put("R.E.P.O", List.of("Horror Multiplayer", 32.3));
		videoGames.put("Minecraft", List.of("Simulation", 5607));
		
		//1-ary constructor
		Table videoGames2 = new BinaryTable("Favorite Video Games");
		videoGames2.put("Lethal Company", List.of("Horror Online Co-op", 78.5));
		videoGames2.put("Fortnite", List.of("Shooter", 3405));
		videoGames2.put("Five Night's at Freddy's", List.of("Survival", 304.6));
		
		//print the table
		System.out.println(videoGames.toString());
		
		System.out.println("The Shows from the 2023 Season of The Pride of West Virginia Marching Band");
		
		//2-ary constructor
		Table bandShows = new BinaryTable("2023 Band Shows", List.of("Title", "Song 1", "Song 2", "Song 3"));
		bandShows.clear();
		bandShows.put("Metallica", List.of("Master of Puppets", "Lux Aeterna", "N/A"));
		bandShows.put("80's Disco", List.of("Superstition", "Celebration",  "Let's Groove"));
		
		//1-ary constructor
		Table bandShows2 = new BinaryTable("2023 Band Shows");
		bandShows2.put("Pop Music", List.of("As It Was", "Flowers", "N/A"));
		bandShows2.put("Patriotic", List.of("America Fantasy", "In Defense of Liberty", "N/A"));
		bandShows2.put("100 Years of Disney Villains", List.of("Poor Unfortunate Souls", "Be Prepared", "Cruella de Vil"));
		
		//print the table
		System.out.println(bandShows.toString());
		
		
	}
}
