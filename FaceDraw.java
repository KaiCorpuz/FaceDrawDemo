/*Kai Corpuz
    Object Oriented Programming
    Here is my attempt at face draw
    I hope you enjoy
    :)
*/
import javax.swing.*;
import java.awt.*;
import java.util.*;
//IMPORTANT!!!!!!!!    include ShapesLibrary in the Zip file.

class RectDraw extends Rectangle{//I used the ShapesLibrary to have a base for the rectangle.

    public RectDraw(){//default constructor
        super(0,0,0,0);
    }

    public RectDraw(int positionXIn, int positionYIn, int widthIn, int heightIn){//non default constructor
        super(positionXIn, positionYIn, widthIn, heightIn);
    }

    public void paintComponent(Graphics g){//paint components require Graphics g as parameter
        g.drawRect(getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}

class Face extends RectDraw{
    private RectDraw eye;
    private RectDraw eye1;
    private RectDraw stankEye;
    private RectDraw stankEye1;
    boolean smileStatus = true;
    private int genNum = Utils.GetNumberBetweenInclusive(0,10);
    
    public Face(){
        super();
        int positionX = Utils.GetNumberBetweenInclusive(50,650); //generates the random data that the face class can use like x,y coordinates and 
        int positionY = Utils.GetNumberBetweenInclusive(50,650);//height and width
        int width = Utils.GetNumberBetweenInclusive(150,250);
        int height = Utils.GetNumberBetweenInclusive(150,250);

        setPositionX(positionX); //sets the x and y coordinates and 
        setPositionY(positionY); //random height and width
        setWidth(width);
        setHeight(height);

        int eyeHeight = height/5;  //this is the math to calculate the positions of the eyes for each face
        int eyeWidth = eyeHeight;
        int eyePositionX = positionX - (width) / (eyeWidth) + 12;
        int eyePositionY = positionY + (height/3) - (eyeHeight/2);
        
        eye = new RectDraw(eyePositionX, eyePositionY, eyeWidth, eyeHeight);

        int eye1Height = height/5;
        int eye1Width = eyeWidth;
        int eye1PositionX = positionX + (width) - (eye1Width) - 9;
        int eye1PositionY = positionY + (height / 3) - (eye1Height / 2);

        eye1 = new RectDraw(eye1PositionX, eye1PositionY, eye1Width, eye1Height);

        int stankEyeHeight = height/10;
        int stankEyeWidth = eyeWidth;
        int stankEyePositionX = positionX + (width) - (eye1Width) - 9;
        int stankEyePositionY = positionY + (height / 3) - (eye1Height / 2);

        stankEye = new RectDraw(stankEyePositionX, stankEyePositionY, stankEyeWidth, stankEyeHeight);

        int stankEye1Height = height/10;  //this is the math to calculate the positions of the eyes for each face
        int stankEye1Width = eyeHeight;
        int stankEye1PositionX = positionX - (width) / (eyeWidth) + 12;
        int stankEye1PositionY = positionY + (height/3) - (eyeHeight/2);
        
        stankEye1 = new RectDraw(stankEye1PositionX, stankEye1PositionY, stankEye1Width, stankEye1Height);
        
    }
    //only one constructor was needed


    public void paintComponent(Graphics g){//allows the Rectangle to paint itself
        //g.setColor(Color.black);
    
        if(genNum%2 == 0){//sets the smile status of the face

            smileStatus = false;
            g.setColor(Color.red); 
            super.paintComponent(g);
            stankEye1.paintComponent(g);//eyes
            stankEye.paintComponent(g);
            g.drawArc(getPositionX(), getPositionY()+(getHeight()/2), getWidth() - 10, getHeight() - 10 ,45, 90);//frown
            //g.rectFill(getPositionX(), getPositionY(), getWidth(), getHeight());

        }else{
            smileStatus = true;
            g.setColor(Color.blue); 
            super.paintComponent(g);
            eye.paintComponent(g);//eyes
            eye1.paintComponent(g);
            g.drawArc(getPositionX()-(getHeight()/256) +10, getPositionY() + 20, getWidth() - 25 , getHeight()-40,0, -180);//smile
            //g.rectFill(getPositionX(), getPositionY(), getWidth(), getHeight());
        }
        //the numbers at the end indicate the angle the arc will start, and the angle of the arc.
    }

}

class FaceFrame extends JFrame{
    public FaceFrame(){ //constructor for the face frame
        
        JFrame myFrame = new JFrame("FaceDraw");
        myFrame.setBounds(300,0,900,1000); //(x,y,l,w) sets the coordinates and the dimensions of window
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);//makes the window visible

  
        FacePanel myFacePanel = new FacePanel(); //new face panel
        myFrame.add(myFacePanel);
        myFrame.setVisible(true);   
    }
}
class FacePanel extends JPanel{
    private ArrayList<Face> faceList;//I put the Arrayt list in the Panel Function because it is more elegant 
    //rather than having it in the main function.
    public FacePanel(){
        faceList = new ArrayList<Face>();//ArrayList that puts faces into the list.
        int faceGen = Utils.GetNumberBetweenInclusive(3,10);//generates the number of faces that gets created
        for(int i = 0; i < faceGen; i++){
            faceList.add(new Face());
            System.out.println("Face " + i + " status: Active.");
        }
    }

    public void paintComponent(Graphics g){//paints the face.
        super.paintComponent(g);

        for(Face currentFace : faceList){//every face in the list is gets painted.
            currentFace.paintComponent(g);
        }
    }
}

class FaceDraw{
    public static void main(String[] args){
        FaceFrame myFaceFrame = new FaceFrame();//the face frame
    }
}