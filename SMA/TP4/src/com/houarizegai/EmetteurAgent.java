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

public class EmetteurAgent extends Agent {

    public JLabel lblEtiq;
    public JTextField fieldMsg;

    class MaFenetre extends JFrame implements ActionListener {

        private JButton btnSend;

        public MaFenetre() {
            lblEtiq = new JLabel("Hello World! My name is " + getLocalName());
            fieldMsg = new JTextField(30);
            btnSend = new JButton("Envoyer");

            btnSend.addActionListener(this);

            Container contenu = getContentPane();
            contenu.setLayout(new FlowLayout());

            contenu.add(lblEtiq);
            contenu.add(fieldMsg);
            contenu.add(btnSend);

            setTitle("Agent Expéditeur (Agent Ali)");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(450, 150);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnSend)) {
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
            msg.setContent(fieldMsg.getText());

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
                        System.out.println("Enregistrement " + nbe + " : " + p);
                        nbe--;
                    } else block();
                }
                doDelete();
            } else block();

        }

    }
}