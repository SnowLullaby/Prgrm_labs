/*
Шлепнувшись в воду, он тотчас принялся работать руками и ногами 
и спустя час уже был у берега. Прибой в этот день был особенно сильный, 
и Пончику никак не удавалось пришвартоваться к берегу. Это происходило 
из-за того, что в громоздком космическом скафандре он был крайне 
неповоротлив и не мог маневрировать в бурной морской воде с достаточной 
ловкостью. Как только он ощущал под собой дно и пытался встать на ноги, 
подкатившаяся сзади волна опрокидывала его и, перевернув на спину, 
тащила обратно в море. Пробившись у самого берега минут двадцать, он 
понял в конце концов, что ему необходимо расстаться со скафандром. 
Кувыркаясь в волнах словно дельфин, он умудрился сбросить с себя 
космические сапоги, потом гермошлем, а потом и сам скафандр. Все эти 
ставшие теперь ненужными ему космические причиндалы были тотчас унесены 
морем, а Пончик, став в тот момент более обтекаемым и подвижным, 
ускользнул от бросавшихся на него волн и выскочил на сухой берег. 
Первое, что требовалось ему после столь героической борьбы с 
разбушевавшейся водной стихшей, был отдых. Сняв с себя вымокшую одежду, 
он разложил ее на берегу для просушки, сам же лег рядом и принялся 
отдыхать. Теплый, ласковый ветерок приятно обдувал его тело. Морские 
волны ритмично шумели, что действовало на Пончика успокаивающе и 
усыпляюще. Решив все же не спать, так как это было бы неблагоразумно в 
незнакомой обстановке, Пончик принялся изучать окружавшую местность.
*/

public class Storyteller {
    public static void main(String[] args) {
        // story will be here
        // Main
        Clothes helmet = new Clothes("Гермошлем", 20, -10, true);
        Clothes boots = new Clothes("Космические сапоги", 20, -10, true);
        Clothes spaceSuit = new Clothes("Скафандр", 30, -10, true);
        Clothes moreClothes = new Clothes("Другая одежда", 5, -10, false);

        Ponchik ponchik = new Ponchik("Пончик", 100, 80);
        ponchik.addClothes(helmet);
        ponchik.addClothes(boots);
        ponchik.addClothes(spaceSuit);
        ponchik.addClothes(moreClothes);

        Ocean thisOcean = new Ocean("Океан");
        Shore thisShore = new Shore("Берег");

        thisOcean.addHuman(ponchik, thisOcean);
        ponchik.swim();

        int Death_timer = 1000;
        while (Death_timer > 0) {
            thisOcean.countSurfForce();
            if (ponchik.getStamina() <= 0)
                ponchik.sleep((int) (Math.random() * 10 + 0));
            else {
                if (ponchik.getCurrentAction() != Action.SLEEP) {
                    ponchik.stamina--;
                    System.out.printf("%s пытается выплыть\n", ponchik.name);
                    if (Math.random() > 0.99 && thisOcean.willSurfCarryAway(ponchik.getManeuverAbility())) {
                        Death_timer = -1;
                        System.out.printf("Вашего %s унесло в море. Он погиб \n", ponchik.name);
                        break;
                    } else {
                        if (thisOcean.willSurfCarryAway(ponchik.getManeuverAbility()) == false) {
                            Death_timer = 2;
                            thisOcean.removeHuman(ponchik);
                            thisShore.addHuman(ponchik, thisShore);
                            break;
                        }
                    }

                    if (Death_timer == 980) {
                        ponchik.removeClothes(helmet);
                        ponchik.removeClothes(boots);
                        ponchik.removeClothes(spaceSuit);
                    }
                } else {
                    ponchik.sleepTimer--;
                    if (ponchik.sleepTimer == 0 || Death_timer % (int) (Math.random() * 60 + 40) == 0)
                        ponchik.wakeUp();
                    ponchik.swim();
                }
            }
            Death_timer--;
        }

        if (Death_timer == 0) {
            System.out.printf("Ваш %s погиб. Нам искренне жаль!\n", ponchik.name);
        } else {
            if (Death_timer > 0) {
                ponchik.removeClothes(moreClothes);
            }

            ponchik.stamina--;

            if (ponchik.getStamina() <= 0)
                ponchik.sleep((int) (Math.random() * 60 + 40));
        }

    }
}
