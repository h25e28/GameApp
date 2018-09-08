package com.example.s183363.kadai_app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s183363.kadai_app.R;
import com.example.s183363.kadai_app.models.Beam;
import com.example.s183363.kadai_app.models.Enemy1;
import com.example.s183363.kadai_app.models.Enemy2;
import com.example.s183363.kadai_app.models.Fire;
import com.example.s183363.kadai_app.models.GameCharacter;
import com.example.s183363.kadai_app.models.Goal;
import com.example.s183363.kadai_app.models.Player;
import com.example.s183363.kadai_app.models.Star;
import com.example.s183363.kadai_app.models.World;

public class StageView extends BaseView {

    // リソース
    ImageFactory imageFactory;

    // ビュー
    ImageViewFactory imageViewFactory;
    Button retryButton;
    Button jumpButton;
    Button beamButton;
    TextView gameClearTextView;

    public StageView(Context context) {
        super(context);
        activity.setContentView(R.layout.activity_main);

        // リソースの取得
        imageFactory = new ImageFactory(context);

        // ビューの取得
        imageViewFactory = new ImageViewFactory(context);
        retryButton = (Button) activity.findViewById(R.id.retryButton);
        jumpButton = (Button) activity.findViewById(R.id.jumpButton);
        beamButton = (Button) activity.findViewById(R.id.beamButton);
        gameClearTextView = (TextView) activity.findViewById(R. id. gameClearTextView);
    }

    // 描画
    public void draw(World world) {

        //横スクロール
        int xMax = world.getPlayer().getxMax();
        if(xMax<150){
            canvasBaseX = 0;
        }else{
            canvasBaseX = xMax - 150;
        }

        // ImageViewの初期化
        imageViewFactory.clear();



        // 描画
        Player player = world.getPlayer();
         int px = player.getX();
         int py = player.getY();
         int pxSize = player.getxSize();
         int pySize = player.getySize();
        Bitmap playerImage = imageFactory.getImage(player);
        ImageView playerImageView = imageViewFactory.getImageView();
        drawImage(px, py, pxSize, pySize, playerImage, playerImageView);
        drawCharacter(player, playerImage, playerImageView);

        Goal goal = world.getGoal();
        Bitmap goalImage = imageFactory.getImage(goal);
        ImageView goalImageView = imageViewFactory.getImageView();
        drawCharacter(goal,goalImage,goalImageView);

        for(Enemy1 enemy1 : world.getEnemy1s()){
            Bitmap enemy1Image = imageFactory.getImage(enemy1);
            ImageView enemy1ImageView = imageViewFactory.getImageView();
            drawCharacter(enemy1,enemy1Image,enemy1ImageView);
        }

        for(Enemy2 enemy2 : world.getEnemy2s()){
            Bitmap enemy2Image = imageFactory.getImage(enemy2);
            ImageView enemy2ImageView = imageViewFactory.getImageView();
            drawCharacter(enemy2,enemy2Image,enemy2ImageView);
        }

        for(Beam beam : world.getBeams()){
            Bitmap beamImage = imageFactory.getImage(beam);
            ImageView beamImageView = imageViewFactory.getImageView();
            drawCharacter(beam,beamImage,beamImageView);
        }

        for(Star star : world.getStars()){
            Bitmap starImage = imageFactory.getImage(star);
            ImageView starImageView = imageViewFactory.getImageView();
            drawCharacter(star,starImage,starImageView);
        }
        for(Fire fire : world.getFires()){
            Bitmap fireImage = imageFactory.getImage(fire);
            ImageView fireImageView = imageViewFactory.getImageView();
            drawCharacter(fire,fireImage,fireImageView);
        }

        if(world.isEnd()){
            retryButton.setVisibility(View.VISIBLE);
        }
        else if(world.isClear()){
            gameClearTextView.setVisibility(View.VISIBLE);
        }

    }

    private void drawCharacter(GameCharacter character, Bitmap image, ImageView imageView) {
        int x = character.getX();
        int y = character.getY();
        int xSize = character.getxSize();
        int ySize = character.getySize();
        if (image != null) {
            imageView.setVisibility(VISIBLE);
            drawImage(x, y, xSize, ySize, image, imageView);
        } else {
            imageView.setVisibility(GONE);
        }
    }

    // アクセッサ

    public Button getRetryButton() {
        return retryButton;
    }

    public Button getJumpButton() {
        return jumpButton;
    }

    public Button getBeamButton(){
        return beamButton;
    }

    public TextView getGameClearTextView(){
        return gameClearTextView;
    }
}
