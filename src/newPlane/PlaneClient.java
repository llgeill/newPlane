package newPlane;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import newPlane.explore.Explore;
import newPlane.plane.enemy.BossEnemyPlane;
import newPlane.util.CreateAward;
import newPlane.bullet.Bullet;
import newPlane.plane.enemy.EnemyPlane;
import newPlane.plane.myPlane.MyPlane;
import newPlane.util.CreateEnemyPlane;
import newPlane.util.PathUtil;

import java.util.*;

public class PlaneClient extends Application{
    public static final int STAGE_HEIGHT=800;
    public static final int STAGE_WEIGHT=1100;
    public static final int LIFE_HEIGHT=30;
    public static final int LIFE_WEIGHT=300;
    public static final int INIT_ENEMYCOUNT=5;
    public static final int MYPLANE_INIT_X=360;
    public static final int MYPLANE_INIT_Y=700;
    public static final int BOSS_INIT_X=330;
    public static final int BOSS_INIT_Y=-20;
    public int WORLD_HEIGHT=800;
    public int WORLD_WEIGHT=800;
    //关卡难度设置
    public int initGameDifficult=35;
    public int difficultIncrement=5;
    public int maxGameDifficult=20;
    public int gameReFreash=initGameDifficult;
    //初始敌军数量
    public int initMaxEnemyPlaneCount=5;
    public int maxEnemyPlaneCount=initMaxEnemyPlaneCount;
    //背景速度
    public int backgroundSpeed=5;
    //我的飞机属性设置
    public int myPlaneInitSuperBullet=50;
    public int myPlaneLifeLength=100;
    public int myPlaneBulletFire=30;
    public int myPlaneSuperBulletFire=100;
    public int myPlaneSuperBulletWeight=600;
    public int myPlaneSuperBulletHeight=600;
    //飞机之间碰撞伤害设置
    public int collsionForMyPlaneFire=5;
    public int collsionForEnemyPlaneFire=10;
    //击败敌群获取分数
    public long score=0;
    //boss出现时间
    public int initBossTime=40000;
    //其他
    public boolean isPass=false;
    public boolean isAuthoicFire=false;
    public boolean isStart=false;
    //背景
    public Image backgroud=new Image("image/llg.jpg");


    public List<Bullet> bulletList=new ArrayList<>();
    public List<EnemyPlane> enemyPlaneList=new ArrayList<>();
    public List<Explore> exploreList=new ArrayList<>();
    public StackPane warPane;
    public GraphicsContext warGraphics;
    public Canvas warCanvas;
    public StackPane myPlaneLifePane;
    public Canvas myPlaneLifeCanvas;
    public MyPlane myPlane;
    public Timeline timeline=new Timeline();
    public Timeline bossTimeLine=new Timeline();
    public MediaPlayer mediaPlayer;
    public EventHandler startGame;
    public EventHandler bossStartGame;
    public BorderPane borderPane = new BorderPane();//边框布局管理器，构建场景需要布局
    public VBox infoBox = new VBox();//构建垂直布局管理器
    public Label scroeText = new Label();//标签
    public Label myPlaneLife=new Label();
    public Label myPlaneSuperBullet=new Label();
    public Button startButton=new Button("开始");;
    public Button reStartButton=new Button("重新开始");;
    public Button authomaticFireButton= new Button("自动开火");;
    public CreateEnemyPlane createEnemyPlane=new CreateEnemyPlane(this);
    public CreateAward createAward=new CreateAward(this);



    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //布置舞台流程
        drawStage(primaryStage);
        //开始游戏流程
        initGame();
        prePareGame();
        startGame();

    }
    //绘制舞台
    public void drawStage(Stage primaryStage){
        //定义舞台参数
        primaryStage.setTitle("飞机大战");
        primaryStage.setResizable(false);
        primaryStage.setHeight(STAGE_HEIGHT);
        primaryStage.setWidth(STAGE_WEIGHT);

        //定义画板参数
        warPane=new StackPane();
        warPane.setStyle("-fx-background-color: WHITE");
        warPane.setMaxSize(WORLD_WEIGHT,WORLD_HEIGHT);
        //定义画布参数（画布为透明）
        warCanvas = new Canvas();//画布
        warCanvas.setHeight(WORLD_HEIGHT);
        warCanvas.setWidth(WORLD_WEIGHT);
        //从画布上拿取画笔或画刷
        warGraphics=warCanvas.getGraphicsContext2D();
        //为画笔上色（边框颜色）
        warGraphics.setStroke(Color.WHITE);
        //为画刷上色（填充颜色）
        warGraphics.setFill(Color.RED);
        //使用画笔开始画画,初始化游戏
        initGame();
        //画布添加到画板
        warPane.getChildren().add(warCanvas);
        //添加分数
        scroeText.setFont(new Font(18));
        scroeText.setPadding(new Insets(10, 10, 15, 0));
        myPlaneLife.setFont(new Font(18));
        myPlaneLife.setPadding(new Insets(10, 10, 15, 0));
        myPlaneSuperBullet.setFont(new Font(18));
        myPlaneSuperBullet.setPadding(new Insets(10, 10, 15, 0));
        //添加按钮。
        startButton.setMinWidth(120);
        startButton.setMinHeight(40);
        startButton.setOnMouseClicked(event -> {
            if(isStart){
                timeline.pause();
                bossTimeLine.pause();
                mediaPlayer.pause();
                startButton.setText("开始");
                isStart=false;
            }else{
                timeline.play();
                bossTimeLine.play();
                mediaPlayer.play();
                startButton.setText("暂停");
                isStart=true;
            }
        });
        reStartButton.setMinWidth(120);
        reStartButton.setMinHeight(40);
        reStartButton.setOnMouseClicked(event -> {
            initGame();
            reStartGame();
        });
        authomaticFireButton.setMinWidth(120);
        authomaticFireButton.setMinHeight(40);
        authomaticFireButton.setOnMouseClicked(event -> {
            if(!isAuthoicFire){
                isAuthoicFire=true;
                authomaticFireButton.setText("手动开火");
            }else {
                isAuthoicFire=false;
                authomaticFireButton.setText("自动开火");
            }

        });
        //添加垂直布局，并将按钮添加到垂直布局中
        VBox buttonVbox = new VBox();
        buttonVbox.setAlignment(Pos.BOTTOM_CENTER);
        buttonVbox.getChildren().addAll(startButton, reStartButton,authomaticFireButton);
        buttonVbox.setSpacing(30);//设置每个组件的间隔距离
        //添加生命值的显示
        myPlaneLifePane=new StackPane();
        myPlaneLifePane.setMaxSize(LIFE_WEIGHT,LIFE_HEIGHT);
        myPlaneLifeCanvas=new Canvas();
        myPlaneLifePane.setStyle("-fx-background-color: white");
        myPlaneLifeCanvas.setHeight(LIFE_HEIGHT);
        myPlaneLifeCanvas.setWidth(LIFE_WEIGHT);
        myPlaneLifeCanvas.getGraphicsContext2D().setStroke(Color.RED);
        myPlaneLifeCanvas.getGraphicsContext2D().strokeRect(0,0,LIFE_WEIGHT,LIFE_HEIGHT);
        myPlaneLifePane.getChildren().add(myPlaneLifeCanvas);
        //添加到右边的组件
        infoBox.getChildren().addAll(myPlaneLifePane,myPlaneLife,myPlaneSuperBullet,scroeText,buttonVbox);//把所有组件放入布局管理器中
        borderPane.setCenter(infoBox);//把垂直布局管理器放置到边框布局管理器的右边
        borderPane.setLeft(warPane);
        //创建场景
        Scene scene=new Scene(borderPane);
        //监听键盘事件
        scene.setOnKeyReleased(event ->{myPlane.keyRelease(event); });
        scene.setOnKeyPressed(event -> {myPlane.keyPressed(event); });
        //布置场景到舞台上
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //开始启动游戏
    public void startGame(){
        //添加主界面时间轴
        timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        //添加事件
        KeyFrame keyFrame = new KeyFrame(Duration.millis(gameReFreash), startGame);
        timeline.getKeyFrames().add(keyFrame);
        //事件开启
        timeline.play();

        //添加Boss时间轴
        bossTimeLine=new Timeline();
        bossTimeLine.setCycleCount(Timeline.INDEFINITE);
        //添加事件
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(initBossTime), bossStartGame);
        bossTimeLine.getKeyFrames().add(keyFrame1);
        //事件开启
        bossTimeLine.play();

        //添加背景音乐
        mediaPlayer=new MediaPlayer(new Media(PathUtil.getUri("music/game.mp3")));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();

    }
    //重新加载游戏
    public void reStartGame(){
        //作废当前时间轴
        timeline.stop();
        bossTimeLine.pause();
        mediaPlayer.stop();
        startGame();

    }
    // 弹出一个游戏结束对话框
    public void alertDialog(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("游戏结束！");
        alert.setHeaderText("当前得分 ："+scroeText.getText());
        alert.setContentText("游戏已经结束，按确认键或者关闭键将重新开始游戏！");
        alert.setOnHidden(event -> {
            initGame();
            timeline.play();
            bossTimeLine.play();
        });
        alert.show();
    }
    //初始化游戏
    public void initGame(){
        this.bulletList=new ArrayList<>();
        this.enemyPlaneList=new ArrayList<>();
        this.exploreList=new ArrayList<>();
        this.score=0;
        this.isPass=false;
        this.isStart=false;
        this.isAuthoicFire=false;
        this.maxEnemyPlaneCount=this.initMaxEnemyPlaneCount;
        this.gameReFreash=initGameDifficult;
        this.startButton.setText("开始");
        this.authomaticFireButton.setText("自动开火");
        myPlane=new MyPlane(this,MYPLANE_INIT_X,MYPLANE_INIT_Y);
        for(int i=0;i<INIT_ENEMYCOUNT;i++) enemyPlaneList.add(createEnemyPlane.createEnemyPlane());

    }
    //填充界面，准备界面数据
    public void prePareGame(){
        //游戏事件
        startGame = new EventHandler<ActionEvent>() {
            int backgroudY=0;
            public void handle(ActionEvent t) {
                //显示动态的分数、生命、炸弹数量
                myPlane.drawLife();
                scroeText.setText("分数 : "+score);
                myPlaneLife.setText("生命 ："+myPlane.lifeLength+"/"+myPlane.initLifeLength);
                myPlaneSuperBullet.setText("炸弹数量 ："+myPlane.superBullet);
                //画出卷轴式地图
                backgroudY+=backgroundSpeed;
                if(backgroudY>=WORLD_HEIGHT) backgroudY=0;
                warGraphics.drawImage(backgroud,0,-WORLD_HEIGHT+backgroudY,WORLD_WEIGHT,WORLD_HEIGHT);
                warGraphics.drawImage(backgroud,0,backgroudY,WORLD_WEIGHT,WORLD_HEIGHT);
                // 遍历飞机
                myPlane.draw();
                myPlane.checkisLive();
                //自动开火
                if(backgroudY%30==0&&isAuthoicFire) myPlane.fire();
                for(int i=0;i<enemyPlaneList.size();i++){
                    enemyPlaneList.get(i).isCollision();
                    enemyPlaneList.get(i).draw();
                    enemyPlaneList.get(i).checkisLive();
                }
                //遍历子弹
                for(int i=0;i<bulletList.size();i++){
                    bulletList.get(i).draw();
                    //防止数组越界
                    if(i!=bulletList.size())bulletList.get(i).hitPlanes(enemyPlaneList);
                    if(i!=bulletList.size()) bulletList.get(i).hitPlane(myPlane);
                    if(i!=bulletList.size())bulletList.get(i).checkIsLive();
                }
                //遍历爆炸
                for(int i=0;i<exploreList.size();i++){
                    exploreList.get(i).draw();
                }
                //判断是否通关,是的话加大难度
                if(isPass){
                    maxEnemyPlaneCount=initMaxEnemyPlaneCount;
                    if(gameReFreash>maxGameDifficult&&isPass)gameReFreash-=difficultIncrement;
                    isPass=false;
                    reStartGame();
                }
                //是否启动游戏
                if(!isStart) {
                    timeline.pause();
                    bossTimeLine.pause();
                    mediaPlayer.pause();
                }

            }
        };
        //boss事件
        bossStartGame=event -> {
            //出现Boss,敌人数量变为1
            if(enemyPlaneList.size()!=1){
                maxEnemyPlaneCount=1;
                enemyPlaneList.add(new BossEnemyPlane(myPlane.planeClient,BOSS_INIT_X,-BOSS_INIT_Y));
            }
        };
    }

}

