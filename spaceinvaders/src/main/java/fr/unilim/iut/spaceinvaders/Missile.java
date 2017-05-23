package fr.unilim.iut.spaceinvaders;

public class Missile extends Sprite {

	private Dimension dimensionMissile;
	private int positionOrigineMissile;
	private int vitesseMissile;

	public Missile(Dimension dimensionMissile, Position positionOrigineMissile, int vitesseMissile) {
		this.dimensionMissile = new Dimension(1, 1);
		this.positionOrigineMissile=0;
		this.vitesseMissile=2;
	}

}
