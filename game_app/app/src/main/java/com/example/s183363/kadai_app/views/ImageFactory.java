package com.example.s183363.kadai_app.views;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.s183363.kadai_app.R;
import com.example.s183363.kadai_app.models.Beam;
import com.example.s183363.kadai_app.models.Enemy1;
import com.example.s183363.kadai_app.models.Enemy2;
import com.example.s183363.kadai_app.models.Fire;
import com.example.s183363.kadai_app.models.Goal;
import com.example.s183363.kadai_app.models.Player;
import com.example.s183363.kadai_app.models.Star;

public class ImageFactory extends BaseView {

    // リソース（画像）
     Bitmap playerImage;
     Bitmap enemy1Image;
     Bitmap enemy2Image;
     Bitmap enemy1deadImage;
     Bitmap enemy2deadImage;
     Bitmap beamImage;
     Bitmap goalImage;
     Bitmap starImage;
     Bitmap fireImage;


    public ImageFactory(Context context) {
        super(context);

        // リソースの取得
         playerImage = loadImage(R.drawable.player,32,32);
         enemy1Image = loadImage(R.drawable.enemy1,32,32);
         enemy1deadImage = loadImage(R.drawable.enemy1dead,32,32);
         enemy2Image = loadImage(R.drawable.enemy2,46,46);
         enemy2deadImage = loadImage(R.drawable.enemy2dead,46,46);
         beamImage = loadImage(R.drawable.beam,20,8);
         goalImage = loadImage(R.drawable.goal,40,40);
         starImage = loadImage(R.drawable.star,32,32);
         fireImage = loadImage(R.drawable.fire,24,48);
    }

    // アクセッサ
    public Bitmap getImage(Player player) {
         return playerImage;
     }

    public Bitmap getImage(Enemy1 enemy1) {
        boolean active = enemy1.isActiveFlag();
        if(active == true){
            return enemy1Image;
        }
        else {
            return enemy1deadImage;
        }
    }

    public Bitmap getImage(Enemy2 enemy2){
        boolean active = enemy2.isActiveFlag();
        if(active == true){
            return enemy2Image;
        }
        else {
            return enemy2deadImage;
        }
    }
    public  Bitmap getImage(Beam beam){
        return beamImage;
    }
    public Bitmap getImage(Goal goal){
        return goalImage;
    }
    public Bitmap getImage(Star star){
        return starImage;
    }
    public Bitmap getImage(Fire fire){
        return fireImage;
    }
}
