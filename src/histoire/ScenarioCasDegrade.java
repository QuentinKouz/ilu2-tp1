package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois obelix = new Gaulois("Obélix", 25);
		
		//NullPointeur exception
		etal.libererEtal();
		
		//etal.acheterProduit(1, null);
		System.out.println("test : AcheterProduit avec un nombre négatif");
		try {
			etal.acheterProduit(-1, obelix);

		}
		catch(IllegalArgumentException e) {
			System.out.println("La quantité de produit à acheter n'est pas positive.");
		}
		
		System.out.println("test : AcheterProduit avec un étal non occupé");
		try {
			etal.acheterProduit(1, obelix);
		}
		catch(IllegalStateException e) {
			System.out.println("L'étal n'est pas occupé.");
		}
		
		System.out.println("Fin du test");
		}

}