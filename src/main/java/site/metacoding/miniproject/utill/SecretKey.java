package site.metacoding.miniproject.utill;

public enum SecretKey {
    SECRETKEY("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");

    private final String key;

    // call constructor
    SecretKey(String key) {
        this.key = key;
    }
    
    public String key() {
        return this.key;
    }
}
