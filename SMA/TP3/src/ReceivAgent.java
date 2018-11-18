import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReceivAgent extends Agent {

    public JLabel Etiq, Etiq2, Etiq3;
    Boolean flag = false;

    class MaFenetre extends JFrame implements ActionListener {
        public MaFenetre() {
            setTitle("Agent Bettaj");
            setBounds(500, 300, 500, 300);
            Container contenu = getContentPane();
            contenu.setLayout(new GridLayout(4, 1));

            Etiq = new JLabel("Hallo World! My name is " + getLocalName());
            Etiq2 = new JLabel("j'attends un message de l'agent ALI");
            Etiq3 = new JLabel("");

            contenu.add(Etiq);
            contenu.add(Etiq2);
            contenu.add(Etiq3);
            boutonCalcul = new JButton("Kill");
            boutonCalcul.setSize(50, 50);
            FlowLayout flow = new FlowLayout();
            JPanel a = new JPanel();
            a.add(boutonCalcul);
            add(a, flow);

            contenu.add(a);
            boutonCalcul.addActionListener(this);

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == boutonCalcul) {
                doDelete();
                this.dispose();

            }
        } // fin du if

        private JButton boutonCalcul;

    }

    protected void setup() {
        MaFenetre fen = new MaFenetre();
        fen.setVisible(true);
        doWait(10000);
        addBehaviour(new RecoiMessage());
    }

    public class RecoiMessage extends CyclicBehaviour {

        public void action() {
            ACLMessage msg;
            msg = receive();
            if (msg != null) {
                Etiq.setText("j'ai reçu un message:");
                Etiq2.setText(msg.getContent());
                Etiq3.setText("je vais répondre...");
                flag = true;
                doWait(5000);
                addBehaviour(new RespondMessage());

            } else {
                if (flag == false) {
                    Etiq2.setText("je n'ai pas encore reçu de message");
                    block();
                }
            }

        }


    }

    public class RespondMessage extends Behaviour {
        private boolean finished = false;

        public void action() {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent("Bonjour je suis l'agent: " + getLocalName() + " Votre réponce a bien été reçu");
            AID receiver = new AID("ali", AID.ISLOCALNAME);
            msg.addReceiver(receiver);
            send(msg);
            finished = true;
        }

        public boolean done() {
            if (finished) {
                Etiq.setText("Ca y est, la réponse est envoyée !!");
                Etiq2.setText("");
                Etiq3.setText("");
            }
            return finished;
        }

    }

}