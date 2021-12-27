import java.util.ArrayList;
import java.util.Objects;

public abstract class Human implements AbleToSwim, AbleToSleep {
    protected String name;
    protected ArrayList<Clothes> clothes;
    protected int maneuverAbility;
    protected int stamina; // количество энергии
    protected Action currentAction; // текущее дейстиве
    protected int sleepTimer = 0; // время, которое он еще должен отдыхат
    protected Place place;

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

    public Action getCurrentAction() {
        return this.currentAction;
    }

    public Place getPlace() {
        return this.place;
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
        return Objects.equals(name, human.name); // и маркер космичности
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }

}
