public class Parser {

    private String text;
    private int position = 0;
    public static final int lineLength4 = 4;
    public static final int lineLength3 = 3;
    public static final int lineLength2 = 2;


    public Token nextToken(String line) {
        String[] arr = line.split(",");

        if (arr.length == lineLength2) {

            if (isQueryBestBid(arr)) {
                return new Token(TokenKind.queryBestBid);
            }

            if (isQueryBestAsk(arr)) {
                return new Token(TokenKind.queryBestAsk);
            }
        }

        if (arr.length == lineLength3) {
            int size = Integer.parseInt(arr[2]);
            int price = Integer.parseInt(arr[2]);

            if (isOrderBuy(arr)) {
                return new Token(TokenKind.orderBuy, size);
            }

            if (isOrderSell(arr)) {
                return new Token(TokenKind.orderSell, size);
            }

            if (isQuerySize(arr)) {
                return new Token(TokenKind.querySize, price,0);
            }
        }


        if (arr.length == lineLength4) {
            int price = Integer.parseInt(arr[1]);
            int size = Integer.parseInt(arr[2]);

            if (isUpdateAsk(arr)) {
                return new Token(TokenKind.updateAsk, price, size);
            }

            if (isUpdateBid(arr)) {
                return new Token(TokenKind.updateBid, price, size);
            }
        }

        return new Token(TokenKind.unknownToken, 0);
    }


    private boolean isUpdateBid(String[] arr) {
        return arr[0].equals("u") && arr[3].equals("bid");
    }

    private boolean isUpdateAsk(String[] arr) {
        return arr[0].equals("u") && arr[3].equals("ask");
    }

    private boolean isOrderBuy(String[] arr) {
        return arr[0].equals("o") && arr[1].equals("buy");
    }

    private boolean isOrderSell(String[] arr) {
        return arr[0].equals("o") && arr[1].equals("sell");
    }

    private boolean isQueryBestBid(String[] arr) {
        return arr[0].equals("q") && arr[1].equals("best_bid");
    }

    private boolean isQueryBestAsk(String[] arr) {
        return arr[0].equals("q") && arr[1].equals("best_ask");
    }

    private boolean isQuerySize(String[] arr) {
        return arr[0].equals("q") && arr[1].equals("size");
    }


}
