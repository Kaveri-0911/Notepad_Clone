import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends Frame implements ActionListener 
{
    TextArea textArea;
    String clipboard = "";
    int fontSize = 16;
    String selectedFont = "Arial";
    int selectedStyle = Font.PLAIN;
    String path = null;

    Dialog d;
    Label fontLabel, styleLabel, sizeLabel;
    List fontList, styleList, sizeList;
    Button okbtn, cancelbtn;

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

        textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_BOTH);
        setLayout(new BorderLayout());
        textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
        add(textArea, BorderLayout.CENTER);

        setSize(600, 600);
        setTitle("Notepad Clone");
        setVisible(true);

        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                dispose();
            }
        });

        textArea.addKeyListener(new KeyAdapter() 
        {
            
            public void keyPressed(KeyEvent e) 
            {
                int key = e.getKeyCode();
                if (e.isControlDown()) 
                {
                    if (key == KeyEvent.VK_S) 
                    {
                        saveFile(); 
                    } 
                    else if (key == KeyEvent.VK_O) 
                    {
                        openFile(); 
                    } 
                    else if (key == KeyEvent.VK_X) 
                    {
                        cutText(); 
                    } 
                    else if (key == KeyEvent.VK_C)
                     {
                        copyText();
                    } 
                    else if (key == KeyEvent.VK_V) 
                    {
                        pasteText(); 
                    }
                }
            }
        });

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);
        mi8.addActionListener(this);
        mi9.addActionListener(this);
        mi10.addActionListener(this);
        mi11.addActionListener(this);
        mi12.addActionListener(this);
        mi13.addActionListener(this);
        mi14.addActionListener(this);
        mi15.addActionListener(this);

        d = new Dialog(this, "Font", true);
        d.setSize(400, 300);
        d.setLayout(new GridLayout(4, 2, 10, 10));

        fontLabel = new Label("Font:");
        styleLabel = new Label("Font Style:");
        sizeLabel = new Label("Size:");

        fontList = new List();
        fontList.add("Arial");
        fontList.add("Times New Roman");
        fontList.add("Courier New");
        fontList.add("Verdana");
        fontList.add("Tahoma");

        styleList = new List();
        styleList.add("Regular");
        styleList.add("Bold");
        styleList.add("Italic");
        styleList.add("Bold Italic");

        sizeList = new List();
        sizeList.add("10");
        sizeList.add("12");
        sizeList.add("14");
        sizeList.add("16");
        sizeList.add("18");
        sizeList.add("20");
        sizeList.add("22");
        sizeList.add("24");

        okbtn = new Button("OK");
        cancelbtn = new Button("Cancel");

        okbtn.addActionListener(this);
        cancelbtn.addActionListener(this);

        d.add(fontLabel);
        d.add(fontList);
        d.add(styleLabel);
        d.add(styleList);
        d.add(sizeLabel);
        d.add(sizeList);
        d.add(okbtn);
        d.add(cancelbtn);
    }

    public void actionPerformed(ActionEvent ae) 
    {
        String src = ae.getActionCommand();

        if (src.equals("New")) 
        {
            textArea.setText(" ");
        } 
        else if (src.equals("Open")) 
        {
            openFile();
        }
        else if (src.equals("Save")) 
        {
            saveFile();
        } 
        else if (src.equals("Save As")) 
        {
            saveAsFile();
        } 
        else if (src.equals("Exit")) 
        {
            dispose();
        } 
        else if (src.equals("Cut")) 
        {
            cutText();
        } 
        else if (src.equals("Copy")) 
        {
            copyText();
        } 
        else if (src.equals("Paste")) 
        {
            pasteText();
        } 
        else if (src.equals("Delete")) 
        {
            textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
        } 
        else if (src.equals("Select All")) 
        {
            textArea.selectAll();
        } 
        else if (src.equals("Zoom In")) 
        {
            zoomIn();
        } 
        else if (src.equals("Zoom Out")) 
        {
            zoomOut();
        } 
        else if (src.equals("Font")) 
        {
            d.setVisible(true);
        } 
        else if (src.equals("OK")) 
        {
            applyFont();
            d.setVisible(false);
        } 
        else if (src.equals("Cancel")) 
        {
            d.setVisible(false);
        }
    }

    private void applyFont() 
    {
        if (fontList.getSelectedItem() != null) 
        {
            selectedFont = fontList.getSelectedItem();
        }
        if (styleList.getSelectedItem() != null) 
        {
            String style = styleList.getSelectedItem();
            if (style.equals("Regular")) selectedStyle = Font.PLAIN;
            else if (style.equals("Bold")) selectedStyle = Font.BOLD;
            else if (style.equals("Italic")) selectedStyle = Font.ITALIC;
            else if (style.equals("Bold Italic")) selectedStyle = Font.BOLD | Font.ITALIC;
        }
        if (sizeList.getSelectedItem() != null) 
        {
            fontSize = Integer.parseInt(sizeList.getSelectedItem());
        }
        textArea.setFont(new Font(selectedFont, selectedStyle, fontSize));
    }

    private void zoomIn() 
    {
        fontSize += 2;
        textArea.setFont(new Font(selectedFont, selectedStyle, fontSize));
    }

    private void zoomOut() 
    {
        if (fontSize > 10)
        {
            fontSize -= 2;
            textArea.setFont(new Font(selectedFont, selectedStyle, fontSize));
        }
    }

    private void openFile()
    {
        FileDialog fd = new FileDialog(this,"Open File", FileDialog.LOAD);
        fd.setVisible(true);
        String dir = fd.getDirectory();
        String file = fd.getFile();

        if (dir != null && file != null) 
        {
            path = dir + file;
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) 
            {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) 
                {
                    textArea.append(line + "\n");
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() 
    {
        if (path == null) 
        {
            saveAsFile();
        } 
        else 
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) 
            {
                writer.write(textArea.getText());
            } 
            catch (IOException e)
             {
                e.printStackTrace();
            }
        }
    }

    private void saveAsFile() 
    {
        FileDialog fd = new FileDialog(this, "Save File", FileDialog.SAVE);
        fd.setVisible(true);
        
        String dir = fd.getDirectory();
        String file = fd.getFile();
        
        if (dir != null && file != null) 
        {
            path = dir + file;
            saveFile();
        }
    }

    private void cutText() 
    {
        String text = textArea.getSelectedText();
        clipboard = text;
        if (text != null) 
        {
            textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
        }
    }

    private void copyText() 
    {
        String text = textArea.getSelectedText();
        if (text != null) 
        {
            clipboard = text;
        }
    }

    private void pasteText() 
    {
        textArea.insert(clipboard, textArea.getCaretPosition());
    }

    public static void main(String[] args) 
    {
        new Notepad();
    }
}
