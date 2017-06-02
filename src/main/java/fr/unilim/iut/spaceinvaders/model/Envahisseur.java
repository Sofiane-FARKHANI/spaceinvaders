package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite{
    public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse){
        super(dimension, positionOrigine, vitesse);
    }

    public Envahisseur(int longueur, int hauteur) {
        this(new Dimension(longueur, hauteur), new Position(0, 0), 1);
    }
}
