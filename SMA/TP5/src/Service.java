import jade.core.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Service extends Agent {
    class MaFenetre extends JFrame implements ActionListener
    { public MaFenetre ()
    { setTitle("Agent Prestataire: "+getLocalName());
        setSize(250,100);
        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout() );

        Etiq= new JLabel ("type de service");
        contenu.add(Etiq);
        Ts= new JTextField (15) ;
        contenu.add(Ts);
        Etiq2= new JLabel ("Nom de service");
        contenu.add(Etiq2);
        Ns= new JTextField (15) ;
        contenu.add(Ns);
        boutonCalcul= new JButton("Register");
        contenu.add(boutonCalcul);
        boutonCalcul.addActionListener(this);
        boutonFin= new JButton("Deregister");
        contenu.add(boutonFin);
        boutonFin.addActionListener(this);
    }

        public void actionPerformed (ActionEvent e)
        { if (e.getSource()==boutonCalcul) flag=true;
        else doDelete();
        }

        private JButton boutonFin,boutonCalcul;
    } // fin de la classe MaFenetre

    protected void setup() {
        fen= new MaFenetre();
        fen.setVisible(true);
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
//attendre l’appui sur le bouton
        while (!flag){}
        sd.setType(Ts.getText());
        sd.setName(Ns.getText());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
        System.out.println(" Enregistrement réalisé avec succès");
    } // fin de setup

    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
        // Close the GUI
        fen.dispose();
        // Printout a dismissal message
        System.out.println("Service-agent: " +getAID().getName()+"  terminating.");
    } // fin de takeDown


    public JLabel Etiq,Etiq2;
    public JTextField Ts,Ns ;
    boolean flag=false ;
    MaFenetre fen;
}// fin de Service
