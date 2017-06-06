package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.Vaisseau;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CollisionTest {
    @Test
    public void test_2sVaisseauxEnCollisionPointHautGaucheCollision() {
        Vaisseau v1 = new Vaisseau(new Dimension(10, 10), new Position(0, 0), 1);
        Vaisseau v2 = new Vaisseau(new Dimension(10, 10), new Position(5, 5), 1);

        assertEquals(true, Collision.detecterCollision(v1, v2));
    }

    @Test
    public void test_2VaisseauxEnCollisionPointHautDroiteCollision() {
        Vaisseau v1 = new Vaisseau(new Dimension(10, 10), new Position(0, 0), 1);
        Vaisseau v2 = new Vaisseau(new Dimension(10, 10), new Position(-5, 5), 1);

        assertEquals(true, Collision.detecterCollision(v1, v2));
    }

    @Test
    public void test_2VaisseauxEnCollisionPielBasGaucheCollision() {
        Vaisseau v1 = new Vaisseau(new Dimension(10, 10), new Position(0, 0), 1);
        Vaisseau v2 = new Vaisseau(new Dimension(10, 10), new Position(5, -5), 1);

        assertEquals(true, Collision.detecterCollision(v1, v2));
    }

    @Test
    public void test_2VaisseauxEnCollisionPixelDroiteEtEnBasCollision() {
        Vaisseau v1 = new Vaisseau(new Dimension(10, 10), new Position(0, 0), 1);
        Vaisseau v2 = new Vaisseau(new Dimension(10, 10), new Position(5, -5), 1);

        assertEquals(true, Collision.detecterCollision(v1, v2));
    }

    @Test
    public void test_2VaisseauxPasEnCollisionAucuneCollistionDetecter() {
        Vaisseau v1 = new Vaisseau(new Dimension(10, 10), new Position(0, 0), 1);
        Vaisseau v2 = new Vaisseau(new Dimension(10, 10), new Position(5, 20), 1);

        assertEquals(false, Collision.detecterCollision(v1, v2));
    }

    @Test
    public void test_vaisseauEtPixelCollision() {
        Vaisseau v1 = new Vaisseau(new Dimension(10, 10), new Position(0, 10), 1);
        Position p = new Position(5, 5);

        assertEquals(true, Collision.detecterCollision(v1, p));
    }

    @Test
    public void test_vaisseauEtPixelAucuneCollision() {
        Vaisseau v1 = new Vaisseau(new Dimension(10, 10), new Position(0, 10), 1);
        Position p = new Position(20, 20);

        assertEquals(false, Collision.detecterCollision(v1, p));
    }
}
