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

    public JLabel lblEtiq, lblEtiq2;

    class MaFenetre extends JFrame implements ActionListener {
        private JButton btnKill;

        public MaFenetre() {
            lblEtiq = new JLabel("Hallo World! My name is : " + getLocalName());
            lblEtiq2 = new JLabel("j'attends la requête de l'agent Mohammed");
            btnKill = new JButton("Kill");
            
            btnKill.addActionListener(this);
            
            Container contenu = getContentPane();
            contenu.setLayout(new FlowLayout());
            
            contenu.add(lblEtiq);
            contenu.add(lblEtiq2);
            contenu.add(btnKill);
            
            setTitle("Agent Houari");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 200);

        }

        // ca marche avec linterfce action listner
        //on va tester ka source de levenemment e
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnKill) {
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

                lblEtiq.setText(msg.getContent());
                System.out.println("requete reçue:" + msg.getContent());
                new Extraction(msg.getContent());
                //envoi du nombre d'enregistrements
                ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                msg2.setContent(String.valueOf(Extraction.data.size()));//valueOf pour le rendre string
                AID receiver = new AID("Mohammed", AID.ISLOCALNAME);
                msg2.addReceiver(receiver);
                send(msg2);
                for (Person person : Extraction.data) {
                    try {
                        msg2.setContentObject(person);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    receiver = new AID("Mohammed", AID.ISLOCALNAME);
                    msg2.addReceiver(receiver);
                    send(msg2);
                }
                doDelete();
            } else
                block();
        }

    }

}
