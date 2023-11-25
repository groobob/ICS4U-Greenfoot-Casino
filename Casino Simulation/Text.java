import greenfoot.*;
/**
 * Text
 * 
 * @Jimmy Zhu
 * @1124
 */
public class Text extends Actor
{
    private int type,size;
    private String font;
    public Text(int size, String font, String text, int type){
        this.size=size;
        this.font=font;
        changeText(text);
        this.type=type;
        UIManager.attachText(this, type);
    }
    public Text(int size, String font, String text){
        this.size=size;
        this.font=font;
        changeText(text);
        this.type=type;
    }
    public void changeText(String text){
        GreenfootImage gfi = new GreenfootImage(size*6,size*2);
        gfi.setColor(Color.BLACK);
        gfi.setFont(new Font(font, true, false, size)); 
        gfi.drawString(text, size/2, size);
        setImage(gfi);
    }
    public void changeText(String text, Color c){
        GreenfootImage gfi = new GreenfootImage(size*6,size*2);
        gfi.setColor(c);
        gfi.setFont(new Font(font, true, false, size)); 
        gfi.drawString(text, size/2, size);
        setImage(gfi);
    }
}
