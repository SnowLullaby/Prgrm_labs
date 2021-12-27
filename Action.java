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

    public String getType() {
        return this.type;
    }
}