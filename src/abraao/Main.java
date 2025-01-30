/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package abraao;

import java.sql.*;

/**
 *
 * @author 1886519
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Connection con = null;

        try {
            con = new ConnectionFactory().getConnection();
            System.out.println("Connection OK!");

            // ********** CRUD ********** 
            String sql = "insert into Customer (store_id, first_name, last_name, email, address_id, active)"
                    + "values (?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, 1);
            pst.setString(2, "Alcemy");
            pst.setString(3, "Severino");
            pst.setString(4, "alcemy@gmail.com");
            pst.setInt(5, 10);
            pst.setInt(6, 1);

            pst.executeUpdate();

            Statement st = con.createStatement();

            String query = "select * from customer"
                    + " order by customer_id desc"
                    + " limit 5";

            ResultSet rs = st.executeQuery(query);

            ResultSetMetaData md = rs.getMetaData();
            int col = md.getColumnCount();

            System.out.println("Tabela: " + md.getTableName(1));
            for (int i = 1; i <= col; i++) {
                System.out.print(md.getColumnName(i) + "\t");
            }
            System.out.println("");

            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println("");
            }

            String update = "update customer set store_id = 2, address_id = 5 where first_name = 'Alcemy' and last_name = 'Severino'";

            PreparedStatement pstII = con.prepareStatement(update);

            pstII.executeUpdate();

            rs = st.executeQuery(query);

            md = rs.getMetaData();
            col = md.getColumnCount();

            System.out.println("Tabela: " + md.getTableName(1));
            for (int i = 1; i <= col; i++) {
                System.out.print(md.getColumnName(i) + "\t");
            }
            System.out.println("");

            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println("");
            }

            String delete = "delete from customer where first_name = 'Alcemy' and last_name = 'Severino'";

            PreparedStatement pstIII = con.prepareStatement(delete);

            pstIII.executeUpdate();

            rs = st.executeQuery(query);

            md = rs.getMetaData();
            col = md.getColumnCount();

            System.out.println("Tabela: " + md.getTableName(1));
            for (int i = 1; i <= col; i++) {
                System.out.print(md.getColumnName(i) + "\t");
            }
            System.out.println("");

            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
            System.out.println("Connection closed!");
        }

    }

}
