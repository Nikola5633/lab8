package exercitiul1;

import java.sql.*;

    class MainApp {
        public static void afisare_tabela(Statement statement, String mesaj) {
            String sql
                    ="select * from persoane";
            System.out.println("\n---"
                    +mesaj
                    +"---");
            try(ResultSet rs
                        =statement.executeQuery(sql)) {
                while (rs.next())
                    System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ",varsta="
                            + rs.getInt(3));
            } catch (SQLException
                    e) {
                e.printStackTrace();
            }
        }

        public static void afisare_tabela_excursii(Statement statement, String mesaj) {
            String sql
                    ="select * from excursii";
            System.out.println("\n---"
                    +mesaj
                    +"---");
            try(ResultSet rs
                        =statement.executeQuery(sql)) {
                while (rs.next())
                    System.out.println("id persoana=" + rs.getInt(1)  + ", id excrusie=" +rs.getInt(2) + ", destinatie"
            + rs.getString(3) +", anul: "+rs.getInt(4));
            } catch (SQLException
                    e) {
                e.printStackTrace();
            }
        }
        public static void adaugare(Connection connection, int id, String nume, int varsta) {
            String sql="insert into persoane values (?,?,?)";
            try(PreparedStatement ps=connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, nume);
                ps.setInt(3, varsta);
                int nr_randuri=ps.executeUpdate();
                System.out.println("\nNumar randuri afectate de adaugare="+nr_randuri);
            } catch (SQLException e) {
                System.out.println(sql);
                e.printStackTrace();
            }
        }

        public static void adaugare_excursii(Connection connection, int id_persoana, int id_excursie, String destinatie, int anul) {
            String sql="insert into excursii (id_persoana,id_excursie,destinatia, anul) values (?,?,?, ?)";
            try(PreparedStatement ps=connection.prepareStatement(sql)) {
                ps.setInt(1, id_persoana);
                ps.setInt(2, id_excursie);
                ps.setString(3, destinatie);
                ps.setInt(4, anul);
                int nr_randuri=ps.executeUpdate();
                System.out.println("\nNumar randuri afectate de adaugare="+nr_randuri);
            } catch (SQLException e) {
                System.out.println(sql);
                e.printStackTrace();
            }
        }

        public static void stergere(Connection connection,int id){
            String sql="delete from excursii where id_excursie=?";
            try(PreparedStatement ps=connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                int nr_randuri=ps.executeUpdate();
                System.out.println("\nNumar randuri afectate de modificare="+nr_randuri);
            }
            catch (SQLException e) {
                System.out.println(sql);
                e.printStackTrace();
            }
        }
        public static void main(String[] args){
            String url = "jdbc:mysql://localhost:3306/lab8";
            try {
                Connection connection = DriverManager.getConnection(url, "root", "root");
                Statement statement = connection.createStatement();
//                afisare_tabela(statement,"Continut initial");
//                adaugare(connection,8,"Dana",23);
//               afisare_tabela(statement,"Dupa adaugare");
//                afisare_tabela_excursii(statement,"Continut initial");
//                adaugare_excursii(connection,1,1,"Timisoara", 2021);
                afisare_tabela_excursii(statement,"Dupa adaugare");
                stergere(connection,7);
                afisare_tabela_excursii(statement,"Dupa stergere");
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
