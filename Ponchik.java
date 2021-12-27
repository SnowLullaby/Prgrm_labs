import java.util.ArrayList;

public class Ponchik extends Human {
    public Ponchik(String name, int stamina, int maneuverAbility, ArrayList<Clothes> clothes) {
        super(name, stamina, maneuverAbility, clothes);
    }

    public Ponchik(String name, int stamina, int maneuverAbility) {
        super(name, stamina, maneuverAbility);
    }

    public void addClothes(Clothes newClothes) {
        this.changeManeuverAbility(newClothes.getManeuverAbilityBuff(), newClothes.getSpaceClothing());
        this.clothes.add(newClothes);
    }

    public void removeClothes(Clothes clothes) {
        this.changeManeuverAbility(-1 * clothes.getManeuverAbilityBuff(), clothes.getSpaceClothing());
        place.addClothes(clothes);
    }

    private void changeManeuverAbility(int value, boolean flag) {
        if (flag)
            this.maneuverAbility += 2 * value;
        else
            this.maneuverAbility += value;
    }

    @Override
    public void swim() {
        System.out.printf("%s пока плывёт \n", this.name);
        super.swim();
    }

    @Override
    public void sleep(int TimeForSleep) { // погрузить в сон на хх часов
        this.stamina = this.stamina + TimeForSleep / 2 > 100 ? 100 : this.stamina + TimeForSleep / 2;
        this.currentAction = Action.SLEEP;
        this.sleepTimer = TimeForSleep;
        System.out.println("Ваш " + this.name + " лег спать на " + TimeForSleep + "c");
    }

    @Override
    public void wakeUp() { // разбудит персонажа, если он уже проспал положенное время
        if (this.currentAction == Action.SLEEP)
            if (this.sleepTimer == 0) {
                this.currentAction = Action.IDLE;
                System.out.println("Проснулся и бездействует");
            } else
                System.out.println("Не проснулся, попробуйте через " + this.sleepTimer);
        else
            System.out.println("И так не спит! Осталось выносливости " + this.getStamina());
    }
}
