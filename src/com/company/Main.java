package definition;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class Main extends JFrame implements KeyListener{

    JPanel p=new JPanel();
    JTextArea dialog=new JTextArea(20,50);
    JTextArea input=new JTextArea(1,50);
    JScrollPane scroll=new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    String[][] chatBot={
            //standard greetings
            {"hi","hello","hola","Hey","hey"},
            {"hi","hello","hey"},
            //question greetings
            {"how are you","how r you","how r u","how are u","How are you?","how r u?","how r you?","how are you?"},
            {"good","doing well"},
            //yes
            {"yes"},
            {"no","NO","NO!","No"},
            {"Where to go for fees enquiry?","where to go for fees enquiry?","where to go for fees enquiry","fees enquiry","Fees enquiry","Fees","fees"},
            {"Go to Academics ---> Select your program ---> All details is given"},
            {"Faculties qualification?","faculties qualification?","faculties qualification","Faculties qualification","Faculties","faculties"},
            {"Life@Upes--->Student & faculty achievement--->All details given"},
            {"Campus tour","campus tour"},
            {"Go to Life@Upes---> Virtual Campus tour"},
            {"Where to go to know about placement?","Placement","placement","placements","Placements"},
            {"Go to Placement section--->Placement and recruiters"},
            {"alumni","Alumni"},
            {"Go to Life@Upes---> Student and alumni stories"},
            {"Hostel enquiry","hostel enquiry","Hostels","hostels","Hostel","hostel","Boys hostel","Girls Hostel","boys hostel","girls hostel","Cafeteria and other chill points","Cafeteria","Food court","food court","cafeteria","Library","library"},
            {"Go to Life@Upes---> Campus Facilities"},




            //default
            {"Sorry I am not trained for this,You can contact us at 1800-102-8737","Please try another question or You can contact us at 1800-102-8737","I didn't get that, You can contact us at 1800-102-8737"}
    };

    public static void main(String[] args){
        new Main();
    }

    public Main(){
        super("Runic's ChatBot");
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialog.setEditable(false);
        input.addKeyListener(this);

        p.add(scroll);
        p.add(input);
        p.setBackground(new Color(255,200,0));
        add(p);

        setVisible(true);
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            input.setEditable(false);
            //-----grab quote-----------
            String quote=input.getText();
            input.setText("");
            addText("-->You:\t"+quote);
            quote.trim();
            while(
                    quote.charAt(quote.length()-1)=='!' ||
                            quote.charAt(quote.length()-1)=='.' ||
                            quote.charAt(quote.length()-1)=='?'
            ){
                quote=quote.substring(0,quote.length()-1);
            }
            quote.trim();
            byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
            //-----check for matches----
            int j=0;//which group we're checking
            while(response==0){
                if(inArray(quote.toLowerCase(),chatBot[j*2])){
                    response=2;
                    int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
                    addText("\n-->Runic\t"+chatBot[(j*2)+1][r]);
                }
                j++;
                if(j*2==chatBot.length-1 && response==0){
                    response=1;
                }
            }

            //-----default--------------
            if(response==1){
                int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
                addText("\n-->Runic\t"+chatBot[chatBot.length-1][r]);
            }
            addText("\n");
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            input.setEditable(true);
        }
    }

    public void keyTyped(KeyEvent e){}

    public void addText(String str){
        dialog.setText(dialog.getText()+str);
    }

    public boolean inArray(String in,String[] str){
        boolean match=false;
        for (String s : str) {
            if (s.equals(in)) {
                match = true;
                break;
            }
        }
        return match;
    }
}