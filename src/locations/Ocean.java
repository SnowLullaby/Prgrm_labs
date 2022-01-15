package locations;

import characters.Human;
import java.util.Random;

public class Ocean extends Place {
    public SurfForce currentForce = SurfForce.MEDIUM;

    public Ocean(String name) {
        super(name);
    }

    public void countSurfForce() {
        this.currentForce = SurfForce.getRandomState();
        System.out.printf("Состояние прибоя в локации %s изменилось на %s\n", this.toString(), currentForce.type);
    }

    public boolean willSurfCarryAway(Human human) {
        int objectManeuverAbility = human.getManeuverAbility();
        if (this.currentForce.forceNum * 0.9 + 1 + Math.random() >= objectManeuverAbility * 0.24) {
            return true;
        } else return false;
    }

    @Override
    public void addHuman(Human newHuman) {
        super.addHuman(newHuman);
        System.out.printf("%s плюхнулся в воду в локации %s\n", newHuman, this.toString());
    }

    protected enum SurfForce {
        LOW("Слабый прибой", 4),
        MEDIUM("Средний прибой", 5),
        HIGH("Cильный прибой", 6);

        private String type;
        private int forceNum;
        private static Random RANDOM = new Random();

        SurfForce(String type, int forceNum) {
            this.type = type;
            this.forceNum = forceNum;
        }

        private static SurfForce getRandomState() {
            return SurfForce.values()[RANDOM.nextInt(3)];
        }
    }
}
