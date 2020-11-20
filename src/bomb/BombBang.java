package bomb;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BombBang extends Entities{
    protected int sizeBomBang, timeline;
    private Image img_left, img_right, img_up, img_down;
    public BombBang(){}
    public BombBang(int x, int y, int sizeBomBang, ArrayList<Box> arrBox) {
        this._x = x;
        this._y = y;
        this.sizeBomBang = sizeBomBang;
        this.timeline = 250;
        img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left_1.png")).getImage();
        img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right_1.png")).getImage();
        img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up_1.png")).getImage();
        img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down_1.png")).getImage();
        for(int i=1;i<sizeBomBang;i++){
            int tmp_left=0, tmp_right=0 ,tmp_up=0 ,tmp_dow=0 ;
            for(int j=0;j<arrBox.size();j++){
                if(isImpactBox(x-(i)*45, y, (i+1)*45, 45, arrBox.get(j))){
                    tmp_left=1;
                }
                if(isImpactBox(x, y, (i+1)*45, 45, arrBox.get(j))){
                    tmp_right=1;
                }
                if(isImpactBox(x, y-(i*45), 45, (i+1)*45, arrBox.get(j))){
                    tmp_up=1;
                }
                if(isImpactBox(x, y, 45, (i+1)*45, arrBox.get(j))){
                    tmp_dow=1;
                }
            }
            if(tmp_left==0){
                setImage(1, i+1);
            }
            if(tmp_right==0){
                setImage(2, i+1);
            }
            if(tmp_up==0){
                setImage(3, i+1);
            }
            if(tmp_dow==0){
                setImage(4,  i+1);
            }
        }

    }

    public void deadlineBomb(){
        if(timeline>0){
            timeline--;
        }
    }

    public int getTimeLine() {
        return timeline;
    }

    private boolean isImpactBox(int x, int y, int width, int height, Box box){
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(box.get_x(), box.get_y(), box.getWidth(), box.getHeight());
        return rec1.intersects(rec2);
    }


    @Override
    public void Draw(Graphics2D graphics2D) {
        graphics2D.drawImage(img_left, _x+45-img_left.getWidth(null), _y,null);
        graphics2D.drawImage(img_right, _x, _y,null);
        graphics2D.drawImage(img_up, _x, _y+45-img_up.getHeight(null),null);
        graphics2D.drawImage(img_down, _x, _y,null);
    }

    @Override
    public int impact(Entities e) {
        //Todo: Va chạm với nhân vật
        if(e instanceof Actor){
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null)+5, _y+5, img_left.getWidth(null)-5, img_left.getHeight(null)-10);
            Rectangle rec2 = new Rectangle(_x, _y+5, img_right.getWidth(null)-5, img_right.getHeight(null)-10);
            Rectangle rec3 = new Rectangle(_x+5, _y+45-img_up.getHeight(null)+5, img_up.getWidth(null)-5, img_up.getHeight(null)-10);
            Rectangle rec4 = new Rectangle(_x+5, _y, img_down.getWidth(null)-10, img_down.getHeight(null)-5);
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                return 1;
            }
            return 0;
        }
        //Todo: va chạm với Bomb khác
        if(e instanceof Bomb){
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null), _y, img_left.getWidth(null), img_left.getHeight(null));
            Rectangle rec2 = new Rectangle(_x, _y, img_right.getWidth(null), img_right.getHeight(null));
            Rectangle rec3 = new Rectangle(_x, _y+45-img_up.getHeight(null), img_up.getWidth(null), img_up.getHeight(null));
            Rectangle rec4 = new Rectangle(_x, _y, img_down.getWidth(null), img_down.getHeight(null));
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                return 1;
            }
            return 0;
        }
        //Todo : va chạm với Box
        if(e instanceof Box){
            if(((Box) e).typeBox == true){
                return 0;
            }
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null), _y, img_left.getWidth(null), img_left.getHeight(null));
            Rectangle rec2 = new Rectangle(_x, _y, img_right.getWidth(null), img_right.getHeight(null));
            Rectangle rec3 = new Rectangle(_x, _y+45-img_up.getHeight(null), img_up.getWidth(null), img_up.getHeight(null));
            Rectangle rec4 = new Rectangle(_x, _y, img_down.getWidth(null), img_down.getHeight(null));
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                return 1;
            }
            return 0;
        }
        //Todo : va chạm với Item
        if(e instanceof Item){
            Rectangle rec1 = new Rectangle(_x+45-img_left.getWidth(null), _y, img_left.getWidth(null), img_left.getHeight(null));
            Rectangle rec2 = new Rectangle(_x, _y, img_right.getWidth(null), img_right.getHeight(null));
            Rectangle rec3 = new Rectangle(_x, _y+45-img_up.getHeight(null), img_up.getWidth(null), img_up.getHeight(null));
            Rectangle rec4 = new Rectangle(_x, _y, img_down.getWidth(null), img_down.getHeight(null));
            Rectangle rec5 = new Rectangle(e.get_x(), e.get_y(), e.getWidth(), e.getHeight());
            if(rec1.intersects(rec5) || rec2.intersects(rec5) || rec3.intersects(rec5) || rec4.intersects(rec5)){
                if(((Item) e).getTimeline()>0){
                    ((Item) e).setTimeline(((Item) e).getTimeline()-1);
                    return 0;
                }else{
                    return 1;
                }
            }
            return 0;
        }
        return 0;
    }

    public void setImage(int orient, int size){
        switch (orient) {
            case 1:
                if(size==2){
                    img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left_2.png")).getImage();
                }
                break;
            case 2:
                if(size==2){
                    img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right_2.png")).getImage();
                }
                break;
            case 3:
                if(size==2){
                    img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up_2.png")).getImage();
                }
                break;
            case 4:
                if(size==2){
                    img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down_2.png")).getImage();
                }
                break;

            default:
                break;
        }
    }

    public int getSizeBomBang() {
        return sizeBomBang;
    }

    public void setSizeBomBang(int sizeBomBang) {
        this.sizeBomBang = sizeBomBang;
    }

    public int getTimeline() {
        return timeline;
    }

    public void setTimeline(int timeline) {
        this.timeline = timeline;
    }
}
