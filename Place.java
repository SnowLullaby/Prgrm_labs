import java.util.ArrayList;

public class Place {
    private ArrayList<Clothes> clothes = new ArrayList<>();
    private ArrayList<Human> humans = new ArrayList<>();
    private final String name;

    public Place(String name) {
        this.name = name;
    }

    ;

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
        System.out.println(newClothes.getName() + " оказался в локации " + this.name);
    }


    public String getClothes() {
        String temp = "";
        for (int i = 0; i < clothes.size(); i++)
            temp += clothes.get(i).getName() + " ";
        return temp;
    }

    public boolean removeClothes(Clothes rmClothes) {
        for (Clothes e : clothes) {
            if (e.equals(rmClothes)) {
                clothes.remove(e);
                System.out.println(rmClothes.getName() + " больше не в локации " + this.name);
                return true;
            }
        }
        return false;
    }

    public boolean containsHumans(Human oneHuman) {
        for (Human e : humans) {
            if (e.equals(oneHuman)) {
                return true;
            }
        }
        return false;
    }

    public void addHuman(Human newHuman, Place newPlace) {
        humans.add(newHuman);
        newHuman.place = newPlace;
        System.out.println(newHuman.name + " оказался в локации " + this.name);
    }


    public String getHumans() {
        String temp = "";
        for (int i = 0; i < humans.size(); i++)
            temp += humans.get(i).name + " ";
        return temp;
    }

    public boolean removeHuman(Human rmHuman) {
        for (Human e : humans) {
            if (e.equals(rmHuman)) {
                humans.remove(e);
                System.out.println(rmHuman.name + " покинул в локацию " + this.name);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

}
