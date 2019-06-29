package my_try;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class Service extends Agent {
    
    public JLabel lblTypeService, lblNomService;
    public JTextField fieldTypeService, fieldNomService;
    boolean flag = false;
    MaFenetre fen;
    Semaphore semaphore;

    class MaFenetre extends JFrame implements ActionListener {
        private JButton boutonFin, btnRegister;
        public MaFenetre() {

            lblTypeService = new JLabel("Type de service");
            fieldTypeService = new JTextField(15);

            lblNomService = new JLabel("Nom de service");
            fieldNomService = new JTextField(15);

            btnRegister = new JButton("Register");
            boutonFin = new JButton("Deregister");

            btnRegister.addActionListener(this);
            boutonFin.addActionListener(this);

            Container contenu = getContentPane();
            contenu.setLayout(new FlowLayout());

            contenu.add(lblTypeService);
            contenu.add(fieldTypeService);
            contenu.add(lblNomService);
            contenu.add(fieldNomService);
            contenu.add(btnRegister);
            contenu.add(boutonFin);
            
            setTitle("Agent Prestataire: " + getLocalName());
            setBounds(10, 100, 250, 300);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnRegister) {
                try {
                    semaphore.release();
                } catch(Exception ez) {

                }
                //flag = true;
                //doActivate();
            }
            else doDelete();
        }
    }

    protected void setup() {
        semaphore = new Semaphore(1);
        try {
            semaphore.acquire();
        } catch(Exception e) {

        }
        fen = new MaFenetre();
        fen.setVisible(true);
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();

        //attendre l’appui sur le bouton
        //this.doWait();
        try {
            semaphore.acquire();
        } catch(Exception e) {

        }
        //while (!flag) {}

        sd.setType(fieldTypeService.getText());
        sd.setName(fieldNomService.getText());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        System.out.println(" Enregistrement réalisé avec succès");
    }

    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        // Close the GUI
        fen.dispose();
        // Printout a dismissal message
        System.out.println("Service-agent: " + getAID().getName() + "  terminating.");
    }

}
