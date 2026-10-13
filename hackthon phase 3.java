
class Product {
    private String productName;
    private double startingPrice;
    private double currentHighestBid;
    private String currentHighestBidder;

    public Product(String productName, double startingPrice) {
        
        this.productName = productName != null ? productName.trim().toUpperCase() : "UNKNOWN";
        this.startingPrice = startingPrice;
        this.currentHighestBid = startingPrice;
        this.currentHighestBidder = "No Bids Yet";
    }

    public String getProductName() {
        return productName;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }

    public String getCurrentHighestBidder() {
        return currentHighestBidder;
    }

    public void updateBid(String bidderName, double amount) {
        this.currentHighestBid = amount;
        this.currentHighestBidder = bidderName;
    }
}

class Auction {
    private Product product;

    public Auction(Product product) {
        this.product = product;
    }

    public void placeBid(String bidderName, double amount) {
        String processedName = (bidderName == null || bidderName.trim().isEmpty()) ? "ANONYMOUS" : bidderName.trim().toUpperCase();
        
        System.out.println("Bidder Name        : " + processedName);
        System.out.println("Bid Amount         : Rs. " + String.format("%.2f", amount));

        if (amount > product.getCurrentHighestBid()) {
            product.updateBid(processedName, amount);
            System.out.println("Bid Status         : Bid Accepted\n");
        } else {
            System.out.println("Bid Status         : Bid Rejected - Bid Must Be Higher\n");
        }
    }

    public void placeBid(double amount) {
        placeBid("ANONYMOUS", amount);
    }

    public void closeAuction() {
        System.out.println("---------- AUCTION SUMMARY ----------");
        System.out.println("Product Name       : " + product.getProductName());
        System.out.println("Winning Bidder     : " + product.getCurrentHighestBidder());
        System.out.println("Final Bid Amount   : Rs. " + String.format("%.2f", product.getCurrentHighestBid()));
        System.out.println("=================================");
    }
}

public class AuctionSystem {
    public static void main(String[] args) {
        System.out.println("========== ONLINE AUCTION PLATFORM ==========\n");

        Product laptop = new Product("laptop", 50000.00);
        System.out.println("Product Name       : " + laptop.getProductName());
        System.out.println("Starting Price     : Rs. " + String.format("%.2f", laptop.getStartingPrice()) + "\n");

        Auction auction = new Auction(laptop);

        auction.placeBid("Ravi", 52000.00);
        auction.placeBid("Priya", 55000.00);
        auction.placeBid("Ravi", 53000.00);

        auction.closeAuction();
    }
}