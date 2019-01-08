import jade.core.*;
import jade.core.behaviours.TickerBehaviour;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
public class Searcher extends Agent {
    class MaFenetre extends JFrame implements ActionListener
    { public MaFenetre ()
    { setTitle("Agent Client: "+getLocalName());
        setSize(250,100);
        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout() );
        Etiq= new JLabel ("type de service");
        contenu.add(Etiq);
        Ts= new JTextField (15) ;
        contenu.add(Ts);
        boutonCalcul= new JButton("Search");
        contenu.add(boutonCalcul);
        boutonCalcul.addActionListener(this);
        boutonFin= new JButton("Kill");
        contenu.add(boutonFin);
        boutonFin.addActionListener(this);
    } // fin du constructeur
        public void actionPerformed (ActionEvent e)
        { if (e.getSource()==boutonCalcul) flag=true;
        else doDelete();
        }
        private JButton boutonFin,boutonCalcul;
    } // fin de la classe MaFenetre
    protected void setup() {
        fen= new MaFenetre();
        fen.setVisible(true);
        while (!flag){ }
// Ajouter un TickerBehaviour pour chercher les agents proposant le service demandé toutes les 10 seconds
        addBehaviour(new TickerBehaviour(this, 10000) {
            protected void onTick() {
// Mise à jour de la liste des agents service
                DFAgentDescription template = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType(Ts.getText());
                template.addServices(sd);
                try {
                    DFAgentDescription[] result =DFService.search(myAgent,template);
                    ServiceAgents = new AID[result.length];
                    System.out.println("----------------------- Les agents proposant ce service sont------------------- -------");
                    for (int i = 0; i < result.length; ++i)
                    {
                        ServiceAgents[i] = result[i].getName();
                        System.out.println(result[i].getName());
                    }
                    if(result.length==0)
                        System.out.println("Aucun agent ne propose ce service pour le moment.");
                }
                catch (FIPAException fe) {
                    fe.printStackTrace();
                }
            } // fin de la method onTick
        });
    }// fin de setup
    protected void takeDown() {
// Close the GUI
        fen.dispose();
// Printout a dismissal message
        System.out.println("Service-agent: " +getAID().getName()+" terminating.");
    } // fin de takeDown
    public JLabel Etiq;
    public JTextField Ts ;
    boolean flag=false ;
    MaFenetre fen;
    // The list of agents that propose the service
    private AID[] ServiceAgents;
}// fin de la classe searcher