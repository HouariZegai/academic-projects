package com.houarizegai;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Emetteur extends Agent {

    public JLabel Etiq;
    public JTextField text;

    class MaFenetre extends JFrame implements ActionListener {

        private JButton btnCalc;

        public MaFenetre() {
            setTitle("Agent Expéditeur");
            setSize(450, 150);
            Container contenu = getContentPane();
            contenu.setLayout(new FlowLayout());

            Etiq = new JLabel("Hello World! My name is " + getLocalName());
            contenu.add(Etiq);
            btnCalc = new JButton("Envoyer");
            contenu.add(btnCalc);
            btnCalc.addActionListener(this);
            text = new JTextField(30);
            contenu.add(text);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnCalc)) {
                // Activate Agent Emetteur
                doWake();
                // Activate Agent ReceivAgent
                ContainerController cc = getContainerController();
                AgentController ac;
                try {
                    ac = cc.getAgent("Bettej");
                    ac.activate();
                } catch (ControllerException ce) {
                    ce.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void setup() {
        MaFenetre fen = new MaFenetre();
        fen.setVisible(true);
        addBehaviour(new EnvoiMessage());
    }

    public class EnvoiMessage extends CyclicBehaviour {

        @Override
        public void action() {
            doWait();

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent(text.getText());

            AID receiver = new AID("Bettaj", AID.ISLOCALNAME);//preciser le recepteur
            msg.addReceiver(receiver);
            send(msg);
            System.out.println(" Requête envoyée : " + msg.getContent());

            ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
            msg2 = receive();//recevoir donnees

            if (msg2 != null) {
                int nbe = Integer.parseInt(msg2.getContent());
                System.out.println("nbe recu par Emetteur: " + nbe);
                if (nbe == 0) System.out.println("aucune donnée ne correspond a la requête");
                else
                    System.out.println(" ---------- Enregistrement reçus par l'agent Emetteur -----------");

                Person p = null;
                while (nbe > 0) {
                    msg2 = receive();
                    if (msg2 != null) {
                        try {
                            p = (Person) msg2.getContentObject();
                        } catch (UnreadableException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Enregistrement: " + (nbe) + "/ " + p);
                        nbe--;
                    } else block();
                }
                doDelete();
            } else block();

        }

    }
}