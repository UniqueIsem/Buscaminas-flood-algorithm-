package front;

import back.Casilla;
import back.Tablero;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class VentanaJuego extends javax.swing.JFrame {
   int filas;
   int columnas;
   int numMinas;
   JButton[][] btnsTablero;
   Tablero tablero;
   ImageIcon lose = new ImageIcon("C:/Users/isaac/OneDrive/Documentos/NetBeansProjects/Buscaminas/src/img/lose.png");
   ImageIcon juego = new ImageIcon("C:/Users/isaac/OneDrive/Documentos/NetBeansProjects/Buscaminas/src/img/juego.png");
   ImageIcon nojao = new ImageIcon("C:/Users/isaac/OneDrive/Documentos/NetBeansProjects/Buscaminas/src/img/nojao.png");
   ImageIcon win = new ImageIcon("C:/Users/isaac/OneDrive/Documentos/NetBeansProjects/Buscaminas/src/img/win.png");
   
    public VentanaJuego() {
        initComponents();
        this.setLocationRelativeTo(null);
        tamañoTablero();
        numMinas();
        juegoNuevo();
    }
    
    private void tamañoTablero(){
        String numTablero = JOptionPane.showInputDialog("Ingrese el tamaño (n*n) para el tablero:");
        this.filas = Integer.parseInt(numTablero);
        this.columnas = Integer.parseInt(numTablero);
    }
    
    private void numMinas(){
        String minas = JOptionPane.showInputDialog("Ingrese el número de minas:");
        this.numMinas = Integer.parseInt(minas);
        lblMinas.setText("Minas: "+numMinas);
    }
    
    private void juegoNuevo(){
        removerTablero();
        cargarControles();
        crearTablero();
        repaint();
    }
    
    void removerTablero(){
        if (btnsTablero != null){
            for (int i = 0; i < btnsTablero.length; i++) {
                for (int j = 0; j < btnsTablero[i].length; j++) {
                    if (btnsTablero[i][j] != null){
                        getContentPane().remove(btnsTablero[i][j]);
                    }
                }
            }
        }
    }
    
    private void crearTablero(){
        tablero = new Tablero(filas, columnas, numMinas);
        tablero.setEventoPartidaPerdida(new Consumer<List<Casilla>>(){
            @Override
            public void accept(List<Casilla> t){
                for (Casilla casillaConMina: t){
                    btnsTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setIcon(lose);
                    btnsTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setBackground(Color.RED);
                    lblIcono.setIcon(nojao);
                    for (int i = 0; i < btnsTablero.length; i++) {
                        for (int j = 0; j < btnsTablero[i].length; j++) {
                            btnsTablero[i][j].setEnabled(false);
                        }
                    }
                }
            }
        });
        tablero.setEventoPartidaGanada(new Consumer<List<Casilla>>(){
            @Override
            public void accept(List<Casilla> t){
                for (Casilla casillaConMina: t){
                    btnsTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setIcon(win);
                    btnsTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setBackground(Color.GREEN);
                }
            }
        });
        tablero.setEventoCasillaAbierta(new Consumer<Casilla>(){
            @Override
            public void accept(Casilla t){
                btnsTablero[t.getPosFila()][t.getPosColumna()].setEnabled(false);
                btnsTablero[t.getPosFila()][t.getPosColumna()].setText(t.getNumMinaAlrededor()==0?"":t.getNumMinaAlrededor()+""); 
            }
        });
        tablero.imprimirTablero();
    }
    
    private void cargarControles(){
        int posXReferencia = 25;
        int posYReferencia = 50;
        int anchoControl = 30;
        int altoControl = 30;
        
        btnsTablero = new JButton[filas][columnas];
        for (int i = 0; i < btnsTablero.length; i++) {
            for (int j = 0; j < btnsTablero[i].length; j++) {
                btnsTablero[i][j] = new JButton();
                btnsTablero[i][j].setName(i +"," +j);
                btnsTablero[i][j].setBorder(null);
                if (i == 0 && j == 0) {
                    btnsTablero[i][j].setBounds(posXReferencia, posYReferencia, anchoControl, altoControl);
                } else if (i == 0 && j != 0) {
                    btnsTablero[i][j].setBounds(
                            btnsTablero[i][j-1].getX() +btnsTablero[i][j-1].getWidth(), 
                            posYReferencia, anchoControl, altoControl);
                } else {
                    btnsTablero[i][j].setBounds(
                            btnsTablero[i-1][j].getX(), 
                            btnsTablero[i-1][j].getY() +btnsTablero[i-1][j].getHeight(),
                            anchoControl, altoControl);
                }
                btnsTablero[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        btnClick(e);
                    }
                }); 
                getContentPane().add(btnsTablero[i][j]);
            }
        }
        this.setSize(btnsTablero[filas-1][columnas-1].getX()+
            btnsTablero[filas-1][columnas-1].getWidth()+30,
            btnsTablero[filas-1][columnas-1].getY()+
            btnsTablero[filas-1][columnas-1].getHeight()+70
            );
    }
    
    private void btnClick(ActionEvent e){
        JButton btn = (JButton)e.getSource();
        String[] coordenada = btn.getName().split(",");
        int posFila = Integer.parseInt(coordenada[0]);
        int posColumna = Integer.parseInt(coordenada[1]);
        //JOptionPane.showMessageDialog(this, posFila +"," +posColumna);
        tablero.seleccionarCasilla(posFila, posColumna);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        pnlFondo = new javax.swing.JPanel();
        lblIcono = new javax.swing.JLabel();
        lblMinas = new javax.swing.JLabel();
        mnuBar = new javax.swing.JMenuBar();
        mnuDificultad = new javax.swing.JMenu();
        mnuItemFacil = new javax.swing.JMenuItem();
        mnuItemNormal = new javax.swing.JMenuItem();
        mnuItemDificil = new javax.swing.JMenuItem();
        mnuItemPersonalizado = new javax.swing.JMenuItem();
        mnuOpciones = new javax.swing.JMenu();
        mnuItemJuegoNuevo = new javax.swing.JMenuItem();
        mnuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscaminas");

        pnlFondo.setBackground(new java.awt.Color(153, 153, 153));

        lblIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/juego.png"))); // NOI18N

        lblMinas.setBackground(new java.awt.Color(0, 0, 0));
        lblMinas.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        lblMinas.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMinas, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addGap(214, 214, 214)
                .addComponent(lblIcono)
                .addContainerGap())
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIcono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnuDificultad.setText("Dificultad");

        mnuItemFacil.setText("Facil");
        mnuItemFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemFacilActionPerformed(evt);
            }
        });
        mnuDificultad.add(mnuItemFacil);

        mnuItemNormal.setText("Normal");
        mnuItemNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemNormalActionPerformed(evt);
            }
        });
        mnuDificultad.add(mnuItemNormal);

        mnuItemDificil.setText("Dificil");
        mnuItemDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemDificilActionPerformed(evt);
            }
        });
        mnuDificultad.add(mnuItemDificil);

        mnuItemPersonalizado.setText("Personalizado");
        mnuItemPersonalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemPersonalizadoActionPerformed(evt);
            }
        });
        mnuDificultad.add(mnuItemPersonalizado);

        mnuBar.add(mnuDificultad);

        mnuOpciones.setText("Opciones");

        mnuItemJuegoNuevo.setText("Nuevo juego");
        mnuItemJuegoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemJuegoNuevoActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuItemJuegoNuevo);

        mnuItemSalir.setText("Salir");
        mnuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemSalirActionPerformed(evt);
            }
        });
        mnuOpciones.add(mnuItemSalir);

        mnuBar.add(mnuOpciones);

        setJMenuBar(mnuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuItemFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemFacilActionPerformed
        filas = 10;
        columnas = 10;
        numMinas = 10;
        lblIcono.setIcon(juego);
        lblMinas.setText("Minas: "+numMinas);
        juegoNuevo();
    }//GEN-LAST:event_mnuItemFacilActionPerformed

    private void mnuItemNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemNormalActionPerformed
        filas = 15;
        columnas = 20;
        numMinas = 30;
        lblIcono.setIcon(juego);
        lblMinas.setText("Minas: "+numMinas);
        juegoNuevo();
    }//GEN-LAST:event_mnuItemNormalActionPerformed

    private void mnuItemDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemDificilActionPerformed
        filas = 20;
        columnas = 25;
        numMinas = 50;
        lblIcono.setIcon(juego);
        lblMinas.setText("Minas: "+numMinas);
        juegoNuevo();
    }//GEN-LAST:event_mnuItemDificilActionPerformed

    private void mnuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemSalirActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir?", "Exit", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_mnuItemSalirActionPerformed

    private void mnuItemJuegoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemJuegoNuevoActionPerformed
        lblIcono.setIcon(juego);
        juegoNuevo();
    }//GEN-LAST:event_mnuItemJuegoNuevoActionPerformed

    private void mnuItemPersonalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemPersonalizadoActionPerformed
        lblIcono.setIcon(juego);
        tamañoTablero();
        numMinas();
        juegoNuevo();
    }//GEN-LAST:event_mnuItemPersonalizadoActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblMinas;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenu mnuDificultad;
    private javax.swing.JMenuItem mnuItemDificil;
    private javax.swing.JMenuItem mnuItemFacil;
    private javax.swing.JMenuItem mnuItemJuegoNuevo;
    private javax.swing.JMenuItem mnuItemNormal;
    private javax.swing.JMenuItem mnuItemPersonalizado;
    private javax.swing.JMenuItem mnuItemSalir;
    private javax.swing.JMenu mnuOpciones;
    private javax.swing.JPanel pnlFondo;
    // End of variables declaration//GEN-END:variables
}
