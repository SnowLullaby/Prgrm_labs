package run;

import characters.*;
import exceptions.*;
import locations.*;
import things.*;

public class Storyteller {
    public static void main(String[] args) throws NoMorePlaceInInventoryException {
        Clothes helmet = new Clothes("Гермошлем", 20, -10, true);
        Clothes boots = new Clothes("Космические сапоги", 20, -10, true);
        Clothes spaceSuit = new Clothes("Скафандр", 30, -10, true);
        Clothes moreClothes = new Clothes("Другая одежда", 5, -10, false);
        Parachute parachute1 = new Parachute("Парашют", 1);

        Ponchik ponchik = new Ponchik("Пончик", 100, 80);
        ponchik.addClothes(helmet);
        ponchik.addClothes(boots);
        ponchik.addClothes(spaceSuit);
        ponchik.addClothes(moreClothes);
        ponchik.takeItem(parachute1);

        Sky sky = new Sky("Небо");
        Place city1     = new Place("Лос-Свинос");
        Place city2     = new Place("Лос-Кабанос");
        Place city3     = new Place("Лос-Паганос");
        Ocean ocean = new Ocean("Океан");
        Shore shore = new Shore("Берег");
        

        Place[] ponchikRoute = {city1, city2, city3, ocean, shore};

        // Часть 1: Пончик в небе
        int locNum = 0;
        sky.addHuman(ponchik, 50, ponchikRoute[locNum]);
        sky.willCharacterStartFly(ponchik);

        while(sky.containsHumans(ponchik) && locNum < ponchikRoute.length) {
            sky.countWind();

            if(locNum == ponchikRoute.length - 1){
                if(sky.willWindMoveInNextLocation(ponchik, null)){
                    System.out.printf("Вашего %s унесло ветром в неизведанное\n", ponchik.toString());
                    locNum += 1;
                    break;
                }
            }else {
                if (sky.willWindMoveInNextLocation(ponchik, ponchikRoute[locNum + 1]))
                    locNum++;
            }

            sky.changeCharacterHeight(ponchik, -17);
        }

        // Часть 2: Пончик попал в океан
        int Death_timer = 1000;

        if(ocean.containsHumans(ponchik)){
            ponchik.swim();

            while (Death_timer > 0) {
                ocean.countSurfForce();
                if (ponchik.getStamina() <= 0)
                    ponchik.sleep((int) (Math.random() * 10 + 0));
                else {
                    if (ponchik.getCurrentAction() != Human.Action.SLEEP) {
                        ponchik.updateStamina(-1);
                        System.out.printf("%s пытается выплыть\n", ponchik);
                        if (Math.random() > 0.99 && ocean.willSurfCarryAway(ponchik)){
                            Death_timer = -1;
                            System.out.printf("Вашего %s унесло в море. Он погиб \n", ponchik);
                            break;
                        } else {
                            if (!ocean.willSurfCarryAway(ponchik)) {
                                Death_timer = 2;
                                ocean.removeHuman(ponchik);
                                shore.addHuman(ponchik);
                                break;
                            }
                        }

                        if (Death_timer == 980) {
                            ponchik.removeClothes(helmet);
                            ponchik.removeClothes(boots);
                            ponchik.removeClothes(spaceSuit);
                        }
                    } else {
                        ponchik.setSleepTimer(-1);
                        if (ponchik.getSleepTimer() == 0 || Death_timer % (int) (Math.random() * 60 + 40) == 0)
                            ponchik.wakeUp();
                        ponchik.swim();
                    }
                }
                Death_timer--;
            }
        }

        // Часть 3: Пончик ходит по берегу
        int sleepOnShoreTimer = 0;

        if (Death_timer == 0) {
            System.out.printf("Ваш %s погиб, не справившись с волнами. Нам искренне жаль!\n", ponchik.toString());
        } else {
            if(shore.containsHumans(ponchik)) {
                ponchik.removeClothes(moreClothes);
                ponchik.setSleepTimer(-1);

                if (ponchik.getStamina() <= 0) {
                    sleepOnShoreTimer = (int) (Math.random() * 60 + 40);
                    ponchik.sleep(sleepOnShoreTimer);
                }

                ponchik.setSleepTimer(sleepOnShoreTimer);
                ponchik.walk();
                Shore.SaltCrystal.grindSaltCrystal(ponchik);
            }
        }

    }
}
