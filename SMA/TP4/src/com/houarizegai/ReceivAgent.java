package com.houarizegai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ReceivAgent extends Agent {

    public JLabel Etiq, Etiq2;

    class MaFenetre extends JFrame implements ActionListener {
        private JButton btnCalc;

        public MaFenetre() {
            setTitle("Agent Bettaj");
            setBounds(100, 100, 450, 200);
            Container contenu = getContentPane();
            contenu.setLayout(new FlowLayout());

            Etiq = new JLabel("Hallo World! My name is : " + getLocalName());
            Etiq2 = new JLabel("j'attends la requête de l'agent ALI");
            contenu.add(Etiq);
            contenu.add(Etiq2);
            btnCalc = new JButton("Kill");
            contenu.add(btnCalc);
            btnCalc.addActionListener(this);

        }

        // ca marche avec linterfce action listner
        //on va tester ka source de levenemment e
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCalc) {
                doDelete();
                this.dispose();
            }
        }
    }

    @Override
    protected void setup() {
        MaFenetre fen = new MaFenetre();
        fen.setVisible(true);
        addBehaviour(new RecoiMessage());
    }

    public class RecoiMessage extends CyclicBehaviour {//done est deaj implementer

        public void action() {
            ACLMessage msg; //pas de performative psk on recoit msg en ne envoit pas
            doWait(5000);
            msg = receive();//consulte la boite au lettre

            if (msg != null) {

                Etiq.setText(msg.getContent());
                System.out.println("requete reçue:" + msg.getContent());
                new Extraction(msg.getContent());
                //envoi du nombre d'enregistrements
                ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                msg2.setContent(String.valueOf(Extraction.data.size()));//valueOf pour le rendre string
                AID receiver = new AID("Ali", AID.ISLOCALNAME);
                msg2.addReceiver(receiver);
                send(msg2);
                for (Person person : Extraction.data) {
                    try {
                        msg2.setContentObject(person);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    receiver = new AID("Ali", AID.ISLOCALNAME);
                    msg2.addReceiver(receiver);
                    send(msg2);
                }
                doDelete();
            } else
                block();
        }

    }

}
