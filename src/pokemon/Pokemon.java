/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon;

import java.util.Random;
import javafx.scene.image.Image;
import java.net.URL;

/**
 *
 * @author Flori
 */
public class Pokemon
{
    String name;
    int age;
    double taille;
    
    Type type1;
    Type type2;
    
    int level;
    double xp;
    
    double hpmax;
    double hp;
    int speed;
    int attack;
    int defense;
    
    Competence [] lst_competence;
    

    //etat actuel
    boolean ko;
    boolean poisoned;
    boolean sleeping;
    boolean paralysed;
    boolean burned;
    boolean sec; // sec - > Statut en combat
    
    
    
    Image img_pokemon;
    
    
    
    
    
    
    public Pokemon (String n, Type t1, Type t2, int lvl, double xp, double hpmax, int speed, int attack, int defense, 
            Competence a1, Competence a2, Competence a3, Competence a4, String lien_img){
        
        
        name = n;
        type1=t1;
        type2=t2;
        level=lvl;
        this.xp=xp;
        this.hpmax=hpmax;
        hp=hpmax;
        this.speed=speed;
        this.attack=attack;
        this.defense=defense;
        
        Competence [] l = {a1,a2,a3,a4};
        lst_competence = l;
        
        ko=false;
        poisoned = false;
        sleeping = false;
        paralysed = false;
        burned =false;
        sec =false;
        URL url = getClass().getResource(lien_img);
        if (url == null) {
            System.out.println("Image non trouvée : " + lien_img);
        }
        img_pokemon = new Image(url.toExternalForm());
        
        
    }
    
    
    
    
    
    
    /**
    * Cette fonction ajoute de l'experience a un pokemon, une fois qu'elle depasse 100, le pokemon monte d'un niveau, gagne en rapidité,
    * attaque, defense, point de vie maximum, et l'experience est réinitialisé à 0.
    */
    public boolean gain_xp_and_lvl_up(){
        
        xp+=40;
        boolean var = false;
        if (xp>=100){
            xp-=100;
            level +=1;
            attack+=5;
            speed+=5;
            defense+=4;
            hpmax+=30;
            var = true;
        }
        return var;
    }
    
    
    
    
    /** 
         Cette fonction return true si le pokemon n'est pas endormie ( sleeping = false ) et dans le cas contraire, 
         * le pokemon a 1 chance sur 3 de se reveiller et de renvoyer true
         */
        
    public boolean awake(){
        
        if (sleeping ==false){
            return true;
        }
        else { // Le pokemon se réveil avec une probabilité d'une chance sur 3
            Random r = new Random();
            int reveil = r.nextInt(3);
            
            if (reveil == 1){
                sleeping=false;
                return true;
            }
            else return false;
        }
    }
    
    
    
    
    
    
    
}
