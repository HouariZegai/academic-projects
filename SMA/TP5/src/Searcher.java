import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Searcher extends Agent {

    private MaFenetre fen;
    private boolean flag;
    private AID[] serviceAgents;

    private JLabel lblEtiq;
    private JTextField fieldTs;
    private JButton btnSearch, btnKill;

    public class MaFenetre extends JFrame implements ActionListener {

        public MaFenetre() {
            lblEtiq = new JLabel("Type de service");
            fieldTs = new JTextField(15);
            btnSearch = new JButton("Search");
            btnKill = new JButton("Kill");

            btnSearch.addActionListener(this);
            btnKill.addActionListener(this);

            Container contenu = getContentPane();
            contenu.setLayout(new FlowLayout());

            contenu.add(lblEtiq);
            contenu.add(fieldTs);
            contenu.add(btnSearch);
            contenu.add(btnKill);

            setTitle("Agent client: " + getLocalName());
            setSize(250, 100);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnSearch)) {
                flag = true;
            } else if (e.getSource().equals(btnKill)) {
                doDelete();
            }
        }
    }

    @Override
    protected void setup() {
        fen = new MaFenetre();
        fen.setVisible(true);

        while (!flag) {
            // Ajouter  un TickerBehaviour pour chercher  les agents proposant le service demandé toutes les  10 seconds
            addBehaviour(new TickerBehaviour(this, 10000) {
                @Override
                protected void onTick() {
                    // Mise à jour de la liste des agents service
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType(fieldTs.getText());
                    template.addServices(sd);
                    try {
                        DFAgentDescription[] result = DFService.search(myAgent, template);
                        serviceAgents = new AID[result.length];
                        System.out.println("----------------------- Les agents proposant ce service sont------------------- -------");
                        for (int i = 0; i < result.length; ++i) {
                            serviceAgents[i] = result[i].getName();
                            System.out.println(result[i].getName());
                        }
                        if (result.length == 0)
                            System.out.println("Aucun agent ne propose ce service pour le moment.");

                    } catch (FIPAException fe) {
                        fe.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    protected void takeDown() {
        // Close the GUI
        fen.dispose();
        // Printout a dismissal message
        System.out.println("Service-agent: " +getAID().getName()+" terminating.");
    }
}
