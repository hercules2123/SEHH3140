package com.example.sehh3140;

        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.Timer;
        import java.util.TimerTask;
        import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    //frame
    private FrameLayout gameFrame;
    private  int frameHeight, frameWidth, initialFrameWidth;
    private LinearLayout startLayout;

    //Image
    private ImageView box, black, orange,orange1,orange2,orange3, pink;
    private Drawable imageBoxRight, imageBoxLeft;

    //Size
    private  int boxSize;
    //Position

    private  float boxX, boxY;
    private  float blackX,blackY;
    private  float orangeX, orangeY,orangeA, orangeB,orangeC, orangeD,orangeE, orangeF;
    private  float pinkX, pinkY;

    //Score
    private TextView scoreLabel,highScoreLabel;
    private int score, highScore,timeScore;
    public int mark;
    public Button btn1;
    private Boolean stop = false;

    //Class
    private Timer timer;
    private  Handler handler= new Handler();
    //private  SoundPlayer soundPlayer;

    //Status
    private  boolean start_flg=false;
    private  boolean action_flg=false;
    private  boolean pink_flg=false;
    private int timeCount;
    // private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //soundPlayer=new SoundPlayer(this);

        gameFrame=findViewById(R.id.gameFrame);
        startLayout=findViewById(R.id.startLayout);
        box=findViewById(R.id.car);
        black=findViewById(R.id.shit);
        orange=findViewById(R.id.food2);
        orange1=findViewById(R.id.food3);
        orange2=findViewById(R.id.food4);
        orange3=findViewById(R.id.food5);
        pink=findViewById(R.id.food);
        scoreLabel=findViewById(R.id.scoreLevel);
        highScoreLabel=findViewById(R.id.highScoreLabel);
        btn1 = findViewById(R.id.btn1);

        imageBoxLeft=getResources().getDrawable(R.drawable.car);
        imageBoxRight=getResources().getDrawable(R.drawable.car);
        //High Score

    }


    public  void changePos(){
        //Add timerCount
        timeCount +=20;

        //Orange
        orangeY+=11;//12
        float orangeCenterX=orangeX+orange.getWidth()/2;
        float orangeCenterY=orangeY+orange.getWidth()/2;

        if(hitCheck(orangeCenterX,orangeCenterY)){
            orangeY=frameHeight+100;
            score +=10;
            //soundPlayer.playHitOrangeSound();
        }
        if(orangeY>frameHeight){
            orangeY=-100;
            orangeX=(float) Math.floor(Math.random()*(frameWidth-orange.getWidth()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        //Orange1
        orangeA+=10;//12
        float orangeCenter1=orangeB+orange1.getWidth()/2;
        float orangeCenter2=orangeA+orange1.getWidth()/2;

        if(hitCheck(orangeCenter1,orangeCenter2)){
            orangeA=frameHeight+100;
            score +=15;
            //soundPlayer.playHitOrangeSound();
        }
        if(orangeA>frameHeight){
            orangeA=-100;
            orangeB=(float) Math.floor(Math.random()*(frameWidth-orange1.getWidth()));
        }
        orange1.setX(orangeB);
        orange1.setY(orangeA);

        //Orange2
        orangeC+=15;//12
        float orangeCenter3=orangeD+orange2.getWidth()/2;
        float orangeCenter4=orangeC+orange2.getWidth()/2;

        if(hitCheck(orangeCenter3,orangeCenter4)){
            orangeC=frameHeight+100;
            score +=20;
            //soundPlayer.playHitOrangeSound();
        }
        if(orangeC>frameHeight){
            orangeC=-100;
            orangeD=(float) Math.floor(Math.random()*(frameWidth-orange2.getWidth()));
        }
        orange2.setX(orangeD);
        orange2.setY(orangeC);

        //Orange3
        orangeE+=12;//12
        float orangeCenter5=orangeF+orange3.getWidth()/2;
        float orangeCenter6=orangeE+orange3.getWidth()/2;

        if(hitCheck(orangeCenter5,orangeCenter6)){
            orangeE=frameHeight+100;
            score +=15;
            //soundPlayer.playHitOrangeSound();
        }
        if(orangeE>frameHeight){
            orangeE=-100;
            orangeF=(float) Math.floor(Math.random()*(frameWidth-orange3.getWidth()));
        }
        orange3.setX(orangeF);
        orange3.setY(orangeE);

//pink
        if(!pink_flg && timeCount % 10000==0){
            pink_flg=true;
            pinkY=-20;
            pinkX=(float)Math.floor(Math.random()*(frameWidth-pink.getWidth()));
        }
        if(pink_flg){
            pinkY+=20;
            float pinkCenterX= pinkX+pink.getWidth()/2;
            float pinkCenterY=pinkY+pink.getWidth()/2;

            if(hitCheck(pinkCenterX,pinkCenterY)){
                pinkY=frameHeight+30;
                score+=30;

                if(initialFrameWidth> frameWidth*110/100){
                    frameWidth=frameWidth*110/100;
                    changeFrameWidth(frameWidth);
                }
               // soundPlayer.playHitPinkSound();
            }
            if(pinkY>frameHeight)pink_flg=false;
            pink.setX(pinkX);
            pink.setY(pinkY);
        }
//black
        blackY+=12;
        float blackCenterX=blackX+black.getWidth()/2;
        float blackCenterY=blackY+black.getHeight()/2;



        if(hitCheck(blackCenterX,blackCenterY)){
            blackY=frameHeight+100;
            score -=10;
            /** //change Frame
            frameWidth=frameWidth*80/100;
            changeFrameWidth(frameWidth);
            //soundPlayer.playHitBlackSound();
            */
            if(score > 300){ //frameWidth<=boxSize ||

                //game over
                gameOver();

            }
        }
        if(blackY>frameHeight){
            blackY=-100;
            blackX=(float) Math.floor(Math.random()*(frameWidth-black.getWidth()));
        }
        black.setX(blackX);
        black.setY(blackY);



        //Move Box

        if(action_flg){
            boxX+=14;
            box.setImageDrawable(imageBoxRight);
        } else{
            //Releasing
            boxX-=14;
            box.setImageDrawable(imageBoxLeft);
        }

        //Check box position
        if(boxX<0){
            boxX=0;
            box.setImageDrawable(imageBoxRight);
        }
        if(frameWidth-boxSize<boxX){
            boxX=frameWidth-boxSize;
            box.setImageDrawable(imageBoxLeft);
        }
        box.setX(boxX);
        // scoreLabel.setText("Score:"+ score);
        scoreLabel.setText("Score:"+ score);
    }




    public  boolean hitCheck(float x, float y){
        if(boxX<=x && x<=boxX+boxSize&&
                boxY<=y && y<= frameHeight){
            return true;
        }
        return false;
    }
    public  void  changeFrameWidth(int frameWidth){
        ViewGroup.LayoutParams params=gameFrame.getLayoutParams();
        params.width=frameWidth;
        gameFrame.setLayoutParams(params);
    }

    public  void gameOver(){

        //stop timer
        timer.cancel();
        timer=null;
        start_flg=false;
        // before showing 1 seconds
        try {
            TimeUnit.SECONDS.sleep(1);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

        changeFrameWidth(initialFrameWidth);

        startLayout.setVisibility(View.VISIBLE);
        box.setVisibility(View.INVISIBLE);
        black.setVisibility(View.INVISIBLE);
        orange.setVisibility(View.INVISIBLE);
        orange1.setVisibility(View.INVISIBLE);
        orange2.setVisibility(View.INVISIBLE);
        orange3.setVisibility(View.INVISIBLE);
        pink.setVisibility(View.INVISIBLE);
        //Update High score





    }






    @Override
    public  boolean onTouchEvent(MotionEvent event) {
        if (start_flg) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg=true;
            }  else if(event.getAction()==MotionEvent.ACTION_UP){
                action_flg=false;
            }
        }
        return  true;
    }



    public  void startGame(View view){
        start_flg=true;
        startLayout.setVisibility(View.INVISIBLE);
        if(frameHeight==0){
            frameHeight=gameFrame.getHeight();
            frameWidth= gameFrame.getWidth();
            initialFrameWidth=frameWidth;

            boxSize=box.getHeight();
            boxX=box.getX();
            boxY=box.getY();
        }
        frameWidth=initialFrameWidth;

        box.setX(0.0f);
        black.setY(3000.0f);
        orange.setY(3000.0f);
        orange1.setY(3000.0f);
        orange2.setY(3000.0f);
        orange3.setY(3000.0f);
        pink.setY(3000.0f);

        blackY=black.getY();
        orangeX=black.getY();
        orangeB=black.getY();
        orangeD=black.getY();
        orangeF=black.getY();
        pinkY=black.getY();


        box.setVisibility(View.VISIBLE);
        black.setVisibility(View.VISIBLE);
        orange.setVisibility(View.VISIBLE);
        orange1.setVisibility(View.VISIBLE);
        orange2.setVisibility(View.VISIBLE);
        orange3.setVisibility(View.VISIBLE);
        pink.setVisibility(View.VISIBLE);


        timeCount = 0;
        score=0;
        scoreLabel.setText("Score : 0");

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(start_flg){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }
        } ,0,50     );


    }

    public  void quitGame(View view){
        mark = score;
        if(mark>150){
            Toast.makeText(this, "恭喜你獲得了一張優惠券", Toast.LENGTH_LONG).show();
        }else{Toast.makeText(this, "真可惜你的分數不足未能獲得優惠券下次努力吧", Toast.LENGTH_LONG).show();}

    }


    public void pauseGame(View view) {
        if(stop == false){
            stop = true;

        timer.cancel();
        timer = null;

        Drawable stopGame = getResources().getDrawable(R.drawable.ic_stop_btn1);
        btn1.setBackgroundDrawable(stopGame);
        gameFrame.setVisibility(View.VISIBLE);
    }else{
            stop = false;
            Drawable stopGame = getResources().getDrawable(R.drawable.ic_stop_btn2end);
            btn1.setBackgroundDrawable(stopGame);
            gameFrame.setVisibility(View.VISIBLE);

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 50);
            ;

        }
    }

    public void homepage(View view) {
        Drawable stopGame = getResources().getDrawable(R.drawable.ic_stop_btn3home);
        gameOver();

    }


}