package my_try;

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
import java.util.concurrent.Semaphore;

public class Searcher extends Agent {

    private MaFenetre fen;
    private boolean flag;
    Semaphore semaphore;
    private AID[] serviceAgents;

    private JLabel lblTypeService;
    private JTextField fieldTypeService;

    private JButton btnSearch, btnKill;

    public class MaFenetre extends JFrame implements ActionListener {

        public MaFenetre() {
            lblTypeService = new JLabel("Type de service");
            fieldTypeService = new JTextField(15);
            
            btnSearch = new JButton("Search");
            btnKill = new JButton("Kill");

            btnSearch.addActionListener(this);
            btnKill.addActionListener(this);

            Container contenu = getContentPane();
            contenu.setLayout(new FlowLayout());

            contenu.add(lblTypeService);
            contenu.add(fieldTypeService);
            contenu.add(btnSearch);
            contenu.add(btnKill);

            setTitle("Agent client: " + getLocalName());
            setBounds(300, 100, 250, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnSearch)) {
                try {
                    semaphore.release();
                } catch(Exception ez) {

                }
                //flag = true;
            } else if (e.getSource().equals(btnKill)) {
                doDelete();
            }
        }
    }

    @Override
    protected void setup() {
        semaphore = new Semaphore(1);
        try {
            semaphore.acquire();
        } catch(Exception e) {

        }
        fen = new MaFenetre();
        fen.setVisible(true);
        try {
            semaphore.acquire();
        } catch(Exception e) {

        }
        //while (!flag) {
            // Ajouter  un TickerBehaviour pour chercher  les agents proposant le service demandé toutes les  10 seconds
            addBehaviour(new TickerBehaviour(this, 10000) {
                @Override
                protected void onTick() {
                    // Mise à jour de la liste des agents service
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType(fieldTypeService.getText());
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
        //}
    }

    @Override
    protected void takeDown() {
        // Close the GUI
        fen.dispose();
        // Printout a dismissal message
        System.out.println("Service-agent: " +getAID().getName()+" terminating.");
    }
}
