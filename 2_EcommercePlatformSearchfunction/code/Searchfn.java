class Product {
    String productId;
    String productName;
    String category;

    Product(String id, String name, String cat) {
        this.productId = id;
        this.productName = name;
        this.category = cat;
    }
}

// Removed `public` here to keep only one public class in the file
class Search {
    static Product linearSearch(Product[] products, String name) {
        for (Product p : products) {
            if (p.productName.equals(name)) {
                return p;
            }
        }
        return null;
    }

    static Product binarySearch(Product[] products, String name) {
        int left = 0;
        int right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = products[mid].productName.compareTo(name);
            if (cmp == 0) return products[mid];
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}

public class Searchfn {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P1", "Keyboard", "Electronics"),
            new Product("P2", "Mouse", "Electronics"),
            new Product("P3", "Monitor", "Electronics")
        };

        Product result = Search.linearSearch(products, "Mouse");

        if (result != null)
            System.out.println("Found Product ID: " + result.productId);
        else
            System.out.println("Product not found");
    }
}
