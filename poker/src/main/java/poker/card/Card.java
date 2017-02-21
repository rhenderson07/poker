package poker.card;

public class Card implements Comparable<Card> {
    private final CardValueEnum valueType;
    private final CardSuitEnum suit;

    public Card(CardValueEnum valueType, CardSuitEnum suit) {
        super();
        this.valueType = valueType;
        this.suit = suit;
    }

    public CardValueEnum getValueType() {
        return valueType;
    }

    public CardSuitEnum getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        return valueType.compareTo(other.valueType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((suit == null) ? 0 : suit.hashCode());
        result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (suit != other.suit)
            return false;
        if (valueType != other.valueType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return valueType + " of " + suit + "S";
    }
}
