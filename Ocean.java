public class Ocean extends Place {
    public SurfForce currentForce = SurfForce.MEDIUM;
    // private int surfForce = SurfForce.MEDIUM.getForceNum();

    public Ocean(String name) {
        super(name);
    }

    public void countSurfForce() {
        this.currentForce = SurfForce.getRandomState();
    }

    public boolean willSurfCarryAway(int objectManeuverAbility) {
        if (this.currentForce.getForceNum() * 0.9 + 1 + Math.random() >= objectManeuverAbility * 0.24) {
            return true;
        } else return false;
    }

    ;
}
