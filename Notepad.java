import java.awt.*;

public class Notepad extends Frame  
{
    TextArea textArea;

    public Notepad() 
    {
        MenuBar mb = new MenuBar();
        Menu m1 = new Menu("File");
        Menu m2 = new Menu("Edit");
        Menu m3 = new Menu("Format");
        Menu m4 = new Menu("View");
        Menu m5 = new Menu("Help");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);

        MenuItem mi1 = new MenuItem("New");
        MenuItem mi5 = new MenuItem("Open");
        MenuItem mi2 = new MenuItem("Save");
        MenuItem mi3 = new MenuItem("Save As");
        MenuItem mi4 = new MenuItem("Exit");
        
        m1.add(mi1);
        m1.add(mi5);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);

        MenuItem mi6 = new MenuItem("Undo");
        MenuItem mi7 = new MenuItem("Cut");
        MenuItem mi8 = new MenuItem("Copy");
        MenuItem mi9 = new MenuItem("Paste");
        MenuItem mi10 = new MenuItem("Delete");
        MenuItem mi11 = new MenuItem("Select All");

        m2.add(mi6);
        m2.add(mi7);
        m2.add(mi8);
        m2.add(mi9);
        m2.add(mi10);
        m2.add(mi11);

        MenuItem mi12 = new MenuItem("Word Wrap");
        MenuItem mi13 = new MenuItem("Font");
        m3.add(mi12);
        m3.add(mi13);

        MenuItem mi14 = new MenuItem("Zoom In");
        MenuItem mi15 = new MenuItem("Zoom Out");
        m4.add(mi14);
        m4.add(mi15);

        setMenuBar(mb);

        textArea = new TextArea();
        add(textArea);

        setSize(600, 400);
        setVisible(true);

       
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
