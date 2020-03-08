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
        final JTable table = new JTable();
        final JScrollPane scrollPane = new JScrollPane(table);
        final DefaultTableModel model = (DefaultTableModel) table.getModel();
        final JLabel label = new JLabel("Filter by an:");
        final JTextField textField = new JTextField();

        Object[] columnsName = new Object[4];
        columnsName[0] = "Nr matricol";
        columnsName[1] = "Nume";
        columnsName[2] = "Prenume";
        columnsName[3] = "An";

        model.setColumnIdentifiers(columnsName);

        scrollPane.setSize(500, 650);
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

        f.setLayout(null);
        f.setVisible(true);

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

        Statement stmt = con.createStatement();

        //stmt.executeUpdate("CREATE INDEX index_an ON studenti(an)");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                ResultSet rs = null;
                String nume, prenume, an;
                int nrMatricol;
                model.setRowCount(0);
                try {
                    int filter;
                    if (textField.getText().equals("1") || textField.getText().equals("2") || textField.getText().equals("3")) {
                        filter = Integer.parseInt(textField.getText());
                        rs = stmt.executeQuery("SELECT * FROM studenti WHERE an=" + filter);
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
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        table.removeAll();
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an});

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
                String nume, prenume, an;
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
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an});

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
                String nume, prenume, an;
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
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an});

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
                String nume, prenume, an;
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
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an});

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
                String nume, prenume, an;
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
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);

                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an});

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
                String nume, prenume, an;
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
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an});

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
                String nume, prenume, an;
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
                        if (!rs.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        nrMatricol = rs.getInt(1);
                        nume = rs.getString(3);
                        prenume = rs.getString(4);
                        an = rs.getString(5);
                        model.insertRow(table.getRowCount(), new Object[]{nrMatricol, nume, prenume, an});

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        //stmt.executeUpdate("DROP INDEX index_an");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}  