package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.moteurjeu.Commande;
import fr.unilim.iut.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public void initialiser() {
		positionnerUnNouveauVaisseau(Constante.VAISSEAU, 
									 new Position((Constante.ECRAN.longueur() / 2) - (Constante.VAISSEAU.longueur() / 2), hauteur -1), Constante.VAISSEAU_VITESSE);

		positionnerUnEnvahisseur(Constante.ENVAHISSEUR,
				new Position((Constante.ECRAN.longueur()/2)-(Constante.ENVAHISSEUR.longueur()/2),40),Constante.ENVAHISSEUR_VITESSE);
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append("\n");
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return aUnMissile() && missile.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y){
		return aUnEnvahisseur() && envahisseur.occupeLaPosition(x,y);
	}

	private boolean aUnVaisseau() {
		return vaisseau != null;
	}
	
	public boolean aUnMissile() {
		return missile != null;
	}

	public boolean aUnEnvahisseur(){
		return envahisseur != null;
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
		vaisseau.positionner(x, y);
	}

	public void positionnerUnEnvahisseur(Dimension dimension, Position position, int vitesse){
		int x = position.abscisse();
		int y = position.ordonnee();

		if(!estDansEspaceJeu(x,y)){
			throw new HorsEspaceJeuException("L'envahisseur est en dehors de l'espace de jeu.");
		}

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();

		if(!estDansEspaceJeu(x+longueurEnvahisseur-1,y))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace de jeu vers la droite à " +
					"cause de sa longueur");

		if(!estDansEspaceJeu(x, y-longueurEnvahisseur-1))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers le haut à cause " +
					"de sa hauteur");

		envahisseur = new Envahisseur(dimension,position,vitesse);
		envahisseur.positionner(x,y);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return x < longueur && x >= 0 && y < hauteur && y >= 0;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonnee());
			}
		}
	}
	
	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonnee());
		}
	}

	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}

	public Envahisseur getEnvahisseur() {
		return this.envahisseur;
	}

	public void evoluer(Commande commandeUser) {
		if(commandeUser.gauche)
			deplacerVaisseauVersLaGauche();
		
		if(commandeUser.droite)
			deplacerVaisseauVersLaDroite();
		
		if(commandeUser.espace && !aUnMissile())
			tirerUnMissile(Constante.MISSILE, Constante.MISSILE_VITESSE);

		if(this.missile != null)
			deplacerMissile();
	}

	public boolean etreFini() {
		return false;
	}

	public void tirerUnMissile(Dimension dimension, int vitesse) {
		if ((vaisseau.hauteur()+ dimension.hauteur()) > this.hauteur )
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		
		this.missile = this.vaisseau.tirerUnMissile(dimension, vitesse);
		
	}

	public Sprite getMissile() {
		return this.missile;
	}

	public void deplacerMissile() {
		this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		if(this.missile.ordonneeLaPlusHaute() < 0)
			this.missile = null;
	}
}
