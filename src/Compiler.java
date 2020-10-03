import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Compiler {
    public Compiler() throws FileNotFoundException {
    }

    File file = new File("resources/input.txt");

    Scanner scanner = new Scanner(file);

    Parser parser = new Parser();
    StringBuilder text = new StringBuilder();

    private Token bestAsk = new Token(TokenKind.updateAsk, 0, 0);
    private Token bestBid = new Token(TokenKind.updateBid, 0, 0);
    private ArrayList<Token> arrBidAsk = new ArrayList<Token>();

    public void run() {
        while (scanner.hasNextLine()) {
            Token token = parser.nextToken(scanner.nextLine());
            switch (token.getTokenKind()) {
                case updateBid:
                    bestBid = updateBid(token);
                    arrBidAsk.add(token);
                    break;

                case updateAsk:
                    bestAsk  = updateAsk(token);
                    arrBidAsk.add(token);
                    break;

                case orderBuy:
                    orderBuy(token.getSize());
                    break;

                case orderSell:
                    orderSell(token.getSize());
                    break;

                case unknownToken:
                    break;

                case queryBestBid:
                    saveBest(bestBid);
                    break;

                case queryBestAsk:
                    saveBest(bestAsk);
                    break;

                case querySize:
                    saveSizeByPrice(token.getPrice());
                    break;
            }
        }
    }

    private Token updateBid(Token token) {

        if (bestBid.getPrice() == 0){
            return token;
        }

        if ((token.getPrice() < bestBid.getPrice())) {
            return bestBid;
        }

        if (token.getSize() == 0)
            return bestBid;

        if (token.getPrice() == bestBid.getPrice()) {
            bestBid.setSize(bestBid.getSize() + token.getSize());
        }

        if (token.getPrice() > bestBid.getPrice()) {
            return token;
        }

        return bestBid;
    }


    private Token updateAsk(Token token) {

        if (bestAsk.getPrice() == 0){
            return token;
        }

        if (!(token.getPrice() < bestAsk.getPrice())) {
            return bestAsk;
        }

        if (token.getSize() == 0)
            return bestAsk;

        if (token.getPrice() == bestAsk.getPrice()) {
            bestAsk.setSize(bestAsk.getSize() + token.getSize());
        }

        if (token.getPrice() > bestAsk.getPrice()) {
            return token;
        }

        return bestAsk;
    }

    private void  saveBest(Token token){
        text.append(token.getPrice()).append(",").append(token.getSize()+"\n");
    }


    private void saveSizeByPrice(int price){
        for (Token token : arrBidAsk) {
            if (token.getPrice() == price){
                text.append(token.getSize() + "\n");
            }
        }
    }

    private void orderBuy (int size){
        bestAsk.setSize(bestAsk.getSize() - size);
    }

    private void orderSell (int size){
        bestBid.setSize(bestBid.getSize() - size);
    }

    public void saveToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("resources/output.txt");
        fileWriter.write(text.toString());
        fileWriter.close();
    }



}
