public class Token {
    private TokenKind tokenKind;
    private int price;
    private int size;

    public Token(TokenKind tokenKind) {
        this.tokenKind = tokenKind;
    }


    public Token(TokenKind tokenKind, int size) {
        this.tokenKind = tokenKind;
        this.size = size;
    }

    public Token(TokenKind tokenKind, int price, int size) {
        this.tokenKind = tokenKind;
        this.price = price;
        this.size = size;
    }

    public TokenKind getTokenKind() {
        return tokenKind;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
