package model;

import org.junit.Test;

import ennemies.Ennemi;

import static org.junit.Assert.*;

public class Tests {

    @Test
    // test pour vérifier si les valeurs par défaut du personnage principal et des entités associées sont correctement initialisées après avoir redémarré le jeu
    public void testPersoPrincipalDefaultValues() {
        Interface inter = new Interface();
        inter.restartGame(); // Assuming restartGame sets default values
        assertEquals(0, inter.persoPrincipal.getLifePoints());
        assertEquals(1200, inter.Demon.getX());
       
    }

    @Test
    // test pour vérifier si la méthode spawnBoules de l'interface génère correctement des boules
    public void testSpawnBoules() {
        Interface inter = new Interface();
        inter.spawnBoules();
        assertNotNull(inter.getBoules());
        assertTrue(inter.getBoules().size() > 0);

       
        for (Boule boule : inter.getBoules()) {
            assertTrue(boule.getX() >= 0 && boule.getX() < inter.screenWidth);
            assertTrue(boule.getY() >= 0 && boule.getY() < inter.screenHeight);
        }
    }

    @Test
    // test pour vérifier si la méthode updateCompteur de l'interface met à jour correctement le compteur de dégâts du personnage principal
    public void testUpdateCompteur() {
        Interface inter = new Interface();
        inter.updateCompteur();
        assertEquals(inter.persoPrincipal.damage_points, inter.damage_points);
       
    }
   
    @Test
    //l'objectif du test est de vérifier que l'interaction entre l'instance de PersoPrincipal (perso) et un démon (demon) se comporte comme prévu
    public void testInteractDemon() {
   
        Interface inter = new Interface();
        KeyHandler keyH = new KeyHandler();
        PersoPrincipal perso = new PersoPrincipal(inter, keyH);
        Ennemi demon = new Ennemi(inter, "Demon", 1200, 150, 2, 3, 18, 42, 30, inter.titleSize + 25);
        inter.setDemon(demon);

        // Set some values for interaction
        perso.getColorsList().add("red");
        inter.setFlagRed(1);

        // Call interactDemon
        perso.interactDemon(0);

        // Check if the Demon is affected, flagRed is decreased, and damage_Demon is increased
       
        assertTrue(inter.getDemon().isDead());
        assertEquals(0, inter.getFlagRed(), 0);
        assertEquals(1, perso.getDamageDemon());
    }
/*
* @Test public void testCheckCollisionWithBoules() { Interface inter = new
* Interface(); KeyHandler keyH = new KeyHandler(); PersoPrincipal perso = new
* PersoPrincipal(inter, keyH); Boule boule = new Boule(50, 50);
*
* // Add boule to the Interface inter.addBoule(boule);
*
* // Call checkCollisionWithBoules perso.checkCollisionWithBoules();
*
* // Check if the colorsList and flagList are updated correctly assertEquals(0,
* perso.getColorsList().size()); //assertEquals("blue",
* perso.getColorsList().get(0)); assertTrue(perso.getFlagList().isEmpty()); }
*/
   

}
