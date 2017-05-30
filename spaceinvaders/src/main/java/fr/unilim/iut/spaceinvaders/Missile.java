package fr.unilim.iut.spaceinvaders;

public class Missile extends Sprite {

	private Dimension dimensionMissile;
	private Position positionOrigineMissile;
	private int vitesseMissile;

	public Missile(Dimension dimensionMissile, Position positionOrigineMissile2, int vitesseMissile) {
		this.dimensionMissile = new Dimension(1, 1);
		this.positionOrigineMissile=positionOrigineMissile2;
		this.vitesseMissile=vitesseMissile;
	}
}
