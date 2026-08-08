/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vedan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Dashboard extends javax.swing.JFrame {

    // 1. Class Variables to remember who is logged in
    private String currentUser;
    private String currentRole;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Dashboard.class.getName());

    // 2. Default Constructor (Required by NetBeans)
    public Dashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

   // 3. NEW Constructor: Catches the data sent from the Login frame
    // 3. NEW Constructor: Catches the data sent from the Login frame
    public Dashboard(String username, String role) {
        initComponents();
        this.setLocationRelativeTo(null); // Center screen
        
        this.currentUser = username;
        this.currentRole = role; // Fixed variable name to match declaration
        
        // Role-based security check
        if ("HR".equals(this.currentRole)) {
            // HR shouldn't be able to Create Schedules
            jButton1.setEnabled(false); 
        }
        
        // --- NEW LINE ADDED HERE ---
        // Load the SQL data into the table the moment the window opens!
        loadScheduleData(); 

        // Listen for ANY changes to the table (like clicking a checkbox)
        jTable1.getModel().addTableModelListener(new javax.swing.event.TableModelListener() {
            @Override
            public void tableChanged(javax.swing.event.TableModelEvent e) {
                // Check if the change happened in the "Status" column (Index 3)
                // Check if it was an UPDATE (checkbox click) in the "Status" column (Index 3)
                if (e.getType() == javax.swing.event.TableModelEvent.UPDATE && e.getColumn() == 3)
                {
                    
                    // 1. Instantly update the top labels
                    updateStats();
                    
                    // 2. Save the new attendance status to MySQL
                    int row = e.getFirstRow();
                    if (row >= 0) {
                        String empName = jTable1.getValueAt(row, 0).toString();
                        boolean isPresent = (boolean) jTable1.getValueAt(row, 3);
                        
                        try {
                            String dbUrl = "jdbc:mysql://localhost:3306/harmonelydesk?useSSL=false";
                            String dbUser = "root";
                            String dbPass = "root";
                            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                            
                            // UPDATE the existing database record
                            PreparedStatement pstmt = conn.prepareStatement("UPDATE Schedules SET status = ? WHERE emp_name = ?");
                            pstmt.setBoolean(1, isPresent);
                            pstmt.setString(2, empName);
                            pstmt.executeUpdate();
                            
                            pstmt.close();
                            conn.close();
                        } catch (Exception ex) {
                            System.out.println("Error saving attendance: " + ex.getMessage());
                        }
                    }
                }
            }
        });
    }
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe Print", 3, 48)); // NOI18N
        jLabel1.setText("HARMonlydesk");

        jLabel2.setText("Employees");

        jLabel3.setText("Today's Shift");

        jLabel4.setText("Attendance");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Employee", "Shift", "Timings", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Create Schedule");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Add Employee");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Report");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Account Manager");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setText("Stress/Wellness Tracking");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton6.setBackground(new java.awt.Color(255, 0, 51));
        jButton6.setFont(new java.awt.Font("Stylus BT", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Logout");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jButton7.setFont(new java.awt.Font("Microsoft Yi Baiti", 3, 18)); // NOI18N
        jButton7.setText("System Users");
        jButton7.addActionListener(this::jButton7ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(25, 25, 25)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(29, 29, 29)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void updateStats() {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        int totalEmployees = model.getRowCount();
        
        int pendingShifts = 0;
        int attendanceCount = 0;
        
        for (int i = 0; i < totalEmployees; i++) {
            String shift = model.getValueAt(i, 1).toString();
            
            // Safely get the checkbox boolean value
            Object statusObj = model.getValueAt(i, 3);
            boolean isPresent = false;
            if (statusObj != null) {
                isPresent = (boolean) statusObj;
            }
            
            if (shift.equals("Pending")) {
                pendingShifts++;
            }
            if (isPresent) {
                attendanceCount++;
            }
        }
        
        jLabel2.setText("Employees: " + totalEmployees);
        jLabel3.setText("Pending Shifts: " + pendingShifts);
        jLabel4.setText("Attendance: " + attendanceCount);
    }
    private void loadScheduleData() {
        String dbUrl = "jdbc:mysql://localhost:3306/harmonelydesk?useSSL=false";
        String dbUser = "root";
        String dbPass = "root";
        
        // Grab the visual table model and wipe it clean before loading new data
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); 
        
        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            java.sql.Statement stmt = conn.createStatement();
            
            // Pull everything from the Schedules table
            ResultSet rs = stmt.executeQuery("SELECT * FROM Schedules");
            
            while (rs.next()) {
                String name = rs.getString("emp_name");
                String shift = rs.getString("shift_type");
                String timings = rs.getString("timings");
                boolean status = rs.getBoolean("status");
                
                // Add the SQL row to the visual table
                model.addRow(new Object[]{name, shift, timings, status});
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error loading table data: " + e.getMessage());
        }
        updateStats();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                      
        // 1. Open the Schedules window
        Schedules scheduleWindow = new Schedules();
        scheduleWindow.setVisible(true);
        scheduleWindow.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        
        // 2. THE BRIDGE: Listen for when the Schedules window closes
        scheduleWindow.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Instantly reload the SQL data into the table!
                loadScheduleData(); 
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                                       
        // 1. Open the new Advanced Employee Manager window
        EmployeeManager empManager = new EmployeeManager();
        empManager.setVisible(true);
        
        // 2. Ensure closing the manager doesn't close the entire dashboard
        empManager.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                                       
        // 1. Gather live stats from the table model
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        int totalEmployees = model.getRowCount();
        
        int attendanceCount = 0;
        for (int i = 0; i < totalEmployees; i++) {
            Object statusObj = model.getValueAt(i, 3);
            if (statusObj != null && (boolean) statusObj) {
                attendanceCount++;
            }
        }
        
        int absentCount = totalEmployees - attendanceCount;
        
        // 2. Build a clean, professional summary report message
        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("       HARMonlyDesk System Report       \n");
        report.append("========================================\n\n");
        report.append("• Generated By: ").append(currentUser).append("\n");
        report.append("• Access Role: ").append(currentRole).append("\n");
        report.append("• Timestamp: ").append(java.time.LocalDateTime.now().toString().substring(0, 16)).append("\n\n");
        report.append("--- WORKFORCE SUMMARY ---\n");
        report.append("Total Active Employees: ").append(totalEmployees).append("\n");
        report.append("Present (Checked In): ").append(attendanceCount).append("\n");
        report.append("Absent / Unchecked: ").append(absentCount).append("\n\n");
        report.append("Status: System Operating Normally [OK]\n");
        report.append("========================================");
        
        // 3. Display the report in a clean message dialog box
        javax.swing.JOptionPane.showMessageDialog(this, 
            report.toString(), 
            "System Activity Report", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // 1. Open the Advanced Employee Manager window
    EmployeeManager empManager = new EmployeeManager();
    empManager.setVisible(true);
        
    // 2. Ensure closing the manager doesn't close the entire dashboard
    empManager.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
  WellnessMonitor monitor = new WellnessMonitor();
    monitor.setVisible(true);
    monitor.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
                                      
        // 1. Ask for confirmation
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to log out?", 
                "Confirm Logout", javax.swing.JOptionPane.YES_NO_OPTION);
                
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // 2. Destroy the current dashboard window
            this.dispose(); 
            
            // 3. Re-open the Login screen
            new LoginFrame().setVisible(true); 
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // We use the currentRole variable you set up way back in the beginning!
        if ("Admin".equals(this.currentRole)) {
            UserSettings settingsWindow = new UserSettings();
            settingsWindow.setVisible(true);
            settingsWindow.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Access Denied: Only Admins can manage user accounts.", 
                "Security Alert", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
