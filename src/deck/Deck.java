package deck;

public interface Deck {
    void shuffle();
    Card pullfromTop();
    Card pullfromIndex(int index);
}
