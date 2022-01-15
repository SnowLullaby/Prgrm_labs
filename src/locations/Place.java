package locations;

import java.util.ArrayList;

import characters.*;
import exceptions.*;
import things.*;

public class Place {
    private ArrayList<Clothes> clothes = new ArrayList<>();
    private ArrayList<Human> humans = new ArrayList<>();
    private final String name;

    public Place(String name) {
        this.name = name;
    }

    public boolean containsClothes(Clothes oneClothes) {
        for (Clothes e : clothes) {
            if (e.equals(oneClothes)) {
                return true;
            }
        }
        return false;
    }

    public void addClothes(Clothes newClothes) {
        clothes.add(newClothes);
        System.out.printf("Одежда %s попала с локацию %s\n",newClothes.getName(), this.name);
    }

    public String getClothes() {
        String temp = "";
        for (int i = 0; i < clothes.size(); i++)
            temp += clothes.get(i).getName() + " ";
        return temp;
    }

    public void removeClothes(Clothes rmClothes) {
        if (this.containsClothes(rmClothes)) {
            clothes.remove(rmClothes);
            System.out.printf("Одежда %s покинула локацию %s \n", rmClothes, this.name);
        }
        else throw new NoClothesInLocationException(rmClothes, this.name);
    }

    public boolean containsHumans(Human oneHuman) {
        for (Human e : humans) {
            if (e.equals(oneHuman)) {
                return true;
            }
        }
        return false;
    }

    public void addHuman(Human newHuman) {
        humans.add(newHuman);
        newHuman.setPlace(this);
    }


    public String getHumans() {
        String temp = "";
        for (int i = 0; i < humans.size(); i++)
            temp += humans.get(i) + " ";
        return temp;
    }

    public void removeHuman(Human rmHuman) throws NoCharacterInLocationException {
        if (this.containsHumans(rmHuman)) {
            humans.remove(rmHuman);
            System.out.printf("%s покинул локацию %s \n", rmHuman, this.name);
        }
        else throw new NoCharacterInLocationException(rmHuman, this.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
