import java.sql.*;

public class TransactionDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/demo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "MyNewPass@123";
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the database!");

            //Order, OrderItems
            // INSERT INTO ORDER
            int orderId=insertOrder(conn,101,"Alice01",2000.0);
            // INSERT INTO ORDER
            insertOrderItem(conn, orderId, "Laptop01",1,2000.0);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static int insertOrder(Connection conn, int customerId, String customerName, double price) {
        String sql = "INSERT INTO order_items (order_id, product_name, quantity, price) " +
                "VALUES (?, ?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1,customerId);
            pstmt.setString(2,customerName);
            pstmt.setDouble(3,price);
            int rows = pstmt.executeUpdate();
            System.out.println("INSERTED into orders: "+ rows);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
