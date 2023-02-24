package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(5);
	}
	
	private static class Marche{
		private Etal[] etals;

		private Marche(int nbrEtals) {
			this.etals = new Etal[nbrEtals];
			for (int i = 0; i < nbrEtals; i++) {
				this.etals[i] = new Etal();
			}
		}
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit,int nbProduit) {
			if (!this.etals[indiceEtal].isEtalOccupe()) {
				this.etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
			}else {
				System.out.println("L'étal choisis par le gaulois est déjà occupé.");
			}
		}
		
		private int trouverEtalLibre() {
			for (int i = 0; i < this.etals.length;i++) {
				if (!this.etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			Etal[] etalsTrouve = null;
			int nbEtalsTrouve = 0;
			for (int i = 0; i < this.etals.length;i++) {
				if (this.etals[i].contientProduit(produit)) {
					etalsTrouve[nbEtalsTrouve] = this.etals[i];
					nbEtalsTrouve ++;
				}
			}
			return etalsTrouve;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i<this.etals.length;i++) {
				if (gaulois == this.etals[i].getVendeur()) {
					return etals[i];
				}
			}
			return null;
		}
		
		
		private void afficherMarche() {
			int nbEtalVide = 0;
			for (int i = 0; i < this.etals.length; i++) {
				if (!this.etals[i].isEtalOccupe()) {
				this.etals[i].afficherEtal();
				}else {
					nbEtalVide ++;
				}
			}
			if (nbEtalVide != 0) {
			System.out.println("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			}
		}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		System.out.println(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + produit );
		int EtalLibre = this.marche.trouverEtalLibre();
		this.marche.utiliserEtal(EtalLibre,vendeur,produit,nbProduit);
		return "Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + EtalLibre;
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		System.out.println("Les vendeurs qui proposent des " + produit + " sont : ");
		Etal[] EtalLibre = this.marche.trouverEtals(produit);
		StringBuilder vendeur = new StringBuilder();
		for (int i =0;i<EtalLibre.length;i++) {
			vendeur.append("- " + EtalLibre[i].getVendeur().getNom() + "\n");
		}
		return vendeur.toString();
	}
	
	
	
	
	
	
	
	
}