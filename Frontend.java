import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;


public class Frontend {

    public static void main(String[] args) {
        JFrame demo = new JFrame();
        demo.setSize(400, 300);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Save");
        JLabel label = new JLabel("Save your best word (Separate multiple words with space)");
        JTextArea textarea = new JTextArea("");

        demo.getContentPane().add(BorderLayout.SOUTH, button);
        label.setHorizontalAlignment(JLabel.CENTER);
        demo.getContentPane().add(BorderLayout.NORTH , label);
        demo.getContentPane().add(BorderLayout.CENTER, textarea);

        demo.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textarea.getText();
                if (!text.equals("")){
                    Frame f= new Frame();
                    Dialog d = new Dialog(f , "Great", true);
                    d.setLayout( new FlowLayout() );
                    Button b = new Button ("OK");
                    b.addActionListener ( new ActionListener()
                    {
                        public void actionPerformed( ActionEvent e )
                        {
                            d.setVisible(false);
                        }
                    });
                    String bestWord = " " + text;
                    try {
                    
                        Files.write(Paths.get("localdb.txt"), bestWord.getBytes(), StandardOpenOption.APPEND);
                        d.add( new Label ("Your beautiful word has benn save in our mind successfully :)"));
                    }catch (IOException ex) {
                        d.add( new Label ("Something Wrong"));
                        d.setTitle("Oops");
                    }finally {
                        d.add(b);
                        d.setSize(400,100);
                        d.setVisible(true);
                    }
                }
            }
        });
    }
}
