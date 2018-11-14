import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ComAgent extends Agent {
	
	public JLabel lblEtiq, lblEtiq2;
	boolean flag = false;
	
	class MaFenetre extends JFrame implements ActionListener {
		private JButton btnCalc;
		
		public MaFenetre() {
			setTitle("Agent Exp�diteur");
			setBounds(400, 270, 250, 200);
			Container container = getContentPane();
			container.setLayout(new FlowLayout());
			
			lblEtiq = new JLabel("Hello World my name is : " + getLocalName());
			btnCalc = new JButton("Kill");
			btnCalc.addActionListener(this);
			lblEtiq2 = new JLabel("Je vais envoyer un message dans 5 s.");
			
			container.add(lblEtiq, btnCalc);
			container.add(lblEtiq2);
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == btnCalc) {
				doDelete();
				this.dispose();
			}
		}
		
	}
	
	@Override
	protected void setup() {
		new MaFenetre().setVisible(true);
		doWait(5000);
		addBehaviour(new EnvoiMessage());
		addBehaviour(new RecoiReponse());
	}
	
	public class EnvoiMessage extends Behaviour {
		private boolean finished = false;

		@Override
		public void action() {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContent("Bonjour je suis l'agent: " + getLocalName());
			AID receiver = new AID("Bettaj", AID.ISLOCALNAME);
			msg.addReceiver(receiver);
			send(msg);
			finished = true;
		}

		@Override
		public boolean done() {
			if(finished) {
				lblEtiq.setText("Ca y est, le message est Envoy� !");
				lblEtiq2.setText("");
			}
			return finished;
		}
		
	}
	
	public class RecoiReponse extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage msg;
			msg = receive();
			if(msg != null) {
				lblEtiq.setText("J'ai re�u un r�ponse:");
				lblEtiq2.setText(msg.getContent());
				flag = true;
			} else {
				if(flag = false) {
					lblEtiq2.setText("j'ai n'ai pas encore re�u de reponse");
					block();
				}
			}
		}
		
	}
}
