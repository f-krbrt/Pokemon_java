/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon;
import static java.lang.Integer.parseInt;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
/**
 *
 * @author Flori
 */



public class Combat
{
    Dresseur d1;
    Dresseur d2;
    Ordi o;
    Fenetre_Combat fen;
    boolean over;
    
    
    
    
    public Combat(Dresseur d1,Dresseur d2){
        this.d1=d1;
        this.d2=d2;
        over = false;
        o = new Ordi(fen);
        
    }
    
    
    
    
    
    /**
     * Cette fonction effectue l'action "attaque avec une capacité x" et l'ajoute dans la liste des messages à afficher
     */
    public String addatt_txt(int x, Dresseur ad1, Dresseur ad2){
        ad1.new_action_competence(x,ad2);
        return ad1.lst_pokemon[0].name+" utilise "+ad1.lst_pokemon[0].lst_competence[x].nom;
    }
    
    
    /**
     * Cette fonction ajoute un message relatif à l'efficacité de l'attaque précédente 
     */
    public String add_efficace(int x, Dresseur ad1, Dresseur ad2){
        String s ="";
        if (ad1.lst_pokemon[0].lst_competence[x].t.efficace(ad2.lst_pokemon[0].type1)==2.0){
            s="C'est très efficace !";}
        if (ad1.lst_pokemon[0].lst_competence[x].t.efficace(ad2.lst_pokemon[0].type1)==0.5){
            s="C'est pas très efficace !";}
        if (ad1.lst_pokemon[0].lst_competence[x].t.efficace(ad2.lst_pokemon[0].type1)==1.0){
            s="C'est efficace !";}
        return s;
    }
    
    
    
    /**
     * Cette fonction effectue l'action " changement de pokémon " et l'ajoute dans la liste des messages à afficher
     */
    public String add_switch_txt(Dresseur d, String num_poke){
        
        String s = d.nom+" :\nRevient "+d.lst_pokemon[0].name+" \n A l'attaque "+d.lst_pokemon[parseInt(num_poke)].name;
        fen.Switch(d, num_poke);
        
        
        return s;
    }
    

    /**
     * Cette fonction effectue l'action " utilisation d'objet" et l'ajoute dans la liste des messages à afficher
     */
    public String add_objet_txt(Dresseur d,int l, int c, int num_poke){
        String s =d.nom+" utilise "+d.new_action_objet(d,l,c,num_poke);
        
        return s;
        
    }
    
    
    
    
    
    
    
    
    /**
     * Cette fonction permet d'effectuer les opération d'un round dans un sens précis
     * Sachant que le Dresseur 1 (nous) à choisit d'attaquer
     */
    public void attaque_tableau(int a) {
        ArrayList<String> tab = new ArrayList<String>();
        fen.ShowDownWait();
        String [] coup_ordi = o.Heuristique_aleatoire2(d2, d1);
        
        if (coup_ordi[0]=="attaque") {
        if (d1.lst_pokemon[0].speed >= d2.lst_pokemon[0].speed) { // je suis plus rapide ?
                
                if (d1.lst_pokemon[0].awake()==true){
                    tab.add(addatt_txt(a,d1,d2));
                    tab.add(add_efficace(a,d1,d2));
                }
                
                else {tab.add(d1.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");}
                
                // si le pokemon n'est pas ko alors :
                if (d2.lst_pokemon[0].ko==false){
                    if (d2.lst_pokemon[0].awake()==true){
                        tab.add(addatt_txt(parseInt(coup_ordi[1]),d2,d1));
                        tab.add(add_efficace(parseInt(coup_ordi[1]),d2,d1));
                         
                    }
                    else{tab.add(d2.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");};
                }
                // mon pokemon est ko apres son attaque ?
                if (d1.lst_pokemon[0].ko==true){
                    
                    if ( d1.is_over()==true){
                        tab.add("Vous avez perdu le combat");
                        over = true;
                    }
                    else{
                        tab.add("votre pokemon est ko");
                    }}}
        
        else {
            if (d2.lst_pokemon[0].awake()==true){
                tab.add(addatt_txt(parseInt(coup_ordi[1]),d2,d1));
                tab.add(add_efficace(parseInt(coup_ordi[1]),d2,d1));
            }
            
            else {tab.add(d2.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");};

            // si le pokemon attaqué n'est pas ko
            if (d1.lst_pokemon[0].ko==false){
                if (d1.lst_pokemon[0].awake()==true){
                    tab.add(addatt_txt(a,d1,d2));
                    tab.add(add_efficace(a,d1,d2));
                }
                else {tab.add(d1.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");};
            }
            
            // si le pokemon attaqué est ko alors : 
            else {
                if (d1.is_over()==true) {
                    tab.add("Vous avez perdu le combat");
                    over = true;
                }
                else {
                tab.add("votre pokemon est ko");
                }
            }}}
        
        else if (coup_ordi[0]=="switch"){
            tab.add(add_switch_txt(d2,coup_ordi[1]));
            if (d1.lst_pokemon[0].awake()){
                tab.add(addatt_txt(a,d1,d2));
                tab.add(add_efficace(a,d1,d2));}
            else {tab.add(d1.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");}
            
        }
        
        else if (coup_ordi[0]=="objet"){
            tab.add(add_objet_txt(d2,parseInt(coup_ordi[1]),parseInt(coup_ordi[2]),parseInt(coup_ordi[3])));
            if (d1.lst_pokemon[0].awake()){
                tab.add(addatt_txt(a,d1,d2));
                tab.add(add_efficace(a,d1,d2));
            }
            else {tab.add(d1.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");}
        }
        
        
        
        // Tic du poison ?
        if (d1.lst_pokemon[0].poisoned==true){
            double value_poison = round((d1.lst_pokemon[0].hpmax-d1.lst_pokemon[0].hp)*0.15);
            tab.add(d1.lst_pokemon[0].name+" est empoisoné");
            tab.add(d1.lst_pokemon[0].name+" perd "+value_poison+"hp du au poison");
            if (d1.lst_pokemon[0].hp-value_poison<=0){
                d1.lst_pokemon[0].hp=0;
                d1.lst_pokemon[0].ko=true;
            }
            else{d1.lst_pokemon[0].hp-=value_poison;}
        }
        
        if (d2.lst_pokemon[0].poisoned==true){
            double value_poison = round((d2.lst_pokemon[0].hpmax-d2.lst_pokemon[0].hp)*0.15);
            tab.add(d2.lst_pokemon[0].name+" est empoisoné");
            tab.add(d2.lst_pokemon[0].name+" perd " +value_poison+ "hp du au poison");
            if (d2.lst_pokemon[0].hp-value_poison<=0){
                d2.lst_pokemon[0].hp=0;
                d2.lst_pokemon[0].ko=true;
            }
            else{d2.lst_pokemon[0].hp-=value_poison;}
        }

        //tic de la brulure ?
        if (d1.lst_pokemon[0].burned==true){
            tab.add(d1.lst_pokemon[0].name+" est brulé");
            tab.add(d1.lst_pokemon[0].name+" perd 20hp du à la brulure");
            if (d1.lst_pokemon[0].hp-20<=0){
                d1.lst_pokemon[0].hp=0;
                d1.lst_pokemon[0].ko=true;}
            else{d1.lst_pokemon[0].hp-=20;}
        }
        
        if (d2.lst_pokemon[0].burned==true){
            tab.add(d2.lst_pokemon[0].name+" est brulé");
            tab.add(d2.lst_pokemon[0].name+" perd 20hp du à la brulure");
            if (d2.lst_pokemon[0].hp-20<=0){
                d2.lst_pokemon[0].hp=0;
                d2.lst_pokemon[0].ko=true;}
            else{d2.lst_pokemon[0].hp-=20;}
        } 
        
        
        
        // KO ? 
        if (d2.lst_pokemon[0].ko==true){
            if (d2.is_over()==true){
                tab.add("Vous avez gagné le combat");
            }
            else {
                tab.add(d2.lst_pokemon[0].name+" est ko.");
                tab.add(d1.lst_pokemon[0].name+" gagne 40xp");
                if(d1.lst_pokemon[0].gain_xp_and_lvl_up()==true) {tab.add(d1.lst_pokemon[0].name+" a gagné un niveau ! "
                                                                    + " Il est Niveau "+d1.lst_pokemon[0].level);};
                    
            }
        }
        if (d1.lst_pokemon[0].ko==true){
            
            if (d1.is_over()==true){
                tab.add("Vous avez perdu le combat");
            }
            else { 
                tab.add("votre pokemon est ko");
            }
        }
        if (d1.lst_pokemon[0].ko==false && d2.lst_pokemon[0].ko==false){
            tab.add("continu");
        }
        fen.Showdefile(tab, 0,o);
        fen.ShowAllTop();
        

        }
    
    
    
    
    
    /**
     * Cette fonction permet d'effectuer les opération d'un round dans un sens précis
     * Sachant que le Dresseur 1 (nous) à choisit d'utiliser un objet
     */
    
    public void objet_tableau(int l, int c, int num_poke){
        
        fen.ShowDownWait();
        ArrayList<String> tab = new ArrayList<String>();
        //String [] coup_ordi = {"attaque","0","1","0"};
        String [] coup_ordi = o.Heuristique_aleatoire2(d2, d1);

        
        
        tab.add(add_objet_txt(d1,l,c,num_poke));
        
        
        if (coup_ordi[0]=="attaque") {
            if (d2.lst_pokemon[0].awake()==true){
                tab.add(addatt_txt(parseInt(coup_ordi[1]),d2,d1));
                tab.add(add_efficace(parseInt(coup_ordi[1]),d2,d1));
                if (d1.lst_pokemon[0].ko==true){
                    tab.add(d1.lst_pokemon[0].name+" est ko, Veuillez en choisir un autre");
                    fen.ShowPokemonList_by_ko();
                }
            }
            else {tab.add(d2.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");}
        }
        
        else if (coup_ordi[0]=="switch"){
            tab.add(add_switch_txt(d2,coup_ordi[1]));
            
        }
        
        else if (coup_ordi[0]=="objet"){
            tab.add(add_objet_txt(d2,parseInt(coup_ordi[1]),parseInt(coup_ordi[2]),parseInt(coup_ordi[3])));
        }
        
        
        // Tic du poison ?
        if (d1.lst_pokemon[0].poisoned==true){
            double value_poison = round((d1.lst_pokemon[0].hpmax-d1.lst_pokemon[0].hp)*0.15);
            tab.add(d1.lst_pokemon[0].name+" est empoisoné");
            tab.add(d1.lst_pokemon[0].name+" perd "+value_poison+"hp du au poison");
            if (d1.lst_pokemon[0].hp-value_poison<=0){
                d1.lst_pokemon[0].hp=0;
                d1.lst_pokemon[0].ko=true;
            }
            else{d1.lst_pokemon[0].hp-=value_poison;}
        }
        
        if (d2.lst_pokemon[0].poisoned==true){
            double value_poison = round((d2.lst_pokemon[0].hpmax-d2.lst_pokemon[0].hp)*0.15);
            tab.add(d2.lst_pokemon[0].name+" est empoisoné");
            tab.add(d2.lst_pokemon[0].name+" perd " +value_poison+ "hp du au poison");
            if (d2.lst_pokemon[0].hp-value_poison<=0){
                d2.lst_pokemon[0].hp=0;
                d2.lst_pokemon[0].ko=true;
            }
            else{d2.lst_pokemon[0].hp-=value_poison;}
        }

        //tic de la brulure ?
        if (d1.lst_pokemon[0].burned==true){
            tab.add(d1.lst_pokemon[0].name+" est brulé");
            tab.add(d1.lst_pokemon[0].name+" perd 20hp du à la brulure");
            if (d1.lst_pokemon[0].hp-20<=0){
                d1.lst_pokemon[0].hp=0;
                d1.lst_pokemon[0].ko=true;}
            else{d1.lst_pokemon[0].hp-=20;}
        }
        
        if (d2.lst_pokemon[0].burned==true){
            tab.add(d2.lst_pokemon[0].name+" est brulé");
            tab.add(d2.lst_pokemon[0].name+" perd 20hp du à la brulure");
            if (d2.lst_pokemon[0].hp-20<=0){
                d2.lst_pokemon[0].hp=0;
                d2.lst_pokemon[0].ko=true;}
            else{d2.lst_pokemon[0].hp-=20;}
        }      
        
        // KO ? 
        if (d2.lst_pokemon[0].ko==true){
            if (d2.is_over()==true){
                tab.add("Vous avez gagné le combat");}
            else {
                tab.add("Le poke adverse est ko");
            }
        }
        
        if (d1.lst_pokemon[0].ko==true){
            
            if (d2.is_over()==true){
                tab.add("Vous avez perdu le combat");
            }
            else { 
                tab.add("votre pokemon est ko");
            }
        }
        
        if (d1.lst_pokemon[0].ko==false && d2.lst_pokemon[0].ko==false){
            tab.add("continu");
        }
        
        
        
        fen.Showdefile(tab, 0,o);
        fen.ShowAllTop();
        
        
        
        
    }
    
    
    
    /**
     * Cette fonction permet d'effectuer les opération d'un round dans un sens précis
     * Sachant que le Dresseur 1 (nous) à choisit de changer de pokémon
     */
    
    public void switch_tableau(String num_poke){
        
        d1.lst_pokemon[5].poisoned=true;
        d1.lst_pokemon[5].burned=true;
        
        fen.ShowDownWait();
        ArrayList<String> tab = new ArrayList<String>();
        String [] coup_ordi1 = {"attaque","0","1","0"};
        
        String [] coup_ordi = o.Heuristique_aleatoire2(d2, d1);
        
        tab.add(add_switch_txt(d1,num_poke));
        
        
        if (coup_ordi[0]=="attaque") {
            if (d2.lst_pokemon[0].awake()==true){
                tab.add(addatt_txt(parseInt(coup_ordi[1]),d2,d1));
                tab.add(add_efficace(parseInt(coup_ordi[1]),d2,d1));
                if (d1.lst_pokemon[0].ko==true){
                    tab.add(d1.lst_pokemon[0].name+" est ko, Veuillez en choisir un autre");
                    fen.ShowPokemonList_by_ko();
                }
            }
            else {tab.add(d2.lst_pokemon[0].name+" dort profodément, il ne peut pas attaquer");}
        }
        
        else if (coup_ordi[0]=="switch"){
            tab.add(add_switch_txt(d2,coup_ordi[1]));
            
        }
        
        else if (coup_ordi[0]=="objet"){
            tab.add(add_objet_txt(d2,parseInt(coup_ordi[1]),parseInt(coup_ordi[2]),parseInt(coup_ordi[3])));
        }
        
        
        // Tic du poison ?
        if (d1.lst_pokemon[0].poisoned==true){
            double value_poison = round((d1.lst_pokemon[0].hpmax-d1.lst_pokemon[0].hp)*0.15);
            tab.add(d1.lst_pokemon[0].name+" est empoisoné");
            tab.add(d1.lst_pokemon[0].name+" perd "+value_poison+"hp du au poison");
            if (d1.lst_pokemon[0].hp-value_poison<=0){
                d1.lst_pokemon[0].hp=0;
                d1.lst_pokemon[0].ko=true;
            }
            else{d1.lst_pokemon[0].hp-=value_poison;}
        }
        
        if (d2.lst_pokemon[0].poisoned==true){
            double value_poison = round((d2.lst_pokemon[0].hpmax-d2.lst_pokemon[0].hp)*0.15);
            tab.add(d2.lst_pokemon[0].name+" est empoisoné");
            tab.add(d2.lst_pokemon[0].name+" perd " +value_poison+ "hp du au poison");
            if (d2.lst_pokemon[0].hp-value_poison<=0){
                d2.lst_pokemon[0].hp=0;
                d2.lst_pokemon[0].ko=true;
            }
            else{d2.lst_pokemon[0].hp-=value_poison;}
        }

        //tic de la brulure ?
        if (d1.lst_pokemon[0].burned==true){
            tab.add(d1.lst_pokemon[0].name+" est brulé");
            tab.add(d1.lst_pokemon[0].name+" perd 20hp du à la brulure");
            if (d1.lst_pokemon[0].hp-20<=0){
                d1.lst_pokemon[0].hp=0;
                d1.lst_pokemon[0].ko=true;}
            else{d1.lst_pokemon[0].hp-=20;}
        }
        
        if (d2.lst_pokemon[0].burned==true){
            tab.add(d2.lst_pokemon[0].name+" est brulé");
            tab.add(d2.lst_pokemon[0].name+" perd 20hp du à la brulure");
            if (d2.lst_pokemon[0].hp-20<=0){
                d2.lst_pokemon[0].hp=0;
                d2.lst_pokemon[0].ko=true;}
            else{d2.lst_pokemon[0].hp-=20;}
        } 
        
        
        // KO ? 
        if (d2.lst_pokemon[0].ko==true){
            if (d2.is_over()==true){
                tab.add("Vous avez gagné le combat");
            }
            else {
                
                tab.add("Le poke adverse est ko");
            }
        }
        
        if (d1.lst_pokemon[0].ko==true){
            
            if (d2.is_over()==true){
                tab.add("Vous avez perdu le combat");
            }
            else { 
                tab.add("votre pokemon est ko");
            }
        }
        
        if (d1.lst_pokemon[0].ko==false && d2.lst_pokemon[0].ko==false){
            tab.add("continu");
        }
        
        
        
        fen.Showdefile(tab, 0,o);
        fen.ShowAllTop();
        
        
        
        
    }
      
        
        
      
    


}
