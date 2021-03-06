package com.GeekTech;

import java.util.Random;

public class Main {
    boolean evasion = false;

    public static String bossName = "Vilgefortz";
    public static int bossHP = 1000;
    public static int bossDamage = 33;
    public static String ultimate;

    public static String[] heroesName = {"Gerald","Regis","Yennefer","Medic","Golem","Lucky"};
    public static int[] heroesHP = {380, 350, 400,600,800,200};
    public static int[] heroesDamage = {25, 18, 21,0,5,40};
    public static int roundNumber = 0;
    public static String medicUltimate = "Medic Heal: ";


    public static void main(String[] args) {
	// write your code here
        printStatistics();
        System.out.println(" ____Witcher Game Started____");

        while (!gameOver()){
             round();
        }


    }

    public static boolean gameOver(){
        if (bossHP <= 0){
            System.out.println("Heroes Won!" + "Game finished"  );
            return true;
        }
         boolean allHeroesDead = true;
        for (int heroHP : heroesHP ) {
            if (heroHP > 0){
                allHeroesDead = false;
                break;
            }

        }
        if (allHeroesDead){
            System.out.println(bossName + " Won!" + "  Game finished ");

        }
        return allHeroesDead;
    }

    public static  void round (){

        roundNumber++;
        healerMedic();
        System.out.println("________Round: " + roundNumber + "__________");
        bossDamage();
        ultimate = criticalStrike();
        heroesDamage();
        printStatistics();
        golem();
        evasion();

    }

    public static void bossDamage(){
        for (int i = 0; i < heroesHP.length; i++) {
            if (heroesHP[i] > 0 && bossHP > 0) {heroesHP[i] = heroesHP[i] - bossDamage;}




        }



    }

    public static void heroesDamage(){
        Random random = new Random();
        int coeff = random.nextInt(3 ) +2;
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHP[i] > 0 && bossHP > 0) {
                if (ultimate == heroesName[i] ){
                    bossHP = bossHP - (heroesDamage[i] * coeff);
                    System.out.println(" Critical hit by " + ultimate + " " + (heroesDamage[i] * coeff));
                } else {
                bossHP = bossHP - heroesDamage[i];
                }
            }
            if (heroesHP[i] < 0){
                heroesHP[i] = 0;
            }
            if (bossHP < 0){
                bossHP = 0;
            }
        }


    }

    public static String criticalStrike() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesName.length);
        if (randomIndex == 3) {
            randomIndex = random.nextInt(heroesName.length);
        }
            return heroesName[randomIndex];
    }

    public static  void printStatistics(){
        System.out.println(bossName + " = HP " + bossHP + " damage [" + bossDamage + "]");

        for (int i = 0; i < heroesName.length; i++) {
            System.out.println(heroesName[i] + " =  HP " + heroesHP[i] + " damage [" + heroesDamage[i]  + "]"
                    );

        }
    }

    public static void healerMedic(){
        for (int i = 0; i<heroesHP.length; i++){
            if (heroesHP[3]  > 0) {
                if (heroesHP[3] <=0 ) {
                    heroesHP[3] = 0;
                }
                else if (heroesHP[i] < 100 && heroesHP[i] >= 0) {
                    Random random = new Random();
                    int r= random.nextInt(50);
                    System.out.println("Medic healed "+heroesName[i] + " by "
                            + r+" hp");
                    heroesHP[i]+=+r;
                    break;
                }
            }
        }
    }

   public static  void golem(){
        if(heroesHP[4] > 0){
            for (int i = 0; i < heroesHP.length ; i++) {
                if (heroesHP[i] != heroesHP[4]){
                    heroesHP[i] += bossDamage /5;
                    heroesHP[4] -= bossDamage /5;
                }
                
            }
        }

   }


   public static  void evasion(){
        Random random = new Random();
        int randomEvasion = random.nextInt(2);
        if (randomEvasion ==1) {
            heroesHP[5] += bossDamage;
            System.out.println(heroesName[5] + " ?????????????????? ???? " + bossName);
        }
   }







}

