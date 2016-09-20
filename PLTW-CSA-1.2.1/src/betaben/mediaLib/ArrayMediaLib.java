package betaben.mediaLib;

public class ArrayMediaLib {

	public static void main(String[] args) {
		
		String[] sharingFriends = {new String("Douglas"),
								   new String("Patricia"),
								   new String("Harrison"),
								   new String("Ben"),
								   new String("Brandon"),
								   new String("Adam")
		};
		
		for (int i=0; i<sharingFriends.length; i++)
		{
		    System.out.println(sharingFriends[i]);
		}
		
		int[] daysBtwnPurchase = {2, 5, 1, 2, 4, 2, 1, 3};

		int total = 0;
		
		for(int i=0; i<daysBtwnPurchase.length; i++){
			total+=daysBtwnPurchase[i];
		}
		
		int average = total/daysBtwnPurchase.length;
		System.out.println("Average days between purchase: " + average);
		
		Song[] topTenSongs = {new Song("The Twist"),
                new Song("Smooth"),
                new Song("Mack the Knife"),
                new Song("How Do I Live"),
                new Song("Party Rock Anthem"),
                new Song("I Gotta Feeling"),
                new Song("Macarena (Bayside Boys Remix)"),
                new Song("Physical"),
                new Song("You Light Up My Life"),
                new Song("Hey Jude"),
                };
		
		for (Song s: topTenSongs)
		{
		    System.out.println(s.getName());
		}
		
		
	}

}
