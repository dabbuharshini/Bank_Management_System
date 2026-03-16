import java.sql.*;
import java.time.LocalDateTime;

public class BankService {
    private static long generateAccountNumber(){

        long min = 10000000000L;
        long max = 99999999999L;

        return min + (long)(Math.random() * (max - min));
    }
    public static String createAccount(String name,String aadhaar,String phone,String address){

        if(!aadhaar.matches("\\d{12}"))
            return "Invalid Aadhaar number.";

        if(!phone.matches("\\d{10}"))
            return "Invalid phone number.";

        long accountId = generateAccountNumber();

        try(Connection conn = DBConnection.getConnection()){

            String check="SELECT * FROM accounts WHERE aadhaar=?";
            PreparedStatement ps1=conn.prepareStatement(check);

            ps1.setString(1,aadhaar);

            ResultSet rs=ps1.executeQuery();

            if(rs.next())
                return "Account already exists for this Aadhaar.";

            String sql="INSERT INTO accounts VALUES(?,?,?,?,?,0)";
            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setLong(1,accountId);
            ps.setString(2,name);
            ps.setString(3,aadhaar);
            ps.setString(4,phone);
            ps.setString(5,address);

            ps.executeUpdate();

            return "Account created successfully.<br>Account Number: "+accountId;

        }catch(Exception e){
            e.printStackTrace();
            return "Error creating account.";
        }
    }

    public static String deposit(long id,double amount){

        if(amount <= 0)
            return "Invalid deposit amount.";

        try(Connection conn = DBConnection.getConnection()){

            String check = "SELECT balance FROM accounts WHERE account_id=?";
            PreparedStatement ps = conn.prepareStatement(check);
            ps.setLong(1,id);

            ResultSet rs = ps.executeQuery();

            if(!rs.next())
                return "Account not found.";

            double before = rs.getDouble("balance");
            double after = before + amount;

            String update = "UPDATE accounts SET balance=? WHERE account_id=?";
            PreparedStatement ps2 = conn.prepareStatement(update);

            ps2.setDouble(1,after);
            ps2.setLong(2,id);

            ps2.executeUpdate();

            String tx = "INSERT INTO transactions(account_id,description,before_balance,after_balance,time) VALUES(?,?,?,?,?)";

            PreparedStatement ps3 = conn.prepareStatement(tx);

            ps3.setLong(1,id);
            ps3.setString(2,"Deposit ₹"+amount);
            ps3.setDouble(3,before);
            ps3.setDouble(4,after);
            ps3.setString(5,LocalDateTime.now().toString());

            ps3.executeUpdate();

            return "Deposit successful. Current Balance: ₹"+after;

        }catch(Exception e){
            e.printStackTrace();
            return "Deposit error.";
        }
    }

    public static String withdraw(long id,double amount){

        if(amount <= 0)
            return "Invalid withdrawal amount.";

        try(Connection conn = DBConnection.getConnection()){

            String check = "SELECT balance FROM accounts WHERE account_id=?";
            PreparedStatement ps = conn.prepareStatement(check);

            ps.setLong(1,id);

            ResultSet rs = ps.executeQuery();

            if(!rs.next())
                return "Account not found.";

            double before = rs.getDouble("balance");

            if(amount > before)
                return "Insufficient balance.";

            if(before - amount < 1000)
                return "Withdrawal denied. Minimum balance ₹1000 must remain.";

            double after = before - amount;

            String update = "UPDATE accounts SET balance=? WHERE account_id=?";
            PreparedStatement ps2 = conn.prepareStatement(update);

            ps2.setDouble(1,after);
            ps2.setLong(2,id);

            ps2.executeUpdate();

            String tx = "INSERT INTO transactions(account_id,description,before_balance,after_balance,time) VALUES(?,?,?,?,?)";

            PreparedStatement ps3 = conn.prepareStatement(tx);

            ps3.setLong(1,id);
            ps3.setString(2,"Withdraw ₹"+amount);
            ps3.setDouble(3,before);
            ps3.setDouble(4,after);
            ps3.setString(5,LocalDateTime.now().toString());

            ps3.executeUpdate();

            return "Withdrawal successful. Current Balance: ₹"+after;

        }catch(Exception e){
            e.printStackTrace();
            return "Withdrawal error.";
        }
    }

    public static String checkBalance(long id){

        try(Connection conn = DBConnection.getConnection()){

            String sql = "SELECT balance FROM accounts WHERE account_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
                return "Current Balance: ₹"+rs.getDouble("balance");

            return "Account not found.";

        }catch(Exception e){
            e.printStackTrace();
            return "Error checking balance.";
        }
    }
    public static String getTransactions(long id){

        try(Connection conn = DBConnection.getConnection()){

            String sql="SELECT * FROM transactions WHERE account_id=?";
            PreparedStatement ps=conn.prepareStatement(sql);

            ps.setLong(1,id);

            ResultSet rs=ps.executeQuery();

            StringBuilder html=new StringBuilder();

            html.append("<table border='1' style='border-collapse:collapse;width:100%'>");
            html.append("<tr><th>Description</th><th>Before</th><th>After</th><th>Time</th></tr>");

            boolean found=false;

            while(rs.next()){

                found=true;

                html.append("<tr>");
                html.append("<td>").append(rs.getString("description")).append("</td>");
                html.append("<td>").append(rs.getDouble("before_balance")).append("</td>");
                html.append("<td>").append(rs.getDouble("after_balance")).append("</td>");
                html.append("<td>").append(rs.getString("time")).append("</td>");
                html.append("</tr>");
            }

            html.append("</table>");

            if(!found)
                return "No transactions found.";

            return html.toString();

        }catch(Exception e){
            e.printStackTrace();
            return "Error loading transactions.";
        }
    }
}