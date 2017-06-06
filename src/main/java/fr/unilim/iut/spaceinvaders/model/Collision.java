package fr.unilim.iut.spaceinvaders.model;

public class Collision {
    static public boolean detecterCollision(Sprite objetNumero1, Sprite objetNumero2) {
        boolean collision = false;

        if(detecterCollision(objetNumero1, new Position(objetNumero2.abscisseLaPlusAGauche(), objetNumero2.ordonneeLaPlusHaute())))
            collision = true;

        else if(detecterCollision(objetNumero1, new Position(objetNumero2.abscisseLaPlusADroite(), objetNumero2.ordonneeLaPlusHaute())))
            collision = true;

        else if(detecterCollision(objetNumero1, new Position(objetNumero2.abscisseLaPlusAGauche(), objetNumero2.ordonneeLaPlusBasse())))
            collision = true;

        else if(detecterCollision(objetNumero1, new Position(objetNumero2.abscisseLaPlusADroite(), objetNumero2.ordonneeLaPlusBasse())))
            collision = true;

        else if(detecterCollision(objetNumero2, new Position(objetNumero1.abscisseLaPlusAGauche(), objetNumero1.ordonneeLaPlusHaute())))
            collision = true;

        else if(detecterCollision(objetNumero2, new Position(objetNumero1.abscisseLaPlusADroite(), objetNumero1.ordonneeLaPlusHaute())))
            collision = true;

        else if(detecterCollision(objetNumero2, new Position(objetNumero1.abscisseLaPlusAGauche(), objetNumero1.ordonneeLaPlusBasse())))
            collision = true;

        else if(detecterCollision(objetNumero2, new Position(objetNumero1.abscisseLaPlusADroite(), objetNumero1.ordonneeLaPlusBasse())))
            collision = true;

        return collision;
    }

    static public boolean detecterCollision(Sprite sprite, Position position) {
        int x = position.abscisse();
        int y = position.ordonnee();

        return (x >= sprite.abscisseLaPlusAGauche() && x <= sprite.abscisseLaPlusADroite())
               && (y >= sprite.ordonneeLaPlusHaute() && y <= sprite.ordonneeLaPlusBasse());
    }
}