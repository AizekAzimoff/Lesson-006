package ayupov.ilgam.lesson006;

class Fact {

    private final String text;

    private final boolean isDeleted;

    Fact(String text, boolean isDeleted) {
        this.text = text;
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    String getText() {
        return text;
    }
}
