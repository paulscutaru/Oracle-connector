import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.TimeUnit;

public class OracleCon extends JFrame {
    public static void main(String args[]) throws Exception {

        JFrame f = new JFrame("Oracle connector by Scutaru Paul");
        f.setSize(1150, 720);
        f.setLocation(300, 200);

        final JButton button = new JButton("Show database");
        final JButton buttonNumeASC = new JButton("ORDER BY nume ASC");
        final JButton buttonNumeDESC = new JButton("ORDER BY nume DESC");
        final JButton buttonNrMatASC = new JButton("ORDER BY nr_matricol ASC");
        final JButton buttonNrMatDESC = new JButton("ORDER BY nr_matricol DESC");
        final JButton buttonPrenumeASC = new JButton("ORDER BY prenume ASC");
        final JButton buttonPrenumeDESC = new JButton("ORDER BY prenume DESC");
        final JButton buttonSearch = new JButton("Submit");
        final JButton buttonRecommendFriends = new JButton("Submit");
        final JTable table = new JTable();
        final JScrollPane scrollPane = new JScrollPane(table);
        final DefaultTableModel model = (DefaultTableModel) table.getModel();
        final JLabel label = new JLabel("Filter by an:");
        final JTextField textField = new JTextField();
        final JLabel labelNume = new JLabel("Search by nume:");
        final JTextField textFieldNume = new JTextField();
        final JLabel labelFriends = new JLabel("Recommend friends for:");
        final JTextField textFieldFriends = new JTextField();

        Object[] columnsName = new Object[5];
        columnsName[0] = "Nr matricol";
        columnsName[1] = "Nume";
        columnsName[2] = "Prenume";
        columnsName[3] = "An";
        columnsName[4] = "Grupa";
        model.setColumnIdentifiers(columnsName);

        scrollPane.setSize(530, 650);
        scrollPane.setLocation(5, 5);
        button.setSize(400, 100);
        button.setLocation(620, 500);
        buttonNrMatASC.setSize(200, 50);
        buttonNrMatASC.setLocation(600, 20);
        buttonNrMatDESC.setSize(200, 50);
        buttonNrMatDESC.setLocation(820, 20);
        buttonNumeASC.setSize(200, 50);
        buttonNumeASC.setLocation(600, 115);
        buttonNumeDESC.setSize(200, 50);
        buttonNumeDESC.setLocation(820, 115);
        buttonPrenumeASC.setSize(200, 50);
        buttonPrenumeASC.setLocation(600, 215);
        buttonPrenumeDESC.setSize(200, 50);
        buttonPrenumeDESC.setLocation(820, 215);
        textField.setSize(100, 30);
        textField.setLocation(700, 350);
        label.setSize(100, 30);
        label.setLocation(620, 350);
        textFieldNume.setSize(100, 30);
        textFieldNume.setLocation(700, 400);
        labelNume.setSize(100, 30);
        labelNume.setLocation(590, 400);
        buttonSearch.setSize(80, 30);
        buttonSearch.setLocation(820, 400);
        buttonRecommendFriends.setSize(80, 30);
        buttonRecommendFriends.setLocation(820, 450);
        textFieldFriends.setSize(100, 30);
        textFieldFriends.setLocation(700, 450);
        labelFriends.setSize(200, 30);
        labelFriends.setLocation(550, 450);

        f.add(scrollPane);
        f.add(button);
        f.add(buttonNumeASC);
        f.add(buttonNumeDESC);
        f.add(buttonNrMatASC);
        f.add(buttonNrMatDESC);
        f.add(buttonPrenumeASC);
        f.add(buttonPrenumeDESC);
        f.add(textField);
        f.add(label);
        f.add(textFieldNume);
        f.add(labelNume);
        f.add(buttonSearch);
        f.add(buttonRecommendFriends);
        f.add(labelFriends);
        f.add(textFieldFriends);

        f.setLayout(null);
        f.setVisible(true);

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

        Statement stmt = con.createStatement();

        //stmt.executeUpdate("CREATE INDEX index_nume ON studenti(nume)");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filterAn;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filterAn = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filterAn);
                    } else
                        rs = stmt.executeQuery("SELECT * FROM studenti");

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        table.removeAll();
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonNumeASC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filter = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter + "ORDER BY nume ASC");
                    } else
                        rs = stmt.executeQuery("SELECT * FROM studenti ORDER BY nume ASC");

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonNumeDESC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filter = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter + "ORDER BY nume DESC");
                    } else
                        rs = stmt.executeQuery("SELECT * FROM studenti ORDER BY nume DESC");

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonNrMatASC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filter = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter + "ORDER BY id ASC");
                    } else
                        rs = stmt.executeQuery("SELECT * FROM studenti ORDER BY id ASC");

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonNrMatDESC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filter = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter + "ORDER BY id DESC");
                    } else
                        rs = stmt.executeQuery("SELECT * FROM studenti ORDER BY id DESC");

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonPrenumeASC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filter = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter + "ORDER BY prenume ASC");
                    } else
                        rs = stmt.executeQuery("SELECT * FROM studenti ORDER BY prenume ASC");

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonPrenumeDESC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filter = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter + "ORDER BY prenume DESC");
                    } else
                        rs = stmt.executeQuery("SELECT * FROM studenti ORDER BY prenume DESC");

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an, grupa;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    String searchedName = textFieldNume.getText();
                    if (searchedName != null) {
                        if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                            filter = Integer.parseInt(textField.getText());
                            rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter + "AND nume like('" + searchedName + "')");
                        } else
                            rs = stmt.executeQuery("SELECT * FROM studenti WHERE nume like('" + searchedName + "')");
                    }

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        grupa = rs.getString(6);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an, grupa});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        buttonRecommendFriends.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String rezultat, nume = "", prenume = "", grupa = "";

                try {
                    int filter = -1, i = 0;
                    String searchedText = textFieldFriends.getText();
                    String[] formattedText = textFieldFriends.getText().split(" ");
                    for (String s : formattedText) {
                        if (formattedText.length == 3) {
                            if (i == 0)
                                nume = s;
                            else if (i == 1)
                                prenume = s;
                            else if (i == 2)
                                grupa = s;
                            i++;
                        } else if (formattedText.length == 4) {
                            if (i == 0)
                                nume = s;
                            else if (i == 1)
                                prenume = s;
                            else if (i == 2)
                                prenume = prenume + " " + s;
                            else if (i == 3)
                                grupa = s;
                            i++;
                        }
                    }

                    if (searchedText != null) {
                        rs = stmt.executeQuery("SELECT id FROM studenti WHERE nume LIKE('" + nume + "') " +
                                "AND prenume LIKE('" + prenume + "') AND grupa LIKE('" + grupa + "')");
                        while (true) {
                            try {
                                assert rs != null;
                                if (!rs.next()) break;
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                filter = rs.getInt(1);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                        rs = stmt.executeQuery("SELECT recomanda_prieteni(" + filter + ") FROM dual");
                    }

                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println("Elapsed time: " + elapsedTime);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        assert rs != null;
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        rezultat = rs.getString(1);
                        rezultat = rezultat.replaceAll(",", "\n");
                        System.out.println(rezultat);
                        JOptionPane.showMessageDialog(f, rezultat + "\n");

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        //stmt.executeUpdate("DROP INDEX index_nume");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}  