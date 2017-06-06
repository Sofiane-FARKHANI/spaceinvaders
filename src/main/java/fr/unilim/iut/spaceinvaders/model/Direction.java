package fr.unilim.iut.spaceinvaders.model;

public enum Direction {

	HAUT(1),
	BAS(-1), 
	GAUCHE(-1), 
	DROITE(1),

	HAUT_ECRAN(-1), 
	BAS_ECRAN(1);

	private int valeur;

	Direction(int valeur) {
		this.valeur = valeur;
	}

	public int valeur() {
		return this.valeur;
	}

	public static Direction changeSensEnvahisseur(Direction direction){
		switch(direction){
			case DROITE:
				return GAUCHE;
			case GAUCHE:
				return DROITE;

			case HAUT:
				return BAS;

			case BAS:
				return HAUT;

			case HAUT_ECRAN:
				return BAS_ECRAN;

			case BAS_ECRAN:
				return HAUT_ECRAN;

			default:
				return null;
		}
	}
}