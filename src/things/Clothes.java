package things;

import java.util.Objects;

public class Clothes {
    private String name;
    private int maneuverAbility;
    private int maneuverAbilityBuff;
    private boolean spaceClothing;

    public Clothes(String name, int maneuverAbility, int maneuverAbilityBuff, boolean spaseClothind) {
        // здесь у нас в конструкторе сразу определяются все поля. Они != null
        this.name = name;
        this.maneuverAbility = maneuverAbility;
        this.maneuverAbilityBuff = maneuverAbilityBuff;
        this.spaceClothing = spaseClothind;

    }

    public int getManeuverAbility() {
        return this.maneuverAbility;
    }

    public int getManeuverAbilityBuff() {
        return this.maneuverAbilityBuff;
    }

    public boolean getSpaceClothing() {
        return this.spaceClothing;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null) {
            return false;
        }
        if (!(o instanceof Clothes))
            return false;
        Clothes clothes = (Clothes) o;
        return Objects.equals(name, clothes.name);
    }
}

