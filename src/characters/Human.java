package characters;

import java.util.ArrayList;
import java.util.Objects;

import exceptions.*;
import locations.*;
import things.*;

public abstract class Human implements AbleToSwim, AbleToSleep {
    protected String name;
    protected ArrayList<Clothes> clothes;
    protected int maneuverAbility;
    protected int stamina; // количество энергии
    protected Action currentAction; // текущее дейстиве
    protected int sleepTimer = 0; // время, которое он еще должен отдыхат
    protected Place place;
    protected Inventory humanInventory = new Inventory(2);


    public Human(String name, int stamina, int maneuverAbility, ArrayList<Clothes> clothes) {
        this.name = name;
        this.stamina = stamina;
        this.maneuverAbility = maneuverAbility;
        this.clothes = clothes;
    }

    public Human(String name, int stamina, int maneuverAbility) {
        this.name = name;
        this.stamina = stamina;
        this.maneuverAbility = maneuverAbility;
        this.clothes = new ArrayList<Clothes>();
    }

    public int getManeuverAbility() {
        return this.maneuverAbility;
    }

    public int getStamina() {
        return this.stamina;
    }

    public int getSleepTimer() {
        return this.sleepTimer;
    }

    public void setSleepTimer(int timeToSleep) {
        this.sleepTimer += timeToSleep;
    }

    public void updateStamina(int value) {
        this.stamina += value;
    }

    public Action getCurrentAction() {
        return this.currentAction;
    }

    public Place getPlace() {
        return this.place;
    }
    
    public void setPlace(Place newPlace) {
        this.place = newPlace;
    }

    public void addClothes(Clothes newClothes) {
        this.changeManeuverAbility(newClothes.getManeuverAbilityBuff(), newClothes.getSpaceClothing());
        this.clothes.add(newClothes);
        System.out.printf("%s надел одежду %s\n",this.name, newClothes.getName());
    }

    public boolean containsClothes(Clothes oneClothes) {
        for (Clothes e : clothes) {
            if (e.equals(oneClothes)) {
                return true;
            }
        }
        return false;
    }

    public void removeClothes(Clothes rmClothes) {
        if(this.containsClothes(rmClothes)) {
            this.changeManeuverAbility(-1 * rmClothes.getManeuverAbilityBuff(), rmClothes.getSpaceClothing());
            this.clothes.remove(rmClothes);
            System.out.printf("%s снял одежду %s\n", this.name, rmClothes.getName());
            place.addClothes(rmClothes);
        }
        else throw new NoClothesOnHumanException(rmClothes, this.name);
    }

    protected void changeManeuverAbility(int value, boolean flag) {
        if (flag)
            this.maneuverAbility += 2 * value;
        else
            this.maneuverAbility += value;
    }

    public void takeItem(Item item) throws NoMorePlaceInInventoryException {
        try {
            this.humanInventory.addToInventory(item, this.name); 
        } catch (NoMorePlaceInInventoryException e){
            System.out.printf("%s не влезает в карманы\n", item.getName());
        }
    }

    public void dropItem(Item rmItem){
        this.humanInventory.removeFromInventory(rmItem, this.name);
    }

    @Override
    public void swim() {
        this.currentAction = Action.SWIM;
    }

    abstract public void sleep(int timeforsleep);

    abstract public void wakeUp();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null) {
            return false;
        }
        if (!(o instanceof Human))
            return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }
    
    @Override
    public String toString() {
        return this.name;
    }

    public void fly(){
        this.currentAction = Action.FLY;
        System.out.println(this.name + " " + currentAction.type);
    }

    public void walk(){
        this.currentAction = Action.WALK;
        System.out.println(this.name + " " + currentAction.type);
    }

    public boolean containsParachute() {
        for (Item e : humanInventory.inventory) {
            if (e instanceof Parachute) {
                return true;
            }
        }
        return false;
    }

    public Parachute getParachuteName(){
        if(this.containsParachute()){
            for (Item e : humanInventory.inventory) {
                if (e instanceof Parachute) {
                    return (Parachute) e;
                }
            }
        } else System.out.printf("У %s нет парашюта\n", this.name);
        return null;
    }

    public enum Action {
        SLEEP("спит"),
        FLY("летит"),
        WALK("идет"),
        SWIM("плывет"),
        IDLE("бездействует");

        private String type;

        Action(String type) {
            this.type = type;
        }
    }

    public class Inventory{
        protected int currentSize = 0;
        protected int maxSize;
        protected ArrayList<Item> inventory;

        private Inventory(int size) {
            this.maxSize = size;
            this.inventory = new ArrayList<Item>(); //так?
        }

        private void changeDefaultSize(int addedSize){
            this.currentSize += addedSize;
        }

        private void addToInventory(Item item, String name) throws NoMorePlaceInInventoryException {
            int predSize = this.currentSize + item.getSize();
            if (predSize > this.maxSize)
                throw new NoMorePlaceInInventoryException();
            this.inventory.add(item);
            this.changeDefaultSize(item.getSize());
            System.out.printf("%s подобрал предмет %s\n", name, item.getName());
        }

        private boolean containsItem(Item oneItem) {
            for (Item e : inventory) {
                if (e.equals(oneItem)) {
                    return true;
                }
            }
            return false;
        }

        private void removeFromInventory(Item rmItem, String name){
            // тут проверка на существование айтема
            if (this.containsItem(rmItem)) {
                this.inventory.remove(rmItem);
                this.changeDefaultSize((-1)*rmItem.getSize());
                System.out.printf("%s выбросил предмет %s\n", name, rmItem.getName());
            }
            else throw new NoItemInInventoryException(rmItem, name);
        }
    }
}
